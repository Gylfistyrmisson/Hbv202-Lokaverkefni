package is.hi.cs.lokaverkefni_hbv202_ggs19;

/**
 * The Product class represents a product developed by a company.
 * It stores information such as name, price, quantity in inventory,
 * and production cost. It also provides a command-line interface
 * for managing products.
 */
public class Product implements InventoryInterface {
    
    /** Name of the product */
    private String name;

    /** Selling price of the product */
    private double price;

    /** Quantity available in inventory */
    private int quantity;

    /** Production cost per unit */
    private double cost;
    
    /**
     * Constructs a Product object.
     *
     * @param name name of the product
     * @param price selling price
     * @param quantity initial quantity in inventory
     * @param cost production cost
     */
    public Product(String name, double price, int quantity, double cost) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.cost = cost;
    }

    /**
     * Handles product-related user commands.
     * Supports adding, removing, producing, printing,
     * and navigating the product system.
     *
     * @param order user input split into tokens
     * @param company the company whose products are managed
     */
    public static Boolean productOrder(Company company) {
        String[] order = StartMenu.order();

        if (order[0].equals("info")) {
            System.out.println("Command list:\n add : develop product\n remove : remove product\n produce : produce product to inventory\n info : display info of commands\n print : prints out summary of product data\n structure : prints out where in company structure you are(global command)\n back : back to layer above(global command)\n exit : exit program(global command)");
        } else if (order[0].equals("print")) {
            printProducts(company);
        } else if (order[0].equals("structure")) {
            StartMenu.structure("products");
        } else if (order[0].equals("add")) {
            addProduct(company);
        } else if (order[0].equals("remove")) {
            removeProduct(company);
        } else if (order[0].equals("produce")) {
            produceProduct(company);
        } else if (order[0].equals("back")) {
            System.out.println("Welcome to " + company.getName() + "'s company! \nType info for help.");
            return false;
        } else if (order[0].equals("exit")) {
            System.exit(0);
        } else {
            System.out.println("Invalid command, type info for help.");
        }
        return true;
    }

    /**
     * Starts the product interface.
     *
     * @param company the company whose products are managed
     */
    public static void productStart(Company company) {
        System.out.println("Welcome to product! \nType info for help.");
        Boolean run = true;
        while (run) {
            run = productOrder(company);
        }
        
    }

    public static void printProducts(Company company) {
        Product[] products = company.getProducts();
        if (products.length > 0) {
            for (int i = 0; i < products.length; i++) {
                if (products[i] != null) {
                    System.out.println(
                        "Name: " + products[i].getName() +
                        "\nPrice: " + products[i].getPrice() +
                        "\nInventory: " + products[i].getQuantity()
                    );
                }
            }
        } else {
            System.out.println("No products developed yet.");
        }
    }
    /**
     * Adds a new product to the company.
     *
     * @param company the company to modify
     */
    public static void addProduct(Company company) {
        System.out.println("Please type product name(string):");
        String name = StartMenu.order()[0];

        System.out.println("Please type product price(double):");
        String order1 = StartMenu.order()[0];
        try {
            double price = Double.parseDouble(order1);
            System.out.println("Please type product cost(double):");
            String order2 = StartMenu.order()[0];
            try {
                double cost = Double.parseDouble(order2);
                Product product = new Product(name, price, 0, cost);
                company.addProduct(product);
                System.out.println("Product added!");
            } catch (Exception e) {
                System.out.println("Invalid input.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    /**
     * Removes a product from the company.
     *
     * @param company the company to modify
     */
    public static void removeProduct(Company company) {
        Product[] products = company.getProducts();

        System.out.println("Select product number:");
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                System.out.println((i+1) + ": " + products[i].getName());
            }
        }

        String order = StartMenu.order()[0];
        try {
            int index = Integer.parseInt(order);
            company.removeProduct(products[index-1]);
            System.out.println("Product removed!");
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    /**
     * Produces (adds quantity to) a product in inventory.
     *
     * @param company the company to modify
     */
    public static void produceProduct(Company company) {
        Product[] products = company.getProducts();

        System.out.println("Select product number:");
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                System.out.println(i + ": " + products[i].getName());
            }
        }

        String order = StartMenu.order()[0];
        try {
            int index = Integer.parseInt(order);

            System.out.println("Enter quantity:");
            int quantity = Integer.parseInt(StartMenu.order()[0]);

            company.produceProduct(products[index], quantity);

            System.out.println("Product produced!");
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    /**
     * Calculates total value of this product in inventory.
     *
     * @return total value (price * quantity)
     */
    public double getAmount() {
        return quantity * price;
    }

    /** @return product name */
    public String getName() {
        return name;
    }

    /** @return product price */
    public double getPrice() {
        return price;
    }

    /** @return quantity in inventory */
    public int getQuantity() {
        return quantity;
    }

    /** @param quantity new quantity */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /** @param price new price */
    public void setPrice(double price) {
        this.price = price;
    }

    /** @param name new name */
    public void setName(String name) {
        this.name = name;
    }

    /** @return production cost */
    public double getCost() {
        return cost;
    }

    /** @param cost new production cost */
    public void setCost(double cost) {
        this.cost = cost;
    }
}