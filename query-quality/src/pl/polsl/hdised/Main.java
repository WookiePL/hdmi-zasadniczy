package pl.polsl.hdised;

import pl.polsl.hdised.queryPlan.Join;
import pl.polsl.hdised.queryPlan.QueryPlan;
import pl.polsl.hdised.sourceIqScores.IqScoreRootVector;
import pl.polsl.hdised.sourceIqScores.IqScoreVector;
import pl.polsl.hdised.sourceIqScores.IqScoresList;

import java.io.IOException;
import java.util.List;

public class Main {
    private static String queryFilePath;
    private static String fileWithIqscoresData;

    public static void main(String[] args) {
        if (System.getProperty("user.name").equals("Wookie")) {
            queryFilePath = "F:\\Projects\\hdmi-zasadniczy\\query-quality\\src\\pl\\polsl\\hdised\\files\\query.sql";
            fileWithIqscoresData = "F:\\Projects\\hdmi-zasadniczy\\query-quality\\src\\pl\\polsl\\hdised\\files\\iqScores.txt";
        } else {
            queryFilePath = "X:\\CO SE STUDIUJE\\sem6\\HDiSED\\hdmi\\hdmi-zasadniczy\\query-quality\\src\\pl\\polsl\\hdised\\files\\query.sql";
            fileWithIqscoresData = "X:\\CO SE STUDIUJE\\sem6\\HDiSED\\hdmi\\hdmi-zasadniczy\\query-quality\\src\\pl\\polsl\\hdised\\files\\\\iqScores.txt";
        }

        try {
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
        }
    }
}
