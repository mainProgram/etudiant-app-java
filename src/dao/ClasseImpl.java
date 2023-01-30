package dao;

import entity.Classe;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClasseImpl implements IClasse{
    private DB db = new DB();
    private ResultSet rs;
    private int ok;

    @Override
    public int add(Classe c) {
        String sql = "INSERT INTO classe VALUES (NULL,?,0)";
        try {
            // Initialisation de la requête
            db.initPrepar(sql);
            // Passage de valeur
            db.getPstm().setString(1, c.getNom());
            // Execution de la requete
            ok = db.executeMaj();
            //fermeture connexion
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }
    @Override
    public int delete(int id) {
        String sql = "DELETE FROM classe WHERE id=? ";
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
    public int update(Classe c) {
        String sql = "UPDATE classe SET effectif=? WHERE id=?";
        try{
            db.initPrepar(sql);
            db.getPstm().setInt(1, c.getEffectif());
            db.getPstm().setInt(2, c.getId());
            ok = db.executeMaj();
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public List<Classe> list() {
        List<Classe> classes = new ArrayList<>();
        String sql = "SELECT * FROM classe ORDER BY nom";
        try{
            db.initPrepar(sql);
            rs = db.executeSelect();
            while (rs.next()){
                Classe classe = new Classe();
                classe.setId(rs.getInt("id"));
                classe.setNom(rs.getString("nom"));
                classe.setEffectif(rs.getInt("effectif"));
                classes.add(classe);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public Classe get(int id) {
        Classe classe = null;
        String sql = "SELECT * FROM classe WHERE id=?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            rs = db.executeSelect();
            if(rs.next()){
                classe = new Classe();
                classe.setId(rs.getInt("id"));
                classe.setNom(rs.getString("nom"));
                classe.setEffectif(rs.getInt("effectif"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return classe;
    }

    public int updateEffectifClasse(Classe c, Boolean isMore){
        if (isMore)
            c.setEffectif(c.getEffectif() + 1);
        else
            c.setEffectif(c.getEffectif() - 1);
        return update(c);
    }
}
