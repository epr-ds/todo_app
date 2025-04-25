package controller;

import model.Todo;
import service.TodoService;
import view.TodoView;
import java.sql.SQLException;
import java.util.List;

public class TodoController {
    private final TodoService service;
    private final TodoView view;
    private int currentSortOption = 1; // Default sort by ID

    public TodoController() throws SQLException {
        this.service = new TodoService();
        this.view = new TodoView();
    }

    public void run() {
        while (true) {
            try {
                view.displayMenu();
                int choice = Integer.parseInt(view.getInput());
                
                switch (choice) {
                    case 1:
                        listTodos();
                        break;
                    case 2:
                        addTodo();
                        break;
                    case 3:
                        updateTodo();
                        break;
                    case 4:
                        deleteTodo();
                        break;
                    case 5:
                        view.showMessage("Exiting...");
                        return;
                    default:
                        view.showError("Invalid option!");
                }
            } catch (NumberFormatException e) {
                view.showError("Please enter a valid number");
            } catch (Exception e) {
                view.showError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private void listTodos() {
        try {
            view.displaySortOptions();
            currentSortOption = view.getSortChoice();
            List<Todo> todos = service.getAllTodosSorted(currentSortOption);
            String sortDescription = service.getSortDescription(currentSortOption);
            view.displayTodos(todos, sortDescription);
        } catch (Exception e) {
            view.showError("Failed to retrieve todos: " + e.getMessage());
        }
    }

    private void addTodo() {
        try {
            Todo todo = view.getNewTodoDetails();
            service.createTodo(todo);
            view.showSuccess("Todo added successfully!");
        } catch (Exception e) {
            view.showError("Failed to add todo: " + e.getMessage());
        }
    }

    private void updateTodo() {
        try {
            int id = view.getTodoIdForUpdate();
            Todo todo = service.getTodoById(id);
            if (todo == null) {
                view.showError("Todo not found!");
                return;
            }
            view.updateTodoDetails(todo);
            service.updateTodo(todo);
            view.showSuccess("Todo updated successfully!");
        } catch (Exception e) {
            view.showError("Failed to update todo: " + e.getMessage());
        }
    }

    private void deleteTodo() {
        try {
            int id = view.getTodoIdForDelete();
            service.deleteTodo(id);
            view.showSuccess("Todo deleted successfully!");
        } catch (Exception e) {
            view.showError("Failed to delete todo: " + e.getMessage());
        }
    }
}