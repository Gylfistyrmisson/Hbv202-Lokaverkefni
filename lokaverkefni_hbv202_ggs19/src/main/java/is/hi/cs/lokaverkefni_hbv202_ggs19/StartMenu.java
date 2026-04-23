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

    public static void structure(String place) {
        System.out.println("You are at:" + place);
        System.out.println("startpage\n|--company\n|--balancesheet\\n   |--loans\n|--products\n|--shareholders\n   |--inputmaterials");
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
                    System.out.println("info for startpage.");
                    String[] nextOrder = order();
                    start(nextOrder);
                } else if (order[0].equals("print")) {
                    System.out.println("print for startpage.");
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
        return input.split(" ");
    }

    public static void main(String[] args) {
        //prop
        Shareholder warrenBuffet = new Shareholder("Warren Buffett", 100, 0);
        Company apple = new Company("Apple", 1000000000.0, warrenBuffet);
        companies[companyCount] = apple;
        companyCount++;
        //start
        String[] start = {"start"};
        start(start);
    }
}