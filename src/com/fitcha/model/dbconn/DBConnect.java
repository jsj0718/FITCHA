package com.fitcha.model.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static Connection conn = null;
    
    private DBConnect() {
        
    }
    
    public static Connection getInstance() {
        // 계정
        String user = SecretDB.getUser();
        // 비밀번호
        String password = SecretDB.getPassword();
        // url
        String url = SecretDB.getUrl();
        
        try {
            if (conn != null && !conn.isClosed()) {
                return conn;
            }
            // 드라이버 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("ojdbc.jar가 없습니다. (드라이버가 존재하지 않습니다.)");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("url, user, password 및 DB가 켜져있는지 확인하세요.");
            e.printStackTrace();
        }
            
        return conn;
    }
}
