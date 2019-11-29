/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientmanagesystem;

/**
 *
 * @author mike_prs
 */
public class Doctor extends User {
    private String startD;
    private int docId;
    private String specialty;
    public Doctor(String name, String lastname,String psw, int age , String sex, String fathersName,
            String startD,String specialty) {
        super(name, lastname, sex,psw, age, fathersName);
        this.docId=docId;
        this.startD= startD;
        this.specialty=specialty;
    }

    public String getStartD() {
        return startD;
    }

    public void setStartD(String startD) {
        this.startD = startD;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    
}
