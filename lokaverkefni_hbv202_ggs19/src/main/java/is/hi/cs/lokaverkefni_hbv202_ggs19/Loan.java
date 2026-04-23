package is.hi.cs.lokaverkefni_hbv202_ggs19;

public class Loan {
    private String name;
    private double amount;
    private int months;
    private double rate;

    public Loan(BalanceSheet balanceSheet, String name, double amount, int months, double rate) {
        this.name = name;
        this.amount = amount;
        this.months = months;
        this.rate = rate;
    }

    public static void loanStart(Loan[] loans) {
        for (int i = 0; i < loans.length; i++) {
            System.out.println((i+1) + ". " + loans[i].getName() + ": " + loans[i].getAmount() + " kr. for " + loans[i].getMonths() + " months at " + loans[i].getRate() * 100 + "%.");
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public int getMonths() {
        return months;
    }

    public double getRate() {
        return rate;
    }

    public double getMonthlyPayment() {
        return (amount * rate) / (1 - Math.pow(1 + rate, -months));
    }

    public double getTotalPayment() {
        return getMonthlyPayment() * months;
    }

    public double getTotalInterest() {
        return getTotalPayment() - amount;
    }

    
}
