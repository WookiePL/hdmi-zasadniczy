package pl.polsl.hdised;

import pl.polsl.hdised.queryPlan.QueryPlan;
import pl.polsl.hdised.sourceIqScores.IqScoresList;

import java.io.IOException;

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
            QueryPlan queryPlan = new QueryPlan(queryFilePath);
            queryPlan.printInfo();
//            queryPlan.findJoins().forEach(Join::print);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        IqScoresList iqScoresList = IqScoresList.getInstance();
        iqScoresList.setFileWithIqScoresData(fileWithIqscoresData);
        iqScoresList.getDataFromFile();
        iqScoresList.printAll();
    }
}
