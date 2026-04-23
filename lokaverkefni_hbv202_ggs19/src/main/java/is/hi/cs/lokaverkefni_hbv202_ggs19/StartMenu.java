package is.hi.cs.lokaverkefni_hbv202_ggs19;
import java.util.Scanner;

public class StartMenu {
    private static Boolean firstStart = true;
    private static Company[] companies = new Company[10]; 
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

    public static void info() {
        System.out.println("info for startpage.");
    }

    public static void start(String[] order) {
        while (true) {
            if (firstStart) {
                firstStart = false;
                System.out.println("Welcome to the startpage! \nType info for help.");
            } else if (order.length > 0) {
                if (order[0].equals("info")) {
                    System.out.println("info for startpage.");
                } else if (order[0].equals("print")) {
                    System.out.println("print for startpage.");
                } else if (order[0].equals("access") && order.length > 1) {
                    access(order[1]);
                } else if (order[0].equals("create")) {
                    createCompany();
                }else {
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
        String[] start = {"Start"};
        start(start);
    }
}