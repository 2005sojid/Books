import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class SearchServices {
    Scanner input;
    String username;

    public SearchServices(Scanner input) {
        this.input = input;
    }

    private List<List<Book>> getBooksByName(String name, String option) {
        try (Connection connection = DriverManager.getConnection(Constant.URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from books where " + option + " = ?");
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            List<List<Book>> booksList = new ArrayList<>();
            List<Book> books = new ArrayList<>();
            ResultSet resSet = preparedStatement.getResultSet();
            while (resSet.next()) {
                Book book = new Book(resSet.getLong("id"), resSet.getString("name"), resSet.getString("year"), resSet.getString("author"), resSet.getString("genre"), resSet.getLong("book_id"));
                books.add(book);
                booksList.add(books);
            }
            return booksList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    private String getUsernameByBook_id(Long book_id) {
        try (Connection connection = DriverManager.getConnection(Constant.URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");
            preparedStatement.setLong(1, book_id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                return resultSet.getString("username");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void showMethod(String column){
        System.out.println();
        String columnUpperCase = column.substring(0, 1).toUpperCase();
        System.out.print(columnUpperCase + column.substring(1).toLowerCase() + ": ");
        input.nextLine();
        String author = input.nextLine();
        List<List<Book>> author1 = getBooksByName(author, column);
        assert author1 != null;
        if (author1.isEmpty()){
            System.out.println("There aren't books with this " + column);
            return;
        }
        for (int i = 0; i < author1.size(); i++) {
            username = getUsernameByBook_id(author1.get(i).get(i).getBook_id());
            System.out.println(i + 1 + ". " + author1.get(i).get(i).getName() + "; " + author1.get(i).get(i).getAuthor() + "; " + author1.get(i).get(i).getYear() + "; " + author1.get(i).get(i).getGenre() + ": " + username);
        }
    }
    public void showByGenre(String column){
        System.out.println();
        BookGenre bookGenre = BookGenre.NOVEL;
        bookGenre.total();
        String columnUpperCase = column.substring(0, 1).toUpperCase();
        System.out.print(columnUpperCase + column.substring(1).toLowerCase() + ": ");
        input.nextLine();
        String genre = input.nextLine();
        int intValue;
        try {
            intValue = Integer.parseInt(genre);
        }
        catch (Exception e){
            System.out.println("Enter only these numbers!");
            return;
        }
        if (intValue == 1){
            genre = BookGenre.SCIENTIFIC.getGenre();
        }
        else if (intValue == 2){
            genre = BookGenre.BUSINESS.getGenre();
        }
        else if (intValue == 3){
            genre = BookGenre.NOVEL.getGenre();
        }
        List<List<Book>> author1 = getBooksByName(genre, column);
        assert author1 != null;
        if (author1.isEmpty()){
            System.out.println("There aren't books with this genre");
            return;
        }
        for (int i = 0; i < author1.size(); i++) {
            username = getUsernameByBook_id(author1.get(i).get(i).getBook_id());
            System.out.println(i + 1 + ". " + author1.get(i).get(i).getName() + "; " + author1.get(i).get(i).getAuthor() + "; " + author1.get(i).get(i).getYear() + "; " + author1.get(i).get(i).getGenre() + ": " + username);
        }
    }



}
