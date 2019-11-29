package patientmanagesystem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mike_prs
 */
public class User {

    private int id;
    private String name;
    private String lastname;
    private String sex;
    private String psw;

    private int age;
    private String fathersName;

    public User() {
    }

    public User(String name, String lastname, String sex,String psw, int age, String fathersName) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.psw = psw;
        this.sex = sex;
        this.age = age;
        this.fathersName = fathersName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }
}
