/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientmanagesystem;

import java.sql.*;

/**
 * all calls in database
 * @author mike_prs
 */
public class Control implements Control_Interface {

    private DataBase base;
    private View view;
    public Connection conn;
    Statement stmt = null;

    /**
     *
     * @param base
     * @param view
     */
    public Control(DataBase base, View view) {
        this.base = base;
        this.view = view;
    }

    public Control() {
    }

    /**
     * called by register patient
     * creates a patient in database
     *
     * @param patient
     * @param db
     * @throws SQLException
     */
    @Override
    public void CreatePatient(Patient patient, DataBase db) throws SQLException {
        Connection conn = db.initConnection();

        String sql = "INSERT INTO "
                + " patient(id,name,lastname,password,age,sex,fatherName,occupation,enterP,exitP) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?) ";

        try (   
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, patient.getId());
            pstmt.setString(2, patient.getName());
            pstmt.setString(3, patient.getLastname());
            pstmt.setString(4, patient.getPsw());
            pstmt.setInt(5, patient.getAge());
            pstmt.setString(6, patient.getSex());
            pstmt.setString(7, patient.getFathersName());
            pstmt.setString(8, patient.getOccupation());
            pstmt.setString(9, patient.getEnter());
            pstmt.setString(10, patient.getExit());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * called from doctor and admin 
     * delete an appointment 
     * 
     * @param id
     * @param db
     * @throws SQLException
     */
    @Override
    public void DeleteAppointmet(int id, DataBase db) throws SQLException {
        String sql = "DELETE FROM appointment WHERE id_app = ? ";
        try (Connection conn = db.initConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Record deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * called from admin 
     * deletes a patient
     *
     * @param id
     * @param db
     * @throws SQLException
     */
    @Override
    public void DeletePatient(int id, DataBase db) throws SQLException {
        String sql = "DELETE FROM patient WHERE id = ? ";
        try (Connection conn = db.initConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Record deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * prints patients for doctors appointments
     *
     * @param db
     * @param id
     * @return
     * @throws SQLException
     */
    
    public ResultSet PrintAllPatients(DataBase db,int id) throws SQLException {
        Connection conn = db.initConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT id_app,id_patient,p_name,appointment FROM appointment WHERE id_doctor= "+id);
        ResultSetMetaData rsmd = rs.getMetaData();

        int columnsNumber = rsmd.getColumnCount();
        
        rsmd = rs.getMetaData();
        columnsNumber = rsmd.getColumnCount();
        
        return rs ;
    }

    /**
     * called from register button 
     * creates a doctor in database
     *
     * @param doc
     * @param db
     */
    @Override
    public void CreateDoctor(Doctor doc, DataBase db) {
        Connection conn = db.initConnection();
       
        String sql = "INSERT INTO "
                + " doctor(iddoctor,name,lastname,password,age,sex,fathersName,startDate,specialty) "
                + " VALUES(?,?,?,?,?,?,?,?,?) ";

        try (
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, doc.getId());
            pstmt.setString(2, doc.getName());
            pstmt.setString(3, doc.getLastname());
            pstmt.setString(4, doc.getPsw());
            pstmt.setInt(5, doc.getAge());
            pstmt.setString(6, doc.getSex());
            pstmt.setString(7, doc.getFathersName());
            pstmt.setString(8, doc.getStartD());
            pstmt.setString(9, doc.getSpecialty());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * selects all doctors from database
     *
     * @param db
     * @return
     * @throws SQLException
     */
    @Override
    public ResultSet PrintAllDoctors(DataBase db) throws SQLException {
        Connection conn = db.initConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT iddoctor,name,lastname,specialty FROM doctor");
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        rsmd = rs.getMetaData();
        columnsNumber = rsmd.getColumnCount();
       return rs;
    }

    /**
     * for admin 
     * deletes a doctor
     *
     * @param id
     * @param db
     */
    @Override
    public void DeleteDoctor(int id, DataBase db) {
        String sql = "DELETE FROM doctor WHERE  iddoctor = ? "; 
        try (Connection conn = db.initConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Record deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * creates an appointment 
     * takes doctors and patients id 
     * 
     *
     * @param id_d
     * @param id_p
     * @param db
     * @param p_name
     * @param appointment
     */
    @Override
    public void CreateAppointment(int id_d , int  id_p , DataBase db,String p_name , String appointment) {
        String sql = "INSERT INTO "
                + " appointment ( id_doctor , id_patient ,p_name, appointment ) "
                + " VALUES(?,?,?,?) ";

        try (Connection conn = db.initConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
           
            //pstmt.setInt(1, );
            pstmt.setInt(1, id_d);
            pstmt.setInt(2, id_p);
            pstmt.setString(3, p_name);
            pstmt.setString(4,appointment);
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}