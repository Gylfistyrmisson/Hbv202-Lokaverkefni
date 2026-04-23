package is.hi.cs.lokaverkefni_hbv202_ggs19;

public class Shareholder {
    private String name;
    private int shares;
    private double dividendsCollected;

    public Shareholder(String name, int shares, double dividendsCollected) {
        this.name = name;
        this.shares = shares;
        this.dividendsCollected = dividendsCollected;
    }

    public String getName() {
        return name;
    }

    public int getShares() {
        return shares;
    }

    public double getDividendsCollected() {
        return dividendsCollected;
    }

    public double getDividendsPerShare() {
        return this.dividendsCollected / this.shares;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public void setDividendsCollected(double dividendsCollected) {
        this.dividendsCollected = dividendsCollected;
    }

    
}
