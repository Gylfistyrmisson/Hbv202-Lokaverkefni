package is.hi.cs.lokaverkefni_hbv202_ggs19;

import java.util.Scanner;

/**
 * StartMenu is the main entry point for interacting with the system.
 * It provides a command-line interface for managing companies and navigating
 * through different parts of the application.
 */
public class StartMenu {

    /** Array storing all companies (max 10) */
    private static Company[] companies = new Company[10];

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
        System.out.println("You are at:" + place);
        System.out.println("startpage\n|--company\n|--balancesheet\n   |--loans\n   |--inventory\n|--products\n|--shareholders\n   |--inputmaterials");
    }

    /**
     * Prompts the user to create a new company and adds it to the system.
     * Also immediately starts the company menu after creation.
     */
    public static void createCompany() {
        System.out.println("Enter company name: ");
        String name = scanner.nextLine();

        System.out.println("Enter company cash: ");
        Double cash = scanner.nextDouble();

        System.out.println("Enter company shareholder: ");
        scanner.nextLine(); // consume leftover newline
        String shareholder = scanner.nextLine();

        Shareholder shareholder1 = new Shareholder(shareholder, 1, 0);
        Company company = new Company(name, cash, shareholder1);

        companies[companies.length - 1] = company;

        company.companyStart();
    }

    /**
     * Prints the names of all existing companies.
     */
    public static void print() {
        for (Company c : companies) {
            if (c != null) {
                System.out.println(c.getName());
            }
        }
    }

    /**
     * Main command loop for the application.
     * Handles user input and routes commands to the correct functionality.
     *
     * @param order initial command input
     */
    public static void start(String[] order) {
        for (int i = 0; i < order.length; i++) {
            System.out.println(order[i]);
        }

        while (true) {
            if (order.length > 0) {
                if (order[0].equals("start")) {
                    System.out.println("Welcome to the startpage! \nType info for help.");
                    String[] nextOrder = order();
                    start(nextOrder);

                } else if (order[0].equals("info")) {
                    System.out.println("\"Command list:\\n info : display info of commands\\n access : layer benatch, followed by company name\\n print : prints out list of companies\\n structure : prints out where in company structure you are(global command)\\n back : back to layer above(global command)\\n exit : exit program(global command)\"");
                    String[] nextOrder = order();
                    start(nextOrder);

                } else if (order[0].equals("print")) {
                    for (int i = 0; i < companies.length; i++) {
                        if (companies[i] != null) {
                            System.out.println(companies[i].getName());
                        }
                    }
                    String[] nextOrder = order();
                    start(nextOrder);

                } else if (order[0].equals("access") && order.length > 1) {
                    access(order[1]);

                } else if (order[0].equals("create")) {
                    createCompany();

                } else if (order[0].equals("exit")) {
                    System.exit(0);

                } else {
                    System.out.println("Invalid command, type info for help.");
                }
            }
            order = order();
        }
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

        // Initial test data
        Shareholder warrenBuffet = new Shareholder("Warren Buffett", 100, 0);
        Company apple = new Company("Apple", 1000000.0, warrenBuffet);

        companies[companyCount] = apple;
        companyCount++;

        apple.getBalanceSheet().addLoan(new Loan("loan1", 1000.0, 24, 3.5));
        apple.getBalanceSheet().addLoan(new Loan("loan2", 1500.0, 12, 4.0));

        // Start program
        String[] start = {"start"};
        start(start);
    }
}