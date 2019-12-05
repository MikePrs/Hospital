/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientmanagesystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mikep
 */
public class Profile extends JFrame {

    ResultSet rs;
    Control control = new Control();

    public void ProfileP(ResultSet rs) throws SQLException {

        JFrame f = new JFrame("User Profile");
        JPanel pl = new JPanel();
        JPanel pp = new JPanel();
        JLabel l1, l11, l2, l3, l3_5, l4, l5, l6, l7, l8, l9;
        pl.setLayout(new GridLayout(10, 3));
        JLabel id = new JLabel(rs.getString("id"));
        JLabel name = new JLabel(rs.getString("name"));
        JLabel last = new JLabel(rs.getString("lastname"));
        JLabel age = new JLabel(rs.getString("age"));
        JLabel sex = new JLabel(rs.getString("sex"));
        JLabel fatherN = new JLabel(rs.getString("fatherName"));
        JLabel occupation = new JLabel(rs.getString("occupation"));
        JLabel enter = new JLabel(rs.getString("enterP"));
        JLabel exit = new JLabel(rs.getString("exitP"));

        ImageIcon img = new ImageIcon("src\\Icons\\name.png");
        l1 = new JLabel(img);
        l1.setIcon(img);   
        l11 = new JLabel("Patient Profile");
        l11.setForeground(Color.blue);
        l11.setFont(new Font("Serif", Font.BOLD, 20));// head label

        l2 = new JLabel("Name");
        l3 = new JLabel("Last Name");
        l3_5 = new JLabel("id");
        l4 = new JLabel("age");
        l5 = new JLabel("sex");
        l6 = new JLabel("fathers name");
        l7 = new JLabel("occupation");
        l8 = new JLabel("enter date ");
        l9 = new JLabel("exit date ");

        pl.add(l1);
        pl.add(l11);
        pl.add(l2);
        pl.add(name);
        pl.add(l3);
        pl.add(last);
        pl.add(l3_5);
        pl.add(id);
        pl.add(l4);
        pl.add(age);
        pl.add(l5);
        pl.add(sex);
        pl.add(l6);
        pl.add(fatherN);
        pl.add(l7);
        pl.add(occupation);
        pl.add(l8);
        pl.add(enter);
        pl.add(l9);
        pl.add(exit);

        pp.add(pl, BorderLayout.CENTER);

        f.add(pp, BorderLayout.CENTER);
        f.setSize(300, 400);
        f.setVisible(true);

    }

    public void ProfileD(ResultSet rs) throws SQLException {

        JFrame f = new JFrame("User Profile");
        JPanel pl = new JPanel();
        JPanel pp = new JPanel();
        JLabel l1, l11, l2, l3, l3_5, l4, l5, l6, l7, l8;
        pl.setLayout(new GridLayout(13, 3));
        
        JLabel id = new JLabel(rs.getString("iddoctor"));
        JLabel name = new JLabel(rs.getString("name"));
        JLabel last = new JLabel(rs.getString("lastname"));
        JLabel age = new JLabel(rs.getString("age"));
        JLabel sex = new JLabel(rs.getString("sex"));
        JLabel fatherN = new JLabel(rs.getString("fathersName"));
        JLabel occupation = new JLabel(rs.getString("startDate"));
        JLabel enter = new JLabel(rs.getString("specialty"));

        ImageIcon img = new ImageIcon("src\\Icons\\name.png");
        l1 = new JLabel(img);
        l1.setIcon(img);
        l11 = new JLabel("Doctor Profile ");
        l11.setForeground(Color.blue);
        l11.setFont(new Font("Serif", Font.BOLD, 20));  // head label 

        l2 = new JLabel("Name");
        l3 = new JLabel("Last Name");
        l3_5 = new JLabel("id");
        l4 = new JLabel("age");
        l5 = new JLabel("sex");
        l6 = new JLabel("fathers name");
        l7 = new JLabel("occupation");
        l8 = new JLabel("start date ");
  
        pl.add(l1);
        pl.add(l11);
        pl.add(l2);
        pl.add(name);
        pl.add(l3);
        pl.add(last);
        pl.add(l3_5);
        pl.add(id);
        pl.add(l4);
        pl.add(age);
        pl.add(l5);
        pl.add(sex);
        pl.add(l6);
        pl.add(fatherN);
        pl.add(l7);
        pl.add(occupation);
        pl.add(l8);
        pl.add(enter);

        pp.add(pl, BorderLayout.CENTER);

        f.add(pp, BorderLayout.CENTER);
        f.setSize(300, 400);
        f.setVisible(true);
    }
}
