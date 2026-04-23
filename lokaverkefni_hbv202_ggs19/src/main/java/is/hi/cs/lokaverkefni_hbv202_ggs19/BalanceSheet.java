package is.hi.cs.lokaverkefni_hbv202_ggs19;

public class BalanceSheet {
    private Double cash;
    private Loan[] loans;
    private Inventory[] inventory;

    public BalanceSheet(Double cash, Loan[] loans, Inventory[] inventory) {
        this.cash = cash;
        this.loans = loans;
        this.inventory = inventory;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Loan[] getLoans() {
        return loans;
    }

    public void setLoans(Loan[] loans) {
        this.loans = loans;
    }



}
