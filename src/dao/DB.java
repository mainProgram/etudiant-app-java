package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB {
    // Connexion
    private Connection cnx;

    // Requêtes préparées
    private PreparedStatement pstm;

    // Requêtes SELECT
    private ResultSet rs;

    // Requêtes de màJ Insert, Update, Delete
    private int ok;

    // Méthode d'ouverture dela connexion


    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/gestion_etudiants_db" ;
        String user = "root";
        String password = "";
        try{
            // Chargement du pilote
            Class.forName("com.mysql.jdbc.Driver");

            // Ouverture de la connexion
            cnx = DriverManager.getConnection(url, user, password);
            //System.out.println("Connexion réussie !");
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        return cnx;
    }

    public void initPrepar(String sql){
        try {
            getConnection();
            pstm = cnx.prepareStatement(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet executeSelect(){
        rs = null;
        try {
            rs = pstm.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public int executeMaj(){
        try {
            ok = pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }

    public void closeConnection(){
        try {
            if (cnx !=null){
                cnx.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public PreparedStatement getPstm() {
        return pstm;
    }
}
