package user;

import java.sql.Statement;

import database.DataMethods;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;

public class User implements DataMethods{
    private Statement statement;
    private Connection connection;
    @Override
    public void init(Connection connection, Statement statement) {
        try{
            this.connection = connection;
            this.statement = statement;
        }catch(Exception e){e.printStackTrace();}
    }

    @Override
    public void management() {
        try{
            int index = 1;
            ResultSet resultSet = this.statement.executeQuery("select * from user");
            System.out.printf("%10s %10s %20s %20s %15s\n","index","user_id","id","password","type");
            while(resultSet.next()){
                String userID = resultSet.getString("user_id");
                String id = resultSet.getString("id");
                String password = resultSet.getString("password");
                String type = resultSet.getString("type");
                System.out.format("%10s %10s %20s %20s %15s", index++,userID,id,password,type);
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
            String id = null;
            String password = null;
            String type = null;
            System.out.print("id : ");
            id = bufferedReader.readLine();
            System.out.print("password : ");
            password = bufferedReader.readLine();
            do{
                System.out.print("type(admin/user): ");
                type = bufferedReader.readLine();
            }while(!(!type.equals("admin") ^ !type.equals("user")));
            statement.executeUpdate("insert into user(id,password,type) values('"+id+"','"+password+"','"+type+"')");
        } catch(Exception e) {
            System.out.println("존재하는 아이디 입니다.");
        } finally{
            management();
        }
    }

    @Override
    public void delete(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String userId;
            System.out.print("삭제하고자 하는 user_id : ");
            userId = bufferedReader.readLine();
            statement.executeUpdate("delete from user where user_id="+userId);
        }catch(Exception e){
            System.out.println("해당 레코드가 존재하지 않습니다.");
        }finally{
            management();
        }
    }

    @Override
    public void update() {
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("변경하고자 하는 UserID: ");
            String userId = bufferedReader.readLine();
            System.out.print("변경하고자 하는 열 :");
            String changeColumn = bufferedReader.readLine();
            String changeProperty;
            if(changeColumn.equals("type")){
                do{
                    System.out.print("변경(admin/user): ");
                    changeProperty = bufferedReader.readLine();
                }while(!(!changeProperty.equals("admin") ^ !changeProperty.equals("user")));
            }else{
                System.out.print("변경 : ");
                changeProperty = bufferedReader.readLine();
            }
            statement.executeUpdate("Update user set "+changeColumn+"='"+changeProperty+"' where user_id="+userId);
        }catch(Exception e) {e.printStackTrace(); System.out.println("업데이트 실패");}finally{
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
