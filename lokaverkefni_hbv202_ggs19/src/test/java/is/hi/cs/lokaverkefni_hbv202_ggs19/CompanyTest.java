package is.hi.cs.lokaverkefni_hbv202_ggs19;

import junit.framework.TestCase;

/**
 * CompanyTest is a unit test class for the Company class.
 * It verifies that the Company object behaves correctly,
 * including its constructor, getters, and setters.
 */
public class CompanyTest extends TestCase {

    /** Test instance of Company */
    private Company company;

    /** Test instance of Shareholder */
    private Shareholder shareholder;

    /**
     * Sets up test data before each test case runs.
     * Initializes a Company with a default Shareholder.
     *
     * @throws Exception if setup fails
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        shareholder = new Shareholder("Test Shareholder", 1, 0);
        company = new Company("Test Company", 1000.0, shareholder);
    }

    /**
     * Cleans up after each test case.
     *
     * @throws Exception if teardown fails
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Tests the Company constructor.
     * Verifies that all fields are initialized correctly.
     */
    public void testConstructor() {
        assertNotNull("Company should not be null", company);
        assertEquals("Company name should be 'Test Company'", "Test Company", company.getName());
        assertNotNull("BalanceSheet should not be null", company.getBalanceSheet());
        assertEquals("Cash should be 1000.0", 1000.0, company.getBalanceSheet().getCash());
        assertEquals("Shareholders array length should be 1", 1, company.getShareholders().length);
        assertEquals("Shareholder name should be 'Test Shareholder'", "Test Shareholder", company.getShareholders()[0].getName());
    }

    /**
     * Tests the getName method.
     * Ensures the correct company name is returned.
     */
    public void testGetName() {
        assertEquals("Company name should be 'Test Company'", "Test Company", company.getName());
    }

    /**
     * Tests the setName method.
     * Ensures the company name can be updated correctly.
     */
    public void testSetName() {
        company.setName("New Name");
        assertEquals("Company name should be 'New Name'", "New Name", company.getName());
    }

    /**
     * Tests the getBalanceSheet method.
     * Ensures a BalanceSheet object is always returned.
     */
    public void testGetBalanceSheet() {
        assertNotNull("BalanceSheet should not be null", company.getBalanceSheet());
    }

    /**
     * Tests the setBalanceSheet method.
     * Verifies that the balance sheet can be replaced
     * and that the new values are correctly applied.
     */
    public void testSetBalanceSheet() {
        BalanceSheet newBalanceSheet = new BalanceSheet(company, 2000.0);
        company.setBalanceSheet(newBalanceSheet);
        assertEquals("BalanceSheet cash should be 2000.0", 2000.0, company.getBalanceSheet().getCash());
    }
}