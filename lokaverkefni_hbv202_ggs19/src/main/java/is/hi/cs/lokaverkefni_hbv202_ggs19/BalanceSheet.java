package is.hi.cs.lokaverkefni_hbv202_ggs19;

public class BalanceSheet {
    private Company company;
    private Double cash;
    private Loan[] loans = new Loan[10];
    private InventoryInterface[] inventory = new InventoryInterface[10];

    public BalanceSheet(Company company, Double cash) {
        this.cash = cash;
        this.company = company;
    }

    public void balancesheetOrder(String[] order) {
        if (order[0].equals("info")) {
            System.out.println("Command list:\\n info : display info of commands\\n access : layer benath, followed by: loans, inventory\\n print : prints out summary of balance sheet data\\n structure : prints out where in company structure you are(global command)\\n back : back to layer above(global command)\\n exit : exit program(global command)\"");
            String[] nextOrder = StartMenu.order();
            balancesheetOrder(nextOrder);
        } else if(order[0].equals("access") && order.length > 1){
            if (order[1].equals("loans")) {
                Loan.start(this);
            } else if (order[1].equals("inventory")) {
                // útfæra
                Inventory.start(company);
            } else {
                System.out.println("The balance sheet does not contain a " + order[1] + " section to access.");
            }
        } else if (order[0].equals("print")) {
            System.out.println("Cash: " + cash);
            double loanAmount = 0;
            for (int i = 0; i < loans.length; i++) {
                if(loans[i] != null) {
                    loanAmount += loans[i].getAmount();
                }
            }
            System.out.println("Loans:" + loanAmount);
            double inventoryAmount = 0;
            for (int i = 0; i < inventory.length; i++) {
                if(inventory[i] != null) {
                    inventoryAmount += inventory[i].getAmount();
                }
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

    public void start() {
        System.out.println("Welcome to the balance sheet! \nType info for help.");
        String[] nextOrder = StartMenu.order();
        balancesheetOrder(nextOrder);
    }

    public void addLoan(Loan loan) {
        for (int i = 0; i < loans.length; i++) {
            if (loans[i] == null) {
                loans[i] = loan;
                break;
            }
        }
    }

    public void addCash(Double cash) {
        this.cash += cash;
    }

    public Company getCompany() {
        return company;
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

    public InventoryInterface[] getInventory() {
        return inventory;
    }

    public void setInventory(InventoryInterface[] inventory) {
        this.inventory = inventory;
    }
}
