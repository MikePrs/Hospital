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
public class Patient extends User {
    
    private String occupation;
    private String enter; // enter hospital date 
    private String exit; // exit hospital date 
   
    /**
     *
     * @param name
     * @param lastname
     * @param psw
     * @param age
     * @param sex
     * @param fathersName
     * @param occupation
     * @param enter
     * @param exit
     */
    public Patient( String name, String lastname,String psw, int age, String sex,String fathersName, 
            String occupation, String enter, String exit) {

        super(name,lastname,sex,psw,age,fathersName);
        this.occupation = occupation;
        this.enter = enter;
        this.exit = exit;
    }
    public Patient(){}
    
    
    public String getOccupation() {
        return occupation;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    public String getEnter() {
        return enter;
    }
    public void setEnter(String enter) {
        this.enter = enter;
    }
    public String getExit() {
        return exit;
    }
    public void setExit(String exit) {
        this.exit = exit;
    }
}
