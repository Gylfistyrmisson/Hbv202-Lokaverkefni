package is.hi.cs.lokaverkefni_hbv202_ggs19;

public class Loan {
    private String name;
    private double amount;
    private int months;
    private double rate;

    public Loan(String name, double amount, int months, double rate) {
        this.name = name;
        this.amount = amount;
        this.months = months;
        this.rate = rate;
    }

    public static void loanOrder(BalanceSheet balanceSheet, String[] order) {
        if (order[0].equals("info")) {
            System.out.println("Command list:\\ issue: issue a loan\\n payment: make a payment\\n payback: pay back full loan amount\\n info : display info of commands\\n print : prints out summary of loans data\\n structure : prints out where in company structure you are(global command)\\n back : back to layer above(global command)\\n exit : exit program(global command)");
            String[] nextOrder = StartMenu.order();
            loanOrder(balanceSheet,nextOrder);
        } else if (order[0].equals("structure")) {
            StartMenu.structure("loan");
            String[] nextOrder = StartMenu.order();
            loanOrder(balanceSheet, nextOrder);
        } else if (order[0].equals("print")) {
            Loan[] loans = balanceSheet.getLoans();
            for (int i = 0; i < loans.length; i++) {
                if (loans[i] != null) {
                    System.out.println("Name: " + loans[i].getName() + "\nAmount: " + loans[i].getAmount() + "\nMonths: " + loans[i].getMonths() + "\nRate: " + loans[i].getRate() + "\nMonthly payment: " + loans[i].getMonthlyPayment());
                }        
            }
            String[] nextOrder = StartMenu.order();
            loanOrder(balanceSheet, nextOrder);
        } else if(order[0].equals("issue")) {
            issueLoan(balanceSheet);
        } else if(order[0].equals("payback")) {
            Loan[] loans = balanceSheet.getLoans();
            System.out.println("Please type the number of the loan you want to pay back: ");
            for (int i = 0; i < balanceSheet.getLoans().length; i++) {
                if (loans[i] != null) {
                    System.out.println((i+1) + ".\nName: " + loans[i].getName() + "\nAmount: " + loans[i].getAmount() + "\nMonths: " + loans[i].getMonths() + "\nRate: " + loans[i].getRate() + "\nMonthly payment: " + loans[i].getMonthlyPayment());
                }               }
              String[] nextOrder = StartMenu.order();
            try {
                Integer.parseInt(nextOrder[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid command, not a number");
                start(balanceSheet);
            }
            int loanNumber = Integer.parseInt(nextOrder[0]);

            try {
                Loan loan = balanceSheet.getLoans()[loanNumber-1];
                if (loan == null) {
                    System.out.println("Invalid command, no loan found.");
                    start(balanceSheet);
                }
            } catch (Exception e) {
                System.out.println("Invalid command, no loan found.");
                start(balanceSheet);
            }

            Loan loan = balanceSheet.getLoans()[loanNumber-1];
            paybackLoan(balanceSheet, loan);
        } else if(order[0].equals("payment")) {
            Loan[] loans = balanceSheet.getLoans();
            System.out.println("Please type the number of the loan you want to make a payment on: ");
            for (int i = 0; i < balanceSheet.getLoans().length; i++) {
                if (loans[i] != null) {
                    System.out.println((i+1) + ".\nName: " + loans[i].getName() + "\nAmount: " + loans[i].getAmount() + "\nMonths: " + loans[i].getMonths() + "\nRate: " + loans[i].getRate() + "\nMonthly payment: " + loans[i].getMonthlyPayment());
                }  
            }
            String[] nextOrder = StartMenu.order();
            try {
                Integer.parseInt(nextOrder[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid command, not a number.");
                start(balanceSheet);
            }
            int loanNumber = Integer.parseInt(nextOrder[0]);

            try {
                Loan loan = balanceSheet.getLoans()[loanNumber-1];
                if (loan == null) {
                    System.out.println("Invalid command, no loan found.");
                    start(balanceSheet);
                }
            } catch (Exception e) {
                System.out.println("Invalid command, no loan found.");
                start(balanceSheet);
            }

            Loan loan = balanceSheet.getLoans()[loanNumber-1];
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

    public static void start(BalanceSheet balanceSheet) {
        System.out.println("Welcome to the loans! \nType info for help.");
        String[] nextOrder = StartMenu.order();
        loanOrder(balanceSheet,nextOrder);
    }
    
    public static void issueLoan(BalanceSheet balanceSheet) {
        System.out.println("Type your loan name(string)");
        String[] nextOrder1 = StartMenu.order();
        String loanName = nextOrder1[0];
        
        System.out.println("Type your loan amount(double):");
        String[] nextOrder2 = StartMenu.order();
        try {
            Double.parseDouble(nextOrder2[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid loan data");
            Loan.start(balanceSheet);
        }

        System.out.println("Type your loan months(int):");
        String[] nextOrder3 = StartMenu.order();
        try {
            Integer.parseInt(nextOrder3[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid loan data");
            Loan.start(balanceSheet);
        }

        System.out.println("Type your loan rate(double):");
        String[] nextOrder4 = StartMenu.order();
        try {
            Double.parseDouble(nextOrder4[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid loan data");
            Loan.start(balanceSheet);
        }
        
        double loanAmount = Double.parseDouble(nextOrder2[0]);
        int loanMonths = Integer.parseInt(nextOrder3[0]);
        double loanRate = Double.parseDouble(nextOrder4[0]);
        Loan loan = new Loan(loanName, loanAmount, loanMonths, loanRate/100);
        System.out.println("Loan issued: " + loanName + "\nAmount: " + loanAmount + "\nMonths: " + loanMonths + "\nRate: " + loanRate + "%\nMonthly payment: " + loan.getMonthlyPayment() + "\n");
        balanceSheet.addLoan(loan);
        balanceSheet.addCash(loanAmount);
        start(balanceSheet);;
    }

    public static void paybackLoan(BalanceSheet balanceSheet, Loan loan) {
        balanceSheet.setCash(balanceSheet.getCash() - loan.getTotalPayment());
        Loan[] loans = balanceSheet.getLoans();
        for (int i = 0; i < loans.length; i++) {
            if (loan.equals(loans[i])) {
                loans[i] = null;
            }
        }
    }

    public static void paymentLoan(BalanceSheet balanceSheet, Loan loan) {
        balanceSheet.setCash(balanceSheet.getCash() - loan.getMonthlyPayment());
        Loan[] loans = balanceSheet.getLoans();
        for (int i = 0; i < loans.length; i++) {
            if (loan.equals(loans[i])) {
                loans[i].amount -= (loans[i].getAmount() / loans[i].getMonths());
            }
        }
        System.out.println("Payment made on " + loan.getName() + "\nremaining amount: " + loan.getAmount());
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
