import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShowServices {
    public void byColumn(String column){
        System.out.println();
        List<String> columns = getColumn(column);
        if (columns == null){
            return;
        }
        for (int i = 0; i < columns.size(); i++) {
            System.out.println(i+1+". " + columns.get(i));
        }
    }
    private List<String> getColumn(String column){
        try (Connection connection = DriverManager.getConnection(Constant.URL)){
            PreparedStatement preparedStatement = connection.prepareStatement("select " + column + " from books");
            preparedStatement.execute();
            List<String> columns = new ArrayList<>();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                columns.add(resultSet.getString(column));
            }
            return columns;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
