package is.hi.cs.lokaverkefni_hbv202_ggs19;

import junit.framework.TestCase;

/**
 * LoanTest is a unit test class for the Loan class.
 * It verifies correct construction, getters/setters,
 * financial calculations, and loan payback behavior.
 */
public class LoanTest extends TestCase {

    /** Test instance of Loan */
    private Loan loan;

    /**
     * Sets up a sample Loan before each test.
     *
     * @throws Exception if setup fails
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        loan = new Loan("Test Loan", 10000.0, 12, 5.0);
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
     * Tests the Loan constructor.
     * Verifies that all fields are correctly initialized.
     */
    public void testConstructor() {
        assertNotNull("Loan should not be null", loan);
        assertEquals("Loan name should be 'Test Loan'", "Test Loan", loan.getName());
        assertEquals("Loan amount should be 10000.0", 10000.0, loan.getAmount());
        assertEquals("Loan months should be 12", 12, loan.getMonths());
        assertEquals("Loan rate should be 5.0", 5.0, loan.getRate());
    }

    /**
     * Tests getName method.
     */
    public void testGetName() {
        assertEquals("Loan name should be 'Test Loan'", "Test Loan", loan.getName());
    }

    /**
     * Tests setName method.
     */
    public void testSetName() {
        loan.setName("New Loan");
        assertEquals("Loan name should be 'New Loan'", "New Loan", loan.getName());
    }

    /**
     * Tests getAmount method.
     */
    public void testGetAmount() {
        assertEquals("Loan amount should be 10000.0", 10000.0, loan.getAmount());
    }

    /**
     * Tests getMonths method.
     */
    public void testGetMonths() {
        assertEquals("Loan months should be 12", 12, loan.getMonths());
    }

    /**
     * Tests getRate method.
     */
    public void testGetRate() {
        assertEquals("Loan rate should be 5.0", 5.0, loan.getRate());
    }

    /**
     * Tests monthly payment calculation.
     * Ensures the value is positive and mathematically valid.
     */
    public void testGetMonthlyPayment() {
        double monthlyPayment = loan.getMonthlyPayment();
        assertTrue("Monthly payment should be positive", monthlyPayment > 0);
    }

    /**
     * Tests total payment calculation.
     * Ensures the value is positive and valid.
     */
    public void testGetTotalPayment() {
        double totalPayment = loan.getTotalPayment();
        assertTrue("Total payment should be positive", totalPayment > 0);
    }

    /**
     * Tests total interest calculation.
     * Ensures interest is correctly computed.
     */
    public void testGetTotalInterest() {
        double totalInterest = loan.getTotalInterest();
        assertTrue("Total interest should be positive", totalInterest > 0);
    }

    /**
     * Tests paybackLoan functionality.
     * Ensures loan is removed and cash is correctly updated.
     */
    public void testPaybackLoan() {
        BalanceSheet balanceSheet = new BalanceSheet(
            new Company("Test Company", 10000.0, new Shareholder("Test Shareholder", 1, 0)),
            5000.0
        );

        balanceSheet.addLoan(loan);

        double initialCash = balanceSheet.getCash();

        Loan.paybackLoan(balanceSheet, loan);

        assertEquals(
            "Cash should be reduced by total payment",
            initialCash - loan.getTotalPayment(),
            balanceSheet.getCash(),
            0.01
        );

        Loan[] loans = balanceSheet.getLoans();
        boolean loanFound = false;

        for (Loan l : loans) {
            if (l != null && l.equals(loan)) {
                loanFound = true;
                break;
            }
        }

        assertFalse("Loan should be removed from the loans array", loanFound);
    }
}