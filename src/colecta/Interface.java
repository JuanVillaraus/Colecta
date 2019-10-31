/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecta;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Sistemas
 */
public class Interface extends JFrame implements ActionListener {

    JFrame admin;
    JFrame fDonor;
    ConxDB db = new ConxDB();

    public Interface() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("resource/cruzroja.png"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 500);
        this.setVisible(true);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - this.getWidth() / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height / 3) - this.getHeight() / 2);
        this.setTitle("Sistema de Colecta");

        JPanel mainPanel = new JPanel();
        JButton bAdmin = new JButton("Admin");
        JButton bDonor = new JButton("Donante");
        JButton bDonate = new JButton("Donación");
        bAdmin.setPreferredSize(new Dimension(330, 20));
        bDonor.setPreferredSize(new Dimension(330, 20));
        bDonate.setPreferredSize(new Dimension(330, 20));
        bAdmin.addActionListener(this);
        bDonor.addActionListener(this);
        bDonate.addActionListener(this);

        mainPanel.add(bAdmin);
        mainPanel.add(bDonor);
        mainPanel.add(bDonate);
        this.add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("event: " + e.getActionCommand());
        switch (e.getActionCommand()) {
            case "Admin":
                JPanel pPass = new JPanel();
                JTextField tUser = new JTextField(8);
                JPasswordField tPass = new JPasswordField(8);
                pPass.add(new JLabel("User:"));
                pPass.add(tUser);
                pPass.add(new JLabel("Pass:"));
                pPass.add(tPass);
                if (0 == JOptionPane.showConfirmDialog(null, pPass, "login", JOptionPane.DEFAULT_OPTION)) {
                    if (String.valueOf(tUser.getText()).equals(db.consultAdmin(String.valueOf(tPass.getPassword())))) {
                        admin = new JFrame("Administrador");
                        admin.setIconImage(Toolkit.getDefaultToolkit().getImage("resource/cruzroja.png"));
                        admin.setSize(400, 500);
                        admin.setVisible(true);
                        admin.setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - this.getWidth() / 2) + this.getWidth(),
                                (Toolkit.getDefaultToolkit().getScreenSize().height / 3) - this.getHeight() / 2);
                        admin.add(new admin(admin, db, this));
                        //int p = JOptionPane.showConfirmDialog(null, new admin(db), "Admin",JOptionPane.DEFAULT_OPTION);
                    } else {
                        JOptionPane.showMessageDialog(null, "El user o pass son incorrectos", "ERROR", JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
            case "Donante":
                fDonor = new JFrame("Alta Donante");
                fDonor.setIconImage(Toolkit.getDefaultToolkit().getImage("resource/cruzroja.png"));
                fDonor.setSize(400, 500);
                fDonor.setVisible(true);
                fDonor.setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - this.getWidth() / 2) + this.getWidth(),
                                (Toolkit.getDefaultToolkit().getScreenSize().height / 3) - this.getHeight() / 2);
                fDonor.add(new Donor(fDonor, db));
                break;
            case "Donación":
                break;
        }
    }

}
