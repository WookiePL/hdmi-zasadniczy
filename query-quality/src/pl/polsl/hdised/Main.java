package pl.polsl.hdised;

import pl.polsl.hdised.queryPlan.Join;
import pl.polsl.hdised.queryPlan.QueryPlan;
import pl.polsl.hdised.sourceIqScores.IqScoreRootVector;
import pl.polsl.hdised.sourceIqScores.IqScoreVector;
import pl.polsl.hdised.sourceIqScores.IqScoresList;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

public class Main {
    private static String queryFilePath;
    private static String fileWithIqscoresData;

    public static void main(String[] args) {

        try {
            queryFilePath = args[1];
            fileWithIqscoresData = args[0];

            IqScoresList iqScoresList = IqScoresList.getInstance();
            iqScoresList.setFileWithIqScoresData(fileWithIqscoresData);
            iqScoresList.getDataFromFile();
            iqScoresList.printAll();

            QueryPlan queryPlan = new QueryPlan(queryFilePath);
            queryPlan.printInfo();
            List<Join> joins = queryPlan.findJoins();

            IqScoreRootVector iqScoreRootVector = IqScoreRootVector.getInstance();
            IqScoreVector queryPlanIqVector = iqScoreRootVector.calculateRootResultVector(joins);

            System.out.println("QUERY INFORMATION QUALITY VECTOR:\n");
            queryPlanIqVector.nicePrintMe();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (TableNotFoundException e) {
            System.out.println("Wystapil blad");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Podano złą ilość argumentów (" + args.length + " a ma być 2: ścieżka do iqScores (np. iqScores.txt) oraz ścieżka do query (np. query.sql))");
        }
    }
}
