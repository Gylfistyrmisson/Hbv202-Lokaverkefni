package is.hi.cs.lokaverkefni_hbv202_ggs19;

/**
 * The Inventory class represents the inventory view of a company.
 * It provides access to the company's products and allows users
 * to interact with inventory data through a command-line interface.
 */
public class Inventory {

    /** Array of products representing the inventory */
    private Product[] products;

    /**
     * Constructs an Inventory object for a given company.
     * It links the company's balance sheet and product list.
     *
     * @param company the company whose inventory is being accessed
     */
    public Inventory(Company company) {
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
    public static Boolean inventoryOrder(Inventory inventory) {
        String[] order = StartMenu.order();
        if (order[0].equals("info")) {
            System.out.println("Command list:\n info : display info of commands\n print : prints out summary of inventory\n structure : prints out where in company structure you are(global command)\n back : back to layer above(global command)\n exit : exit program(global command)");
        } else if (order[0].equals("print")) {
            printInventory(inventory);
        } else if (order[0].equals("structure")) {
            StartMenu.structure("inventory");
        } else if (order[0].equals("back")) {
            System.out.println("Welcome to the balance sheet! \nType info for help.");
            return false;
        } else if (order[0].equals("exit")) {
            System.exit(0);
        } else {
            System.out.println("Invalid command, type info for help.");
        }
        return true;
    }

    /**
     * Starts the inventory interface for a given company.
     * Displays a welcome message and processes the first command.
     *
     * @param company the company whose inventory is being accessed
     */
    public static void start(Company company) {
        Inventory inventory = new Inventory(company);
        System.out.println("Welcome to the inventory! Type info for help.");
        Boolean run = true;
        while (run) {
            run = inventoryOrder(inventory);
        }
    }

    /**
     * Prints the inventory contents.
     */
    public static void printInventory(Inventory inventory) {
        System.out.println("Inventory: " + inventory.products.length);
            for (int i = 0; i < inventory.products.length; i++) {
                if (inventory.products[i] != null) {
                    System.out.println(
                        inventory.products[i].getName() +
                        ":\n Quantity: " +
                        inventory.products[i].getQuantity() +
                        "\n Worth: " +
                        (inventory.products[i].getPrice() * inventory.products[i].getQuantity()) + "\n"
                    );
                }
            }
    }
}