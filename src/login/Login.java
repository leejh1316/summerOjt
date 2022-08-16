package login;

import java.sql.Statement;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.SQLite;

public class Login extends SQLite{
    private Boolean loginState;
    private Statement statement;
    private String inputId;
    private String inputPassword;
    public Login() throws SQLException{
        connection();
        this.statement = super.getConnection().createStatement();
        this.loginState = false;
        this.inputId = null;
        this.inputPassword= null;
    }
    public void match() throws SQLException{
        ResultSet resultSet = this.statement.executeQuery("select id,password,type from user where id like '"+this.inputId+"'");
        if(resultSet.next()){
            do{
                String password = resultSet.getString("password");
                String type = resultSet.getString("type");
                if(!this.inputPassword.equals(password)){
                    System.out.println("비밀번호가 일치하지 않습니다.");
                }
                else if(!type.equals("admin")){
                    System.out.println("권한이 없는 계정입니다.");
                } else if(this.inputPassword.equals(password)&&type.equals("admin")){
                    System.out.println("로그인 성공");
                    this.loginState = true;
                }
            }while(resultSet.next());
        }else{
            System.out.println("아이디가 일치하지 않습니다.");
        }
    }
    @Override
    public Connection getConnection() {
        if(this.loginState){
            return super.getConnection();
        } else{
            System.out.println("DB 커넥션 넘겨주기 실패");
            return null;
        }
    }
    public Statement getStatement(){
        return this.statement;
    }
    public void login() throws SQLException,IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Console console = System.console();
        do{
            System.out.print("id : ");
            this.inputId = bufferedReader.readLine();
            char[] password = console.readPassword("password : ");
            this.inputPassword = new String(password);
            this.match();
        }while(!this.loginState);
    }
    public Boolean getLoginState(){
        return this.loginState;
    }
    public void logout(){
        this.loginState = false;
    }
}
