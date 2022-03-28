package src.main.java.com.jwa.app;

import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

public interface DAOinterface<T, ID> {
    Optional<T> find(ID id) throws SQLException;
    List<T> findAll() throws SQLException;
    boolean save(T o) throws SQLException;
    boolean update(T o) throws SQLException;
    boolean delete(T o) throws SQLException;
}
