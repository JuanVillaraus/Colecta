/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecta;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Sistemas
 */
public class Promoter extends JPanel implements ActionListener {

    JFrame window;
    ConxDB db;
    JTextField tName = new JTextField(30);
    JTextField tWork = new JTextField(30);
    JTextField tDir = new JTextField(30);
    JTextField tPhone = new JTextField(30);
    JTextField tCel = new JTextField(30);
    JTextField tMail = new JTextField(30);
    JTextField tFirstDate = new JTextField(30);

    Promoter(JFrame window, ConxDB db) {
        this.window = window;
        this.db = db;
        JLabel lName = new JLabel("Nombre del Promotor");
        JLabel lWork = new JLabel("Puesto o Actividad del Promotor");
        JLabel lDir = new JLabel("Domicilio");
        JLabel lPhone = new JLabel("Telefono");
        JLabel lCel = new JLabel("Celular");
        JLabel lMail = new JLabel("Correo");
        JLabel lFirstDate = new JLabel("Fecha inicio como Promotor de Colecta");
        JButton bSave = new JButton("Guardar");
        lName.setPreferredSize(new Dimension(330, 20));
        lWork.setPreferredSize(new Dimension(330, 20));
        lDir.setPreferredSize(new Dimension(330, 20));
        lPhone.setPreferredSize(new Dimension(330, 20));
        lCel.setPreferredSize(new Dimension(330, 20));
        lMail.setPreferredSize(new Dimension(330, 20));
        lFirstDate.setPreferredSize(new Dimension(330, 20));

        this.add(lName);
        this.add(tName);
        this.add(lWork);
        this.add(tWork);
        this.add(lDir);
        this.add(tDir);
        this.add(lPhone);
        this.add(tPhone);
        this.add(lCel);
        this.add(tCel);
        this.add(lMail);
        this.add(tMail);
        this.add(lFirstDate);
        this.add(tFirstDate);
        this.add(bSave);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
