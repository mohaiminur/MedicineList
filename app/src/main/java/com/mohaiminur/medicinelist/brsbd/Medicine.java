package com.mohaiminur.medicinelist.brsbd;

import java.util.ArrayList;

public class Medicine extends ArrayList {
    String id,drugs,indications,therapeutic_class,pharmacology,dosage,interaction,contraindications,side_effects,pregnancy,precautions,storage;


    public Medicine(String id, String drugs, String indications, String therapeutic_class, String pharmacology, String dosage, String interaction, String contraindications, String side_effects, String pregnancy, String precautions, String storage) {
        this.id = id;
        this.drugs = drugs;
        this.indications = indications;
        this.therapeutic_class = therapeutic_class;
        this.pharmacology = pharmacology;
        this.dosage = dosage;
        this.interaction = interaction;
        this.contraindications = contraindications;
        this.side_effects = side_effects;
        this.pregnancy = pregnancy;
        this.precautions = precautions;
        this.storage = storage;
    }

    public Medicine(String drugs) {
        this.drugs=drugs;
    }


    public String getId() {
        return id;
    }

    public String getDrugs() {
        return drugs;
    }

    public String getIndications() {
        return indications;
    }

    public String getTherapeutic_class() {
        return therapeutic_class;
    }

    public String getPharmacology() {
        return pharmacology;
    }

    public String getDosage() {
        return dosage;
    }

    public String getInteraction() {
        return interaction;
    }

    public String getContraindications() {
        return contraindications;
    }

    public String getSide_effects() {
        return side_effects;
    }

    public String getPregnancy() {
        return pregnancy;
    }

    public String getPrecautions() {
        return precautions;
    }

    public String getStorage() {
        return storage;
    }


}
