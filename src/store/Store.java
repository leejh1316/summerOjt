package store;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import database.DataMethods;

public class Store {
    private DataMethods dataMethods;
    private Connection connection;
    private Statement statement;
    public Store(Connection connection, Statement statement) throws SQLException{
        this.connection = connection;
        this.statement = statement;
    };
    public void setDataManagement(DataMethods dataMethods){
        this.dataMethods = dataMethods;
    }
    public void management(){
        this.dataMethods.init(this.connection,this.statement);
        this.dataMethods.management();
    }
    public String select(String key){
        return this.dataMethods.select(key);
    }
    public void close() throws SQLException{
        this.statement.close();
        this.connection.close();
    }
}
