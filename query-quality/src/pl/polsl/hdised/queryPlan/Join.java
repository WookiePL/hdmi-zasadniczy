package pl.polsl.hdised.queryPlan;

import pl.polsl.hdised.sourceIqScores.IqScoreVector;

public class Join {

    private IqScoreVector table1;
    private IqScoreVector table2;

    public Join() {}

    public Join(IqScoreVector table1, IqScoreVector table2) {
        this.table1 = table1;
        this.table2 = table2;
    }

    public void print() {
        table1.printMe();
        table2.printMe();
    }

    public IqScoreVector calculateResultVector() {
        Integer believability = (table1.getBelievability() + table2.getBelievability()) / 2;
        Integer objectivity = (table1.getObjectivity() + table2.getObjectivity()) / 2;
        Integer reputation = (table1.getReputation() + table2.getReputation()) / 2;
        Integer verifiability = (table1.getVerifiability() + table2.getVerifiability()) / 2;
        return new IqScoreVector("result", believability, objectivity, reputation, verifiability);
    }
}
