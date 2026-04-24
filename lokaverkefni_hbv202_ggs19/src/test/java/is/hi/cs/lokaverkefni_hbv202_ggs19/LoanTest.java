package is.hi.cs.lokaverkefni_hbv202_ggs19;

import junit.framework.TestCase;

public class LoanTest extends TestCase {

    private Loan loan;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        loan = new Loan("Test Loan", 10000.0, 12, 5.0);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testConstructor() {
        assertNotNull("Loan should not be null", loan);
        assertEquals("Loan name should be 'Test Loan'", "Test Loan", loan.getName());
        assertEquals("Loan amount should be 10000.0", 10000.0, loan.getAmount());
        assertEquals("Loan months should be 12", 12, loan.getMonths());
        assertEquals("Loan rate should be 5.0", 5.0, loan.getRate());
    }

    public void testGetName() {
        assertEquals("Loan name should be 'Test Loan'", "Test Loan", loan.getName());
    }

    public void testSetName() {
        loan.setName("New Loan");
        assertEquals("Loan name should be 'New Loan'", "New Loan", loan.getName());
    }

    public void testGetAmount() {
        assertEquals("Loan amount should be 10000.0", 10000.0, loan.getAmount());
    }

    public void testGetMonths() {
        assertEquals("Loan months should be 12", 12, loan.getMonths());
    }

    public void testGetRate() {
        assertEquals("Loan rate should be 5.0", 5.0, loan.getRate());
    }

    public void testGetMonthlyPayment() {
        double monthlyPayment = loan.getMonthlyPayment();
        assertTrue("Monthly payment should be positive", monthlyPayment > 0);
        // If you know the expected value, you can assert it directly:
        // assertEquals("Monthly payment should be X", expectedValue, monthlyPayment, 0.01);
    }

    public void testGetTotalPayment() {
        double totalPayment = loan.getTotalPayment();
        assertTrue("Total payment should be positive", totalPayment > 0);
        // If you know the expected value, you can assert it directly:
        // assertEquals("Total payment should be X", expectedValue, totalPayment, 0.01);
    }

    public void testGetTotalInterest() {
        double totalInterest = loan.getTotalInterest();
        assertTrue("Total interest should be positive", totalInterest > 0);
        // If you know the expected value, you can assert it directly:
        // assertEquals("Total interest should be X", expectedValue, totalInterest, 0.01);
    }

    public void testPaybackLoan() {
        BalanceSheet balanceSheet = new BalanceSheet(new Company("Test Company", 10000.0, new Shareholder("Test Shareholder", 1, 0)), 5000.0);
        balanceSheet.addLoan(loan);

        double initialCash = balanceSheet.getCash();
        Loan.paybackLoan(balanceSheet, loan);

        assertEquals("Cash should be reduced by total payment", initialCash - loan.getTotalPayment(), balanceSheet.getCash(), 0.01);

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
