import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class UserServices {
    Scanner input;
    BookWindow bookWindow;

    public UserServices(Scanner input) {
        this.input = input;
        bookWindow = new BookWindow(input);
    }

    public void registration() {
        System.out.println();
        System.out.print("Username: ");
        String username = input.next();
        System.out.print("Password: ");
        String password = input.next();
        System.out.print("Confirm password: ");
        String confirmPassword = input.next();
        if (!password.equals(confirmPassword)) {
            System.out.println("Password mismatch");
            return;
        }
        User user = new User(username, password);
        boolean check = addUser(user);
        if (!check) {
            System.out.println("Error");
        } else {
            System.out.println("Registration finished successfully");
        }
    }

    private boolean addUser(User user) {
        try (Connection connection = DriverManager.getConnection(Constant.URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users (username, password) values (?, ?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
            return preparedStatement.getUpdateCount() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void login() {
        System.out.println();
        System.out.print("Username: ");
        String username = input.next();
        System.out.print("Password: ");
        String password = input.next();
        User user = new User(username, password);
        Long id = checkUser(user);
        if (id == -1L) {
            System.out.println("Error");
        } else {
            user.setId(id);
            bookWindow.start(user);
        }
    }

    private Long checkUser(User user) {
        try (Connection connection = DriverManager.getConnection(Constant.URL)) {
            String sql = "select * from users";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("username").equals(user.getUsername()) && resultSet.getString("password").equals(user.getPassword())) {
                    return resultSet.getLong("id");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1L;
    }


}
