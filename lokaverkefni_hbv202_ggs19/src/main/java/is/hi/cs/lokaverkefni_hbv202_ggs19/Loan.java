package is.hi.cs.lokaverkefni_hbv202_ggs19;

public class Loan {
    private double amount;
    private int months;
    private double rate;

    public Loan(double amount, int months, double rate) {
        this.amount = amount;
        this.months = months;
        this.rate = rate;
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
