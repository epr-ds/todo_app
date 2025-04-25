package view;

import model.Todo;
import java.util.List;
import java.util.Scanner;

public class TodoView 
{
    private final Scanner scanner;

    public TodoView() {
        this.scanner = new Scanner(System.in);
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void displayMenu() {
        System.out.println("\nTodo App Console");
        System.out.println("1. List all todos");
        System.out.println("2. Add new todo");
        System.out.println("3. Update todo");
        System.out.println("4. Delete todo");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    public void displayTodos(List<Todo> todos) {
        System.out.println("\nAll Todos:");
        if (todos.isEmpty()) {
            System.out.println("No todos found.");
        } else {
            todos.forEach(todo -> System.out.printf("%d. %s - %s (%s)%n",
                    todo.getId(),
                    todo.getTitle(),
                    todo.getDescription(),
                    todo.isCompleted() ? "Completed" : "Pending"));
        }
    }

    public Todo getNewTodoDetails() {
        System.out.println("\nAdd New Todo");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setCompleted(false);
        return todo;
    }

    public int getTodoIdForUpdate() {
        System.out.println("\nUpdate Todo");
        System.out.print("Enter todo ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return id;
    }

    public void updateTodoDetails(Todo todo) {
        System.out.print("Enter new title (leave blank to keep current): ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) {
            todo.setTitle(title);
        }

        System.out.print("Enter new description (leave blank to keep current): ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) {
            todo.setDescription(description);
        }

        System.out.print("Mark as completed? (y/n): ");
        String completed = scanner.nextLine();
        if (completed.equalsIgnoreCase("y")) {
            todo.setCompleted(true);
        }
    }

    public int getTodoIdForDelete() {
        System.out.println("\nDelete Todo");
        System.out.print("Enter todo ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return id;
    }

    public void showSuccess(String message) {
        System.out.println("\nSUCCESS: " + message);
    }

    public void showError(String message) {
        System.err.println("\nERROR: " + message);
    }

    public void showMessage(String message) {
        System.out.println("\n" + message);
    }

    public void displaySortOptions() {
        System.out.println("\nSort by:");
        System.out.println("1. ID (default)");
        System.out.println("2. Title (A-Z)");
        System.out.println("3. Title (Z-A)");
        System.out.println("4. Creation Date (newest first)");
        System.out.println("5. Creation Date (oldest first)");
        System.out.println("6. Completion Status (completed first)");
        System.out.println("7. Completion Status (pending first)");
        System.out.print("Choose sorting option: ");
    }

    public int getSortChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 1; // Default to ID sort
        }
    }

    public void displayTodos(List<Todo> todos, String sortDescription) {
        System.out.println("\nAll Todos (Sorted by: " + sortDescription + ")");
        if (todos.isEmpty()) {
            System.out.println("No todos found.");
        } else {
            todos.forEach(todo -> System.out.printf("%d. %s - %s (%s) [Created: %s]%n",
                    todo.getId(),
                    todo.getTitle(),
                    todo.getDescription(),
                    todo.isCompleted() ? "Completed" : "Pending",
                    todo.getCreatedAt()));
        }
    }
}