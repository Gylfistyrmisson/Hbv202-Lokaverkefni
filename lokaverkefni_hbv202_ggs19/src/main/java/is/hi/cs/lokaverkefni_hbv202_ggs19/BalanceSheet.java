package is.hi.cs.lokaverkefni_hbv202_ggs19;

public class BalanceSheet {
    private Company company;
    private Double cash;
    private Loan[] loans = new Loan[10];
    private Inventory[] inventory = new Inventory[10];

    public BalanceSheet(Company company, Double cash) {
        this.cash = cash;
        this.company = company;
    }

    public void balancesheetOrder(String[] order) {
        if (order[0].equals("info")) {
            System.out.println("info for balance sheet.");
            String[] nextOrder = StartMenu.order();
            balancesheetOrder(nextOrder);
        } else if(order[0].equals("access") && order.length > 1){
            if (order[1].equals("loans")) {
                Loan.loanStart(loans);
            } else {
                System.out.println("The balance sheet does not contain a " + order[1] + " section to access.");
            }
        } else if (order[0].equals("print")) {
            System.out.println("Cash: " + cash);
            double loanAmount = 0;
            for (int i = 0; i < loans.length; i++) {
                loanAmount += loans[i].getAmount();
            }
            System.out.println("Loans:" + loanAmount);
            double inventoryAmount = 0;
            for (int i = 0; i < inventory.length; i++) {
                inventoryAmount += inventory[i].getAmount();
            }
            System.out.println("Inventory:" + inventoryAmount);

            String[] nextOrder = StartMenu.order();
            balancesheetOrder(nextOrder);
            
        } else if (order[0].equals("structure")) {
            StartMenu.structure("balancesheet");
            String[] nextOrder = StartMenu.order();
            balancesheetOrder(nextOrder);
        } else if (order[0].equals("back")) {
            company.companyStart();
        } else if (order[0].equals("exit")) {
            System.exit(0);
        }
    }

    public void balancesheetStart() {
        System.out.println("Welcome to the balance sheet! \nType info for help.");
        String[] nextOrder = StartMenu.order();
        balancesheetOrder(nextOrder);
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

    public Inventory[] getInventory() {
        return inventory;
    }

    public void setInventory(Inventory[] inventory) {
        this.inventory = inventory;
    }
}
