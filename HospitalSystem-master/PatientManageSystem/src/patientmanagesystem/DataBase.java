/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientmanagesystem;
import java.sql.*;
/**
 *
 * @author mike_prs
 */
public class DataBase {
    private static DataBase instance = null;
    public Connection conn ;
    private String url;

    DataBase() {
        this.initConnection();
    }

    /**
     *
     * @return
     */
    public Connection initConnection() {
        try {
            this.url = "jdbc:mysql://localhost:3306/hospitaldatabase?useSSL=false";
            this.conn = DriverManager.getConnection(url, "root","root");
            System.out.println("Connection established");

        } catch (Exception e) {
            System.out.println("Connection not established");
            System.err.println(e.getMessage());
        }
       return this.conn;

    }

    /**
     *
     * @return
     */
    public static DataBase getInstance() {
        if (instance != null) {
            System.out.println("This is a singleton object");
            return null;
        } else {
            System.out.println("Object created");
            return DataBase.instance = new DataBase();
        }

    }

    /**
     *
     * @return
     */
    public Connection getConnection() {
        return this.conn;
    }
}
