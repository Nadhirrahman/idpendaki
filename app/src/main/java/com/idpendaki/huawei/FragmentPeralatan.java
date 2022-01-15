package com.idpendaki.huawei;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPeralatan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPeralatan extends Fragment {

    public FragmentPeralatan() {
        // Required empty public constructor
    }

    public static FragmentPeralatan newInstance() {
        FragmentPeralatan fragment = new FragmentPeralatan();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_peralatan, container, false);

        return v;
    }

    ArrayList<Peralatan> peralatans = new ArrayList<>();
    PeralatanAdapter peralatanAdapter;
    RecyclerView rvPeralatan;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initComponent(view);
        getPeralatanGunung();
        setAdapter();
    }

    public void initComponent(View view){
        rvPeralatan = view.findViewById(R.id.rvPeralatan);
    }

    private void setAdapter(){
        peralatanAdapter = new PeralatanAdapter(peralatans, getContext());
        peralatanAdapter.onItemClick = new PeralatanAdapter.onItemClick() {
            @Override
            public void onClick(View view, Peralatan p) {
                Intent i = new Intent(getContext(), DetailPeralatanActivity.class);
                i.putExtra(DetailPeralatanActivity.DETAIL_PERALATAN, p);
                startActivity(i);
            }
        };
        rvPeralatan.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPeralatan.setAdapter(peralatanAdapter);
        rvPeralatan.setHasFixedSize(true);
    }

    private String getPeralatanGunung() {
        String json = null;
        try {
            InputStream is = getContext().getAssets().open("peralatan.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("peralatan");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    Peralatan peralatan = new Peralatan();
                    peralatan.strNamaPeralatan = jsonObject2.getString("nama");
                    peralatan.strImagePeralatan = jsonObject2.getString("image_url");
                    peralatan.strTipePeralatan = jsonObject2.getString("tipe");
                    peralatan.strDeskripsiPeralatan = jsonObject2.getString("deskripsi");
                    peralatan.strTipsPeralatan = jsonObject2.getString("tips");
                    peralatans.add(peralatan);
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
}