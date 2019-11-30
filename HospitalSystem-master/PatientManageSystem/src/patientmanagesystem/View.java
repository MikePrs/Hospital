/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientmanagesystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author mike_prs
 */
public class View extends JFrame {

    private Control control;
    JMenu menu, menu2;
    JButton home;
    JMenuItem i1, i2, i3;
    JLabel l = new JLabel("welcome");
    JFrame f = new JFrame("Hospital Managment System");
    JPanel p = new JPanel();
    JPanel pp = new JPanel();
    JMenuBar mb = new JMenuBar();
    String user_lb;
    ResultSet rs = null;
    boolean flag = false;

    public void View() {
    }

    View() {
        this.control = new Control();

        home = new JButton("HOME PAGE ");  // this should change 
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                HomePage();
            }
        });

        menu = new JMenu("LOGIN");
        i1 = new JMenuItem("PATIENT");
        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                LoginP();
            }
        });
        i2 = new JMenuItem("DOCTOR");
        i2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                LoginD();
            }
        });
        menu.add(i1);
        menu.add(i2);

        menu2 = new JMenu("REGISTER");
        i1 = new JMenuItem("NEW PATIENT");
        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                RegisterP();
            }
        });
        i2 = new JMenuItem("NEW DOCTOR");
        i2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                RegisterD();
            }
        });
        menu2.add(i1);
        menu2.add(i2);

        p.add(l, BorderLayout.NORTH);
        f.add(p, BorderLayout.CENTER);

        mb.add(home);
        mb.add(menu);
        mb.add(menu2);

        pp.add(p, BorderLayout.CENTER);

        f.add(pp, BorderLayout.CENTER);
        f.setJMenuBar(mb);
        f.setSize(700, 600);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {  // for closing
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        }
        );
    }

    private void HomePage() {
        p.add(l);
    }

    public void LoginP() {
        JLabel l1, l2, l3, l4;
        JTextField tf1, p1;
        JButton btn1;

        l1 = new JLabel("Login Patient Form");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Username");
        l3 = new JLabel("Password");
        tf1 = new JTextField();
        p1 = new JTextField();
        btn1 = new JButton("Login");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String sql = ("SELECT * FROM patient WHERE name=? and password=?  ");
                    DataBase db = new DataBase();
                    Connection con = db.getConnection();
                    PreparedStatement pst = null;

                    try {
                        pst = con.prepareStatement(sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    pst.setString(1, tf1.getText());
                    pst.setString(2, p1.getText());
                    rs = pst.executeQuery();

                    if (rs.next()) {// if credencials are correct wintdow opens 
                        user_lb = rs.getString("name") + " " + rs.getString("lastname");
                        flag = true;   // flag for the user profile button that apears after login
                        JOptionPane.showMessageDialog(null,
                                "Welcome mr " + rs.getString("name")
                                + " " + rs.getString("lastname") // geters from the query result 
                                + "\n id " + rs.getString("id")
                                + "\n date of your last enter in this hospital and exit \n"
                                + rs.getString("enterP")
                                + " -- " + rs.getString("exitP"));

                    } else {
                        JOptionPane.showMessageDialog(null, "username and password do not matched");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
                if (flag == true) {    // users profile button 
                    String user = user_lb;
                    JButton pf = new JButton(user);
                    pf.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                new Profile().ProfileP(rs); //  profile frame call 
                            } catch (SQLException ex) {
                                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    mb.add(pf);
                }
            }
        });
        l4 = new JLabel("           ");
        p.setLayout(new GridLayout(7, 2));
        p.add(l1);
        p.add(l2);
        p.add(tf1);
        p.add(l3);
        p.add(p1);
        p.add(l4);
        p.add(btn1);

    }

    public void LoginD() {
        JLabel l1, l2, l3, l4;
        JTextField tf1, p1;
        JButton btn1;

        l1 = new JLabel("Login Doctor Form");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Username");
        l3 = new JLabel("Password");
        l4 = new JLabel("           ");
        tf1 = new JTextField();
        p1 = new JTextField();
        btn1 = new JButton("Login");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sql = ("SELECT * FROM doctor WHERE name=? and password=?  ");
                    DataBase db = new DataBase();
                    Connection con = db.getConnection();
                    PreparedStatement pst = null;
                    
                    try {
                        pst = con.prepareStatement(sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    pst.setString(1, tf1.getText());
                    pst.setString(2, p1.getText());
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        user_lb = (" Dr " + rs.getString("name") + " " + rs.getString("lastname"));
                        flag = true;
                        JOptionPane.showMessageDialog(null, "Welcome mr " + rs.getString("name")
                                + " " + rs.getString("lastname")
                                + "\n id " + rs.getString("iddoctor")
                                + "\n start date \n"
                                + rs.getString("startDate")
                                + " \n your specialty : " + rs.getString("specialty"));
                    } else {
                        JOptionPane.showMessageDialog(null, "username and password do not matched");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (flag == true) {
                    String user = user_lb;
                    JButton pf = new JButton(user);
                    pf.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                new Profile().ProfileD(rs);
                            } catch (SQLException ex) {
                                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    mb.add(pf);
                }
            }
        });

        p.setLayout(new GridLayout(7, 2));

        p.add(l1);
        p.add(l2);
        p.add(tf1);
        p.add(l3);
        p.add(p1);
        p.add(l4);
        p.add(btn1);

    }

    public void RegisterP() {

        JLabel l1, l2, l3, l3_5, l4, l5, l6, l7, l8, l9, l10, l11, l12;
        JTextField tf1, tf2, tf2_5, tf3, tf4, tf5, tf6, tf7, tf8;
        JButton btn1;    // register button 

        l1 = new JLabel("       Register new");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l11 = new JLabel(" Patient Form       ");
        l11.setForeground(Color.blue);
        l11.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Name");
        l3 = new JLabel("Last Name");
        l3_5 = new JLabel("Password");
        l4 = new JLabel("age");
        l5 = new JLabel("sex");
        l6 = new JLabel("fathers name");
        l7 = new JLabel("occupation");
        l8 = new JLabel("enter date ");
        l9 = new JLabel("exit date ");
        l10 = new JLabel("          ");
        l12 = new JLabel("          ");

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf2_5 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();
        tf8 = new JTextField();

        btn1 = new JButton("Register Patient");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase base = new DataBase();   // geters from text field 
                Patient patient = new Patient(tf1.getText(), tf2.getText(), tf2_5.getText(), Integer.parseInt(tf3.getText()),
                        tf4.getText(), tf5.getText(), tf6.getText(), tf7.getText(), tf8.getText());
                try {
                    control.CreatePatient(patient, base);
                } catch (SQLException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        p.setLayout(new GridLayout(12, 2));

        p.add(l1);
        p.add(l11); // head label 
        p.add(l2);
        p.add(tf1);
        p.add(l3);
        p.add(tf2);
        p.add(l3_5);
        p.add(tf2_5);
        p.add(l4);
        p.add(tf3);
        p.add(l5);
        p.add(tf4);
        p.add(l6);
        p.add(tf5);
        p.add(l7);
        p.add(tf6);
        p.add(l8);
        p.add(tf7);
        p.add(l9);
        p.add(tf8);
        p.add(l10);
        p.add(l12);
        p.add(btn1); // register button 
    }

    public void RegisterD() {
        JLabel l1, l2, l3, l3_5, l4, l5, l6, l7, l8, l10, l11, l12;
        JTextField tf1, tf2, tf2_5, tf3, tf4, tf5, tf6, tf7;
        JButton btn1;  // login button 

        l1 = new JLabel("       Register new");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l11 = new JLabel(" Doctor Form       ");
        l11.setForeground(Color.blue);
        l11.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Name");
        l3 = new JLabel("Last Name");
        l3_5 = new JLabel("Password");
        l4 = new JLabel("age");
        l5 = new JLabel("sex");
        l6 = new JLabel("fathers name");
        l7 = new JLabel("start date ");
        l8 = new JLabel("specialty");
        l10 = new JLabel("          ");
        l12 = new JLabel("          ");

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf2_5 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();

        btn1 = new JButton("Register Doctor");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase base = new DataBase();
                Doctor doc = new Doctor(tf1.getText(), tf2.getText(), tf2_5.getText(), Integer.parseInt(tf3.getText()),
                        tf4.getText(), tf5.getText(), tf6.getText(), tf7.getText());
                control.CreateDoctor(doc, base);
            }
        });
        p.setLayout(new GridLayout(12, 2));

        p.add(l1);
        p.add(l11); // head label 
        p.add(l2);
        p.add(tf1);
        p.add(l3);
        p.add(tf2);
        p.add(l3_5);
        p.add(tf2_5);
        p.add(l4);
        p.add(tf3);
        p.add(l5);
        p.add(tf4);
        p.add(l6);
        p.add(tf5);
        p.add(l7);
        p.add(tf6);
        p.add(l8);
        p.add(tf7);
        p.add(l10);
        p.add(l12);
        p.add(btn1); // login button 
    }
}
