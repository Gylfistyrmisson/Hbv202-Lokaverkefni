package is.hi.cs.lokaverkefni_hbv202_ggs19;

public class Company {
    private String name;
    private BalanceSheet balanceSheet;
    private Product[] products;
    private InputMaterial[] inputMaterials;
    private Shareholder[] shareholders;



    public Company(String name, Double cash,Shareholder shareholder) {
        this.name = name;
        this.balanceSheet = new BalanceSheet(cash, new Loan[0], new Inventory[0]);
        this.products = new Product[0];
        this.shareholders = new Shareholder[]{shareholder};
        this.inputMaterials = new InputMaterial[0];
    }

    public static void companyOrder(String[] order) {

        if(order[0] == "info") {

        } else if(order[0] == "access") {

        } else if(order[0] == "change") {

        } else if(order[0] == "print") {

        } else if (order[0] == "info!" || order[0] == "structure" || order[0] == "exit") {

        } else {
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
