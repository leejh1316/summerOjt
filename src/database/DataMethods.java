package database;

import java.sql.Connection;
import java.sql.Statement;

public interface DataMethods {
    void init(Connection connection,Statement statement);
    void management();
    void insert();
    void delete();
    void update();
    String select(String key);
}
