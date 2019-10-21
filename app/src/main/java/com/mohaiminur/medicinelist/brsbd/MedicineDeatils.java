package com.mohaiminur.medicinelist.brsbd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MedicineDeatils extends AppCompatActivity {

    TextView drugs_namee,indicationss,therapeutic_classs,pharmacologys,dosages,interactions,
            contraindicationss,side_effectss,pregnancys,precautionss,storages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_deatils);

        drugs_namee= findViewById(R.id.drugs_namee);
        indicationss= findViewById(R.id.indications);
        therapeutic_classs= findViewById(R.id.therapeutic_class);
        pharmacologys= findViewById(R.id.pharmacology);
        dosages= findViewById(R.id.dosage);
        interactions= findViewById(R.id.interaction);
        contraindicationss= findViewById(R.id.contraindications);
        side_effectss= findViewById(R.id.side_effects);
        pregnancys= findViewById(R.id.pregnancy);
        precautionss= findViewById(R.id.precautions);
        storages= findViewById(R.id.storage);

        Bundle bundle=getIntent().getExtras();

        String drugs = bundle.getString("DRUGS");
        String indications = bundle.getString("INDICATIONS");
        String therapeutic_class = bundle.getString("THERAPEUTIC_CLASS");
        String pharmacology=bundle.getString("PHARMACOLOGY");
        String dosage=bundle.getString("DOSAGE");
        String interaction=bundle.getString("INTERACTION");
        String contraindications=bundle.getString("CONTRAINDICATIONS");
        String side_effects=bundle.getString("SIDE_EFFECTS");
        String pregnancy=bundle.getString("PREGNANCY");
        String precautions=bundle.getString("PRECAUTIONS");
        String storage=bundle.getString("STORAGE");

        drugs_namee.setText(drugs);
        indicationss.setText(indications);
        therapeutic_classs.setText(therapeutic_class);
        pharmacologys.setText(pharmacology);
        dosages.setText(dosage);
        interactions.setText(interaction);
        contraindicationss.setText(contraindications);
        side_effectss.setText(side_effects);
        pregnancys.setText(pregnancy);
        precautionss.setText(precautions);
        storages.setText(storage);

    }
}
