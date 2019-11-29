/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientmanagesystem;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author mike_prs
 */
public class PatientSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        DataBase db = DataBase.getInstance();
        View view = new View();
        Control control = new Control(db, view);
        
       
        

        
        control.PrintAllPatients(db);
        control.PrintAllDoctors(db);
        
    }

}
