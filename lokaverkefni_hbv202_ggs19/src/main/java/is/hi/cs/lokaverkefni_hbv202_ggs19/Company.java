package is.hi.cs.lokaverkefni_hbv202_ggs19;

/**
 * The Company class represents a company entity in the system.
 * It contains financial data (via BalanceSheet), products,
 * input materials, and shareholders.
 * 
 * It also provides a command-line interface for interacting
 * with company-specific functionality.
 */
public class Company {

    /** Name of the company */
    private String name;

    /** Financial data associated with the company */
    private BalanceSheet balanceSheet;

    /** List of products owned by the company */
    private Product[] products;

    /** List of input materials used by the company */
    private InputMaterial[] inputMaterials;

    /** List of shareholders of the company */
    private Shareholder[] shareholders;

    /**
     * Constructs a Company object with a name, initial cash,
     * and a single shareholder.
     *
     * @param name the name of the company
     * @param cash the initial cash amount
     * @param shareholder the initial shareholder
     */
    public Company(String name, Double cash, Shareholder shareholder) {
        this.name = name;
        this.balanceSheet = new BalanceSheet(this, cash);
        this.shareholders = new Shareholder[]{shareholder};
        this.inputMaterials = new InputMaterial[0];
    }

    /**
     * Handles user commands within the company context.
     * Routes commands to appropriate functionality such as
     * balance sheet, products, or shareholders.
     *
     * @param order user input split into command tokens
     */
    public void companyOrder(String[] order) {

        if (order[0].equals("info")) {
            System.out.println("Command list:\n info : display info of commands\n access : layer benatch, followed by: balance sheet / products / shareholders\n print : prints out summary of company data\n structure : prints out where in company structure you are(global command)\n back : back to layer above(global command)\n exit : exit program(global command)");
            String[] nextOrder = StartMenu.order();
            companyOrder(nextOrder);

        } else if (order[0].equals("access")) {
            if (order[1].equals("balance") && order[2].equals("sheet")) {
                balanceSheet.start();
            } else if (order[1].equals("products")) {
                Product.productStart(products);
            } else if (order[1].equals("shareholders")) {
                Shareholder.shareholderStart(shareholders);
            } else {
                System.out.println("Invalid command, type info for help.");
                String[] nextOrder = StartMenu.order();
                companyOrder(nextOrder);
            }

        } else if (order[0].equals("print")) {
            String shareholderList = "";
            for (int i = 0; i < shareholders.length; i++) {
                shareholderList += shareholders[i].getName() + "\n";
            }
            System.out.println("Name: " + name + "\nCash: " + balanceSheet.getCash() + "\nShareholders: " + shareholderList);
            String[] nextOrder = StartMenu.order();
            companyOrder(nextOrder);

        } else if (order[0].equals("structure")) {
            StartMenu.structure("company");
            String[] nextOrder = StartMenu.order();
            companyOrder(nextOrder);

        } else if (order[0].equals("back")) {
            String[] nextOrder = new String[]{"start"};
            StartMenu.start(nextOrder);

        } else if (order[0].equals("exit")) {
            System.exit(0);

        } else {
            System.out.println("Invalid command, type info for help.");
            String[] nextOrder = StartMenu.order();
            companyOrder(nextOrder);
        }
    }

    /**
     * Starts the company-specific interface.
     * Displays a welcome message and begins reading user commands.
     */
    public void companyStart() {
        System.out.println("Wwelcome to " + name + "'s company! \nType info for help.");
        String[] order = StartMenu.order();
        companyOrder(order);
    }

    /**
     * Adds a new shareholder to the company.
     *
     * @param shareholder the shareholder to add
     */
    public void addShareholder(Shareholder shareholder) {
        Shareholder[] newShareholders = new Shareholder[shareholders.length + 1];
        for (int i = 0; i < shareholders.length; i++) {
            newShareholders[i] = shareholders[i];
        }
        newShareholders[newShareholders.length - 1] = shareholder;
        shareholders = newShareholders;
    }

    /**
     * Adds a new product to the company.
     *
     * @param product the product to add
     */
    public void addProduct(Product product) {
        Product[] newProducts = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            newProducts[i] = products[i];
        }
        newProducts[newProducts.length - 1] = product;
        products = newProducts;
    }

    /**
     * Gets the company name.
     *
     * @return company name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the company name.
     *
     * @param name new company name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the company's balance sheet.
     *
     * @return balance sheet
     */
    public BalanceSheet getBalanceSheet() {
        return balanceSheet;
    }

    /**
     * Sets the company's balance sheet.
     *
     * @param balanceSheet new balance sheet
     */
    public void setBalanceSheet(BalanceSheet balanceSheet) {
        this.balanceSheet = balanceSheet;
    }

    /**
     * Gets the company's products.
     *
     * @return array of products
     */
    public Product[] getProducts() {
        return products;
    }

    /**
     * Sets the company's products.
     *
     * @param products new products array
     */
    public void setProducts(Product[] products) {
        this.products = products;
    }

    /**
     * Gets the company's input materials.
     *
     * @return array of input materials
     */
    public InputMaterial[] getInputMaterials() {
        return inputMaterials;
    }

    /**
     * Sets the company's input materials.
     *
     * @param inputMaterials new input materials array
     */
    public void setInputMaterials(InputMaterial[] inputMaterials) {
        this.inputMaterials = inputMaterials;
    }

    /**
     * Gets the company's shareholders.
     *
     * @return array of shareholders
     */
    public Shareholder[] getShareholders() {
        return shareholders;
    }

    /**
     * Sets the company's shareholders.
     *
     * @param shareholders new shareholders array
     */
    public void setShareholders(Shareholder[] shareholders) {
        this.shareholders = shareholders;
    }
}