package pl.polsl.hdised.sourceIqScores;

import pl.polsl.hdised.queryPlan.Join;

import java.util.List;

public class IqScoreRootVector {

    private static IqScoreRootVector instance = null;

    private IqScoreRootVector() {}

    public static IqScoreRootVector getInstance() {
        if (instance == null) {
            instance = new IqScoreRootVector();
        }
        return instance;
    }

    public IqScoreVector calculateRootResultVector(List<Join> listOfJoins) {

        Integer believability = 0;
        Integer objectivity = 0;
        Integer reputation = 0;
        Integer verifiability = 0;

        for (Join join : listOfJoins) {
            IqScoreVector iqScoreVector = join.calculateResultVector();
            believability += iqScoreVector.getBelievability();
            objectivity += iqScoreVector.getObjectivity();
            reputation += iqScoreVector.getReputation();
            verifiability += iqScoreVector.getVerifiability();
        }

        believability /= listOfJoins.size();
        objectivity /= listOfJoins.size();
        reputation /= listOfJoins.size();
        verifiability /= listOfJoins.size();

        return new IqScoreVector("root", believability, objectivity, reputation, verifiability);
    }

}
