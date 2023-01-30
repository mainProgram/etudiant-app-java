package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Etudiant {
    private int id;
    private String matricule;
    private String nom;
    private String prenom;
    private double moyenne;

    public String genererMatricule(){
        // ET@ + anneemoisjourheureminuteseconde + nom de la classe + # | ex:ET@20230122171015GL#
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String amjhms = (formatter.format(date));
        String mat = "ET@" + amjhms + classe.getNom() + "#";
        return mat;    }

    public Etudiant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Etudiant(String nom, String prenom, double moyenne, Classe classe) {
        this.nom = nom;
        this.prenom = prenom;
        this.moyenne = moyenne;
        this.classe = classe;
    }

    private Classe classe;
}
