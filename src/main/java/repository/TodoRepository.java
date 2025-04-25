package repository;

import dao.TodoDao;
import model.Todo;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoRepository implements TodoDao {
    private final Connection connection;

    public TodoRepository() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<Todo> findAll() throws SQLException {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT * FROM todos";
        
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                todos.add(mapResultSetToTodo(rs));
            }
            return todos;
        }
    }

    @Override
    public Todo findById(int id) throws SQLException {
        String sql = "SELECT * FROM todos WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToTodo(rs);
            }
            return null;
        }
    }

    @Override
    public void save(Todo todo) throws SQLException {
        String sql = "INSERT INTO todos (title, description, completed) VALUES (?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, todo.getTitle());
            pstmt.setString(2, todo.getDescription());
            pstmt.setBoolean(3, todo.isCompleted());
            pstmt.executeUpdate();
            
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                todo.setId(generatedKeys.getInt(1));
            }
        }
    }

    @Override
    public void update(Todo todo) throws SQLException {
        String sql = "UPDATE todos SET title = ?, description = ?, completed = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, todo.getTitle());
            pstmt.setString(2, todo.getDescription());
            pstmt.setBoolean(3, todo.isCompleted());
            pstmt.setInt(4, todo.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM todos WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    private Todo mapResultSetToTodo(ResultSet rs) throws SQLException {
        return new Todo(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getBoolean("completed"),
            rs.getTimestamp("created_at").toLocalDateTime()
        );
    }
}