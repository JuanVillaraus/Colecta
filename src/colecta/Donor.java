/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecta;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Sistemas
 */
public class Donor extends JPanel implements ActionListener {

    JFrame window;
    ConxDB db;
    SimpleDateFormat timeFull = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    JTextField tName = new JTextField(30);
    JTextField tLastName = new JTextField(30);
    JTextField tLastName2 = new JTextField(30);
    JTextField tDir = new JTextField(30);
    JTextField tCP = new JTextField(30);
    JTextField tCel = new JTextField(30);
    JTextField alert = new JTextField(10);
    JMenu mGiro = new JMenu("Giro");

    public Donor(JFrame window, ConxDB db) {
        this.window = window;
        this.db = db;
        JButton bInsert = new JButton("Agregar");
        JLabel lName = new JLabel("Nombre: ");
        JLabel lLastName = new JLabel("Apellido paterno: ");
        JLabel lLastName2 = new JLabel("Apellido materno: ");
        JLabel lDir = new JLabel("Dirección: ");
        JLabel lCP = new JLabel("Codigo Postal: ");
        JLabel lCel = new JLabel("Celular: ");
        JMenuBar mBarGiro = new JMenuBar();
        menuUpdate();
        mBarGiro.add(mGiro);
        alert.setEditable(false);
        mGiro.setPreferredSize(new Dimension(330, 30));
        mGiro.setHorizontalTextPosition(JTextField.CENTER);
        JPanel pAlert = new JPanel(new GridLayout(1, 1));
        alert.setHorizontalAlignment(JTextField.CENTER);
        this.add(lName);
        this.add(tName);
//        this.add(lLastName);
//        this.add(tLastName);
//        this.add(lLastName2);
//        this.add(tLastName2);
        this.add(mBarGiro);
        this.add(lDir);
        this.add(tDir);
        this.add(lCP);
        this.add(tCP);
        this.add(lCel);
        this.add(tCel);
        this.add(bInsert);
        pAlert.add(alert);
        window.add(pAlert, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Calendar calendario = new GregorianCalendar();
        if (e.getSource().getClass().getSimpleName().equals("JButton")) {
            if (tName.getText().equals("")) {
                alert.setText("Deben escribir un nombre");
                alert.setBackground(Color.RED);
            } else {
                System.out.println("insert: "+db.insertDonor(tName.getText(), 
                        timeFull.format(calendario.getTime()), tDir.getText(), 
                        tCP.getText(), tCel.getText(), "", WIDTH));
            }
        } else {
            mGiro.setText(e.getActionCommand());
        }
    }

    public void menuUpdate() {
        char[] cadena;
        String giro = db.consultGiro();
        cadena = giro.toCharArray();
        String word = "";
        for (int i = 0; i < giro.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iGiro = new JMenuItem(word);
                mGiro.add(iGiro);
                iGiro.addActionListener(this);
                word = "";
            } else {
                word += cadena[i];
            }
        }
    }

}
