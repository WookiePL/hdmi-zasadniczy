package pl.polsl.hdised.queryPlan;

public class Join {

    public String table1;
    public String table2;

    public Join() {}

    public Join(String table1, String table2) {
        this.table1 = table1;
        this.table2 = table2;
    }

    public void print() {
        System.out.println(table1 + " - " + table2);
    }
}
