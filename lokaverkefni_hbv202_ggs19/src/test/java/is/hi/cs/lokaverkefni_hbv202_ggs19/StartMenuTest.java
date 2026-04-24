package is.hi.cs.lokaverkefni_hbv202_ggs19;

import junit.framework.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * StartMenuTest is a unit test class for the StartMenu class.
 * It verifies that output-producing methods behave correctly
 * by capturing and inspecting console output.
 */
public class StartMenuTest extends TestCase {

    /**
     * Output stream used to capture System.out prints
     * so they can be tested.
     */
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Redirects System.out to a custom output stream before each test.
     *
     * @throws Exception if setup fails
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Restores the original System.out after each test.
     *
     * @throws Exception if teardown fails
     */
    @Override
    protected void tearDown() throws Exception {
        System.setOut(System.out);
        super.tearDown();
    }

    /**
     * Tests the print method in StartMenu.
     * Verifies that company names are correctly printed to the console.
     */
    public void testPrintCompanies() {
        // Add a test company to the array
        StartMenu.getCompanies()[0] = new Company(
            "TestCompany",
            1000.0,
            new Shareholder("TestShareholder", 1, 0)
        );

        // Call the method being tested
        StartMenu.print();

        // Verify output contains the company name
        assertTrue(
            "Output should contain 'TestCompany'",
            outputStream.toString().contains("TestCompany")
        );
    }

    /**
     * Tests the structure method in StartMenu.
     * Verifies that the structure output contains expected sections.
     */
    public void testStructure() {
        // Call the method with a test location
        StartMenu.structure("startpage");

        // Verify expected output content
        assertTrue(
            "Output should contain 'startpage'",
            outputStream.toString().contains("startpage")
        );
        assertTrue(
            "Output should contain 'company'",
            outputStream.toString().contains("company")
        );
        assertTrue(
            "Output should contain 'balancesheet'",
            outputStream.toString().contains("balancesheet")
        );
    }
}