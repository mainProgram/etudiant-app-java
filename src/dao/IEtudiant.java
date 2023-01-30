package dao;

import entity.Classe;
import entity.Etudiant;

import java.util.List;

public interface IEtudiant extends Repository<Etudiant>{
    public List<Etudiant> getEtudiantsByClasse(String classe);
}
