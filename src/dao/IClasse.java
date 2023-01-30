package dao;

import entity.Classe;

import java.util.List;

public interface IClasse extends Repository<Classe> {
    public int updateEffectifClasse(Classe c, Boolean isMore);
}
