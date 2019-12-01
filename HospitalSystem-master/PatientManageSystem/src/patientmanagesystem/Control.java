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
public class Control implements Control_Interface {

    private DataBase base;
    private View view;
    public Connection conn;
    public Connection conn1;
    Statement stmt = null;

    public Control(DataBase base, View view) {
        this.base = base;
        this.view = view;
    }

    public Control() {
    }

    @Override
    public void CreatePatient(Patient patient, DataBase db) throws SQLException {
        String sql = "INSERT INTO "
                + " patient(id,name,lastname,password,age,sex,fatherName,occupation,enterP,exitP) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?) ";

        try (Connection conn = db.initConnection();
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

    @Override
    public void DeletePatient(int id, DataBase db) throws SQLException {
        String sql = "DELETE FROM patient WHERE  id = ? ";
        try (Connection conn = db.initConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Record deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void PrintAllPatients(DataBase db) throws SQLException {
        Connection conn = db.initConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT id,name,lastname FROM patient");
        ResultSetMetaData rsmd = rs.getMetaData();

        int columnsNumber = rsmd.getColumnCount();

        rsmd = rs.getMetaData();
        columnsNumber = rsmd.getColumnCount();

        // Iterate through the data in the result set and display it. 
        while (rs.next()) {
            //Print one row          
            for (int i = 1; i <= columnsNumber; i++) {

                System.out.print(rs.getString(i) + " "); //Print one element of a row

            }

            System.out.println();//Move to the next line to print the next row.           

        }
    }

    @Override
    public void CreateDoctor(Doctor doc, DataBase db) {
        String sql = "INSERT INTO "
                + " doctor(iddoctor,name,lastname,password,age,sex,fathersName,startDate,specialty) "
                + " VALUES(?,?,?,?,?,?,?,?,?) ";

        try (Connection conn = db.initConnection();
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

    @Override
    public void CreateAppointment(int id_p , int  id_d , DataBase db , String appointment) {
        String sql = "INSERT INTO "
                + " appointment ( id_app , id_doctor , id_patient , appointment ) "
                + " VALUES(?,?,?,?) ";

        try (Connection conn = db.initConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
           
            pstmt.setInt(1, 1);
            pstmt.setInt(2, id_d);
            pstmt.setInt(3, id_p);
            pstmt.setString(4,appointment);
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}