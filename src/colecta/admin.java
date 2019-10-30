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
public class admin extends JPanel implements ActionListener {

    JFrame window;
    Interface frameMain;
    ConxDB db;
    JButton bInsert = new JButton("Agregar");
    JButton bDelete = new JButton("Eliminar");
    JButton bConsult = new JButton("Consultar");
    String resultDB = null;
    JMenu mOption = new JMenu("Opción");
    JMenu mCategory = new JMenu("Opción");
    JLabel lName = new JLabel("Nombre");
    JLabel lId = new JLabel("ID");
    JTextField tName = new JTextField(30);
    JTextField tId = new JTextField(10);
    JTextField alert = new JTextField(10);
    JTextArea aConsult = new JTextArea();

    public admin(JFrame window, ConxDB db, Interface frameMain) {
        this.window = window;
        this.db = db;
        this.frameMain = frameMain;
        JMenuBar mBarCategory = new JMenuBar();
        JMenuBar mBarOption = new JMenuBar();
        JMenuItem iTipoDonativo = new JMenuItem("Tipo Donativo");
        JMenuItem iTipoDeposito = new JMenuItem("Tipo Deposito");
        JMenuItem iPromotor = new JMenuItem("Promotor");
        JMenuItem iGiroDonante = new JMenuItem("Giro del Donante");
        JMenuItem iInsert = new JMenuItem("Agregar");
        JMenuItem iDelete = new JMenuItem("Eliminar");

        mCategory.add(iTipoDonativo);
        mCategory.add(iTipoDeposito);
        mCategory.add(iPromotor);
        mCategory.add(iGiroDonante);
        mBarCategory.add(mCategory);
        mOption.add(iInsert);
        mOption.add(iDelete);
        mBarOption.add(mOption);

        mOption.setVisible(false);
        lName.setVisible(false);
        tName.setVisible(false);
        lId.setVisible(false);
        tId.setVisible(false);
        bInsert.setVisible(false);
        bDelete.setVisible(false);
        bConsult.setVisible(false);
        alert.setEditable(false);
        iTipoDonativo.addActionListener(this);
        iTipoDeposito.addActionListener(this);
        iPromotor.addActionListener(this);
        iGiroDonante.addActionListener(this);
        iInsert.addActionListener(this);
        iDelete.addActionListener(this);
        bInsert.addActionListener(this);
        bDelete.addActionListener(this);
        mCategory.setPreferredSize(new Dimension(330, 30));
        mOption.setPreferredSize(new Dimension(330, 30));
        lId.setPreferredSize(new Dimension(60, 30));
        aConsult.setPreferredSize(new Dimension(window.getWidth() - 50, 300));

        JPanel pAlert = new JPanel(new GridLayout(1, 1));
        JPanel pOption = new JPanel(new GridLayout(1, 1));
        mCategory.setHorizontalTextPosition(JTextField.CENTER);
        mOption.setHorizontalTextPosition(JTextField.CENTER);
        alert.setHorizontalAlignment(JTextField.CENTER);
        lId.setHorizontalAlignment(JTextField.RIGHT);

        pOption.add(mBarCategory);
        window.add(pOption, BorderLayout.NORTH);
        this.setLayout(new FlowLayout());
        this.add(mBarOption);
        this.add(lId);
        this.add(tId);
        this.add(lName);
        this.add(tName);
        this.add(bInsert);
        this.add(bDelete);
        this.add(aConsult);
        pAlert.add(alert);
        window.add(pAlert, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass().getSimpleName().equals("JButton")) {
            switch (e.getActionCommand()) {
                case "Agregar":
                    switch (mCategory.getText()) {
                        case "Tipo Donativo":
                            resultDB = db.insertTipoDonativo(tName.getText());
                            if (resultDB.toCharArray()[0] == 'D' && resultDB.toCharArray()[1] == 'N') {
                                alert.setText("El" + mCategory.getText() + "ha sido agregada exitosamente");
                                alert.setBackground(Color.GREEN);
                                tName.setText(null);
                                aConsult.setText(db.consultTipoDonativo());
                            }
                            break;
                        case "Tipo Deposito":
                            resultDB = db.insertTipoDeposito(tName.getText());
                            if (resultDB.toCharArray()[0] == 'D' && resultDB.toCharArray()[1] == 'P') {
                                alert.setText("El" + mCategory.getText() + "ha sido agregada exitosamente");
                                alert.setBackground(Color.GREEN);
                                tName.setText(null);
                                aConsult.setText(db.consultTipoDeposito());
                            }
                            break;
                        case "Giro del Donante":
                            resultDB = db.insertGiro(tName.getText());
                            if (resultDB.toCharArray()[0] == 'G' && resultDB.toCharArray()[1] == 'R') {
                                alert.setText("El" + mCategory.getText() + "ha sido agregada exitosamente");
                                alert.setBackground(Color.GREEN);
                                tName.setText(null);
                                aConsult.setText(db.consultGiro());
                            }
                            break;
                    }
                    break;
                case "Eliminar":
                    if (tId.getText().equals("")) {
                        alert.setText("Deben ingresar el ID");
                        alert.setBackground(Color.RED);
                    } else {
                        resultDB = "";
                        switch (mCategory.getText()) {
                            case "Tipo Donativo":
                                if (0 == JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar a "
                                        + db.consultTipoDonativo(Integer.valueOf(tId.getText())) + "?", "Alerta!",
                                        JOptionPane.YES_NO_OPTION)) {
                                    resultDB = db.deleteTipoDonativo(Integer.parseInt(tId.getText()));
                                    aConsult.setText(db.consultTipoDonativo());
                                }
                                break;
                            case "Tipo Deposito":
                                if (0 == JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar a "
                                        + db.consultTipoDeposito(Integer.valueOf(tId.getText())) + "?", "Alerta!",
                                        JOptionPane.YES_NO_OPTION)) {
                                    resultDB = db.deleteTipoDeposito(Integer.parseInt(tId.getText()));
                                    aConsult.setText(db.consultTipoDonativo());
                                }
                                break;
                            case "Giro del Donante":
                                if (0 == JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar a "
                                        + db.consultGiro(Integer.valueOf(tId.getText())) + "?", "Alerta!",
                                        JOptionPane.YES_NO_OPTION)) {
                                    resultDB = db.deleteGiro(Integer.parseInt(tId.getText()));
                                    aConsult.setText(db.consultGiro());
                                }
                                break;
                        }
                        switch (resultDB) {
                            case "successfully completed":
                                alert.setText("El " + mCategory.getText() + " con ID:" + tId.getText()
                                        + " a sido eliminado exitosamente");
                                tId.setText(null);
                                alert.setBackground(Color.GREEN);
                                break;
                            case "not found":
                                alert.setText("El " + mCategory.getText() + " con ID:" + tId.getText()
                                        + " no fue encontrado");
                                alert.setBackground(Color.RED);
                                break;
                            case "":
                                break;
                            default:
                                alert.setText(resultDB);
                                alert.setBackground(Color.RED);
                                break;
                        }
                    }
            }
        } else {
            tName.setText(null);
            tId.setText(null);
//            aConsult.setText("");
            switch (e.getActionCommand()) {
                case "Tipo Donativo":
                case "Tipo Deposito":
                case "Promotor":
                case "Giro del Donante":
                    mCategory.setText(e.getActionCommand());
                    mOption.setText("Opción");
                    mOption.setVisible(true);
                    lId.setVisible(false);
                    tId.setVisible(false);
                    lName.setVisible(false);
                    tName.setVisible(false);
                    bInsert.setVisible(false);
                    bDelete.setVisible(false);
                    aConsult.setVisible(true);
                    switch (e.getActionCommand()) {
                        case "Tipo Donativo":
                            aConsult.setText(db.consultTipoDonativo());
                            break;
                        case "Tipo Deposito":
                            aConsult.setText(db.consultTipoDeposito());
                            break;
                        case "Giro del Donante":
                            aConsult.setText(db.consultGiro());
                            break;
                    }
                    break;
                case "Agregar":
                    mOption.setText(e.getActionCommand());
//                    mOption.setVisible(true);
                    lName.setVisible(true);
                    tName.setVisible(true);
                    bInsert.setVisible(true);
                    lId.setVisible(false);
                    tId.setVisible(false);
                    bDelete.setVisible(false);
                    aConsult.setVisible(true);
                    break;
                case "Eliminar":
                    mOption.setText(e.getActionCommand());
//                    mOption.setText("Opción");
//                    mOption.setVisible(true);
                    lName.setVisible(false);
                    tName.setVisible(false);
                    bInsert.setVisible(false);
                    lId.setVisible(true);
                    tId.setVisible(true);
                    bDelete.setVisible(true);
                    aConsult.setVisible(true);
                    break;
            }
        }
    }

}
