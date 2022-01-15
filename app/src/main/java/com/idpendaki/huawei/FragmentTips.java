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
 * Use the {@link FragmentTips#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTips extends Fragment {

    public FragmentTips() {
        // Required empty public constructor
    }

    public static FragmentTips newInstance() {
        FragmentTips fragment = new FragmentTips();
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
        View v = inflater.inflate(R.layout.fragment_tips, container, false);

        return v;
    }

    ArrayList<Peralatan> peralatans = new ArrayList<>();
    RecyclerView rvTips;
    TipsAdapter tipsAdapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initComponent(view);
        getPeralatanGunung();
        setAdapter();
    }

    public void initComponent(View view){
        rvTips = view.findViewById(R.id.rvTips);
    }

    private void setAdapter(){
        tipsAdapter = new TipsAdapter(peralatans, getContext());
        tipsAdapter.onItemClick = new TipsAdapter.onItemClick() {
            @Override
            public void onClick(View view, Peralatan p) {
                Intent i = new Intent(getContext(), DetailTipsActivity.class);
                i.putExtra(DetailTipsActivity.DETAIL_PERALATAN, p);
                startActivity(i);
            }
        };
        rvTips.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTips.setAdapter(tipsAdapter);
        rvTips.setHasFixedSize(true);
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