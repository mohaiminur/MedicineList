package com.mohaiminur.medicinelist.brsbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String JSON_URL = "http://brsbd.org/services/medicineList.php";
    public ListViewAdapter adapter;
    ListView listView;
    EditText Search;
    ArrayList<Medicine> medicineList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        Search = findViewById(R.id.search);
        medicineList = new ArrayList<>();

        loadMedicineList();

        Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                try {
                    filter(editable.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void filter(String text) {
        ArrayList<Medicine> filterlist = new ArrayList<>();
        for (Medicine item : medicineList) {
            if (item.getDrugs().toLowerCase().contains((text.toLowerCase()))) {
                filterlist.add(item);
            }

        }
        adapter.filterlist(filterlist);
    }


    private void loadMedicineList() {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            JSONObject obj = new JSONObject(response);


                            final JSONArray medicineArray = obj.getJSONArray("medicines");

                            for (int i = 0; i < medicineArray.length(); i++) {

                                JSONObject medicineObject = medicineArray.getJSONObject(i);

                                Medicine medicine = new Medicine(medicineObject.getString("id"), medicineObject.getString("drugs"),
                                        medicineObject.getString("indications"), medicineObject.getString("therapeutic_class"),
                                        medicineObject.getString("pharmacology"), medicineObject.getString("dosage"),
                                        medicineObject.getString("interaction"), medicineObject.getString("contraindications"),
                                        medicineObject.getString("side_effects"), medicineObject.getString("pregnancy"),
                                        medicineObject.getString("precautions"), medicineObject.getString("storage"));

                                //adding the medicine details to medicinelist
                                medicineList.add(medicine);

                            }

                            //creating custom adapter object
                            adapter = new ListViewAdapter(medicineList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);


                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Medicine m = medicineList.get(i);

                                    String drugs = m.getDrugs();
                                    String indications = m.getIndications();
                                    String therapeutic_class = m.getTherapeutic_class();
                                    String pharmacology = m.getPharmacology();
                                    String dosage = m.getDosage();
                                    String interaction = m.getInteraction();
                                    String contraindications = m.getContraindications();
                                    String side_effects = m.getSide_effects();
                                    String pregnancy = m.getPregnancy();
                                    String precautions = m.getPrecautions();
                                    String storage = m.getStorage();

                                    Bundle bundle = new Bundle();
                                    bundle.putString("DRUGS", drugs);
                                    bundle.putString("INDICATIONS", indications);
                                    bundle.putString("THERAPEUTIC_CLASS", therapeutic_class);
                                    bundle.putString("PHARMACOLOGY", pharmacology);
                                    bundle.putString("DOSAGE", dosage);
                                    bundle.putString("INTERACTION", interaction);
                                    bundle.putString("CONTRAINDICATIONS", contraindications);
                                    bundle.putString("SIDE_EFFECTS", side_effects);
                                    bundle.putString("PREGNANCY", pregnancy);
                                    bundle.putString("PRECAUTIONS", precautions);
                                    bundle.putString("STORAGE", storage);

                                    Intent intent = new Intent(MainActivity.this, MedicineDeatils.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cacheHitButRefreshed = 3 * 60 * 1000;
                    final long cacheExpired = 72 * 60 * 60 * 1000;
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHitButRefreshed;
                    final long ttl = now + cacheExpired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = softExpire;
                    cacheEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    headerValue = response.headers.get("Last-Modified");
                    if (headerValue != null) {
                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(new String(jsonString), cacheEntry);
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }

            @Override
            public void deliverError(VolleyError error) {
                super.deliverError(error);
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }
        };
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }


}