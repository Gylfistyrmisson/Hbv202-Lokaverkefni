package is.hi.cs.lokaverkefni_hbv202_ggs19;

public class InputMaterial implements InventoryInterface{
    private String materialName;
    private int quantity;
    private double price;
    
    public InputMaterial(String materialName, int quantity, double price) {
        this.materialName = materialName;
        this.quantity = quantity;
        this.price = price;
    }

    public double getAmount() {
        return quantity * price;
    }
    
    public String getName() {
        return materialName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int amount) {
        this.quantity = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

}
