package com.idpendaki.huawei;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ListGunungActivity extends AppCompatActivity {

    public static String LIST_GUNUNG = "LIST_GUNUNG";
    String gunung;
    TextView tvLokasi;
    Toolbar toolbar;
    RecyclerView rvListGunung;
    GunungAdapater gunungAdapater;

    ArrayList<Gunung> gunungs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gunung);

        gunung = getIntent().getStringExtra(LIST_GUNUNG);

        tvLokasi = findViewById(R.id.tvLokasi);
        rvListGunung = findViewById(R.id.rvListGunung);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        tvLokasi.setText(gunung);

        loadGunung();
        setAdapter();
    }

    public void setAdapter(){
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvListGunung.setLayoutManager(new GridLayoutManager(ListGunungActivity.this, 2));
        } else {
            rvListGunung.setLayoutManager(new GridLayoutManager(ListGunungActivity.this, 3));
        }

        gunungAdapater = new GunungAdapater(gunungs, ListGunungActivity.this);
        gunungAdapater.onItemClick = new GunungAdapater.onItemClick() {
            @Override
            public void onClick(View view, Gunung gunung) {
                Intent i = new Intent(ListGunungActivity.this, DetailGunungActivity.class);
                i.putExtra(DetailGunungActivity.DETAIL_GUNUNG, gunung);
                startActivity(i);
            }
        };

        rvListGunung.setHasFixedSize(true);
        rvListGunung.setAdapter(gunungAdapater);
    }

    public String loadGunung() {
        String json = null;
        try {
            InputStream is = getAssets().open("nama_gunung.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("gunung");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    if (jsonObject2.getString("lokasi").equalsIgnoreCase(gunung)) {
                        JSONArray jsonArray2 = jsonObject2.getJSONArray("nama_gunung");
                        for (int j = 0; j < jsonArray2.length(); j++) {
                            Gunung g = new Gunung();
                            JSONObject gunung_obj = jsonArray2.getJSONObject(j);
                            g.strImageGunung = gunung_obj.getString("image_gunung");
                            g.strNamaGunung = gunung_obj.getString("nama");
                            g.strLokasiGunung = gunung_obj.getString("lokasi");
                            g.strDeskripsi = gunung_obj.getString("deskripsi");
                            g.strInfoGunung = gunung_obj.getString("info_gunung");
                            g.strJalurPendakian = gunung_obj.getString("jalur_pendakian");
                            g.strLat = gunung_obj.getDouble("lat");
                            g.strLong = gunung_obj.getDouble("lon");

                            gunungs.add(g);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}