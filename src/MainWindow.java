import java.util.Scanner;

public class MainWindow {
    Scanner input;
    UserServices userServices;
    SearchWindow searchWindow;
    ShowWindow showWindow;
    public MainWindow(Scanner input) {
        this.input = input;
        userServices = new UserServices(input);
        searchWindow = new SearchWindow(input);
        showWindow = new ShowWindow(input);
    }

    public void start() {
        while (true) {
            menu();
            System.out.print("Choose one: ");
            String opt = input.next();
            if (opt.equals("5")) {
                break;
            } else if (opt.equals("1")) {
                userServices.login();
            } else if (opt.equals("2")) {
                userServices.registration();
            } else if (opt.equals("3")) {
                searchWindow.start();
            }
            else if (opt.equals("4")){
                showWindow.start();
            }
            else {
                System.out.println("Enter only these numbers!");
            }
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("Main Menu");
        System.out.println("1. Login");
        System.out.println("2. Registration");
        System.out.println("3. Search");
        System.out.println("4. Show");
        System.out.println("5. Exit");
    }
}
