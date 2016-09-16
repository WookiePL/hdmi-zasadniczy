package pl.polsl.hdised.sourceIqScores;

public class IqScoreVector {

    /**
     * nazwa tabeli
     */
    private String tableName;
    /**
     * pewność
     */
    private Integer believability;
    /**
     * celowość
     */
    private Integer objectivity;
    /**
     * reputacja
     */
    private Integer reputation;
    /**
     * weryfikowalność
     */
    private Integer verifiability;

    public IqScoreVector(String tableName, Integer believability, Integer objectivity, Integer reputation, Integer verifiability) {
        this.tableName = tableName;
        this.believability = believability;
        this.objectivity = objectivity;
        this.reputation = reputation;
        this.verifiability = verifiability;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getBelievability() {
        return believability;
    }

    public void setBelievability(Integer believability) {
        this.believability = believability;
    }

    public Integer getObjectivity() {
        return objectivity;
    }

    public void setObjectivity(Integer objectivity) {
        this.objectivity = objectivity;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public Integer getVerifiability() {
        return verifiability;
    }

    public void setVerifiability(Integer verifiability) {
        this.verifiability = verifiability;
    }

    public void printMe() {
        System.out.println(this.tableName + ": " + "p = " + this.believability + ", c = " + this.objectivity + ", r = " + this.reputation + ", w = " + this.verifiability);
    }

    public void nicePrintMe() {
        System.out.println("believability: " + this.believability);
        System.out.println("objectivity: " + this.objectivity);
        System.out.println("reputation: " + this.reputation);
        System.out.println("verifiability: " + this.verifiability);
    }
}
