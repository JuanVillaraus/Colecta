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
public class Donate extends JPanel implements ActionListener {

    JFrame window;
    ConxDB db;
    JTextField NumCtrl = new JTextField(30);
    JMenu mDonor = new JMenu("Nombre del Donante");
    JMenu mPromoter = new JMenu("Nombre del Promotor");
    JMenu mTipoDeposito = new JMenu("Tipo del Deposito");
    JMenu mTipoDonativo = new JMenu("Tipo de Donativo");

    Donate(JFrame window, ConxDB db) {
        this.window = window;
        this.db = db;
        menuUpdate();
        JButton bInsert = new JButton("Donar");
        JMenuBar mBarDonor = new JMenuBar();
        JMenuBar mBarPromoter = new JMenuBar();
        JMenuBar mBarTipoDeposito = new JMenuBar();
        JMenuBar mBarTipoDonativo = new JMenuBar();
        mBarDonor.add(mDonor);
        mBarPromoter.add(mPromoter);
        mBarTipoDeposito.add(mTipoDeposito);
        mBarTipoDonativo.add(mTipoDonativo);
        mDonor.setPreferredSize(new Dimension(330, 30));
        mPromoter.setPreferredSize(new Dimension(330, 30));
        mTipoDeposito.setPreferredSize(new Dimension(330, 30));
        mTipoDonativo.setPreferredSize(new Dimension(330, 30));
        bInsert.addActionListener(this);

        this.add(mBarDonor);
        this.add(mBarPromoter);
        this.add(mBarTipoDeposito);
        this.add(mBarTipoDonativo);
        this.add(bInsert);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("event: " + e.getActionCommand());
        if (e.getSource().getClass().getSimpleName().equals("JButton")) {
            System.out.println("pendiente");
        } else {
            char[] cadena = e.getActionCommand().toCharArray();
            if (cadena[2] == '#') {
                System.out.println("" + cadena[0] + cadena[1]);
                String cmd = "" + cadena[0] + cadena[1];
                switch (cmd) {
                    case "DN":
                        mTipoDonativo.setText(e.getActionCommand());
                        break;
                    case "DP":
                        mTipoDeposito.setText(e.getActionCommand());
                        break;
                    case "PR":
                        mPromoter.setText(e.getActionCommand());
                        break;
                    case "DT":
                        mDonor.setText(e.getActionCommand());
                        break;
                }
            }
        }
    }

    public void menuUpdate() {
        char[] cadena;
        String searched = db.consultPromotor();
        cadena = searched.toCharArray();
        String word = "";
        for (int i = 0; i < searched.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iSearched = new JMenuItem(word);
                mPromoter.add(iSearched);
                iSearched.addActionListener(this);
                word = "";
            } else {
                word += cadena[i];
            }
        }
        searched = db.consultDonor();
        cadena = searched.toCharArray();
        word = "";
        for (int i = 0; i < searched.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iSearched = new JMenuItem(word);
                mDonor.add(iSearched);
                iSearched.addActionListener(this);
                word = "";
            } else {
                word += cadena[i];
            }
        }
        searched = db.consultTipoDeposito();
        cadena = searched.toCharArray();
        word = "";
        for (int i = 0; i < searched.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iSearched = new JMenuItem(word);
                mTipoDeposito.add(iSearched);
                iSearched.addActionListener(this);
                word = "";
            } else {
                word += cadena[i];
            }
        }
        searched = db.consultTipoDonativo();
        cadena = searched.toCharArray();
        word = "";
        for (int i = 0; i < searched.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iSearched = new JMenuItem(word);
                mTipoDonativo.add(iSearched);
                iSearched.addActionListener(this);
                word = "";
            } else {
                word += cadena[i];
            }
        }
    }
}
