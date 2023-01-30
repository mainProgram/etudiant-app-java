package dao;

import entity.Classe;
import entity.Etudiant;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EtudiantImpl implements  IEtudiant{
    private DB db = new DB();
    private ResultSet rs;
    private int ok;
    private IClasse iClasse = new ClasseImpl();
    @Override
    public int add(Etudiant e) {
        String sql = "INSERT INTO etudiant VALUES (NULL,?,?,?,?,?)";
        try {
            // Initialisation de la requête
            db.initPrepar(sql);
            // Passage de valeur
            db.getPstm().setString(1, e.getMatricule());
            db.getPstm().setString(2, e.getNom());
            db.getPstm().setString(3, e.getPrenom());
            db.getPstm().setDouble(4, e.getMoyenne());
            db.getPstm().setInt(5, e.getClasse().getId());
            // Execution de la requete
            ok = db.executeMaj();
            db.closeConnection();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ok;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM etudiant WHERE id=?";
        try{
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            ok = db.executeMaj();
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }
    @Override
    public int update(Etudiant e) {
        String sql = "UPDATE etudiant SET nom=?, prenom=?, matricule=?, moyenne=?, classe_id=? WHERE id=?";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(3, e.getMatricule());
            db.getPstm().setString(1, e.getNom());
            db.getPstm().setString(2, e.getPrenom());
            db.getPstm().setDouble(4, e.getMoyenne());
            db.getPstm().setInt(5, e.getClasse().getId());
            db.getPstm().setInt(6, e.getId());
            ok = db.executeMaj();
            db.closeConnection();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ok;
    }

    @Override
    public List<Etudiant> list() {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM etudiant ORDER BY nom";
        try{
            db.initPrepar(sql);
            rs = db.executeSelect();
            while (rs.next()){
                Etudiant etudiant = new Etudiant();
                etudiant.setId(rs.getInt("id"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setMatricule(rs.getString("matricule"));
                etudiant.setMoyenne(rs.getDouble("moyenne"));
                IClasse iClasse = new ClasseImpl();
                Classe classe = iClasse.get(rs.getInt("classe_id"));
                etudiant.setClasse(classe);
                etudiants.add(etudiant);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return etudiants;
    }

    @Override
    public Etudiant get(int id) {
        Etudiant etudiant = null;
        String sql = "SELECT * FROM etudiant WHERE id=?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            rs = db.executeSelect();
            if(rs.next()){
                etudiant = new Etudiant();
                etudiant.setId(rs.getInt("id"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setMatricule(rs.getString("matricule"));
                etudiant.setMoyenne(rs.getDouble("moyenne"));
                IClasse iClasse = new ClasseImpl();
                Classe classe = iClasse.get(rs.getInt("classe_id"));
                etudiant.setClasse(classe);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return etudiant;
    }

    @Override
    public List<Etudiant> getEtudiantsByClasse(String classe) {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM etudiant e, classe c WHERE c.id = e.classe_id AND c.nom = ? ORDER BY e.nom ";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, classe);
            rs = db.executeSelect();
            while (rs.next()){
                Etudiant etudiant = new Etudiant();
                etudiant.setId(rs.getInt("id"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setMatricule(rs.getString("matricule"));
                etudiant.setMoyenne(rs.getDouble("moyenne"));
                IClasse iClasse = new ClasseImpl();
                Classe classe_ = iClasse.get(rs.getInt("classe_id"));
                etudiant.setClasse(classe_);
                etudiants.add(etudiant);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return etudiants;
    }
}
