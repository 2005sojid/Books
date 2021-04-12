import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookService {
    Scanner input;

    public BookService(Scanner input) {
        this.input = input;
    }

    public void add(User user) {
        System.out.println();
        System.out.print("Name: ");
        input.nextLine();
        String name = input.nextLine();
        if (name.equals("")) {
            System.out.println("Enter book's name");
            return;
        }
        System.out.print("Year: ");
        String year = input.nextLine();
        if (year.equals("")) {
            System.out.println("Enter book's year");
            return;
        }

        System.out.print("Author: ");
        String author = input.nextLine();
        if (author.equals("")) {
            System.out.println("Enter book's author");
            return;
        }

        BookGenre bookGenre = BookGenre.SCIENTIFIC;
        bookGenre.total();
        System.out.print("Genre: ");
        String genre = input.nextLine();
        if (genre.equals("1")) {
            genre = BookGenre.SCIENTIFIC.getGenre();
        } else if (genre.equals("2")) {
            genre = BookGenre.BUSINESS.getGenre();
        } else if (genre.equals("3")) {
            genre = BookGenre.NOVEL.getGenre();
        } else {
            System.out.println("Enter only these genres!");
        }
        Book book = new Book(name, year, author, genre, user.getId());
        boolean check = addBook(book);
        if (check) {
            System.out.println("Added book");
        } else {
            System.out.println("Error");
        }
    }

    private boolean addBook(Book book) {
        try (Connection connection = DriverManager.getConnection(Constant.URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert  into books(name, year, author, genre, book_id) values(?, ?, ?, ?, ?)");
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getYear());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getGenre());
            preparedStatement.setLong(5, book.getBook_id());
            preparedStatement.execute();
            return preparedStatement.getUpdateCount() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void delete(User user) {
        List<Book> books = getBooks(user);
        assert books != null;
        if (books.size() == 0){
            System.out.println("You don't have any books");
            return;
        }
        int[] numbers = new int[books.size()];
        for (int i = 0; i < books.size(); i++) {
            System.out.println(i+1+". " + books.get(i).getName() + "; " + books.get(i).getAuthor());
            numbers[i] = i;
        }

        System.out.print("Choose one: ");
        input.nextLine();
        String opt = input.nextLine();
        int intValue;
        try {
            intValue = Integer.parseInt(opt);
        }catch (Exception e){
            System.out.println("It isn't numeric");
            return;
        }
        if (intValue > books.size() || intValue < 1) {
            System.out.println("Enter only these numbers!");
            return;
        }
        boolean check = removeBook(books.get(numbers[intValue - 1]).getId());
        if (check){
            System.out.println("Deleted successfully");
        }
        else {
            System.out.println("Error");
        }
    }

    private boolean removeBook(Long id){
        try (Connection connection = DriverManager.getConnection(Constant.URL)){
            PreparedStatement preparedStatement = connection.prepareStatement("delete from books where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            return preparedStatement.getUpdateCount() > 0;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void edit(User user) {
        List<Book> books = getBooks(user);
        assert books != null;
        if (books.size() == 0){
            System.out.println("You don't have any books");
            return;
        }
        int[] numbers = new int[books.size()];
        for (int i = 0; i < books.size(); i++) {
            System.out.println(i+1+". " + books.get(i).getName() + "; " + books.get(i).getAuthor());
            numbers[i] = i;
        }

        System.out.print("Choose one: ");
        input.nextLine();
        String opt = input.nextLine();
        int intValue;
        try {
            intValue = Integer.parseInt(opt);
        }catch (Exception e){
            System.out.println("It isn't numeric");
            return;
        }
        if (intValue > books.size() || intValue < 1) {
            System.out.println("Enter only these numbers!");
            return;
        }
        System.out.println();
        System.out.print("Name: ");
        String name = input.nextLine();
        if (name.equals("")) {
            System.out.println("Enter book's name");
            return;
        }
        System.out.print("Year: ");
        String year = input.nextLine();
        if (year.equals("")) {
            System.out.println("Enter book's year");
            return;
        }

        System.out.print("Author: ");
        String author = input.nextLine();
        if (author.equals("")) {
            System.out.println("Enter book's author");
            return;
        }

        BookGenre bookGenre = BookGenre.SCIENTIFIC;
        bookGenre.total();
        System.out.print("Genre: ");
        String genre = input.nextLine();
        if (genre.equals("1")) {
            genre = BookGenre.SCIENTIFIC.getGenre();
        } else if (genre.equals("2")) {
            genre = BookGenre.BUSINESS.getGenre();
        } else if (genre.equals("3")) {
            genre = BookGenre.NOVEL.getGenre();
        } else {
            System.out.println("Enter only these genres!");
        }
        Book book = new Book(name, year, author, genre, user.getId());
        boolean check = updateBook(book, books.get(numbers[intValue - 1]).getId());
        if (check){
            System.out.println("Edited successfully");
        }
        else {
            System.out.println("Error");
        }
    }

    private boolean updateBook(Book book, Long id){
        try(Connection connection = DriverManager.getConnection(Constant.URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement("update books set name = ?, year = ?, author = ?, genre = ? where id = ?");
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getYear());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getGenre());
            preparedStatement.setLong(5, id);
            preparedStatement.execute();
            return preparedStatement.getUpdateCount() > 0;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void show(User user) {
        List<Book> books = getBooks(user);
        assert books != null;
        if (books.size() == 0){
            System.out.println("You don't have any books");
            return;
        }
        for (int i = 0; i < books.size(); i++) {
            System.out.println(i+1+". " + books.get(i).getName() + "; " + books.get(i).getAuthor());
        }
    }
    private List<Book> getBooks(User user){
        try (Connection connection = DriverManager.getConnection(Constant.URL)){
            PreparedStatement preparedStatement = connection.prepareStatement("select * from books where book_id = ?");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()){
                Book book = new Book(resultSet.getLong("id"),resultSet.getString("name"), resultSet.getString("year"), resultSet.getString("author"), resultSet.getString("genre"), resultSet.getLong("book_id"));
                books.add(book);
            }
            return books;

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
