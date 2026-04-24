package is.hi.cs.lokaverkefni_hbv202_ggs19;

public class Inventory {
    private BalanceSheet balanceSheet;
    private InputMaterial[] inputMaterials;
    private Product[] products;

    public Inventory(Company company) {
        this.balanceSheet = company.getBalanceSheet();
        this.inputMaterials = company.getInputMaterials();
        this.products = company.getProducts();
    }    

    public static void inventoryOrder(String[] order, Inventory inventory) {
        if (order[0].equals("info")) {
            System.out.println("Command list:\\n info : display info of commands\\n print : prints out summary of inventory\\n structure : prints out where in company structure you are(global command)\\n back : back to layer above(global command)\\n exit : exit program(global command)\"");
        }  else if (order[0].equals("print")) {
            System.out.println("Input materials: " + inventory.inputMaterials.length);
            for (int i = 0; i < inventory.inputMaterials.length; i++) {
                if(inventory.inputMaterials[i] != null) {
                    System.out.println(inventory.inputMaterials[i].getName() + ": " + inventory.inputMaterials[i].getAmount());
                }
            }
            System.out.println("Products: " + inventory.products.length);
            for (int i = 0; i < inventory.products.length; i++) {
                if(inventory.products[i] != null) {
                    System.out.println(inventory.products[i].getName() + ": " + inventory.products[i].getAmount());
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

    public static void start(Company company) {
        Inventory inventory = new Inventory(company);
        System.out.println("Welcome to the inventory! \nType info for help.");
        System.out.println("This is the inventory of " + company.getName() + ", to produce products or buy input materials please access products section in the company layer.");
        String[] order = StartMenu.order();
        Inventory.inventoryOrder(order,inventory);
    }
}
