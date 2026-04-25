package is.hi.cs.lokaverkefni_hbv202_ggs19;

public interface InventoryInterface {
    /** Thought of as an interface for future types of inventory (e.g. input materials, etc.)*/
    public String getName();
    public double getPrice();
    public int getQuantity();
    public double getAmount();
}