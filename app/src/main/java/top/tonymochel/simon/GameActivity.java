package top.tonymochel.simon;


import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import top.tonymochel.simon.model.Jeux;
import top.tonymochel.simon.model.Niveau;
import top.tonymochel.simon.model.Plateau;
import top.tonymochel.simon.model.Simon;

/**
 * Activity : Jeux
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvNiveau;
    private TextView tvEtape;
    private ImageButton imgBtnPlay;
    private LinearLayout layoutResultat;
    private ImageView imgViewResultat;
    private TextView txtResultat;
    private TextView txtResultatInfo;
    private ImageButton btnRecommancer;
    private ImageButton btnRetourAccueil;

    private Niveau niveau;

    private ArrayList<Button> listeBoutonsCouleurs;
    private int[] listCouleursDisponibleComplementaire;
    private int[] listCouleursDisponible;
    private String[] listNomCouleursDisponible;

    private Jeux jeuxCourrent;

    public static final int INDEX_CODE_VERT = 0;
    public static final int INDEX_CODE_ROUGE = 1;
    public static final int INDEX_CODE_BLEU = 2;
    public static final int INDEX_CODE_JAUNE = 3;
    public static final int INDEX_CODE_VIOLET = 4;
    public static final int INDEX_CODE_NOIR = 5;
    public static final int INDEX_CODE_ORANGE = 6;
    public static final int INDEX_CODE_TURQUOISE = 7;
    public static final int INDEX_CODE_ARGENT = 8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        initialisationDesComposants();
        creationInterface();


        this.jeuxCourrent = new Jeux(this.niveau, this.listCouleursDisponible);
        initialiseAction();

    }

    /**
     * Récuperation des composants / ressources / indents et les initialise par défaut
     */
    private void initialisationDesComposants(){
        // Récupération des Indents
        this.niveau = (Niveau) this.getIntent().getSerializableExtra("NIVEAU");

        // Récupération des ressources
        listCouleursDisponible = getCouleurDisponible(niveau, getResources().getIntArray(R.array.listeCouleursDisponible));
        listCouleursDisponibleComplementaire = getCouleurDisponible(niveau, getResources().getIntArray(R.array.listeCouleursDisponibleComplementaire));
        listNomCouleursDisponible = getCouleurDisponible(niveau, getResources().getStringArray(R.array.listeNomCouleursDisponible));

        // récupération des composants graphiques
        this.tvNiveau = findViewById(R.id.txtNiveau);
        this.tvEtape = findViewById(R.id.txtEtape);
        this.imgBtnPlay = findViewById(R.id.imgBtnPlay);
        this.layoutResultat = findViewById(R.id.layoutResultat);
        this.txtResultat = findViewById(R.id.txtResultat);
        this.txtResultatInfo = findViewById(R.id.txtResultatInfo);
        this.btnRecommancer = findViewById(R.id.btnRecommencer);
        this.btnRetourAccueil = findViewById(R.id.btnRetourAccueil);
        this.imgViewResultat = findViewById(R.id.imgViewResultat);
        // Initialisé les composants / Paramètres
        this.listeBoutonsCouleurs = new ArrayList<>();
    }

    /**
     * Crée l'interface graphique de l'activity
     */
    private void creationInterface(){

        generationDuPlateau(this.niveau.getPlateau());
        metAJourLesEtape(this.niveau);
        tvNiveau.setText(this.niveau.getIntituler());
    }
    /**
     * récupère les couleurs utilisées dans l'activité courante
     * @param niveau
     * @param listDesCouleurs
     * @return
     */
    private int[] getCouleurDisponible(Niveau niveau, int[] listDesCouleurs){

        int[] tableauCouleursDisponibles = new int[niveau.getPlateau().getNbColonne() * niveau.getPlateau().getNbLigne()];
        int indexCouleur = 0;

        for(int col=0; col<niveau.getPlateau().getNbColonne(); col++){
            for(int ligne=0; ligne < niveau.getPlateau().getNbLigne(); ligne++){
                tableauCouleursDisponibles[indexCouleur] = listDesCouleurs[indexCouleur];
                indexCouleur ++;
            }
        }
        return tableauCouleursDisponibles;
    }

    /**
     * récupère les couleur utilisé dans l'activité courante
     * @param niveau
     * @param listDesCouleurs
     * @return
     */
    private String[] getCouleurDisponible(Niveau niveau, String[] listDesCouleurs){

        String[] tableauCouleursDisponibles = new String[niveau.getPlateau().getNbColonne() * niveau.getPlateau().getNbLigne()];
        int indexCouleur = 0;

        for(int col=0; col<niveau.getPlateau().getNbColonne(); col++){
            for(int ligne=0; ligne < niveau.getPlateau().getNbLigne(); ligne++){
                tableauCouleursDisponibles[indexCouleur] = listDesCouleurs[indexCouleur];
                indexCouleur ++;
            }
        }
        return tableauCouleursDisponibles;
    }

    /**
     * Met à jour l'affichage des étapes
     */
    private void metAJourLesEtape(Niveau niveau){
        if(niveau.getIdNiveau() == Simon.NIVEAU_ARCADE){
            tvEtape.setText("Etape : " + niveau.getNbEtapeValidier());
        }else{
            tvEtape.setText("Etape : " + niveau.getNbEtapeValidier() + "/" + niveau.getNbEtapesAValider());
        }
    }

    /**
     * Génération des boutons de couleurs
     * Remplie les liste des boutons disponibles pour l'instance du jeu
     * @param unPlateau
     */
    private void generationDuPlateau(Plateau unPlateau){

        LinearLayout layoutConteneur = findViewById(R.id.LayoutZoneCouleurs);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        GridLayout layoutPlateauDeCouleurs = new GridLayout(this);
        layoutPlateauDeCouleurs.setRowCount(niveau.getPlateau().getNbColonne());
        layoutPlateauDeCouleurs.setColumnCount(niveau.getPlateau().getNbLigne());
        layoutPlateauDeCouleurs.setTag("GridLayout_PanneauCouleurs");
        layoutPlateauDeCouleurs.setLayoutParams(params);
        layoutPlateauDeCouleurs.setPadding(30, 30, 30, 30);


        int indexCouleur = 0;

        // Generation des colonne du plateau
        for(int col = 0; col < unPlateau.getNbColonne(); col++){

            // Generation des ligne du plateau
            for(int ligne = 0; ligne < unPlateau.getNbLigne(); ligne++){

                // Placement des boutons dans le layout:
                GridLayout.LayoutParams gridLayoutParams = new GridLayout.LayoutParams(
                        GridLayout.spec(col,1.0f),
                        GridLayout.spec(ligne,1.0f)
                );
                gridLayoutParams.setMargins(20,20,20,20);

                // Création des boutons
                Button unBouton = new Button(this);
                unBouton.setLayoutParams(gridLayoutParams);
                unBouton.setTag("btn_" + listNomCouleursDisponible[indexCouleur]);
                unBouton.setBackgroundColor(this.listCouleursDisponible[indexCouleur]);

                this.listeBoutonsCouleurs.add(unBouton);
                layoutPlateauDeCouleurs.addView(unBouton);

                indexCouleur ++;
            }
        }
        layoutConteneur.addView(layoutPlateauDeCouleurs);
    }


    /**
     * Initialise les action des boutons
     */
    private void initialiseAction() {

        for(Button unBouton : this.listeBoutonsCouleurs){
            unBouton.setOnClickListener(this);
        }
        this.imgBtnPlay.setOnClickListener(this);
        this.btnRecommancer.setOnClickListener(this);
        this.btnRetourAccueil.setOnClickListener(this);
    }


    /**
     * Débute le jeux
     */
    private void commencerJeux(){
        this.jeuxCourrent.setState(Jeux.STATE_EN_COURS, true);
        this.jeuxCourrent.prochaineCombinaisonDeCouleur();
        afficheCombinaisonCouleurs(this.jeuxCourrent.getListCouleursATrouver());
        this.jeuxCourrent.setState(Jeux.STATE_EN_ATTENTE, true);
        this.imgBtnPlay.setEnabled(false);
    }

    /**
     * Fini le jeux
     */
    private void finiJeux(){
        this.jeuxCourrent.finish();
        this.imgBtnPlay.setEnabled(true);
    }

    /**
     * Verifie l'etat du jeux
     */
    private void checkStatOfGame(Jeux jeux){
        if(jeux.enCours()){
            if(jeux.enAttente() && jeux.getNombreDeClique() != jeux.getListCouleursATrouver().size()){
                jeux.setState(Jeux.STATE_EN_ATTENTE,true);
                Log.d("INFO", "NbClick : " + jeux.getNombreDeClique() + " / " + jeux.getListCouleursATrouver().size());
            }
            else if(jeux.enAttente() && jeux.getNombreDeClique() == jeux.getListCouleursATrouver().size()) {
                boolean propositionJuste = jeux.checkProposition();

                if(propositionJuste){
                    // Validation des toutes les étapes du jeux
                    if(jeux.getNiveau().getNbEtapeValidier() == niveau.getNbEtapesAValider() ){
                        jeux.setState(Jeux.STATE_EST_FINI, true);
                        afficheResultat(
                                "Vous avez validé toutes les étapes du  niveau : "
                                        + jeux.getNiveau().getIntituler(),
                                Simon.RESULTAT_TYPE_VALIDIER
                        );
                    }
                    // Validation d'une etape : le jeux continue
                    else{
                        Log.d("INFO", "Etape Valider (+)" );
                        jeux.getNiveau().validerEtape();
                        metAJourLesEtape(jeuxCourrent.getNiveau());
                        jeux.videProposition();
                        jeux.prochaineCombinaisonDeCouleur();
                        afficheCombinaisonCouleurs(jeux.getListCouleursATrouver());
                        jeux.setState(Jeux.STATE_EN_ATTENTE, true);

                    }
                }
                // Mauvaise Proposition de couleurs : ERREUR
                else{
                    if(jeuxCourrent.getNiveau().getIdNiveau() == Simon.NIVEAU_ARCADE){
                        jeux.setState(Jeux.STATE_EST_FINI, true);

                        if(estMeilleur(jeuxCourrent.getNiveau().getNbEtapeValidier())){
                            afficheResultat(
                                    "Votre nouveau record est : "
                                            + jeux.getNiveau().getNbEtapeValidier(),
                                    Simon.RESULTAT_TYPE_BEST_NEW
                            );
                        }else{
                            afficheResultat(
                                    "Votre score est : "
                                            + jeux.getNiveau().getNbEtapeValidier()
                                            + "\n"
                                            + "Votre record est : "
                                            + getBestScore(),
                                    Simon.RESULTAT_TYPE_BEST
                            );
                        }
                    }else{
                        jeux.setState(Jeux.STATE_EST_FINI, true);
                        afficheResultat(
                                "Vous vous êtes trompé. Vous avez validé "
                                        + jeux.getNiveau().getNbEtapeValidier()
                                        + "/" + jeux.getNiveau().getNbEtapesAValider()
                                        + " étapes du niveau  : " + jeux.getNiveau().getIntituler(),
                                Simon.RESULTAT_TYPE_ECHEQUE
                        );
                    }
                }
            }
        }
    }

    /**
     * Sauvgarde le meilleur score de l'utilisateur
     * @param score
     */
    public void saveBestScore(int score) {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        preferences.edit().putInt("best_score", score).apply();
    }

    /**
     * récupère le meilleur score de l'utilisateur
     * @return
     */
    public int getBestScore(){
        return getPreferences(MODE_PRIVATE).getInt("best_score", 0);
    }

    /**
     * Verifie si le score est meilleur que le meilleu score stocké
     * @param scoreAComparer
     * @return
     */
    private boolean estMeilleur(int scoreAComparer){

        if(scoreAComparer > getBestScore()){
            saveBestScore(scoreAComparer);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onClick(View view) {

        if(view.equals(this.imgBtnPlay)){
            commencerJeux();
            Log.i("INFO", "--- DEBUT DU JEUX ---");
        }
        else if(view.equals(this.btnRecommancer)){
            this.layoutResultat.setVisibility(View.INVISIBLE);
            this.jeuxCourrent.restartGame();
            metAJourLesEtape(this.jeuxCourrent.getNiveau());
            commencerJeux();
        }
        else if(view.equals(this.btnRetourAccueil)){
            finish();
        }
        else if(view.equals(this.listeBoutonsCouleurs.get(INDEX_CODE_VERT))){
            if(jeuxCourrent.enAttente()){
                effetSurbriance((Button) view,INDEX_CODE_VERT);
                this.jeuxCourrent.couleurSelectionner(INDEX_CODE_VERT);
                checkStatOfGame(this.jeuxCourrent);
            }
        }
        else if(view.equals(this.listeBoutonsCouleurs.get(INDEX_CODE_ROUGE))){
            if(jeuxCourrent.enAttente()) {
                effetSurbriance((Button) view, INDEX_CODE_ROUGE);
                this.jeuxCourrent.couleurSelectionner(INDEX_CODE_ROUGE);
                checkStatOfGame(this.jeuxCourrent);
            }
        }
        else if(view.equals(this.listeBoutonsCouleurs.get(INDEX_CODE_BLEU))){
            if(jeuxCourrent.enAttente()) {
                effetSurbriance((Button) view, INDEX_CODE_BLEU);
                this.jeuxCourrent.couleurSelectionner(INDEX_CODE_BLEU);
                checkStatOfGame(this.jeuxCourrent);
            }
        }
        else if(view.equals(this.listeBoutonsCouleurs.get(INDEX_CODE_JAUNE))){
            if(jeuxCourrent.enAttente()) {
                effetSurbriance((Button) view, INDEX_CODE_JAUNE);
                this.jeuxCourrent.couleurSelectionner(INDEX_CODE_JAUNE);
                checkStatOfGame(this.jeuxCourrent);
            }
        }
        else if(view.equals(this.listeBoutonsCouleurs.get(INDEX_CODE_VIOLET))){
            if(jeuxCourrent.enAttente()) {
                effetSurbriance((Button) view, INDEX_CODE_VIOLET);
                this.jeuxCourrent.couleurSelectionner(INDEX_CODE_VIOLET);
                checkStatOfGame(this.jeuxCourrent);
            }
        }
        else if(view.equals(this.listeBoutonsCouleurs.get(INDEX_CODE_NOIR))){
            if(jeuxCourrent.enAttente()) {
                effetSurbriance((Button) view, INDEX_CODE_NOIR);
                this.jeuxCourrent.couleurSelectionner(INDEX_CODE_NOIR);
                checkStatOfGame(this.jeuxCourrent);
            }
        }
        else if(view.equals(this.listeBoutonsCouleurs.get(INDEX_CODE_ORANGE))){
            if(jeuxCourrent.enAttente()) {
                effetSurbriance((Button) view, INDEX_CODE_ORANGE);
                this.jeuxCourrent.couleurSelectionner(INDEX_CODE_ORANGE);
                checkStatOfGame(this.jeuxCourrent);
            }
        }
        else if(view.equals(this.listeBoutonsCouleurs.get(INDEX_CODE_TURQUOISE))){
            if(jeuxCourrent.enAttente()) {
                effetSurbriance((Button) view, INDEX_CODE_TURQUOISE);
                this.jeuxCourrent.couleurSelectionner(INDEX_CODE_TURQUOISE);
                checkStatOfGame(this.jeuxCourrent);
            }
        }
        else if(view.equals(this.listeBoutonsCouleurs.get(INDEX_CODE_ARGENT))){
            if(jeuxCourrent.enAttente()) {
                effetSurbriance((Button) view, INDEX_CODE_ARGENT);
                this.jeuxCourrent.couleurSelectionner(INDEX_CODE_ARGENT);
                checkStatOfGame(this.jeuxCourrent);
            }
        }
        else{
            Log.i("INFO", "Aucun bouton associer");
        }

    }

    /**
     * Affiche une combinaison de couleurs
     * @param combinaison
     */
    public void afficheCombinaisonCouleurs(final ArrayList<Integer> combinaison){

        // Cree un nouveau processus
        new Thread(new Runnable(){
            public void run(){
                jeuxCourrent.setState(Jeux.STATE_EN_ATTENTE, false);
                // Pause
                try{
                    Thread.sleep(niveau.getVitesse());
                }catch(Exception e){
                    e.printStackTrace();
                }
                // Met en surbriance le bouton correspondant
                for(int indexCouleur : combinaison){

                    switch(indexCouleur) {
                        case INDEX_CODE_VERT:
                            effetSurbriance(listeBoutonsCouleurs.get(INDEX_CODE_VERT), INDEX_CODE_VERT);
                            break;
                        case INDEX_CODE_ROUGE:
                            effetSurbriance(listeBoutonsCouleurs.get(INDEX_CODE_ROUGE), INDEX_CODE_ROUGE);
                            break;
                        case INDEX_CODE_BLEU:
                            effetSurbriance(listeBoutonsCouleurs.get(INDEX_CODE_BLEU),INDEX_CODE_BLEU );
                            break;
                        case INDEX_CODE_JAUNE:
                            effetSurbriance(listeBoutonsCouleurs.get(INDEX_CODE_JAUNE), INDEX_CODE_JAUNE);
                            break;
                        case INDEX_CODE_VIOLET:
                            effetSurbriance(listeBoutonsCouleurs.get(INDEX_CODE_VIOLET), INDEX_CODE_VIOLET);
                            break;
                        case INDEX_CODE_NOIR:
                            effetSurbriance(listeBoutonsCouleurs.get(INDEX_CODE_NOIR), INDEX_CODE_NOIR);
                            break;
                        case INDEX_CODE_ORANGE:
                            effetSurbriance(listeBoutonsCouleurs.get(INDEX_CODE_ORANGE), INDEX_CODE_ORANGE);
                            break;
                        case INDEX_CODE_TURQUOISE:
                            effetSurbriance(listeBoutonsCouleurs.get(INDEX_CODE_TURQUOISE), INDEX_CODE_TURQUOISE);
                            break;
                        case INDEX_CODE_ARGENT:
                            effetSurbriance(listeBoutonsCouleurs.get(INDEX_CODE_ARGENT), INDEX_CODE_ARGENT);
                            break;
                        default:
                            Log.i("INFO", "Aucune comrepsondance à la couleur");
                            break;
                    }
                    // Pause
                    try{
                        Thread.sleep(niveau.getVitesse());
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                jeuxCourrent.setState(Jeux.STATE_EN_ATTENTE, true);
            }
        }).start();
    }

    /**
     * Applique une animation à un bouton
     * @param btnCouleur
     */
    public void effetSurbriance(Button btnCouleur, int indexCouleursComplementaire){
        ColorDrawable[] color = {
                new ColorDrawable(this.listCouleursDisponibleComplementaire[indexCouleursComplementaire]),
                new ColorDrawable(this.listCouleursDisponible[indexCouleursComplementaire]),
        };
        TransitionDrawable trans = new TransitionDrawable(color);

        Animation animation = AnimationUtils.loadAnimation(btnCouleur.getContext(), R.anim.surbrillance);
        btnCouleur.setBackground(trans);

        trans.startTransition(300);
        btnCouleur.startAnimation(animation);
    }

    /**
     * Affiche le message de resultat : validation ou Echèque
     * @param type
     */
    private void afficheResultat(String info, int type){

        switch(type){
            case Simon.RESULTAT_TYPE_VALIDIER:
                this.txtResultat.setText("Félcicitation !");
                this.imgViewResultat.setImageResource(R.drawable.ic_mood_black_24dp);
                break;
            case Simon.RESULTAT_TYPE_ECHEQUE:
                this.txtResultat.setText("Dommage !");
                this.imgViewResultat.setImageResource(R.drawable.ic_mood_bad_black_24dp);
                break;
            case Simon.RESULTAT_TYPE_BEST_NEW:
                this.txtResultat.setText("Félcicitation !");
                this.imgViewResultat.setImageResource(R.drawable.ic_mood_black_24dp);
                break;
            case Simon.RESULTAT_TYPE_BEST:
                this.txtResultat.setText("Game Over !");
                this.imgViewResultat.setImageResource(R.drawable.ic_mood_bad_black_24dp);
                break;
            default:
                break;
        }
        layoutResultat.setVisibility(View.VISIBLE);
        txtResultatInfo.setText(info);
    }
}