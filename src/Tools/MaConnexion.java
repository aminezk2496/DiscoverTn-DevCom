/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

/**
 *
 * @author Amine
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnexion {
    private String url="jdbc:mysql://localhost:3306/discovertn";
    private String user="root";
    private String pwd ="";
    private Connection cnx;
    private static MaConnexion mc;

    private MaConnexion(){
        try{
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connected Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static MaConnexion getInstance(){
        if(mc == null)
            mc = new MaConnexion();
        return mc;
    }
    public Connection getCnx(){
        return cnx;
    }
}