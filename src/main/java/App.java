import java.sql.SQLException;
import controller.TodoController;

public class App {
    public static void main(String[] args) {
        try {
            TodoController controller = new TodoController();
            controller.run();
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Application error: " + e.getMessage());
            System.exit(1);
        }
    }
}