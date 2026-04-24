package is.hi.cs.lokaverkefni_hbv202_ggs19;
import java.util.Scanner;

public class StartMenu {
    private static Company[] companies = new Company[10]; 
    private static int companyCount = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void access(String company) {
        for (Company c : companies) {
            if (c != null && c.getName().equals(company)) {
                c.companyStart();
                return;
            }
        }
        System.out.println("Company not found.");
    }

    public static Company[] getCompanies() {
    return companies;
}

    public static int getCompanyCount() {
        return companyCount;
    }
    public static void structure(String place) {
        System.out.println("You are at:" + place);
        System.out.println("startpage\n|--company\n|--balancesheet\n   |--loans\n   |--inventory\n|--products\n|--shareholders\n   |--inputmaterials");
    }

    public static void createCompany() {
        System.out.println("Enter company name: ");
        String name = scanner.nextLine();
        System.out.println("Enter company cash: ");
        Double cash = scanner.nextDouble();
        System.out.println("Enter company shareholder: ");
        String shareholder = scanner.nextLine();
        Shareholder shareholder1 = new Shareholder(shareholder, 1, 0);
        Company company = new Company(name, cash, shareholder1);
        companies[companies.length - 1] = company;
        company.companyStart();
    }

    public static void print() {
        for (Company c : companies) {
            if (c != null) {
                System.out.println(c.getName());
            }
        }
    }

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
                } else if(order[0].equals("exit")) {
                    System.exit(0);
                } else {
                    System.out.println("Invalid command, type info for help.");
                }
            }
            order = order();
        }
    }

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

    public static void main(String[] args) {
        //prop
        Shareholder warrenBuffet = new Shareholder("Warren Buffett", 100, 0);
        Company apple = new Company("Apple", 1000000.0, warrenBuffet);
        companies[companyCount] = apple;
        companyCount++;
        apple.getBalanceSheet().addLoan(new Loan("loan1", 1000.0, 24, 3.5));
        apple.getBalanceSheet().addLoan(new Loan("loan2", 1500.0, 12, 4.0));

        //start
        String[] start = {"start"};
        start(start);
    }
}