import java.util.Scanner;

public class BookWindow {
    Scanner input;
    BookService bookService;

    public BookWindow(Scanner input) {
        this.input = input;
        bookService = new BookService(input);

    }

    public void start(User user) {
        label:
        while (true) {
            menu();
            System.out.print("Choose one: ");
            String opt = input.next();
            switch (opt) {
                case "6":
                    break label;
                case "1":
                    bookService.add(user);
                    break;
                case "2":
                    bookService.edit(user);
                    break;
                case "3":
                    bookService.show(user);
                    break;
                case "4":
                    bookService.delete(user);
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Enter only these numbers!");
                    break;
            }
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("Books Menu");
        System.out.println("1. Add");
        System.out.println("2. Edit");
        System.out.println("3. Show");
        System.out.println("4. Delete");
        System.out.println("5. Account");
        System.out.println("6. Exit");
    }

}
