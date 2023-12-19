package Util;

import org.h2.tools.RunScript;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//create active connection to database with singleton design pattern
//in-memory called h2database for the sql demos

public class ConnectionUtil {

    //in memory db
    private static String url = "jdbc:h2:./h2/db";

    private static String username = "sa";
    private static String password = "sa";
    private static Connection connection = null;
    public static Connection getConnection(){
        if(connection == null){
            try {
                connection = DriverManager.getConnection(url, username, password);
                resetTestDatabase();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }
    public static void resetTestDatabase(){
        if(connection == null){
            getConnection();
        }else {
            try {
                FileReader sqlReader = new FileReader("src/main/resources/SocialMedia.sql");
                RunScript.execute(connection, sqlReader);
            } catch (SQLException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
