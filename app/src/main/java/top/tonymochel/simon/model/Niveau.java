package top.tonymochel.simon.model;

/**
 * Created by Tony on 09/04/2018.
 */

import java.io.Serializable;

/**
 * Classe Niveau : Défini les paramètres d'unn niveau (Debutant, intermédiaire)
 */
public class Niveau implements Serializable{

    private int idNiveau;
    private String intituler;
    private int vitesse;
    private int nbEtapesAValider;
    private int etape;
    private Plateau plateauDeCouleurs ;

    /**
     * Niveau : niveau personnalisé
     * @param intituler : intitulé du niveau
     * @param vitesse : vitesse d'apparision des couleurs
     * @param nbEtapesAValider : nombre d'étapes pour valider le niveau
     * @param plateauDeCouleurs : Plateau du jeux
     */
    public Niveau(int idNiveau, String intituler, int vitesse, int nbEtapesAValider, Plateau plateauDeCouleurs){
        this.idNiveau = idNiveau;
        this.intituler = intituler;
        this.vitesse = vitesse;
        this.nbEtapesAValider = nbEtapesAValider;
        this.plateauDeCouleurs = plateauDeCouleurs;
        this.etape = 0;
    }

    /**
     * Valide une étape du niveau
     */
    public void validerEtape(){
        this.etape++;
    }

    /**
     * Modifie le nombre d'étapes à validé
     * @param nbEtape
     */
    public void setEtapeValider(int nbEtape){
        this.etape = nbEtape;
    }

    /**
     * Retourne le nombre d'étapes à validé
     * @return int : nombre d'étapes
     */
    public int getNbEtapeValidier(){
        return this.etape;
    }

    /**
     * Retourne l'intituler du niveau
     * @return string : intituler
     */
    public String getIntituler() {
        return intituler;
    }

    /**
     * Modifie l'intitulé du niveau
     * @param : intitulé du niveau
     */
    public void setIntituler(String intituler) {
        this.intituler = intituler;
    }

    /**
     * Retourne la vitesse du niveau
     * @return int : la vitesse du niveau
     */
    public int getVitesse() {
        return vitesse;
    }

    /**
     * Modifie la vitesse du niveau
     * @param vitesse : vitesse du niveau
     */
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    /**
     * Retourn le nombre d'étapes à valider
     * @return int : nombre d'étapes
     */
    public int getNbEtapesAValider() {
        return nbEtapesAValider;
    }

    /**
     * Modifie le nombre d'étapes à valider
     * @param nbEtapes : nombre d'étapes
     */
    public void setNbEtapesAValider(int nbEtapes) {
        this.nbEtapesAValider = nbEtapes;
    }

    /**
     * Retourn le plateau courrent du niveau
     * @return Plateau : plateau du niveau
     */
    public Plateau getPlateau() {
        return this.plateauDeCouleurs;
    }

    /**
     * Modifie l'instance du plateau du niveau
     * @param plateauDeCouleurs : plateau
     */
    public void setPlateau(Plateau plateauDeCouleurs) {
        this.plateauDeCouleurs = plateauDeCouleurs;
    }

    /**
     * Retourn l'identifiant du niveau
     * @return int : identifiant du niveau
     */
    public int getIdNiveau(){
        return this.idNiveau;
    }
}
