package is.hi.cs.lokaverkefni_hbv202_ggs19;

/**
 * The Inventory class represents the inventory view of a company.
 * It provides access to the company's products and allows users
 * to interact with inventory data through a command-line interface.
 */
public class Inventory {

    /** The balance sheet associated with the inventory */
    private BalanceSheet balanceSheet;

    /** Array of products representing the inventory */
    private Product[] products;

    /**
     * Constructs an Inventory object for a given company.
     * It links the company's balance sheet and product list.
     *
     * @param company the company whose inventory is being accessed
     */
    public Inventory(Company company) {
        this.balanceSheet = company.getBalanceSheet();
        this.products = company.getProducts();
    }

    /**
     * Handles user commands within the inventory context.
     * Supports displaying information, printing inventory contents,
     * navigating structure, and exiting.
     *
     * @param order user input split into command tokens
     * @param inventory the inventory instance being operated on
     */
    public static void inventoryOrder(String[] order, Inventory inventory) {

        if (order[0].equals("info")) {
            System.out.println("Command list:\\n info : display info of commands\\n print : prints out summary of inventory\\n structure : prints out where in company structure you are(global command)\\n back : back to layer above(global command)\\n exit : exit program(global command)\"");

        } else if (order[0].equals("print")) {
            System.out.println("Inventory: " + inventory.products.length);

            for (int i = 0; i < inventory.products.length; i++) {
                if (inventory.products[i] != null) {
                    System.out.println(
                        inventory.products[i].getName() +
                        ": " +
                        inventory.products[i].getAmount()
                    );
                }
            }

        } else if (order[0].equals("structure")) {
            StartMenu.structure("inventory");

        } else if (order[0].equals("back")) {
            inventory.balanceSheet.start();

        } else if (order[0].equals("exit")) {
            System.exit(0);

        } else {
            System.out.println("Invalid command, type info for help.");
        }
    }

    /**
     * Starts the inventory interface for a given company.
     * Displays a welcome message and processes the first command.
     *
     * @param company the company whose inventory is being accessed
     */
    public static void start(Company company) {
        Inventory inventory = new Inventory(company);

        System.out.println("Welcome to the inventory! \nType info for help.");
        System.out.println(
            "This is the inventory of " + company.getName() +
            ", to produce products or buy input materials please access products section in the company layer."
        );

        String[] order = StartMenu.order();
        Inventory.inventoryOrder(order, inventory);
    }
}