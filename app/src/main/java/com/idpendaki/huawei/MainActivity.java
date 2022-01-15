package com.idpendaki.huawei;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.service.AccountAuthService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvLokasi;
    LokasiAdapter lokasiAdapter;
    ArrayList<String> lokasis = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvLokasi = findViewById(R.id.rvLokasi);
        loadLokasi();
        setAdapter();
    }

    public void setAdapter(){
        lokasiAdapter = new LokasiAdapter(lokasis);
        lokasiAdapter.onItemClick = new LokasiAdapter.onItemClick() {
            @Override
            public void onClick(View view, String lokasi) {
                Intent i = new Intent(MainActivity.this, ListGunungActivity.class);
                i.putExtra(ListGunungActivity.LIST_GUNUNG, lokasi);
                startActivity(i);
            }
        };
        rvLokasi.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvLokasi.setAdapter(lokasiAdapter);
    }

    public String loadLokasi() {
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
                    System.out.println(jsonObject2.getString("lokasi"));
                    lokasis.add(jsonObject2.getString("lokasi"));
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

    public void onClickFloatingButton(View view){
        Intent i = new Intent(MainActivity.this, PeralatanActivity.class);
        startActivity(i);
    }

    private AccountAuthService mAuthService;
    private AccountAuthParams mAuthParam;
    private static final String TAG = "Account";
    public void onCliCkSignout(View view) {
        mAuthParam = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setEmail()
                .createParams();
        mAuthService = AccountAuthManager.getService(this, mAuthParam);

        Task<Void> signOutTask = mAuthService.signOut();
        signOutTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i(TAG, "signOut Success");

                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.i(TAG, "signOut fail");
            }
        });
    }
}

