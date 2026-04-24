package is.hi.cs.lokaverkefni_hbv202_ggs19;

import junit.framework.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StartMenuTest extends TestCase {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.setOut(new PrintStream(outputStream));
    }

    @Override
    protected void tearDown() throws Exception {
        System.setOut(System.out);
        super.tearDown();
    }

    public void testPrintCompanies() {
        // Reset the companies array to avoid interference between tests
        StartMenu.getCompanies()[0] = new Company("TestCompany", 1000.0, new Shareholder("TestShareholder", 1, 0));
        StartMenu.print();
        assertTrue("Output should contain 'TestCompany'", outputStream.toString().contains("TestCompany"));
    }

    public void testStructure() {
        StartMenu.structure("startpage");
        assertTrue("Output should contain 'startpage'", outputStream.toString().contains("startpage"));
        assertTrue("Output should contain 'company'", outputStream.toString().contains("company"));
        assertTrue("Output should contain 'balancesheet'", outputStream.toString().contains("balancesheet"));
    }
}