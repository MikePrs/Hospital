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
        JPanel p = new JPanel();
        JPanel pp = new JPanel();
        JLabel l1, l11, l2, l3, l3_5, l4, l5, l6, l7, l8, l9;
        p.setLayout(new GridLayout(10, 3));
        JLabel id = new JLabel(rs.getString("id"));
        JLabel name = new JLabel(rs.getString("name"));
        JLabel last = new JLabel(rs.getString("lastname"));
        JLabel age = new JLabel(rs.getString("age"));
        JLabel sex = new JLabel(rs.getString("sex"));
        JLabel fatherN = new JLabel(rs.getString("fatherName"));
        JLabel occupation = new JLabel(rs.getString("occupation"));
        JLabel enter = new JLabel(rs.getString("enterP"));
        JLabel exit = new JLabel(rs.getString("exitP"));

        l1 = new JLabel("          Patient");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l11 = new JLabel("Profile        ");
        l11.setForeground(Color.blue);
        l11.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Name");
        l3 = new JLabel("Last Name");
        l3_5 = new JLabel("id");
        l4 = new JLabel("age");
        l5 = new JLabel("sex");
        l6 = new JLabel("fathers name");
        l7 = new JLabel("occupation");
        l8 = new JLabel("enter date ");
        l9 = new JLabel("exit date ");

        p.add(l1);
        p.add(l11);
        p.add(l2);
        p.add(name);
        p.add(l3);
        p.add(last);
        p.add(l3_5);
        p.add(id);
        p.add(l4);
        p.add(age);
        p.add(l5);
        p.add(sex);
        p.add(l6);
        p.add(fatherN);
        p.add(l7);
        p.add(occupation);
        p.add(l8);
        p.add(enter);
        p.add(l9);
        p.add(exit);

        pp.add(p, BorderLayout.CENTER);

        f.add(pp, BorderLayout.CENTER);
        f.setSize(300, 400);
        f.setVisible(true);

    }

    public void ProfileD(ResultSet rs) throws SQLException {

        JFrame f = new JFrame("User Profile");
        JPanel p = new JPanel();
        JPanel pp = new JPanel();
        JLabel l1, l11, l2, l3, l3_5, l4, l5, l6, l7, l8, l9;
        p.setLayout(new GridLayout(13, 3));
        
        JLabel id = new JLabel(rs.getString("iddoctor"));
        JLabel name = new JLabel(rs.getString("name"));
        JLabel last = new JLabel(rs.getString("lastname"));
        JLabel age = new JLabel(rs.getString("age"));
        JLabel sex = new JLabel(rs.getString("sex"));
        JLabel fatherN = new JLabel(rs.getString("fathersName"));
        JLabel occupation = new JLabel(rs.getString("startDate"));
        JLabel enter = new JLabel(rs.getString("specialty"));

        l1 = new JLabel("          Doctor");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l11 = new JLabel("Profile        ");
        l11.setForeground(Color.blue);
        l11.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Name");
        l3 = new JLabel("Last Name");
        l3_5 = new JLabel("id");
        l4 = new JLabel("age");
        l5 = new JLabel("sex");
        l6 = new JLabel("fathers name");
        l7 = new JLabel("occupation");
        l8 = new JLabel("start date ");
  

        p.add(l1);
        p.add(l11);
        p.add(l2);
        p.add(name);
        p.add(l3);
        p.add(last);
        p.add(l3_5);
        p.add(id);
        p.add(l4);
        p.add(age);
        p.add(l5);
        p.add(sex);
        p.add(l6);
        p.add(fatherN);
        p.add(l7);
        p.add(occupation);
        p.add(l8);
        p.add(enter);

       // p.add(exit);

        pp.add(p, BorderLayout.CENTER);

        f.add(pp, BorderLayout.CENTER);
        f.setSize(300, 400);
        f.setVisible(true);

    }
}
