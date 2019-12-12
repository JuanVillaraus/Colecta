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
    Calendar calendarioDate = new GregorianCalendar();
    SimpleDateFormat timeFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
    JTextField tName = new JTextField(30);
    JTextField tLastName = new JTextField(30);
    JTextField tLastName2 = new JTextField(30);
    JTextField tDate = new JTextField(30);
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
        JLabel lDate = new JLabel("Fecha:    ");
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
        tDate.setHorizontalAlignment(JTextField.CENTER);
        tDate.setText(date.format(calendarioDate.getTime()));
        bInsert.addActionListener(this);
        this.add(lName);
        this.add(tName);
        this.add(mBarGiro);
        this.add(lDate);
        this.add(tDate);
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
            if (tName.getText().equals("") || mGiro.getText().equals("Giro")) {
                alert.setText("Deben escribir un nombre y/o giro");
                alert.setBackground(Color.RED);
            } else {
                String idGiro = "";
                char[] cadena = mGiro.getText().toCharArray();
                for (int i = 3; i < cadena.length; i++) {
                    if (cadena[i] == ' ') {
                        i = cadena.length;
                    } else {
                        idGiro += cadena[i];
                    }
                }
                String fecha = timeFull.format(calendario.getTime());
                if (!tDate.getText().equals(date.format(calendarioDate.getTime()))) {
                    fecha = tDate.getText();
                    String[] sDate = new String[3];
                    for (int i = 0; i < sDate.length; i++) {
                        sDate[i] = "";
                    }
                    cadena = fecha.toCharArray();
                    int pass = 0;
                    for (int i = 0; i < cadena.length; i++) {
                        if (cadena[i] == '/') {
                            pass++;
                        } else {
                            sDate[pass] += cadena[i];
                        }
                    }
                    fecha = sDate[2] + "-" + sDate[1] + "-" + sDate[0] + " " + time.format(calendario.getTime());
                }
                System.out.println("insert: " + db.insertDonor(tName.getText(),
                        fecha, tDir.getText(),
                        tCP.getText(), tCel.getText(), "", Integer.valueOf(idGiro)));
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
