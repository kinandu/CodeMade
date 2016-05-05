package com.guide.farmer.farmerguide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

public class LanguageSelector extends AppCompatActivity {

    private Spinner spinner;
    private Button btnok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selector);

        spinner= (Spinner) findViewById(R.id.spinner);
        btnok= (Button) findViewById(R.id.button_ok);
        btnok.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                     }
                                 }
        );
        getlanguage();

       spinner.setAdapter(new ArrayAdapter<String>(this,R.layout.spinner_item,getResources().getStringArray(R.array.languages)));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               final int items=spinner.getSelectedItemPosition();

                        switch(items) {
                            case 0: //English
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "en").commit();
                                setLangRecreate("en");
                                return;
                            case 1: //telugu
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "te").commit();
                                setLangRecreate("te");
                                return;
                            default: //By default set to english
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("LANG", "en").commit();
                                setLangRecreate("en");
                                return;
                        }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void getlanguage(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration config = getBaseContext().getResources().getConfiguration();

        String lang = settings.getString("LANG", "");
        if (! "".equals(lang) && ! config.locale.getLanguage().equals(lang)) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            getlanguage();
        }

    }
    public void setLangRecreate(String langval) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale(langval);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

       //recreate();

    }

}
