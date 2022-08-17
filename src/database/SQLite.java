package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite {
    private Connection connection;
    public SQLite(){
        this.connection=null;
    }
    public void connection(){
        try{
            File file = new File("");
            Class.forName("org.sqlite.JDBC");
            String pathDB = file.getAbsolutePath().replaceAll(("\\\\"), "\\\\\\\\")+"\\\\db\\\\stock.db";
            this.connection = DriverManager.getConnection("jdbc:sqlite:"+pathDB);
        }catch(Exception e){e.printStackTrace();} 
    }
    public Connection getConnection(){
        return this.connection;
    }
    public void reconnetion() throws Exception{
        this.close();
        this.connection();
    }
    public void close() throws SQLException{
        this.connection.close();
    }
}
