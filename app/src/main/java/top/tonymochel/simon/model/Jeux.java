package top.tonymochel.simon.model;
/**
 * Created by Tony on 11/04/2018.
 */

import android.content.Context;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

import top.tonymochel.simon.GameActivity;
import top.tonymochel.simon.R;

/**
 * Classe Jeux : définie les états du jeux
 */
public class Jeux {

    private Niveau niveau;

    private int[] lesCouleursDisponible;
    private ArrayList<Integer> listCouleursATrouver;
    private ArrayList<Integer> listCouleursPropositions;

    private int nombreDeClique;

    private boolean enCours;
    private boolean estFini;
    private boolean enAttente;

    public static final int STATE_EN_COURS = 0;
    public static final int STATE_EST_FINI = 1;
    public static final int STATE_EN_ATTENTE = 2;

    /**
     * Constructeur
     * @param niveau : niveau selectionner
     * @param couleursDisponible : liste des couleurs disponibles
     */
    public Jeux(Niveau niveau, int[] couleursDisponible){

        this.niveau = niveau;
        this.nombreDeClique = 0;
        this.enCours = false;
        this.estFini = false;
        this.enAttente = false;

        this.lesCouleursDisponible = couleursDisponible;
        this.listCouleursATrouver = new ArrayList();
        this.listCouleursPropositions = new ArrayList();
    }

    /**
     * Recommencer le jeux
     */
    public void restartGame(){
        this.niveau.setEtapeValider(0);
        this.nombreDeClique = 0;
        this.enCours = false;
        this.estFini = false;
        this.enAttente = false;
        this.listCouleursATrouver = new ArrayList();
        this.listCouleursPropositions = new ArrayList();
    }


    /**
     * Génére la nouvelle combinaison de couleurs
     */
    public void prochaineCombinaisonDeCouleur(){
        int random = (int) (Math.random() * this.lesCouleursDisponible.length);
        this.listCouleursATrouver.add(random);
    }

    /**
     * Ajoute une couleur à la liste des propositions
     * @param indexCouleur : index de la couleur
     */
    public void couleurSelectionner(int indexCouleur){
        Log.d("INFO", "En Cours : " + enCours + " - En Attante : " + enAttente + " Fini : " + estFini);

        if(enAttente && enCours){
            this.nombreDeClique++;
            this.listCouleursPropositions.add(indexCouleur);
        }else{
            Log.d("INFO", "L'application n'est pas en attente de réponse");
        }
    }

    /**
     * Verifi si la proposition de l'utilisateur
     */
    public boolean checkProposition(){

        boolean estJuste = true;
        if(this.listCouleursATrouver.size() == this.listCouleursPropositions.size()){

            for(int i=0; i < this.listCouleursATrouver.size();i++){
                if(!this.listCouleursPropositions.get(i).equals(this.listCouleursATrouver.get(i))){
                    estJuste = false;
                }
            }
        }else{
            estJuste = false;
        }
        return estJuste;
    }


    /**
     * Stockage des score/ Etape.
     */
    public void finish(){

    }

    /**
     * Vide la proposition de l'utilisateur
     */
    public void videProposition(){
        this.listCouleursPropositions.clear();
        this.nombreDeClique = 0;
    }

    /**
     * Retourne la liste des couleurs à trouver
     * @return ArrayList : couleurs à trouvé
     */
    public ArrayList<Integer> getListCouleursATrouver(){
        return this.listCouleursATrouver;
    }

    /**
     * Retourne la liste des couleurs proposé par l'utilisateur
     * @return ArrayList : couleurs proposées
     */
    public ArrayList<Integer> getlistCouleursPropositions(){
        return this.listCouleursPropositions;
    }

    /**
     * Retourne la liste des couleurs disponibles
     * @return int[] : tableau de couleur
     */
    public int[] getLesCouleursDisponible(){
        return lesCouleursDisponible;
    }

    /**
     * Retourne le nombres de clique effécté par l'utilisateur
     * @return int : nombre de clique
     */
    public int getNombreDeClique(){
        return this.nombreDeClique;
    }

    /**
     * Retourne l'état de l'application : en cours
     * @return boolean : en cours
     */
    public boolean enCours(){
        return enCours;
    }
    /**
     * Retourne l'état de l'application : est fini
     * @return boolean : est fini
     */
    public boolean estFini(){
        return estFini;
    }
    /**
     * Retourne l'état de l'application : en attente
     * @return boolean : en attente
     */
    public boolean enAttente(){
        return enAttente;
    }


    /**
     * Change l'état du jeux
     * @param type : type du jeux
     * @param value : valeur (true/faulse)
     */
    public void setState(int type, boolean value){

        switch (type){
            case STATE_EN_ATTENTE:
                if(value) {
                    this.enAttente = true;
                    this.estFini = false;
                    this.enCours = true;
                }else{
                    this.enAttente = false;
                }
                break;
            case STATE_EN_COURS:
                if(value) {
                    this.enCours = true;
                }else{
                    this.enCours = false;
                    this.enAttente = false;
                    this.estFini = true;
                }
                break;
            case STATE_EST_FINI:
                if(value){
                    this.estFini = true;
                    this.enCours = false;
                    this.enAttente = false;
                }else{
                    this.estFini = false;
                    this.enCours = true;
                }

                break;
            default:
                break;
        }
    }

    /**
     * Retourn le niveau courrent
     * @return Niveau : niveau courrent
     */
    public Niveau getNiveau(){
        return this.niveau;
    }
}
