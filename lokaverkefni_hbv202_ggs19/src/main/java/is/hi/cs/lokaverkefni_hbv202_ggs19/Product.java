package is.hi.cs.lokaverkefni_hbv202_ggs19;

public class Product implements Inventory{
    
    private String name;
    private double price;
    private int quantity;
    private InputMaterial[] inputMaterials;
    private int[] inputQuantity;
    
    
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.inputMaterials = new InputMaterial[0];
        this.inputQuantity = new int[0];
    }

    public double getAmount() {
        return quantity * price;
    }
    
    
    public static void productStart(Product[] products) {
        System.out.println("Wwelcome to product! \nType info for help.");
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }   

    public InputMaterial[] getInputMaterials() {
        return inputMaterials;
    }

    public int[] getInputQuantity() {
        return inputQuantity;
    }

    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }   
    
    public void setName(String name) {
        this.name = name;
    }

    public void setInputMaterials(InputMaterial[] inputMaterials) {
        this.inputMaterials = inputMaterials;
    }

    public void setInputQuantity(int[] inputQuantity) {
        this.inputQuantity = inputQuantity;
    }
    
}
