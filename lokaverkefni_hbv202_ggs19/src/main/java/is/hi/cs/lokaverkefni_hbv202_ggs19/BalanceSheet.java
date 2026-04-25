package is.hi.cs.lokaverkefni_hbv202_ggs19;

/**
 * The BalanceSheet class represents the financial state of a company.
 * It manages cash, loans, and inventory, and provides a command-line
 * interface for interacting with balance sheet data.
 */
public class BalanceSheet {

    /** The company this balance sheet belongs to */
    private Company company;

    /** The amount of cash the company currently holds */
    private Double cash;

    /** Array storing loans associated with the company */
    private Loan[] loans = new Loan[0];

    /** Array storing inventory items */
    private InventoryInterface[] inventory = new InventoryInterface[10];

    /**
     * Constructs a BalanceSheet for a given company with initial cash.
     *
     * @param company the company this balance sheet belongs to
     * @param cash the initial cash amount
     */
    public BalanceSheet(Company company, Double cash) {
        this.cash = cash;
        this.company = company;
    }

    /**
     * Handles user commands within the balance sheet context.
     * Routes commands to loans, inventory, or displays financial data.
     */
    public boolean balancesheetOrder() {
        String[] order = StartMenu.order();
        if (order[0].equals("info")) {
            System.out.println("Command list:\n info : display info of commands\n access : layer benath, followed by: loans, inventory\n print : prints out summary of balance sheet data\n structure : prints out where in company structure you are(global command)\n back : back to layer above(global command)\n exit : exit program(global command)");
        } else if (order[0].equals("access") && order.length > 1) {
            if (order[1].equals("loans")) {
                Loan.start(this);
            } else if (order[1].equals("inventory")) {
                Inventory.start(company);
            } else {
                System.out.println("Invalid command, type info for help.");
            }
        } else if (order[0].equals("print")) {
            printBalanceSheet();
        } else if (order[0].equals("structure")) {
            StartMenu.structure("balancesheet");

        } else if (order[0].equals("back")) {
            System.out.println("Welcome to " + company.getName() + "'s company! \nType info for help.");
            return false;
        } else if (order[0].equals("exit")) {
            System.exit(0);
        }
        return true;
    }

    /**
     * Starts the balance sheet interface.
     * Displays a welcome message and begins command processing.
     */
    public void start() {
        Boolean run = true;
        System.out.println("Welcome to the balance sheet! \nType info for help.");
        while (run) {
            run = balancesheetOrder();
        }
    }

    /**
     * Prints a summary of the balance sheet data.
     */
    public void printBalanceSheet() {
        System.out.println("Cash: " + cash);
        double loanAmount = 0;
        for (int i = 0; i < loans.length; i++) {
            if (loans[i] != null) {
                loanAmount += loans[i].getAmount();
            }
        }
        System.out.println("Loans:" + loanAmount);
        double inventoryAmount = 0;
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                inventoryAmount += inventory[i].getAmount();
            }
        }
        System.out.println("Inventory:" + inventoryAmount);
    }

    /**
     * Adds a loan to the first available slot in the loans array.
     *
     * @param loan the loan to add
     */
    public void addLoan(Loan loan) {
        Loan[] newLoans = new Loan[loans.length + 1];
        for (int i = 0; i < loans.length; i++) {
            newLoans[i] = loans[i];
        }
        newLoans[loans.length] = loan;
        this.loans = newLoans;
    }

    /**
     * Adds cash to the current balance.
     *
     * @param cash amount to add
     */
    public void addCash(Double cash) {
        this.cash += cash;
    }

    /**
     * Gets the company associated with this balance sheet.
     *
     * @return company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Gets the current cash amount.
     *
     * @return cash value
     */
    public Double getCash() {
        return cash;
    }

    /**
     * Sets the cash amount.
     *
     * @param cash new cash value
     */
    public void setCash(Double cash) {
        this.cash = cash;
    }

    /**
     * Gets the list of loans.
     *
     * @return array of loans
     */
    public Loan[] getLoans() {
        return loans;
    }

    /**
     * Sets the loans array.
     *
     * @param loans new loans array
     */
    public void setLoans(Loan[] loans) {
        this.loans = loans;
    }

    /**
     * Gets the inventory items.
     *
     * @return array of inventory items
     */
    public InventoryInterface[] getInventory() {
        return inventory;
    }

    /**
     * Sets the inventory array.
     *
     * @param inventory new inventory array
     */
    public void setInventory(InventoryInterface[] inventory) {
        this.inventory = inventory;
    }

    /**
     * Removes a loan from the loans array.
     *
     * @param loan loan to remove
     */
    public void removeLoan(Loan loan) {
        for (int i = 0; i < loans.length; i++) {
            if (loans[i] == loan) {
                loans[i] = null;
            }
        }
    }
}