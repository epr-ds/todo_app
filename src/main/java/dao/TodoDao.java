package dao;

import model.Todo;
import java.util.List;
import java.sql.SQLException;

public interface TodoDao {
    List<Todo> findAll() throws SQLException;
    Todo findById(int id) throws SQLException;
    void save(Todo todo) throws SQLException;
    void update(Todo todo) throws SQLException;
    void delete(int id) throws SQLException;
}