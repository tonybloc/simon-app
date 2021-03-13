package top.tonymochel.simon.model;

/**
 * Created by Tony on 18/04/2018.
 */

import java.io.Serializable;

/**
 * Classe Plateau : plateau de couleurs
 */
public class Plateau implements Serializable {

    private int nbColonne;
    private int nbLigne;

    /**
     * Constructeur : Plateau par default (2x2)
     */
    public Plateau(){
        this.nbLigne = 2;
        this.nbColonne = 2;
    }

    /**
     * Coonstructeur : Plateau personnalisé
     * @param nbColonne
     * @param nbLigne
     */
    public Plateau(int nbColonne, int nbLigne){
        this.nbColonne = nbColonne;
        this.nbLigne = nbLigne;
    }

    /**
     * Modifie le nombre du colonne du plateau
     * @param nbColonne : nombre de colonne
     */
    public void setNbColonne(int nbColonne){
        this.nbColonne = nbColonne;
    }

    /**
     * Retourne le nombre de colonne du plateau
     * @return int : nombre de colonne
     */
    public int getNbColonne(){
        return this.nbColonne;
    }

    /**
     * Modifie le nombre de ligne du plateau
     * @param nbLigne : nombre de ligne
     */
    public void setNbLigne(int nbLigne){
        this.nbLigne = nbLigne;
    }

    /**
     * Retourn le nombre de ligne du plateau
     * @return int : nombre de ligne
     */
    public int getNbLigne(){
        return this.nbLigne;
    }
}
