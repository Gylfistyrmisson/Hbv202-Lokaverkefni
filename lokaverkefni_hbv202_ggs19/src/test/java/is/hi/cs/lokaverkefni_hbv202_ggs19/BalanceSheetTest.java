package is.hi.cs.lokaverkefni_hbv202_ggs19;

import junit.framework.TestCase;

/**
 * BalanceSheetTest is a unit test class for the BalanceSheet class.
 * It verifies correct initialization, getters/setters, and core functionality
 * such as adding loans and modifying cash.
 */
public class BalanceSheetTest extends TestCase {

    /** Test instance of BalanceSheet */
    private BalanceSheet balanceSheet;

    /** Test instance of Company */
    private Company company;

    /**
     * Sets up test data before each test runs.
     * Initializes a Company and its BalanceSheet.
     *
     * @throws Exception if setup fails
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        company = new Company(
            "Test Company",
            1000.0,
            new Shareholder("Test Shareholder", 1, 0)
        );
        balanceSheet = new BalanceSheet(company, 5000.0);
    }

    /**
     * Cleans up after each test.
     *
     * @throws Exception if teardown fails
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Tests the constructor of BalanceSheet.
     * Verifies that all fields are initialized correctly.
     */
    public void testConstructor() {
        assertNotNull("BalanceSheet should not be null", balanceSheet);
        assertEquals("Company should match", company, balanceSheet.getCompany());
        assertEquals("Cash should be 5000.0", 5000.0, balanceSheet.getCash());

        assertNotNull("Loans array should not be null", balanceSheet.getLoans());
        assertEquals("Loans array length should be 10", 10, balanceSheet.getLoans().length);

        assertNotNull("Inventory array should not be null", balanceSheet.getInventory());
        assertEquals("Inventory array length should be 10", 10, balanceSheet.getInventory().length);
    }

    /**
     * Tests getCompany method.
     * Ensures the correct company reference is returned.
     */
    public void testGetCompany() {
        assertEquals("Company should match", company, balanceSheet.getCompany());
    }

    /**
     * Tests getCash method.
     * Ensures the correct cash value is returned.
     */
    public void testGetCash() {
        assertEquals("Cash should be 5000.0", 5000.0, balanceSheet.getCash());
    }

    /**
     * Tests setCash method.
     * Ensures the cash value can be updated.
     */
    public void testSetCash() {
        balanceSheet.setCash(7000.0);
        assertEquals("Cash should be 7000.0", 7000.0, balanceSheet.getCash());
    }

    /**
     * Tests getLoans method.
     * Verifies that the loans array exists and has correct size.
     */
    public void testGetLoans() {
        assertNotNull("Loans array should not be null", balanceSheet.getLoans());
        assertEquals("Loans array length should be 10", 10, balanceSheet.getLoans().length);
    }

    /**
     * Tests setLoans method.
     * Ensures that the loans array can be replaced and accessed correctly.
     */
    public void testSetLoans() {
        Loan[] newLoans = new Loan[5];
        newLoans[0] = new Loan("Test Loan", 1000.0, 12, 5.0);

        balanceSheet.setLoans(newLoans);

        assertEquals("Loans array should be replaced", newLoans, balanceSheet.getLoans());
        assertEquals(
            "First loan name should be 'Test Loan'",
            "Test Loan",
            balanceSheet.getLoans()[0].getName()
        );
    }

    /**
     * Tests getInventory method.
     * Verifies that the inventory array exists and has correct size.
     */
    public void testGetInventory() {
        assertNotNull("Inventory array should not be null", balanceSheet.getInventory());
        assertEquals("Inventory array length should be 10", 10, balanceSheet.getInventory().length);
    }

    /**
     * Tests addLoan method.
     * Ensures that a loan is correctly added to the loans array.
     */
    public void testAddLoan() {
        Loan loan = new Loan("New Loan", 2000.0, 24, 4.0);

        balanceSheet.addLoan(loan);

        boolean loanFound = false;
        for (Loan l : balanceSheet.getLoans()) {
            if (l != null && l.equals(loan)) {
                loanFound = true;
                break;
            }
        }

        assertTrue("Loan should be added to the loans array", loanFound);
    }

    /**
     * Tests addCash method.
     * Ensures that cash is correctly increased.
     */
    public void testAddCash() {
        balanceSheet.addCash(1000.0);
        assertEquals("Cash should be 6000.0", 6000.0, balanceSheet.getCash());
    }
}