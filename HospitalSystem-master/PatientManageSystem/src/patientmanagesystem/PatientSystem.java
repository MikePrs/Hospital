/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientmanagesystem;

import java.io.IOException;
import java.sql.*;

/**
 *
 * @author mike_prs
 */
public class PatientSystem {

    public static void main(String[] args) throws SQLException, IOException {
        DataBase db = DataBase.getInstance();
        View view = new View(db);
        Control control = new Control(db, view);
    }

}
