package it.unipd.dei.esp1920.quickynews.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import it.unipd.dei.esp1920.quickynews.R;

public class CategoriesSettings extends Settings {

    private final String TAG="Categories1";
    private Toolbar mToolbar;
    private static boolean bln_cb_sport,bln_cb_tech,bln_cb_food,bln_cb_mot; //variabili per salvare lo stato delle categorie



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.categories_settings);

        //TOOLBAR
        mToolbar=findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);              //rende predefinata la toolbar creata
        getSupportActionBar().setTitle("Choose categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        bln_cb_sport = preferences.getBoolean("chBoxSport",false);
        bln_cb_tech = preferences.getBoolean("chBoxTech",false);
        bln_cb_food = preferences.getBoolean("chBoxFood",false);
        bln_cb_mot = preferences.getBoolean("chBoxMot",false);
        CheckBox mSelectedSport=(CheckBox)findViewById(R.id.checkBox_sport);
        CheckBox mSelectedTech=(CheckBox)findViewById(R.id.checkBox_tech);
        CheckBox mSelectedFood=(CheckBox)findViewById(R.id.checkBox_business);
        CheckBox mSelectedMot=(CheckBox)findViewById(R.id.checkBox_science);
        mSelectedSport.setChecked(bln_cb_sport);
        mSelectedTech.setChecked(bln_cb_tech);
        mSelectedFood.setChecked(bln_cb_food);
        mSelectedMot.setChecked(bln_cb_mot);




    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //------SALVATAGGIO STATO
        CheckBox mSelectedSport=(CheckBox)findViewById(R.id.checkBox_sport);
        CheckBox mSelectedTech=(CheckBox)findViewById(R.id.checkBox_tech);
        CheckBox mSelectedFood=(CheckBox)findViewById(R.id.checkBox_business);
        CheckBox mSelectedMot=(CheckBox)findViewById(R.id.checkBox_science);
        bln_cb_sport=mSelectedSport.isChecked();
        bln_cb_tech=mSelectedTech.isChecked();
        bln_cb_food=mSelectedFood.isChecked();
        bln_cb_mot=mSelectedMot.isChecked();

        editor.putBoolean("chBoxSport", bln_cb_sport);
        editor.putBoolean("chBoxTech", bln_cb_tech);
        editor.putBoolean("chBoxFood", bln_cb_food);
        editor.putBoolean("chBoxMot", bln_cb_mot);

        // Commit to storage synchronously
        editor.commit();
    }


    public static boolean getPreferenceSport(){

        //bln_cb_sport=testSport();
        return bln_cb_sport;
    }

    public boolean testSport(){
        SharedPreferences pref= getPreferences(MODE_PRIVATE);
        bln_cb_sport = pref.getBoolean("chBoxSport",false);
        return bln_cb_sport;

    }


    public static boolean getPreferenceTech(){
        return bln_cb_tech;
    }

    public static boolean getPreferenceBusiness(){
        return bln_cb_food; //TODO: CHANGE NAME
    }

    public static boolean getPreferenceScience(){
        return bln_cb_mot; //TODO:CHANGE NAME
    }



}
