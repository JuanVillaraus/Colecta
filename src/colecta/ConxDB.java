/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colecta;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JFrame;

/**
 *
 * @author Sistemas
 */
public class ConxDB {

    Connection c = null;

    public ConxDB(JFrame frameInsert) {
        try {
            Class.forName("org.postgresql.Driver");
            this.c = DriverManager.getConnection("jdbc:postgresql://192.168.1.101:5432/ColectaDB", "admincrm", "CRM*1975*dvb");
            System.out.println("Opened database successfully");
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
            System.err.println("ConxDB/ConxDB$\t" + e.getClass().getName() + "\t" + e.getMessage());
            frameInsert.dispose();
        }
    }

    public ConxDB() {
        try {
            Class.forName("org.postgresql.Driver");
            this.c = DriverManager.getConnection("jdbc:postgresql://192.168.1.101:5432/ColectaDB", "admincrm", "CRM*1975*dvb");
            System.out.println("Opened database successfully");
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
            System.err.println("ConxDB/ConxDB$\t" + e.getClass().getName() + "\t" + e.getMessage());
            Logger.getLogger(ConxDB.class.getName()).log(Level.SEVERE, null, e);
            System.exit(0);
        }
    }

    public void close() {
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger("ConxDB/Close$\t" + ConxDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String insertAdmin(String user, String pass) {
        String query = "INSERT INTO \"ADMIN\"(\"USER\", \"PASS\") VALUES(?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query)) {

            pst.setString(1, user);
            pst.setString(2, pass);
            pst.executeUpdate();
            return ("done");
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertOper$\n" + ex.getClass().getName() + "\n" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String insertTipoDonativo(String tipoDonativo) {
        String query = "INSERT INTO \"TIPO_DONATIVO\"(\"NOMBRE_TIPO_DONATIVO\") VALUES(?)";

        try (PreparedStatement pst = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, tipoDonativo);
            pst.executeUpdate();
            Long id = null;
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            return ("DN#" + id);
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertOper$\n" + ex.getClass().getName() + "\n" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String insertTipoDeposito(String tipoDeposito) {
        String query = "INSERT INTO \"TIPO_DEPOSITO\"(\"NOMBRE_TIPO_DEPOSITO\") VALUES(?)";

        try (PreparedStatement pst = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, tipoDeposito);
            pst.executeUpdate();
            Long id = null;
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            return ("DP#" + id);
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertOper$\n" + ex.getClass().getName() + "\n" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String insertGiro(String giro) {
        String query = "INSERT INTO \"GIRO\"(\"NOMBRE_GIRO\") VALUES(?)";

        try (PreparedStatement pst = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, giro);
            pst.executeUpdate();
            Long id = null;
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            return ("GR#" + id);
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertOper$\n" + ex.getClass().getName() + "\n" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String insertPromotor(String promotor) {
        String query = "INSERT INTO \"PROMOTOR\"(\"NOMBRE_PROMOTOR\") VALUES(?)";

        try (PreparedStatement pst = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, promotor);
            pst.executeUpdate();
            Long id = null;
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            return ("PR#" + id);
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertOper$\n" + ex.getClass().getName() + "\n" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String insertDonor(String name, String date, String dir, String cp, String cel, String contacto, int idGiro) {
        String query = "INSERT INTO \"DONANTE\"(\"NOMBRE_DONANTE\", \"FECHA_ALTA_DONANTE\", "
                + "\"DOMICILIO_DONANTE\", \"CP_DONANTE\", \"CEL_DONANTE\", \"CONTACTO_DONANTE\", "
                + "\"ID_GIRO_DONANTE\") VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, name);
            pst.setString(2, date);
            pst.setString(3, dir);
            pst.setString(4, cp);
            pst.setString(5, cel);
            pst.setString(6, contacto);
            pst.setInt(7, idGiro);
            pst.executeUpdate();
            Long id = null;
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            return ("DT#" + id);
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertOper$\n" + ex.getClass().getName() + "\n" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String consultAdmin() {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"ADMIN\" "
                    + "ORDER BY \"USER\" ASC;");
            while (rs.next()) {
                String user = rs.getString("USER");

                resp += (user + "\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultAdmin(String user) {
        String pass = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"ADMIN\" "
                    + "WHERE \"USER\"= '" + user + "';");
            while (rs.next()) {
                pass = rs.getString("PASS");
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        return pass;
    }

    public String consultTipoDonativo() {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"TIPO_DONATIVO\" "
                    + "ORDER BY \"PK_ID_TIPO_DONATIVO\" ASC;");
            while (rs.next()) {
                String id = rs.getString("PK_ID_TIPO_DONATIVO");
                String tipo = rs.getString("NOMBRE_TIPO_DONATIVO");

                resp += ("DN#" + id + " " + tipo + "\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultTipoDonativo(int id) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"TIPO_DONATIVO\" "
                    + "WHERE \"PK_ID_TIPO_DONATIVO\"= '" + id + "';");
            while (rs.next()) {
                String nom = rs.getString("NOMBRE_TIPO_DONATIVO");
                resp = nom;
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultTipoDeposito() {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"TIPO_DEPOSITO\" "
                    + "ORDER BY \"PK_ID_TIPO_DEPOSITO\" ASC;");
            while (rs.next()) {
                String id = rs.getString("PK_ID_TIPO_DEPOSITO");
                String tipo = rs.getString("NOMBRE_TIPO_DEPOSITO");

                resp += ("DP#" + id + " " + tipo + "\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultTipoDeposito(int id) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"TIPO_DEPOSITO\" "
                    + "WHERE \"PK_ID_TIPO_DEPOSITO\"= '" + id + "';");
            while (rs.next()) {
                String nom = rs.getString("NOMBRE_TIPO_DEPOSITO");
                resp = nom;
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultGiro() {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"GIRO\" "
                    + "ORDER BY \"PK_ID_GIRO\" ASC;");
            while (rs.next()) {
                String id = rs.getString("PK_ID_GIRO");
                String name = rs.getString("NOMBRE_GIRO");

                resp += ("GR#" + id + " " + name + "\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultGiro(int id) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"GIRO\" "
                    + "WHERE \"PK_ID_GIRO\"= '" + id + "';");
            while (rs.next()) {
                String nom = rs.getString("NOMBRE_GIRO");
                resp = nom;
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }
    
    public String consultPromotor() {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"PROMOTOR\" "
                    + "ORDER BY \"PK_ID_PROMOTOR\" ASC;");
            while (rs.next()) {
                String id = rs.getString("PK_ID_PROMOTOR");
                String name = rs.getString("NOMBRE_PROMOTOR");

                resp += ("PR#" + id + " " + name + "\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }
    
    public String consultPromotor(int id) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"PROMOTOR\" "
                    + "WHERE \"PK_ID_PROMOTOR\"= '" + id + "';");
            while (rs.next()) {
                String nom = rs.getString("NOMBRE_PROMOTOR");
                resp = nom;
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }
    
    public String consultDonor() {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"DONANTE\" "
                    + "ORDER BY \"PK_ID_DONANTE\" ASC;");
            while (rs.next()) {
                String id = rs.getString("PK_ID_DONANTE");
                String name = rs.getString("NOMBRE_DONANTE");

                resp += ("DT#" + id + " " + name + "\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }
    
    public String consultDonor(int id) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"DONANTE\" "
                    + "WHERE \"PK_ID_DONANTE\"= '" + id + "';");
            while (rs.next()) {
                String nom = rs.getString("NOMBRE_DONANTE");
                resp = nom;
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String deleteTipoDonativo(int id) {
        String SQL = "DELETE FROM \"TIPO_DONATIVO\" WHERE \"PK_ID_TIPO_DONATIVO\" = ?";
        try (PreparedStatement pstmt = c.prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() == 1) {
                return ("successfully completed");
            } else {
                return ("not found");
            }
        } catch (SQLException ex) {
            return (ex.getMessage());
        }
    }

    public String deleteTipoDeposito(int id) {
        String SQL = "DELETE FROM \"TIPO_DEPOSITO\" WHERE \"PK_ID_TIPO_DEPOSITO\" = ?";
        try (PreparedStatement pstmt = c.prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() == 1) {
                return ("successfully completed");
            } else {
                return ("not found");
            }
        } catch (SQLException ex) {
            return (ex.getMessage());
        }
    }

    public String deleteGiro(int id) {
        String SQL = "DELETE FROM \"GIRO\" WHERE \"PK_ID_GIRO\" = ?";
        try (PreparedStatement pstmt = c.prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() == 1) {
                return ("successfully completed");
            } else {
                return ("not found");
            }
        } catch (SQLException ex) {
            return (ex.getMessage());
        }
    }
}
