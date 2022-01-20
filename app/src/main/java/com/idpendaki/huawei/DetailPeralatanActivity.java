package com.idpendaki.huawei;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idpendaki.huawei.Peralatan;
import com.idpendaki.huawei.R;

public class DetailPeralatanActivity extends AppCompatActivity {

    public static String DETAIL_PERALATAN = "DETAIL_PERALATAN";

    Toolbar toolbar;
    Peralatan peralatan;
    ImageView imageAlat;
    TextView tvNamaAlat, tvDetailAlat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_peralatan);

        peralatan = getIntent().getParcelableExtra(DETAIL_PERALATAN);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageAlat = findViewById(R.id.imageAlat);
        Glide.with(this)
                .load(peralatan.strImagePeralatan)
                .into(imageAlat);

        tvNamaAlat = findViewById(R.id.tvNamaAlat);
        tvDetailAlat = findViewById(R.id.tvDetailAlat);
        tvNamaAlat.setText(peralatan.strNamaPeralatan);
        tvDetailAlat.setText(peralatan.strDeskripsiPeralatan);
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