package is.hi.cs.lokaverkefni_hbv202_ggs19;

import junit.framework.TestCase;

/**
 * Unit tests for the Shareholder class.
 *
 * These tests verify:
 * - Constructor and getter correctness
 * - Setter behavior
 * - Correct calculation of dividends per share
 *
 * NOTE:
 * Interactive methods (shareholderOrder, addShares, etc.)
 * are not tested because they depend on user input,
 * recursion, and external Company logic.
 */
public class ShareholderTest extends TestCase {

    /**
     * Tests that constructor initializes fields correctly.
     */
    public void testConstructorAndGetters() {
        Shareholder s = new Shareholder("Alice", 100, 500.0);

        assertEquals("Alice", s.getName());
        assertEquals(100, s.getShares());
        assertEquals(500.0, s.getDividendsCollected(), 0.0001);
    }

    /**
     * Tests that setters correctly update fields.
     */
    public void testSetters() {
        Shareholder s = new Shareholder("Bob", 50, 200.0);

        s.setName("Charlie");
        s.setShares(75);
        s.setDividendsCollected(300.0);

        assertEquals("Charlie", s.getName());
        assertEquals(75, s.getShares());
        assertEquals(300.0, s.getDividendsCollected(), 0.0001);
    }

    /**
     * Tests correct calculation of dividends per share.
     */
    public void testDividendsPerShare() {
        Shareholder s = new Shareholder("Dana", 10, 100.0);

        assertEquals(10.0, s.getDividendsPerShare(), 0.0001);
    }

    /**
     * Tests dividends per share after updating dividends collected.
     */
    public void testDividendsPerShareAfterUpdate() {
        Shareholder s = new Shareholder("Eve", 20, 200.0);

        s.setDividendsCollected(400.0);

        assertEquals(20.0, s.getDividendsPerShare(), 0.0001);
    }

    /**
     * Tests case where no dividends have been collected.
     */
    public void testDividendsPerShareZeroDividends() {
        Shareholder s = new Shareholder("Frank", 10, 0.0);

        assertEquals(0.0, s.getDividendsPerShare(), 0.0001);
    }

    /**
     * Tests behavior when number of shares is zero.
     * Current implementation results in division by zero → Infinity.
     */
    public void testDividendsPerShareZeroShares() {
        Shareholder s = new Shareholder("Grace", 0, 100.0);

        assertTrue(Double.isInfinite(s.getDividendsPerShare()));
    }

    /**
     * Tests behavior with negative values.
     * Reflects current implementation (no validation).
     */
    public void testNegativeValuesAllowed() {
        Shareholder s = new Shareholder("Test", -10, -100.0);

        assertEquals(-10, s.getShares());
        assertEquals(-100.0, s.getDividendsCollected(), 0.0001);

        // (-100 / -10) = 10
        assertEquals(10.0, s.getDividendsPerShare(), 0.0001);
    }
}