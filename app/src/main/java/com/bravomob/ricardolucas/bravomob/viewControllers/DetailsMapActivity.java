package com.bravomob.ricardolucas.bravomob.viewControllers;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bravomob.ricardolucas.bravomob.R;
import com.bravomob.ricardolucas.bravomob.connection.BancoController;
import com.bravomob.ricardolucas.bravomob.model.SinalGps;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailsMapActivity extends FragmentActivity {

    private GoogleMap mMap;
    private ImageView imageView;

    private TextView textViewName , textViewWeek, textViewHour, textViewStreet;

    private Integer itens;


    double detalheLat , detalheLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_map);

        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        textViewName = findViewById(R.id.tvName);
        textViewWeek = findViewById(R.id.tvWeek);
        textViewHour = findViewById(R.id.tvHour);
        textViewStreet = findViewById(R.id.tvStreet);

        Bundle extras = getIntent().getExtras();
        itens = extras.getInt("ids");

        BancoController bancoController = new BancoController(DetailsMapActivity.this);
        SinalGps sinalGps = bancoController.getPotsItem(itens);

        textViewName.setText(sinalGps.getName());
        textViewHour.setText(sinalGps.getTime());
        textViewStreet.setText(sinalGps.getAddress());
        //textViewWeek.setText(sinalGps.get());

        detalheLat = sinalGps.getLatitude();
        detalheLong = sinalGps.getLatitude();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                // Add a marker in Sydney and move the camera
                LatLng newPosition = new LatLng(detalheLong, detalheLat);
                mMap.addMarker(new MarkerOptions().position(newPosition).title("Marker Details :)"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(newPosition));
            }
        });
    }
}
