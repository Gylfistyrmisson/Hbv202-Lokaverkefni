package is.hi.cs.lokaverkefni_hbv202_ggs19;

import java.util.Scanner;

/**
 * StartMenu is the main entry point for interacting with the system.
 * It provides a command-line interface for managing companies and navigating
 * through different parts of the application.
 */
public class StartMenu {

    /** Array storing all companies (max 10) */
    private static Company[] companies = new Company[0];

    /** Tracks the current number of companies */
    private static int companyCount = 0;

    /** Scanner used for user input */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Accesses a company by name and starts its internal menu.
     *
     * @param company the name of the company to access
     */
    public static void access(String company) {
        for (Company c : companies) {
            if (c != null && c.getName().equals(company)) {
                c.companyStart();
                return;
            }
        }
        System.out.println("Company not found.");
    }

    /**
     * Returns the list of companies.
     *
     * @return array of companies
     */
    public static Company[] getCompanies() {
        return companies;
    }

    public static void addCompany(Company company) {
        Company[] newCompanies = new Company[companies.length + 1];
        for (int i = 0; i < companies.length; i++) {
            newCompanies[i] = companies[i];
        }
        companies = newCompanies;
        companies[companies.length - 1] = company;
        companyCount++;
    }

    /**
     * Returns the number of companies currently stored.
     *
     * @return company count
     */
    public static int getCompanyCount() {
        return companyCount;
    }

    /**
     * Prints the structure of the application and the current location.
     *
     * @param place current location in the system
     */
    public static void structure(String place) {
        if (place.equals("startpage")) {
            System.out.println("startpage <-- you are here!\n|--company\n|  |--balancesheet\n|  |  |--loans\n|  |  |--inventory\n|  |--products\n|  |--shareholders");
        } else if (place.equals("company")) {
            System.out.println("startpage\n|--company <-- you are here!\n|  |--balancesheet\n|  |  |--loans\n|  |  |--inventory\n|  |--products\n|  |--shareholders");
        } else if (place.equals("balancesheet")) {
            System.out.println("startpage\n|--company\n|  |--balancesheet <-- you are here!\n|  |  |--loans\n|  |  |--inventory\n|  |--products\n|  |--shareholders");
        } else if (place.equals("loans")) {
            System.out.println("startpage\n|--company\n|  |--balancesheet\n|  |  |--loans <-- you are here!\n|  |  |--inventory\n|  |--products\n|  |--shareholders");
        } else if (place.equals("inventory")) {
            System.out.println("startpage\n|--company\n|  |--balancesheet\n|  |  |--loans\n|  |  |--inventory <-- you are here!\n|  |--products\n|  |--shareholders");
        } else if (place.equals("products")) {
            System.out.println("startpage\n|--company\n|  |--balancesheet\n|  |  |--loans\n|  |  |--inventory\n|  |--products <-- you are here!\n|  |--shareholders");
        } else if (place.equals("shareholders")) {
            System.out.println("startpage\n|--company\n|  |--balancesheet\n|  |  |--loans\n|  |  |--inventory\n|  |--products\n|  |--shareholders <-- you are here!");
        } else {
            System.out.println("Invalid place.");
            return;
        }
    }

    /**
     * Prompts the user to create a new company and adds it to the system.
     * Also immediately starts the company menu after creation.
     */
    public static void createCompany() {
        System.out.println("Enter company name: ");
        String[] order1 = order();
        String name = "";
        for (int i = 0; i < order1.length; i++) {
            name += order1[i] + " ";
        }

        System.out.println("Enter company cash: ");
        String[] order2 = order();
        try {
            Double.parseDouble(order2[0]);
        } catch (Exception e) {
            System.out.println("Invalid input.");
            return;
        }
        double cash = Double.parseDouble(order2[0]);

        System.out.println("Enter company shareholder: ");
        String[] order3 = order();
        String shareholder = "";
        for (int i = 0; i < order3.length; i++) {
            shareholder += order3[i] + " ";
        }

        Shareholder shareholder1 = new Shareholder(shareholder, 1, 0);
        Company company = new Company(name, cash, shareholder1);

        addCompany(company);

        company.companyStart();
    }

    /**
     * Prints the names of all existing companies.
     */

    public static void printStartMenu() {
        System.out.println("Companies:");
        for (int i = 0; i < companies.length; i++) {
            if (companies[i] != null) {
                System.out.println(" -" +companies[i].getName());
            }
        }
    }

    /**
     * Main command loop for the application.
     * Handles user input and routes commands to the correct functionality.
     *
     * @param order initial command input
     */
    public static void startMenuOrder() {
        String[] order = order();
        if (order.length > 0) {
            if (order[0].equals("info")) {
                System.out.println("Command list:\n info : display info of commands\n access : followed by company name\n print : prints out list of companies\n structure : prints out where in company structure you are(global command)\n back : back to layer above(global command)\n exit : exit program(global command)");
            } else if (order[0].equals("print")) {
                printStartMenu();
            } else if (order[0].equals("access") && order.length > 1) {
                access(order[1]);
            } else if (order[0].equals("structure")) {
                structure("startpage");
            } else if (order[0].equals("create")) {
            } else if(order[0].equals("back")){
                System.out.println("You are at top layer.");
            } else if (order[0].equals("exit")) {
                System.exit(0);
            } else {
                System.out.println("Invalid command, type info for help.");
            }
        }
        return;
    }

    /**
     * Reads user input from the console and splits it into command tokens.
     *
     * @return array of command arguments
     */
    public static String[] order() {
        System.out.print("> ");
        String input = scanner.nextLine();
        String[] order = input.split(" ");
        String[] invalid = {"Invalid"};

        if (order.length < 1) {
            return invalid;
        } else {
            return order;
        }
    }

    /**
     * Application entry point.
     * Initializes default data and starts the program.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // Prop data
        Shareholder warrenBuffet = new Shareholder("Warren Buffett", 100, 0);
        Company apple = new Company("Apple", 1000000.0, warrenBuffet);
        addCompany(apple);
        apple.getBalanceSheet().addLoan(new Loan("loan1", 1000.0, 24, 0.035));
        apple.getBalanceSheet().addLoan(new Loan("loan2", 1500.0, 12, 0.04));
        apple.addProduct(new Product("iPone", 1200, 100, 200));
        apple.addProduct(new Product("iPad", 1500, 20, 300));
        apple.addProduct(new Product("iMac", 2500, 100, 500));
        
        // start
        System.out.println("Welcome to the company clone application!");
        System.out.println("Type info for help.");
        while(true) {
            startMenuOrder();
        }
    }
}