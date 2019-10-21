package com.mohaiminur.medicinelist.brsbd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Medicine> {

    private List<Medicine> medicineList;
    private Context mCtx;


    public ListViewAdapter(List<Medicine> medicineList, Context mCtx) {
        super(mCtx, R.layout.list_items, medicineList);
        this.medicineList = medicineList;
        this.mCtx = mCtx;


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with xml layout
        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        //getting text views
        TextView drugId = listViewItem.findViewById(R.id.drug_id);
        TextView drugsName = listViewItem.findViewById(R.id.drugs_name);

        //Getting the medicine for the specified position
        Medicine medicine = medicineList.get(position);

        //setting medicine values to textviews
        drugId.setText(medicine.getId());
        drugsName.setText(medicine.getDrugs());

        //returning the listitem
        return listViewItem;
    }

    public void filterlist(ArrayList<Medicine> filterlist) {


        medicineList = filterlist;
        notifyDataSetChanged();
    }


}
