package is.hi.cs.lokaverkefni_hbv202_ggs19;

/**
 * The Shareholder class represents an owner of shares in a company.
 * It stores information about the shareholder’s name, number of shares,
 * and dividends collected. It also provides a command-line interface
 * for managing shareholders within a company.
 */
public class Shareholder {

    /** Name of the shareholder */
    private String name;

    /** Number of shares owned */
    private int shares;

    /** Total dividends collected by the shareholder */
    private double cashProfitCollected;

    /**
     * Constructs a Shareholder object.
     *
     * @param name name of the shareholder
     * @param shares number of shares owned
     * @param dividendsCollected total dividends collected
     */
    public Shareholder(String name, int shares, double cashProfitCollected) {
        this.name = name;
        this.shares = shares;
        this.cashProfitCollected = cashProfitCollected;
    }

    /**
     * Handles user commands related to shareholders.
     * Supports adding/removing shareholders, modifying shares,
     * printing information, and navigation.
     *
     * @param order user input split into command tokens
     * @param shareholders array of shareholders
     * @param company the company being managed
     */
    public static Boolean shareholderOrder(Company company) {
        String[] order = StartMenu.order();
        if (order[0].equals("info")) {
            System.out.println("Command list:\n add shareholder : add shareholder\n add shares : add shares to shareholder\n remove : remove shareholder\n dividend : pay out dividends\n info : display info of commands\n print : prints out summary of shareholders\n structure : prints out where in company structure you are(global command)\n back : back to layer above(global command)\n exit : exit program(global command)");
        } else if (order[0].equals("add")) {
            if (order[1].equals("shareholder")) {
                addShareholder(company);
            } else if (order[1].equals("shares")) {
                addShares(company);
            } else {
                System.out.println("Invalid command, type info for help.");
            }
        } else if (order[0].equals("remove")) {
            removeShareholder(company);
        } else if (order[0].equals("dividend")) {
            payDividends(company);
        } else if (order[0].equals("buyback")) {
            buyBackShares(company);
        } else if (order[0].equals("print")) {
            printShareholders(company);
        } else if (order[0].equals("structure")) {
            StartMenu.structure("ShareHolders");
        } else if (order[0].equals("back")) {
            System.out.println("Welcome to product! \nType info for help.");
            return false;
        } else if (order[0].equals("exit")) {
            System.exit(0);
        } else {
            System.out.println("Invalid command, type info for help.");
        }
        return true;
    }

    /**
     * Starts the shareholder interface.
     *
     * @param shareholders array of shareholders
     * @param company the company being managed
     */
    public static void shareholderStart(Shareholder[] shareholders, Company company) {
        System.out.println("Welcome to shareholder! \nType info for help.");
        Boolean run = true;
        while (run) {
            run = shareholderOrder(company);
        }
    }

    public static void printShareholders(Company company) {
        Shareholder[] shareholders = company.getShareholders();
            for (int i = 0; i < shareholders.length; i++) {
                if (shareholders[i] != null) {
                    System.out.println(
                        "Name: " + shareholders[i].getName() + 
                        "\\n -Shares: " + shareholders[i].getShares() + " " +
                        "\\n -Profit: " +shareholders[i].getCashProfitCollected() 
                    );
                }
            }
    }

    /**
     * Adds a new shareholder to the company.
     *
     * @param company the company to add the shareholder to
     */
    public static void addShareholder(Company company) {
        System.out.println("Please type shareholder name(string):");
        String[] order1 = StartMenu.order();

        String name = "";
        for (int i = 0; i < order1.length; i++) {
            name += order1[i] + " ";
        }

        System.out.println("Please type shareholder shares(int):");
        try {
            int shares = Integer.parseInt(StartMenu.order()[0]);
            Shareholder shareholder = new Shareholder(name, shares, 0.0);
            company.addShareholder(shareholder);
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    /**
     * Adds shares to an existing shareholder.
     *
     * @param company the company being modified
     */
    public static void addShares(Company company) {
        Shareholder[] shareholders = company.getShareholders();

        System.out.println("Please type shareholder number from this list:");
        for (int i = 0; i < shareholders.length; i++) {
            if (shareholders[i] != null) {
                System.out.println((i + 1) + ". " + shareholders[i].getName());
            }
        }

        try {
            int index = Integer.parseInt(StartMenu.order()[0]) - 1;

            System.out.println("Please type amount of shares to add(int):");
            int shares = Integer.parseInt(StartMenu.order()[0]);

            company.addShares(shareholders[index], shares);
        } catch (Exception e) {
            System.out.println("Invalid input.");}
    }

    /**
     * Removes a shareholder from the company.
     *
     * @param company the company being modified
     */
    public static void removeShareholder(Company company) {
        Shareholder[] shareholders = company.getShareholders();

        System.out.println("Please type shareholder number from this list:");
        for (int i = 0; i < shareholders.length; i++) {
            if (shareholders[i] != null) {
                System.out.println((i + 1) + ". " + shareholders[i].getName());
            }
        }

        String[] order = StartMenu.order();
        try {
            int index = Integer.parseInt(order[0]) - 1;
            company.removeShareholder(shareholders[index]);
        } catch (Exception e) {
            System.out.println("Invalid input.");
            return;
        }
    }

    /**
     * Pays dividends to shareholders.
     *
     * @param company the company being modified
     */
    public static void payDividends(Company company) {
        System.out.println("Please enter dividend amount(double) per share(total cash per share ="+ company.getCash() / company.getTotalShares() +"):");
        String[] order = StartMenu.order();
        try {
            double amount = Double.parseDouble(order[0]);
            if (amount * company.getTotalShares() <= company.getCash()) {
                company.payDividends(amount);    
            } else {
                System.out.println("Not enough cash.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    public static void buyBackShares(Company company) {
        System.out.println("Please enter shareholder number from this list:");
        for (int i = 0; i < company.getShareholders().length; i++) {
            if (company.getShareholders()[i] != null) {
                System.out.println((i + 1) + ". " + company.getShareholders()[i].getName());
            }
        }
        String[] order1 = StartMenu.order();
        try {
            int index = Integer.parseInt(order1[0]) - 1;
            company.getShareholders()[index].getShares();
        } catch (Exception e) {
            System.out.println("Invalid input.");
            return;
        }

        int index = Integer.parseInt(order1[0]) - 1;

        if (company.getShareholders()[index].getShares() == 0) {
            System.out.println("No shares to buy back.");
            return;
        }

        System.out.println("Please enter amount(int) of shares to buy back("+ company.getShareholders()[index].getShares() +" available):");
        String[] order2 = StartMenu.order();
        try {
            int shares = Integer.parseInt(order2[0]);
            if (shares > company.getShareholders()[index].getShares()) {
                System.out.println("Not enough shares.");
                return;
            }     
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
        int shares = Integer.parseInt(order2[0]);
        System.out.println("Please enter amount(double) paid per share:");
        String[] order3 = StartMenu.order();
        try {
            double amount = Double.parseDouble(order3[0]);
            if (amount * shares > company.getCash()) {
                System.out.println("Not enough cash.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
            return;
        }
        double amount = Double.parseDouble(order3[0]);
        company.buyBackShares(company.getShareholders()[index], shares, amount);   
    }

    /** @return shareholder name */
    public String getName() {
        return name;
    }

    /** @return number of shares */
    public int getShares() {
        return shares;
    }

    /** @return total dividends collected */
    public double getCashProfitCollected() {
        return cashProfitCollected;
    }

    /**
     * Calculates dividends per share.
     *
     * @return dividends per share
     */
    public double getDividendsPerShare() {
        return this.cashProfitCollected / this.shares;
    }

    /** @param name new name */
    public void setName(String name) {
        this.name = name;
    }

    /** @param shares new share count */
    public void setShares(int shares) {
        this.shares = shares;
    }

    /** @param dividendsCollected new dividend amount */
    public void setDividendsCollected(double dividendsCollected) {
        this.cashProfitCollected = dividendsCollected;
    }
}