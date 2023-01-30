package main;

import dao.ClasseImpl;
import dao.EtudiantImpl;
import dao.IClasse;
import dao.IEtudiant;
import entity.Classe;
import entity.Etudiant;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IClasse iClasse = new ClasseImpl();
        IEtudiant iEtudiant = new EtudiantImpl();

        System.out.println("\n##### Ajout d'une classe #####");
        Classe classe = new Classe();
        classe.setNom("L2GL");
        if (iClasse.add(classe)==1){
            System.out.println("Classe créée avec succés !");
        }else {
            System.out.println("Insertion échouée !");
        }

        List<Classe> classes = iClasse.list();
        System.out.println("\n##### Liste des classes #####");
        System.out.println("\n##### Nom et effectif #####");
        for (Classe c: classes){
            System.out.println(c.getNom() + " " + c.getEffectif());
        }

        System.out.println("\n##### Modification d'une classe #####");
        Classe classe1 = null;
        classe1 = iClasse.get(1);
        System.out.println(classe1.getId() + " " + classe1.getNom() + " " + classe1.getEffectif());
        classe1.setNom("Lic1Gl");
        if(iClasse.update(classe1) == 1){
            System.out.println("Classe modifiée avec succés !");
        }else {
            System.out.println("Modification échouée !");
        }

        System.out.println("\n##### Suppression d'une classe #####");
        if (iClasse.delete(3)==1){
            System.out.println("Classe supprimée avec succés !");
        }else {
            System.out.println("Suppression échouée !");
        }

        System.out.println("\n##### Ajout d'un étudiant #####");
        Etudiant e = new Etudiant();
        e.setNom("Gueye");
        e.setPrenom("Fama");
        e.setMoyenne(17);
        e.setClasse(classe1);
        e.setMatricule(e.genererMatricule());
        int ok = iEtudiant.add(e);
        if ((ok == 1)) {
            System.out.println("Etudiant créé");
            iClasse.updateEffectifClasse(classe1, true);
        } else {
            System.out.println("Echec");
        }

        System.out.println("\n##### Modification d'un etudiant #####");
        Etudiant e5 = iEtudiant.get(5);
        e5.setNom("Traoré");
        if(iEtudiant.update(e5) == 1){
            System.out.println("Etudiant modifié avec succés !");
        }else {
            System.out.println("Modification échouée !");
        }

        Etudiant et = iEtudiant.get(4);
        Classe cl = iClasse.get(et.getClasse().getId());
        if(iEtudiant.delete(4)==1){
            System.out.println("Etudiant supprimé avec succés !");
            iClasse.updateEffectifClasse(cl, false);
        }else {
            System.out.println("Suppression échouée !");
        }

        List<Etudiant> etudiants = iEtudiant.list();
        System.out.println("Liste des étudiants");
        for (Etudiant c: etudiants){
            System.out.println(c.getId() + " " + c.getPrenom() + " " + c.getNom());
        }

        List<Etudiant> etudiantsL2gl = iEtudiant.getEtudiantsByClasse("L1GL");
        System.out.println("Liste des étudiants de la L1GL");
        for (Etudiant c: etudiantsL2gl){
            System.out.println(c.getId() + " " + c.getPrenom() + " " + c.getNom());
        }
    }
}