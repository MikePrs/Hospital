/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientmanagesystem;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mike_prs
 */
public class View extends JFrame {

    PanelAction ac = new PanelAction();
    private Control control;
    JMenu menu, menu2;
    JButton home, info, appointment, searchDoc;
    JMenuItem i1, i2, i3;
    JLabel l = new JLabel("welcome");
    JFrame f = new JFrame("Hospital Managment System");
    JPanel p = new JPanel();
    JPanel pp = new JPanel();
    JPanel leftP = new JPanel();
    JMenuBar mb = new JMenuBar();
    String user_lb;
    ResultSet rs = null;
    boolean flag = false;
    Color blue = new Color(173, 226, 242);
    Color Lblue = new Color(191, 230, 242);

    public void View() {
    }

    View() throws IOException {
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

        appointment = new JButton("make appointment");
        ImageIcon cal = new ImageIcon("src\\Icons\\year.png");
        appointment.setIcon(cal);
        appointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                p.add(ac.Appointment(flag));            
            }
        });
        searchDoc = new JButton("search for doctor");
        ImageIcon sr = new ImageIcon("src\\Icons\\search.png");
        searchDoc.setIcon(sr);
        searchDoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                try {
                    p.add(ac.SearchDoc());
                } catch (SQLException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        home = new JButton("HOME PAGE ");  // this should change 
        ImageIcon hm = new ImageIcon("src\\Icons\\home1.png");
        home.setIcon(hm);
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                p.add(ac.HomePage());

            }
        });

        menu = new JMenu("LOGIN");
        ImageIcon lg = new ImageIcon("src\\Icons\\unlock.png");
        menu.setIcon(lg);
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
        ImageIcon rg = new ImageIcon("src\\Icons\\plus.png");
        menu2.setIcon(rg);
        i1 = new JMenuItem("NEW PATIENT");
        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                p.add(ac.RegisterP());
            }
        });
        i2 = new JMenuItem("NEW DOCTOR");
        i2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.removeAll();
                p.revalidate();
                p.repaint();
                p.add(ac.RegisterD());
            }
        });
        menu2.add(i1);
        menu2.add(i2);

        p.setBackground(Lblue);
        p.add(l, BorderLayout.NORTH);
        f.add(p, BorderLayout.CENTER);

        mb.add(home);
        mb.add(menu);
        mb.add(menu2);

        JLabel lb = new JLabel(); // plain for space 
        JLabel lb1 = new JLabel();
        JLabel lb0 = new JLabel();

        leftP.setBackground(blue);
        leftP.setLayout(new GridLayout(8, 1));
        leftP.add(lb0);
        leftP.add(appointment);
        leftP.add(lb);
        leftP.add(searchDoc);
        leftP.add(lb1);
        leftP.add(info);

        pp.setBackground(Lblue);
        pp.add(p, BorderLayout.CENTER);
        pp.add(leftP, BorderLayout.WEST);

        f.add(leftP, BorderLayout.WEST);
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

    private void LoginP() {
        JLabel l1, l2, l3, l4;
        JTextField tf1;
        JPasswordField p1;
        JButton btn1;

        l1 = new JLabel("Login Patient Form");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Username");
        l3 = new JLabel("Password");
        tf1 = new JTextField();
        p1 = new JPasswordField();
        p1.setEchoChar('*');
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

                    } else {
                        JOptionPane.showMessageDialog(null, "username and password do not matched");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
                if (flag == true) {    // users profile button 
                    String user = user_lb;
                    JButton pf = new JButton(user);
                    ImageIcon us = new ImageIcon("src\\Icons\\user.png");
                    pf.setIcon(us);
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
                    JButton out = new JButton("LOGOUT");
                    ImageIcon ot = new ImageIcon("src\\Icons\\logout.png");
                    out.setIcon(ot);
                    out.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mb.remove(pf);
                            mb.remove(out);
                            mb.revalidate();
                            mb.repaint();
                            flag = false;
                        }
                    });
                    mb.add(Box.createHorizontalGlue());
                    mb.add(pf);
                    mb.add(Box.createHorizontalGlue());
                    mb.add(out);
                    mb.revalidate();
                    mb.repaint();
                }
            }
        });
        l4 = new JLabel("           ");
        p.setBackground(Lblue);
        p.setLayout(new GridLayout(7, 2));
        p.add(l1);
        p.add(l2);
        p.add(tf1);
        p.add(l3);
        p.add(p1);
        p.add(l4);
        p.add(btn1);

    }

    private void LoginD() {
        JLabel l1, l2, l3, l4;
        JTextField tf1;
        JPasswordField p1;
        JButton btn1;

        l1 = new JLabel("Login Doctor Form");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Username");
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
                    String sql = ("SELECT * FROM doctor WHERE name=? and password=?  ");
                    DataBase db = new DataBase();
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
                    } else {
                        JOptionPane.showMessageDialog(null, "username and password do not matched");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (flag == true) {
                    String user = user_lb;
                    JButton pf = new JButton(user);
                    ImageIcon us = new ImageIcon("src\\Icons\\user.png");
                    pf.setIcon(us);
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
                    JButton out = new JButton("LOGOUT");
                    ImageIcon ot = new ImageIcon("src\\Icons\\logout.png");
                    out.setIcon(ot);
                    out.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mb.remove(pf);
                            mb.remove(out);
                            mb.revalidate();
                            mb.repaint();
                            flag = false;
                        }
                    });
                    mb.add(Box.createHorizontalGlue());
                    mb.add(pf);
                    mb.add(Box.createHorizontalGlue());
                    mb.add(out);
                    mb.revalidate();
                    mb.repaint();
                }
            }
        });

        p.setLayout(new GridLayout(7, 2));
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
