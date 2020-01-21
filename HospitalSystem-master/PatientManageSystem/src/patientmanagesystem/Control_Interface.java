package patientmanagesystem;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author mikep
 */
public interface Control_Interface {

    void CreatePatient(Patient patient, DataBase db) throws SQLException;

    void CreateDoctor(Doctor doc, DataBase db) throws SQLException;

    void DeleteAppointmet(int id, DataBase db) throws SQLException;

    void DeleteDoctor(int id, DataBase db) throws SQLException;
    
    void DeletePatient(int id, DataBase db) throws SQLException;


    ResultSet PrintAllDoctors(DataBase db) throws SQLException;

    void CreateAppointment(int id_d,int id_p ,DataBase db,String p_name ,String appointment) throws SQLException;

}
