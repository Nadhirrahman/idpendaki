package com.idpendaki.huawei;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huawei.hms.maps.CameraUpdate;
import com.huawei.hms.maps.CameraUpdateFactory;
import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.MapsInitializer;
import com.huawei.hms.maps.OnMapReadyCallback;
import com.huawei.hms.maps.SupportMapFragment;
import com.huawei.hms.maps.model.CameraPosition;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.MarkerOptions;
import com.idpendaki.huawei.Gunung;
import com.idpendaki.huawei.R;

public class DetailGunungActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static String DETAIL_GUNUNG = "DETAIL_GUNUNG";
    Gunung gunung;
    TextView tvNamaGunung, tvLokasiGunung, tvDeskripsi, tvJalurGunung, tvInfoGunung;
    ImageView imageGunung;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gunung);

        gunung = getIntent().getParcelableExtra(DETAIL_GUNUNG);
        setComponents();

        MapsInitializer.setApiKey("DAEDACaSkv8ZSj+GbS+Kvi6kGDGhtnyf341pHGbYEofQXzLf/JnKDzTX8liHGmEphvDTJPvKuYLjguSVVO1mwsRMcrTNTaDbR2e9Sw==");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }

    public void setComponents(){
        tvNamaGunung = findViewById(R.id.tvNamaGunung);
        tvLokasiGunung = findViewById(R.id.tvLokasiGunung);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
        tvJalurGunung = findViewById(R.id.tvJalurGunung);
        tvInfoGunung = findViewById(R.id.tvInfoGunung);
        imageGunung = findViewById(R.id.imageGunung);
        toolbar = findViewById(R.id.toolbar);

        tvNamaGunung.setText(gunung.strLokasiGunung);
        tvLokasiGunung.setText(gunung.strNamaGunung);
        tvDeskripsi.setText(gunung.strDeskripsi);
        tvJalurGunung.setText(gunung.strJalurPendakian);
        tvInfoGunung.setText(gunung.strInfoGunung);

        Glide.with(this)
                .load(gunung.strImageGunung)
                .into(imageGunung);

        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    HuaweiMap map;
    @Override
    public void onMapReady(HuaweiMap huaweiMap) {
        map = huaweiMap;

        float zoom = .0f;

        System.out.println(gunung.strLat + "----");
        System.out.println(gunung.strLong + "----");
        LatLng latLng = new LatLng(gunung.strLat, gunung.strLong);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10f);
        map.moveCamera(cameraUpdate);
        map.setMaxZoomPreference(10);
        map.setMinZoomPreference(7);

        map.addMarker(new MarkerOptions().position(latLng).clusterable(true));
    }
}