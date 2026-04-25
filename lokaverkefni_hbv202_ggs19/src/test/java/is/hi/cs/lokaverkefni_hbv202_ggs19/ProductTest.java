package is.hi.cs.lokaverkefni_hbv202_ggs19;

import junit.framework.TestCase;

/**
 * Unit tests for the Product class.
 *
 * These tests verify:
 * - Correct initialization via constructor
 * - Getter and setter behavior
 * - Correct calculation of total inventory value (getAmount)
 *
 * NOTE:
 * Interactive methods (productOrder, addProduct, etc.)
 * are not tested because they depend on user input and recursion.
 */
public class ProductTest extends TestCase {

    /**
     * Tests that constructor correctly initializes all fields
     * and getters return expected values.
     */
    public void testConstructorAndGetters() {
        Product p = new Product("Laptop", 1500.0, 10, 900.0);

        assertEquals("Laptop", p.getName());
        assertEquals(1500.0, p.getPrice(), 0.0001);
        assertEquals(10, p.getQuantity());
        assertEquals(900.0, p.getCost(), 0.0001);
    }

    /**
     * Tests that setters correctly update all fields.
     */
    public void testSetters() {
        Product p = new Product("Phone", 800.0, 5, 400.0);

        p.setName("Tablet");
        p.setPrice(600.0);
        p.setQuantity(20);
        p.setCost(300.0);

        assertEquals("Tablet", p.getName());
        assertEquals(600.0, p.getPrice(), 0.0001);
        assertEquals(20, p.getQuantity());
        assertEquals(300.0, p.getCost(), 0.0001);
    }

    /**
     * Tests correct calculation of total value (price * quantity).
     */
    public void testGetAmount() {
        Product p = new Product("Monitor", 200.0, 3, 120.0);

        assertEquals(600.0, p.getAmount(), 0.0001);
    }

    /**
     * Tests that total value is zero when quantity is zero.
     */
    public void testGetAmountZeroQuantity() {
        Product p = new Product("Keyboard", 50.0, 0, 20.0);

        assertEquals(0.0, p.getAmount(), 0.0001);
    }

    /**
     * Tests that updating quantity affects total value correctly.
     */
    public void testSetQuantityUpdatesAmount() {
        Product p = new Product("Mouse", 25.0, 2, 10.0);

        p.setQuantity(10);

        assertEquals(250.0, p.getAmount(), 0.0001);
    }

    /**
     * Tests that updating price affects total value correctly.
     */
    public void testSetPriceUpdatesAmount() {
        Product p = new Product("Headphones", 100.0, 4, 60.0);

        p.setPrice(150.0);

        assertEquals(600.0, p.getAmount(), 0.0001);
    }

    /**
     * Tests behavior with negative values.
     * This reflects current implementation (no validation).
     */
    public void testNegativeValuesAllowed() {
        Product p = new Product("Test", -10.0, -5, -2.0);

        assertEquals(-10.0, p.getPrice(), 0.0001);
        assertEquals(-5, p.getQuantity());
        assertEquals(-2.0, p.getCost(), 0.0001);

        // (-10 * -5) = 50
        assertEquals(50.0, p.getAmount(), 0.0001);
    }
}