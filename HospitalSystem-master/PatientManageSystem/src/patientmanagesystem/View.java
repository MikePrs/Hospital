/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientmanagesystem;

import java.awt.*;
import static java.awt.Color.green;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;

/**
 *
 *
 * @author mike_prs
 */
public class View extends JFrame {

    private Control control;
    DataBase db;
    PanelAction ac;

    JMenu menu, menu2;
    JButton home, info, appointment, searchDoc;
    JMenuItem i1, i2, i3;
    ImageIcon hm = new ImageIcon("src\\Icons\\home.jpg");
    JLabel l = new JLabel(hm);
    JFrame f = new JFrame("Hospital Managment System");
    JPanel p = new JPanel(); // main panel 
    JPanel pp = new JPanel();
    JPanel leftP = new JPanel(); // side bar 
    JMenuBar mb = new JMenuBar(); // menu bar 
    String user_lb;
    ResultSet rs = null;
    boolean flag = false;
    Color blue = new Color(173, 226, 242);
    Color Lblue = new Color(191, 230, 242);
    JButton uts = new JButton("Admin");

    View() {
    }

    /**
     * main frame that contains side , menu bar and main panel
     *
     * @param db
     */
    public void View(DataBase db) {
    }

    View(DataBase db) throws IOException {
        this.db = db;
        ac = new PanelAction(db);
        this.control = new Control();

        info = new JButton("info");
        ImageIcon img = new ImageIcon("src\\Icons\\info.png");
        info.setIcon(img);
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hospital Manager Platform Version 1.0 \n "
                        + "by Tasos Kre & Mike Prs");
            }
        });
        appointment = new JButton("make appointment");  // side bar button 
        ImageIcon cal = new ImageIcon("src\\Icons\\year.png");
        appointment.setIcon(cal);
        appointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                p.add(ac.Appointment(flag)); //calls the appointment form from panel action 
            }                                    //(flag is to check if user is logged in) 
        });
        searchDoc = new JButton("search for doctor"); // side bar button 
        ImageIcon sr = new ImageIcon("src\\Icons\\search.png");
        searchDoc.setIcon(sr);
        searchDoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                try {
                    p.add(ac.SearchDoc()); // calls search doc from panel action 
                } catch (SQLException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        home = new JButton("HOME PAGE ");  // menu bar button menu bar object
        ImageIcon hm = new ImageIcon("src\\Icons\\home1.png");
        home.setIcon(hm);
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                p.add(ac.HomePage()); // home page from panel action 

            }
        });

        menu = new JMenu("LOGIN");   // login options menu bar onject 
        ImageIcon lg = new ImageIcon("src\\Icons\\unlock.png");
        menu.setIcon(lg);
        i1 = new JMenuItem("PATIENT"); // option 1 
        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                LoginP();
            }
        });
        i2 = new JMenuItem("DOCTOR"); // option 2 
        i2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                LoginD();
            }
        });
        menu.add(i1); // adding options to menu 
        menu.add(i2);

        menu2 = new JMenu("REGISTER"); // register options 
        ImageIcon rg = new ImageIcon("src\\Icons\\plus.png");
        menu2.setIcon(rg);
        i1 = new JMenuItem("NEW PATIENT"); // option 1
        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                p.add(ac.RegisterP()); // register patient call 
            }
        });
        i2 = new JMenuItem("NEW DOCTOR"); // option 2
        i2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                p.add(ac.RegisterD()); // register doctor call 
            }
        });
        menu2.add(i1);// adding options to menu
        menu2.add(i2);

        p.setBackground(Lblue);
        p.add(l, BorderLayout.NORTH); // main panel 
        f.add(p, BorderLayout.CENTER);// main frame 

        mb.add(home); // menu bar objects 
        mb.add(menu);
        mb.add(menu2);

        JLabel lb = new JLabel(); // plain for space 
        JLabel lb1 = new JLabel();
        JLabel lb0 = new JLabel();

        leftP.setBackground(blue); // side bar objects 
        leftP.setLayout(new GridLayout(8, 1));
        leftP.add(lb0);
        leftP.add(appointment);
        leftP.add(lb);
        leftP.add(searchDoc);
        leftP.add(lb1);
        leftP.add(info);

        pp.setBackground(Lblue);// positioning panel 
        pp.add(p, BorderLayout.CENTER); // main panel in center 
        pp.add(leftP, BorderLayout.WEST);// side bar to left 

        f.add(leftP, BorderLayout.WEST); // main frame objects 
        f.add(pp, BorderLayout.CENTER);
        f.setJMenuBar(mb);
        f.setBounds(100, 100, 750, 500);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {  // for closing
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        }
        );
    }

    /**
     * login patients form
     *
     */
    public void LoginP() {
        JLabel l1, l2, l3, l4;
        JTextField tf1;
        JPasswordField p1;
        JButton btn1;

        l1 = new JLabel("Login Patient Form");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20)); // labels 

        l2 = new JLabel("Name");
        l3 = new JLabel("Password");
        tf1 = new JTextField();
        p1 = new JPasswordField();
        p1.setEchoChar('*');
        btn1 = new JButton("Login");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String sql = ("SELECT * FROM patient WHERE name=? and password=?  "); // check credentials in database  
                    Connection con = db.getConnection();
                    PreparedStatement pst = null;

                    try {
                        pst = con.prepareStatement(sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    char[] passw = p1.getPassword();
                    pst.setString(1, tf1.getText());
                    pst.setString(2, String.valueOf(passw));
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

                        int option = JOptionPane.OK_CANCEL_OPTION;
                        if (option == 2) {
                            p.removeAll();
                            p.revalidate();
                            p.repaint();
                            p.add(ac.HomePage());
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "username and password do not matched");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
                try {
                    if (("1".equals(rs.getString("id"))) || ("14".equals(rs.getString("id")))) { // admins 
                        uts.setBackground(green);
                        mb.add(uts);// admins button 
                        uts.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                p.removeAll();
                                p.revalidate();
                                p.repaint();
                                p.add(ac.Admin()); // call to action panel for admin 
                            }
                        });
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (flag == true) {    // users profile button if logged in 
                    String user = user_lb;
                    JButton pf = new JButton(user);
                    ImageIcon us = new ImageIcon("src\\Icons\\user.png");
                    pf.setIcon(us);
                    pf.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                new PopUps().ProfileP(rs); // // shows frame with profile details
                            } catch (SQLException ex) {
                                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    JButton out = new JButton("LOGOUT");
                    ImageIcon ot = new ImageIcon("src\\Icons\\logout.png");
                    out.setIcon(ot);
                    out.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mb.remove(pf);// removing objects from menu bar 
                            mb.remove(out);
                            mb.remove(uts);
                            mb.revalidate();
                            mb.repaint();
                            flag = false; // user logged out

                        }
                    });
                    mb.add(Box.createHorizontalGlue());// add onjects in menu bar after login 
                    mb.add(pf);
                    mb.add(Box.createHorizontalGlue());
                    mb.add(out);
                    mb.revalidate();
                    mb.repaint();
                }
            }
        });
        l4 = new JLabel("           ");
        p.setBackground(Lblue); // main panel
        p.setLayout(new GridLayout(7, 2));
        p.add(l1);
        p.add(l2);
        p.add(tf1);
        p.add(l3);
        p.add(p1);
        p.add(l4);
        p.add(btn1);
    }

    /**
     * login doctors form
     *
     */
    public void LoginD() {
        JLabel l1, l2, l3, l4;
        JTextField tf1;
        JPasswordField p1;
        JButton btn1;

        l1 = new JLabel("Login Doctor Form");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Name");
        l3 = new JLabel("Password");
        l4 = new JLabel("           ");
        tf1 = new JTextField();
        p1 = new JPasswordField();
        p1.setEchoChar('*');
        btn1 = new JButton("Login");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sql = ("SELECT * FROM doctor WHERE name=? and password=?  "); // check credentials in database 
                    Connection con = db.getConnection();
                    PreparedStatement pst = null;

                    try {
                        pst = con.prepareStatement(sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    char[] passw = p1.getPassword();
                    pst.setString(1, tf1.getText());
                    pst.setString(2, String.valueOf(passw));
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

                        int option = JOptionPane.OK_CANCEL_OPTION;
                        if (option == 2) {
                            p.removeAll();
                            p.revalidate();
                            p.repaint();
                            p.add(ac.HomePage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "username and password do not matched");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (flag == true) { // if doctor is logged in 
                    JButton btnP = new JButton("Your Patients");
                    btnP.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            p.removeAll();
                            p.revalidate();
                            p.repaint();
                            try {
                                p.add(ac.CheckPatients(rs.getInt("iddoctor"))); //shows doctors patients 
                            } catch (SQLException ex) {
                                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    String user = user_lb;
                    JButton pf = new JButton(user);
                    ImageIcon us = new ImageIcon("src\\Icons\\user.png");
                    pf.setIcon(us);
                    pf.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                new PopUps().ProfileD(rs); // shows frame with profile details
                            } catch (SQLException ex) {
                                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    JButton out = new JButton("LOGOUT");
                    ImageIcon ot = new ImageIcon("src\\Icons\\logout.png");
                    out.setIcon(ot);
                    out.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mb.remove(pf); // remove objects from menu bar after logout 
                            mb.remove(out);
                            mb.remove(btnP);
                            mb.revalidate();
                            mb.repaint();
                            flag = false;
                        }
                    });
                    mb.add(btnP); // add object to menu bar after log in 
                    mb.add(Box.createHorizontalGlue());
                    mb.add(pf);
                    mb.add(Box.createHorizontalGlue());
                    mb.add(out);
                    mb.revalidate();
                    mb.repaint();
                }
            }
        });
        p.setLayout(new GridLayout(7, 2)); // main panel 
        p.setBackground(Lblue);
        p.add(l1);
        p.add(l2);
        p.add(tf1);
        p.add(l3);
        p.add(p1);
        p.add(l4);
        p.add(btn1);
    }
}
