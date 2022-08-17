package product;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import database.DataMethods;

public class Product implements DataMethods{
    private Statement statement;
    // private Connection connection;
    @Override
    public void init(Connection connection, Statement statement) {
        // TODO Auto-generated method stub
        this.statement = statement;
        // this.connection = connection;
    }

    @Override
    public void management() {
        try{
            int index = 1;
            ResultSet resultSet = this.statement.executeQuery("select * from product");
            System.out.printf("%5s %15s %20s %10s\n","index","product_id","name","stock");
            while(resultSet.next()){
                String productId = resultSet.getString("product_id");
                String name = resultSet.getString("name");
                String stock = resultSet.getString("stock");
                System.out.format("%5s %15s %20s %10s", index++,productId,name,stock);
                System.out.println();
            }
            System.out.println("key(1: add, 2: fix, 3: delete, 4: Exit)");
            System.out.print("key(number): ");
        }catch(Exception e) {e.printStackTrace();}
    }

    @Override
    public void insert() {
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String name = null;
            String stock = null;
            System.out.print("name : ");
            name = bufferedReader.readLine();
            System.out.print("stock : ");
            stock = bufferedReader.readLine();
            statement.executeUpdate("insert into product(name,stock) values('"+name+"','"+stock+"')");
        } catch(Exception e) {
            e.printStackTrace();
        } finally{
            management();
        }
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String productId;
            System.out.print("삭제하고자 하는 product_id : ");
            productId = bufferedReader.readLine();
            statement.executeUpdate("delete from product where product_id="+productId);
        }catch(Exception e){
            System.out.println("해당 레코드가 존재하지 않습니다.");
        }finally{
            management();
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("변경하고자 하는 product_id: ");
            String productId = bufferedReader.readLine();
            System.out.print("변경하고자 하는 열 :");
            String changeColumn = bufferedReader.readLine();
            System.out.print("변경 : ");
            String changeProperty = bufferedReader.readLine();
            statement.executeUpdate("Update product set "+changeColumn+"='"+changeProperty+"' where product_id="+productId);
        }catch(Exception e) {System.out.println("업데이트 실패");}finally{
            management();
        }
    }

    @Override
    public String select(String key) {
        switch(key){
            case "1":{insert(); break;}
            case "2":{update(); break;}
            case "3":{delete(); break;}
            case "4":{return "exit";}
        }
        return "noneExit";
    }
    
}
