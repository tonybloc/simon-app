package top.tonymochel.simon;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import top.tonymochel.simon.model.Niveau;
import top.tonymochel.simon.model.Simon;

/**
 * Activity : Menu de l'application
 */
public class MainActivity extends AppCompatActivity {

    private Simon jeuSimon;
    private Button btnNiveauDebutant;
    private Button btnNiveauIntermediaire;
    private Button btnNiveauDifficile;
    private Button btnNiveauExpert;
    private Button btnNiveauArcade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.jeuSimon = new Simon();
        genereLesNiveaux();
        initialiseLesAction();
    }

    /**
     * Initialise les niveaux
     */
    private void genereLesNiveaux(){

        ArrayList<Button> lesBoutonsNiveau = new ArrayList<>();
        LinearLayout listeDesNiveau = findViewById(R.id.layoutListeDesButtons);

        // Liste des boutons associé à un niveau
        for ( Niveau unNiveau : this.jeuSimon.getLesNiveaux() ) {

            Button unBouton = new Button(this);
            unBouton.setPadding(10,10,10,10);
            unBouton.setText(unNiveau.getIntituler());
            lesBoutonsNiveau.add(unBouton);
        }

        // Initialise les boutons
        for (int index = 0; index < lesBoutonsNiveau.size(); index++){

            switch (index){
                case Simon.NIVEAU_DEBUTANT :
                    this.btnNiveauDebutant = lesBoutonsNiveau.get(index);
                    listeDesNiveau.addView(this.btnNiveauDebutant);
                    break;
                case Simon.NIVEAU_INTERMEDIAIRE :
                    this.btnNiveauIntermediaire = lesBoutonsNiveau.get(index);
                    listeDesNiveau.addView(this.btnNiveauIntermediaire);
                    break;
                case Simon.NIVEAU_DIFFICILE :
                    this.btnNiveauDifficile = lesBoutonsNiveau.get(index);
                    listeDesNiveau.addView(this.btnNiveauDifficile);
                    break;
                case Simon.NIVEAU_EXPERT:
                    this.btnNiveauExpert = lesBoutonsNiveau.get(index);
                    listeDesNiveau.addView(this.btnNiveauExpert);
                    break;
                case Simon.NIVEAU_ARCADE:
                    this.btnNiveauArcade = lesBoutonsNiveau.get(index);
                    listeDesNiveau.addView(this.btnNiveauArcade);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Initialise les évenement associé au composants
     */
    private void initialiseLesAction(){

        btnNiveauDebutant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startNiveauDebutant();
            }
        });
        btnNiveauIntermediaire.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startNiveauIntermediaire();
            }
        });
        btnNiveauDifficile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startNiveauDifficile();
            }
        });
        btnNiveauExpert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startNiveauExpert();
            }
        });
        btnNiveauArcade.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startNiveauArcade();
            }
        });
    }

    /**
     * Demarre une nouvelle activitée
     */
    private void startNiveauDebutant(){
        Intent niveauDebutantActivity = new Intent(MainActivity.this, GameActivity.class);
        niveauDebutantActivity.putExtra("NIVEAU", this.jeuSimon.getLesNiveaux().get(Simon.NIVEAU_DEBUTANT));
        startActivity(niveauDebutantActivity);
    }

    /**
     * Demarre une nouvelle activitée
     */
    public void startNiveauIntermediaire(){
        Intent niveauIntermediaireActivity = new Intent(MainActivity.this, GameActivity.class);
        niveauIntermediaireActivity.putExtra("NIVEAU", this.jeuSimon.getLesNiveaux().get(Simon.NIVEAU_INTERMEDIAIRE));
        startActivity(niveauIntermediaireActivity);
    }

    /**
     * Demarre une nouvelle activitée
     */
    public void startNiveauDifficile(){
        Intent niveauDifficileActivity = new Intent(MainActivity.this, GameActivity.class);
        niveauDifficileActivity.putExtra("NIVEAU", this.jeuSimon.getLesNiveaux().get(Simon.NIVEAU_DIFFICILE));
        startActivity(niveauDifficileActivity);
    }
    /**
     * Demarre une nouvelle activitée
     */
    public void startNiveauExpert(){
        Intent niveauExpertActivity = new Intent(MainActivity.this, GameActivity.class);
        niveauExpertActivity.putExtra("NIVEAU", this.jeuSimon.getLesNiveaux().get(Simon.NIVEAU_EXPERT));
        startActivity(niveauExpertActivity);
    }
    public void startNiveauArcade(){
        Intent niveauExpertActivity = new Intent(MainActivity.this, GameActivity.class);
        niveauExpertActivity.putExtra("NIVEAU", this.jeuSimon.getLesNiveaux().get(Simon.NIVEAU_ARCADE));
        startActivity(niveauExpertActivity);
    }
}
