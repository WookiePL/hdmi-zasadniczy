package pl.polsl.hdised;

import pl.polsl.hdised.queryPlan.Join;
import pl.polsl.hdised.queryPlan.QueryPlan;

import java.io.IOException;

public class Main {

    final private static String queryFilePath = "X:\\CO SE STUDIUJE\\sem6\\HDiSED\\hdmi\\hdmi-zasadniczy\\query-quality\\src\\pl\\polsl\\hdised\\files\\query.sql";

    public static void main(String[] args) {

        try {
            QueryPlan queryPlan = new QueryPlan(queryFilePath);
            queryPlan.printInfo();
//            queryPlan.findJoins().forEach(Join::print);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
