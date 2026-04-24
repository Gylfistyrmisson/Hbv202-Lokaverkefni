package is.hi.cs.lokaverkefni_hbv202_ggs19;

public class Company {
    private String name;
    private BalanceSheet balanceSheet;
    private Product[] products;
    private InputMaterial[] inputMaterials;
    private Shareholder[] shareholders;



    public Company(String name, Double cash,Shareholder shareholder) {
        this.name = name;
        this.balanceSheet = new BalanceSheet(this, cash);      
        this.shareholders = new Shareholder[]{shareholder};
        this.inputMaterials = new InputMaterial[0];
    }
    

    public void companyOrder(String[] order) {

        if(order[0].equals("info")) {
            System.out.println("Command list:\n info : display info of commands\n access : layer benatch, followed by: balance sheet / products / shareholders\n print : prints out summary of company data\n structure : prints out where in company structure you are(global command)\n back : back to layer above(global command)\n exit : exit program(global command)");
            String[] nextOrder = StartMenu.order();
            companyOrder(nextOrder);
        } else if(order[0].equals("access")) {
            if(order[1].equals("balance") && order[2].equals("sheet")) {
                balanceSheet.start();
            } else if(order[1].equals("products")) {
                Product.productStart(products);
            } else if(order[1].equals("shareholders")) {
                Shareholder.shareholderStart(shareholders);
            } else {
                System.out.println("Invalid command, type info for help.");
                String[] nextOrder = StartMenu.order();
                companyOrder(nextOrder);
            }
        } else if(order[0].equals("print")) {
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
            String[] nextOrder = new String[] {"start"};
            StartMenu.start(nextOrder);
        } else if(order[0].equals( "exit")) {
            System.exit(0);
        }else {
            System.out.println("Invalid command, type info for help.");
            String[] nextOrder = StartMenu.order();
            companyOrder(nextOrder);
        }
    }

    public void companyStart() {
        System.out.println("Wwelcome to " + name + "'s company! \nType info for help.");
        String[] order = StartMenu.order();
        companyOrder(order);
    }

    public void addShareholder(Shareholder shareholder) {
        Shareholder[] newShareholders = new Shareholder[shareholders.length + 1];
        for (int i = 0; i < shareholders.length; i++) {
            newShareholders[i] = shareholders[i];
        }
        newShareholders[newShareholders.length - 1] = shareholder;
        shareholders = newShareholders;
    }

    public void addProduct(Product product) {
        Product[] newProducts = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            newProducts[i] = products[i];
        }
        newProducts[newProducts.length - 1] = product;
        products = newProducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BalanceSheet getBalanceSheet() {
        return balanceSheet;
    }   

    public void setBalanceSheet(BalanceSheet balanceSheet) {
        this.balanceSheet = balanceSheet;
    }   

    public Product[] getProducts() {
        return products;
    }   

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public InputMaterial[] getInputMaterials() {
        return inputMaterials;
    }

    public void setInputMaterials(InputMaterial[] inputMaterials) {
        this.inputMaterials = inputMaterials;
    }

    public Shareholder[] getShareholders() {
        return shareholders;
    }

    public void setShareholders(Shareholder[] shareholders) {
        this.shareholders = shareholders;
    }
}
