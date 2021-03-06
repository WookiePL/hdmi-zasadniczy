package pl.polsl.hdised.queryPlan;

import pl.polsl.hdised.TableNotFoundException;
import pl.polsl.hdised.sourceIqScores.IqScoreVector;
import pl.polsl.hdised.sourceIqScores.IqScoresList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryPlan {

    final private static String joinExpressionRegex = "[a-zA-Z1-9_]+\\.[a-zA-Z1-9_]+\\s*=\\s*[a-zA-Z1-9_]+\\.[a-zA-Z1-9_]+";
    final private static String leftJoinExpressionTableNameRegex = "^[a-zA-Z1-9_]+";
    final private static String rightJoinExpressionTableNameRegex = "=\\s*[a-zA-Z1-9_]+";

    private String query;

    public QueryPlan() {}

    public QueryPlan(String queryFilePath) throws IOException {
        readQueryFromFile(queryFilePath);
    }

    private void readQueryFromFile(String queryFilePath) {
        try {
            query = new String(Files.readAllBytes(Paths.get(queryFilePath)));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Join> findJoins() throws TableNotFoundException {
        List<Join> joins = new ArrayList<>();
        IqScoresList iqScoresList = IqScoresList.getInstance();
        for(String joinExpression: findPattern(query, joinExpressionRegex)) {
            IqScoreVector table1 = iqScoresList.getVectorByName(findJoinLeftTableName(joinExpression));
            IqScoreVector table2 = iqScoresList.getVectorByName(findJoinRightTableName(joinExpression));
            if(table1 == null || table2 == null) {
                System.err.println("[ERROR] nie rozpoznano tabeli");
                throw new TableNotFoundException();
            }
            joins.add(new Join(table1, table2));
        }
        return joins;
    }

    private String findJoinLeftTableName(String joinExpression) {
        return findPattern(joinExpression, leftJoinExpressionTableNameRegex).get(0);
    }

    private String findJoinRightTableName(String joinExpression) {
        return findPattern(joinExpression, rightJoinExpressionTableNameRegex).get(0).replaceFirst("=\\s*", "");
    }

    private static List<String> findPattern(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        List<String> results = new ArrayList<>();
        while (matcher.find()) {
            results.add(matcher.group());
        }
        return results;
    }

    public void printInfo() {
        System.out.println("QUERY:\n\n" + query + "\n");
        System.out.println("JOINS:\n");
        findPattern(query, joinExpressionRegex).forEach(joinExpression -> {
            System.out.println("- " + findJoinLeftTableName(joinExpression) +
                " <-> " + findJoinRightTableName(joinExpression));
        });
        System.out.println();
    }
}
