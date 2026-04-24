package is.hi.cs.lokaverkefni_hbv202_ggs19;

import junit.framework.TestCase;

public class CompanyTest extends TestCase {

    private Company company;
    private Shareholder shareholder;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        shareholder = new Shareholder("Test Shareholder", 1, 0);
        company = new Company("Test Company", 1000.0, shareholder);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testConstructor() {
        assertNotNull("Company should not be null", company);
        assertEquals("Company name should be 'Test Company'", "Test Company", company.getName());
        assertNotNull("BalanceSheet should not be null", company.getBalanceSheet());
        assertEquals("Cash should be 1000.0", 1000.0, company.getBalanceSheet().getCash());
        assertEquals("Shareholders array length should be 1", 1, company.getShareholders().length);
        assertEquals("Shareholder name should be 'Test Shareholder'", "Test Shareholder", company.getShareholders()[0].getName());
        assertEquals("InputMaterials array length should be 0", 0, company.getInputMaterials().length);
    }

    public void testGetName() {
        assertEquals("Company name should be 'Test Company'", "Test Company", company.getName());
    }

    public void testSetName() {
        company.setName("New Name");
        assertEquals("Company name should be 'New Name'", "New Name", company.getName());
    }

    public void testGetBalanceSheet() {
        assertNotNull("BalanceSheet should not be null", company.getBalanceSheet());
    }

    public void testSetBalanceSheet() {
        BalanceSheet newBalanceSheet = new BalanceSheet(company, 2000.0);
        company.setBalanceSheet(newBalanceSheet);
        assertEquals("BalanceSheet cash should be 2000.0", 2000.0, company.getBalanceSheet().getCash());
    }
}