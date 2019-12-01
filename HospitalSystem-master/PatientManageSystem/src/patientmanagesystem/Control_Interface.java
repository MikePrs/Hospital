package patientmanagesystem;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mikep
 */
public interface Control_Interface {

    void CreatePatient(Patient patient, DataBase db) throws SQLException;

    void CreateDoctor(Doctor doc, DataBase db) throws SQLException;

    void DeletePatient(int id, DataBase db) throws SQLException;

    void DeleteDoctor(int id, DataBase db) throws SQLException;

    void PrintAllPatients(DataBase db) throws SQLException;

    ResultSet PrintAllDoctors(DataBase db) throws SQLException;

    void CreateAppointment(int id_p,int id_d ,DataBase db,String appointment) throws SQLException;

}
