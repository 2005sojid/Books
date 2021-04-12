import java.util.Scanner;

public class ShowWindow {
    Scanner input;
    ShowServices showServices;

    public ShowWindow(Scanner input) {
        this.input = input;
        showServices = new ShowServices();
    }

    public void start() {
        while (true) {
            menu();
            System.out.print("Choose one: ");
            String opt = input.next();
            if (opt.equals("5")) {
                break;
            } else if (opt.equals("1")) {
                showServices.byColumn("name");
            } else if (opt.equals("2")) {
                showServices.byColumn("year");
            } else if (opt.equals("3")) {
                showServices.byColumn("author");
            } else if (opt.equals("4")) {
                showServices.byColumn("genre");
            } else {
                System.out.println("Enter only these numbers!");
            }
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("Show Menu");
        System.out.println("1. Show all Names");
        System.out.println("2. Show all Years");
        System.out.println("3. Show all Authors");
        System.out.println("4. Show all Genres");
        System.out.println("5. Exit");
    }
}
