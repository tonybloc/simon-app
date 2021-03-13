package top.tonymochel.simon.model;

import java.util.ArrayList;

/**
 * Created by Tony on 11/04/2018.
 */

/**
 * Classe Simon : Défini les niveaux de l'application
 */
public class Simon {

    private ArrayList<Niveau> lesNiveaux;
    private String appTitre;
    private String appVersion;
    private String appAuteur;

    // Identification des niveaux
    public static final int NIVEAU_DEBUTANT = 0;
    public static final int NIVEAU_INTERMEDIAIRE = 1;
    public static final int NIVEAU_DIFFICILE = 2;
    public static final int NIVEAU_EXPERT = 3;
    public static final int NIVEAU_ARCADE = 4;

    // Identification des résultats
    public static final int RESULTAT_TYPE_VALIDIER = 0;
    public static final int RESULTAT_TYPE_ECHEQUE = 1;
    public static final int RESULTAT_TYPE_BEST_NEW = 2;
    public static final int RESULTAT_TYPE_BEST = 3;



    /**
     * Constructeur : définie les niveaux de l'application
     */
    public Simon(){

        this.lesNiveaux = new ArrayList<Niveau>();
        this.lesNiveaux.add(new Niveau(Simon.NIVEAU_DEBUTANT,"Débutant", 1000, 10, new Plateau(2,2)));
        this.lesNiveaux.add(new Niveau(Simon.NIVEAU_INTERMEDIAIRE,"Intermediaire", 500, 15, new Plateau(2,2)));
        this.lesNiveaux.add(new Niveau(Simon.NIVEAU_DIFFICILE,"Difficile", 500, 15, new Plateau(3,3)));
        this.lesNiveaux.add(new Niveau(Simon.NIVEAU_EXPERT,"Expert", 200, 20, new Plateau(3,3)));
        this.lesNiveaux.add(new Niveau(Simon.NIVEAU_ARCADE, "Arcade", 500, 100, new Plateau(2,2)));

        this.appTitre = "Jeux du Simon";
        this.appAuteur = "tonybloc";
        this.appVersion = "v1.0";
    }

    /**
     * Retroune la liste des niveaux de l'application
     * @return ArrayList : liste des niveaux
     */
    public ArrayList<Niveau> getLesNiveaux() {
        return lesNiveaux;
    }

    /**
     * Retourn le titre de l'application
     * @return String : nom de l'application
     */
    public String getAppTitre() {
        return appTitre;
    }

    /**
     * Retourne la version de l'application
     * @return String : version
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * Retourne l'auteur de l'application
     * @return String : auteur
     */
    public String getAppAuteur() {
        return appAuteur;
    }
}
