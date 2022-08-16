package management;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import login.Login;
import product.Product;
import store.Store;
import user.User;

public class Management {
    public Management() throws SQLException, IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String homeKey = null;
        String storeKey = null;
        Login login = new Login();
        login.login();
        Store store = new Store(login.getConnection(), login.getStatement());
        do{
            if(!login.getLoginState()){
                login.login();
            }
            System.out.println("\n관리 프로그램");
            System.out.println("1: Product Management");
            System.out.println("2: User Management");
            System.out.println("3: Logout");
            System.out.println("4: Exit");
            System.out.print("key(number): ");
            homeKey = bufferedReader.readLine();
            switch(homeKey){
                case "1":{
                    store.setDataManagement(new Product());
                    store.management();
                    do{
                        storeKey = bufferedReader.readLine();
                        storeKey = store.select(storeKey);
                    }while(!storeKey.equals("exit"));
                    break;
                }
                case "2":{
                    store.setDataManagement(new User());
                    store.management();
                    do{
                        storeKey = bufferedReader.readLine();
                        storeKey = store.select(storeKey);
                    }while(!storeKey.equals("exit"));
                    break;
                }
                case "3":{
                    login.logout();
                    break;
                }
            }
        }while(!homeKey.equals("4")); 
        store.close();
    }
}
