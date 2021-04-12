import java.util.Scanner;

public class SearchWindow {
    Scanner input;
    SearchServices searchServices;
    public SearchWindow(Scanner input) {
        this.input = input;
        searchServices = new SearchServices(input);
    }

    public void start(){
        while (true){
            menu();
            System.out.print("Choose one: ");
            String opt = input.next();
            if (opt.equals("5")){
                break;
            }
            else if (opt.equals("1")){
                searchServices.showMethod("name");
            }
            else if (opt.equals("2")){
                searchServices.showMethod("year");
            }
            else if (opt.equals("3")){
                searchServices.showMethod("author");
            }
            else if(opt.equals("4")){
                searchServices.showByGenre("genre");
            }
            else {
                System.out.println("Enter only these numbers!");
            }
        }
    }
    private void menu(){
        System.out.println();
        System.out.println("Search Menu");
        System.out.println("1. Search by Name");
        System.out.println("2. Search by Year");
        System.out.println("3. Search by Author");
        System.out.println("4. Search by Genre");
        System.out.println("5. Exit");
    }
}
