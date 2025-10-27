package Repository;

import java.sql.SQLException;

public interface crudRepository {

    <List> void getAll() throws SQLException;



}
