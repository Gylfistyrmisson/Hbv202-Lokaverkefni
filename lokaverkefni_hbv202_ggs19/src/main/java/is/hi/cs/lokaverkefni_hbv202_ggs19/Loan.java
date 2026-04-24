package is.hi.cs.lokaverkefni_hbv202_ggs19;

/**
 * The Loan class represents a financial loan taken by a company.
 * It stores loan details such as amount, duration, and interest rate,
 * and provides methods for calculating payments and interacting
 * with the loan system through a command-line interface.
 */
public class Loan {

    /** Name/identifier of the loan */
    private String name;

    /** Original loan amount */
    private double amount;

    /** Loan duration in months */
    private int months;

    /** Interest rate (stored as decimal, e.g. 0.05 = 5%) */
    private double rate;

    /**
     * Constructs a Loan object.
     *
     * @param name loan name
     * @param amount loan amount
     * @param months loan duration in months
     * @param rate interest rate (as decimal, not percentage)
     */
    public Loan(String name, double amount, int months, double rate) {
        this.name = name;
        this.amount = amount;
        this.months = months;
        this.rate = rate;
    }

    /**
     * Handles user commands related to loans.
     * Supports issuing loans, payments, payback, printing, and navigation.
     *
     * @param balanceSheet the balance sheet containing loan data
     * @param order user input command array
     */
    public static void loanOrder(BalanceSheet balanceSheet, String[] order) {

        if (order[0].equals("info")) {
            System.out.println("Command list:\\ issue: issue a loan\\n payment: make a payment\\n payback: pay back full loan amount\\n info : display info of commands\\n print : prints out summary of loans data\\n structure : prints out where in company structure you are(global command)\\n back : back to layer above(global command)\\n exit : exit program(global command)");
            String[] nextOrder = StartMenu.order();
            loanOrder(balanceSheet, nextOrder);

        } else if (order[0].equals("structure")) {
            StartMenu.structure("loan");
            String[] nextOrder = StartMenu.order();
            loanOrder(balanceSheet, nextOrder);

        } else if (order[0].equals("print")) {
            Loan[] loans = balanceSheet.getLoans();

            for (int i = 0; i < loans.length; i++) {
                if (loans[i] != null) {
                    System.out.println(
                        "Name: " + loans[i].getName() +
                        "\nAmount: " + loans[i].getAmount() +
                        "\nMonths: " + loans[i].getMonths() +
                        "\nRate: " + loans[i].getRate() +
                        "\nMonthly payment: " + loans[i].getMonthlyPayment()
                    );
                }
            }

            String[] nextOrder = StartMenu.order();
            loanOrder(balanceSheet, nextOrder);

        } else if (order[0].equals("issue")) {
            issueLoan(balanceSheet);

        } else if (order[0].equals("payback")) {

            Loan[] loans = balanceSheet.getLoans();

            System.out.println("Please type the number of the loan you want to pay back: ");

            for (int i = 0; i < loans.length; i++) {
                if (loans[i] != null) {
                    System.out.println((i + 1) + ".\nName: " + loans[i].getName());
                }
            }

            String[] nextOrder = StartMenu.order();

            try {
                Integer.parseInt(nextOrder[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid command, not a number");
                start(balanceSheet);
                return;
            }

            int loanNumber = Integer.parseInt(nextOrder[0]);

            try {
                Loan loan = balanceSheet.getLoans()[loanNumber - 1];
                if (loan == null) {
                    System.out.println("Invalid command, no loan found.");
                    start(balanceSheet);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid command, no loan found.");
                start(balanceSheet);
                return;
            }

            Loan loan = balanceSheet.getLoans()[loanNumber - 1];
            paybackLoan(balanceSheet, loan);

        } else if (order[0].equals("payment")) {

            Loan[] loans = balanceSheet.getLoans();

            System.out.println("Please type the number of the loan you want to make a payment on: ");

            for (int i = 0; i < loans.length; i++) {
                if (loans[i] != null) {
                    System.out.println((i + 1) + ".\nName: " + loans[i].getName());
                }
            }

            String[] nextOrder = StartMenu.order();

            try {
                Integer.parseInt(nextOrder[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid command, not a number.");
                start(balanceSheet);
                return;
            }

            int loanNumber = Integer.parseInt(nextOrder[0]);

            try {
                Loan loan = balanceSheet.getLoans()[loanNumber - 1];
                if (loan == null) {
                    System.out.println("Invalid command, no loan found.");
                    start(balanceSheet);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid command, no loan found.");
                start(balanceSheet);
                return;
            }

            Loan loan = balanceSheet.getLoans()[loanNumber - 1];
            paymentLoan(balanceSheet, loan);

        } else if (order[0].equals("back")) {
            balanceSheet.start();

        } else if (order[0].equals("exit")) {
            System.exit(0);

        } else {
            System.out.println("Invalid command, type info for help.");
            String[] nextOrder = StartMenu.order();
            loanOrder(balanceSheet, nextOrder);
        }
    }

    /**
     * Starts the loan interface.
     *
     * @param balanceSheet the balance sheet to operate on
     */
    public static void start(BalanceSheet balanceSheet) {
        System.out.println("Welcome to the loans! \nType info for help.");
        String[] nextOrder = StartMenu.order();
        loanOrder(balanceSheet, nextOrder);
    }

    /**
     * Creates and issues a new loan based on user input.
     *
     * @param balanceSheet the balance sheet to add the loan to
     */
    public static void issueLoan(BalanceSheet balanceSheet) {

        System.out.println("Type your loan name(string)");
        String loanName = StartMenu.order()[0];

        System.out.println("Type your loan amount(double):");
        double loanAmount = Double.parseDouble(StartMenu.order()[0]);

        System.out.println("Type your loan months(int):");
        int loanMonths = Integer.parseInt(StartMenu.order()[0]);

        System.out.println("Type your loan rate(double):");
        double loanRate = Double.parseDouble(StartMenu.order()[0]);

        Loan loan = new Loan(loanName, loanAmount, loanMonths, loanRate / 100);

        System.out.println(
            "Loan issued: " + loanName +
            "\nAmount: " + loanAmount +
            "\nMonths: " + loanMonths +
            "\nRate: " + loanRate + "%" +
            "\nMonthly payment: " + loan.getMonthlyPayment()
        );

        balanceSheet.addLoan(loan);
        balanceSheet.addCash(loanAmount);

        start(balanceSheet);
    }

    /**
     * Pays back a loan fully and removes it from the balance sheet.
     *
     * @param balanceSheet the balance sheet
     * @param loan the loan to pay back
     */
    public static void paybackLoan(BalanceSheet balanceSheet, Loan loan) {
        balanceSheet.setCash(balanceSheet.getCash() - loan.getTotalPayment());

        Loan[] loans = balanceSheet.getLoans();
        for (int i = 0; i < loans.length; i++) {
            if (loan.equals(loans[i])) {
                loans[i] = null;
            }
        }
    }

    /**
     * Makes a single monthly payment on a loan.
     *
     * @param balanceSheet the balance sheet
     * @param loan the loan to pay
     */
    public static void paymentLoan(BalanceSheet balanceSheet, Loan loan) {
        balanceSheet.setCash(balanceSheet.getCash() - loan.getMonthlyPayment());

        Loan[] loans = balanceSheet.getLoans();
        for (int i = 0; i < loans.length; i++) {
            if (loan.equals(loans[i])) {
                loans[i].amount -= (loans[i].getAmount() / loans[i].getMonths());
            }
        }

        System.out.println(
            "Payment made on " + loan.getName() +
            "\nremaining amount: " + loan.getAmount()
        );
    }

    /** @return loan name */
    public String getName() {
        return name;
    }

    /** @param name new loan name */
    public void setName(String name) {
        this.name = name;
    }

    /** @return loan amount */
    public double getAmount() {
        return amount;
    }

    /** @return loan duration in months */
    public int getMonths() {
        return months;
    }

    /** @return interest rate */
    public double getRate() {
        return rate;
    }

    /**
     * Calculates monthly payment using annuity formula.
     *
     * @return monthly payment
     */
    public double getMonthlyPayment() {
        return (amount * rate) / (1 - Math.pow(1 + rate, -months));
    }

    /**
     * Calculates total repayment over full loan period.
     *
     * @return total payment
     */
    public double getTotalPayment() {
        return getMonthlyPayment() * months;
    }

    /**
     * Calculates total interest paid over the loan.
     *
     * @return total interest
     */
    public double getTotalInterest() {
        return getTotalPayment() - amount;
    }
}