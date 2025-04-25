package service;

import model.Todo;
import repository.TodoRepository;
import java.util.Comparator;
import java.util.List;
import java.sql.SQLException;

public class TodoService 
{
    private final TodoRepository repository;

    public TodoService() throws SQLException {
        this.repository = new TodoRepository();
    }

    public List<Todo> getAllTodos() throws SQLException {
        try {
            return repository.findAll();
        } catch (SQLException e) {
            throw new SQLException("Database operation failed", e);
        }
    }

    public Todo getTodoById(int id) throws SQLException {
        try {
            return repository.findById(id);
        } catch (SQLException e) {
            throw new SQLException("Could not find todo with ID: " + id, e);
        }
    }

    public void createTodo(Todo todo) throws SQLException {
        try {
            repository.save(todo);
        } catch (SQLException e) {
            throw new SQLException("Could not create todo", e);
        }
    }

    public void updateTodo(Todo todo) throws SQLException {
        try {
            repository.update(todo);
        } catch (SQLException e) {
            throw new SQLException("Could not update todo with ID: " + todo.getId(), e);
        }
    }

    public void deleteTodo(int id) throws SQLException {
        try {
            repository.delete(id);
        } catch (SQLException e) {
            throw new SQLException("Could not delete todo with ID: " + id, e);
        }
    }

     public List<Todo> getAllTodosSorted(int sortOption) throws SQLException {
        try {
            List<Todo> todos = repository.findAll();
            return sortTodos(todos, sortOption);
        } catch (SQLException e) {
            throw new SQLException("Database operation failed", e);
        }
    }

    private List<Todo> sortTodos(List<Todo> todos, int sortOption) {
        switch (sortOption) {
            case 2:
                todos.sort(Comparator.comparing(Todo::getTitle));
                break;
            case 3:
                todos.sort(Comparator.comparing(Todo::getTitle).reversed());
                break;
            case 4:
                todos.sort(Comparator.comparing(Todo::getCreatedAt).reversed());
                break;
            case 5:
                todos.sort(Comparator.comparing(Todo::getCreatedAt));
                break;
            case 6:
                todos.sort(Comparator.comparing(Todo::isCompleted).reversed());
                break;
            case 7:
                todos.sort(Comparator.comparing(Todo::isCompleted));
                break;
            case 1:
            default:
                todos.sort(Comparator.comparing(Todo::getId));
                break;
        }
        return todos;
    }

    public String getSortDescription(int sortOption) {
        switch (sortOption) {
            case 2: return "Title (A-Z)";
            case 3: return "Title (Z-A)";
            case 4: return "Creation Date (newest first)";
            case 5: return "Creation Date (oldest first)";
            case 6: return "Completion Status (completed first)";
            case 7: return "Completion Status (pending first)";
            case 1:
            default: return "ID";
        }
    }
}