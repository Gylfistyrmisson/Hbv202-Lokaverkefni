package is.hi.cs.lokaverkefni_hbv202_ggs19;

import junit.framework.TestCase;

public class BalanceSheetTest extends TestCase {

    private BalanceSheet balanceSheet;
    private Company company;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        company = new Company("Test Company", 1000.0, new Shareholder("Test Shareholder", 1, 0));
        balanceSheet = new BalanceSheet(company, 5000.0);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testConstructor() {
        assertNotNull("BalanceSheet should not be null", balanceSheet);
        assertEquals("Company should match", company, balanceSheet.getCompany());
        assertEquals("Cash should be 5000.0", 5000.0, balanceSheet.getCash());
        assertNotNull("Loans array should not be null", balanceSheet.getLoans());
        assertEquals("Loans array length should be 10", 10, balanceSheet.getLoans().length);
        assertNotNull("Inventory array should not be null", balanceSheet.getInventory());
        assertEquals("Inventory array length should be 10", 10, balanceSheet.getInventory().length);
    }

    public void testGetCompany() {
        assertEquals("Company should match", company, balanceSheet.getCompany());
    }

    public void testGetCash() {
        assertEquals("Cash should be 5000.0", 5000.0, balanceSheet.getCash());
    }

    public void testSetCash() {
        balanceSheet.setCash(7000.0);
        assertEquals("Cash should be 7000.0", 7000.0, balanceSheet.getCash());
    }

    public void testGetLoans() {
        assertNotNull("Loans array should not be null", balanceSheet.getLoans());
        assertEquals("Loans array length should be 10", 10, balanceSheet.getLoans().length);
    }

    public void testSetLoans() {
        Loan[] newLoans = new Loan[5];
        newLoans[0] = new Loan("Test Loan", 1000.0, 12, 5.0);
        balanceSheet.setLoans(newLoans);
        assertEquals("Loans array should be replaced", newLoans, balanceSheet.getLoans());
        assertEquals("First loan name should be 'Test Loan'", "Test Loan", balanceSheet.getLoans()[0].getName());
    }

    public void testGetInventory() {
        assertNotNull("Inventory array should not be null", balanceSheet.getInventory());
        assertEquals("Inventory array length should be 10", 10, balanceSheet.getInventory().length);
    }

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

    public void testAddCash() {
        balanceSheet.addCash(1000.0);
        assertEquals("Cash should be 6000.0", 6000.0, balanceSheet.getCash());
    }
}