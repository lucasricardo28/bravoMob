package com.bravomob.ricardolucas.bravomob.viewControllers;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bravomob.ricardolucas.bravomob.DetailsMapActivity;
import com.bravomob.ricardolucas.bravomob.R;
import com.bravomob.ricardolucas.bravomob.connection.BancoController;
import com.bravomob.ricardolucas.bravomob.model.SinalGps;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private List<SinalGps> listPots = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        insertItens();

    }

    public void insertItens(){
        BancoController bancoController = new BancoController(getApplicationContext());
        bancoController.inserirPosition(-3.785515,-38.6301591,"CASA 1","RUA da casa 1","12:00 as 13:00");
        bancoController.inserirPosition(-3.783599,-38.6314677,"CASA B","RUA a casa 2","1:00 as 2:00");
        bancoController.inserirPosition(-3.783262,-38.6289143,"CASA C","RUA da casa 3","5:00 as 9:00");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        BancoController bancoController = new BancoController(getApplicationContext());
        listPots = bancoController.getPots();
        LatLng latDefault = new LatLng(-3.7870768,-38.6286736);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latDefault));

        for (SinalGps sinalGps: listPots){

            LatLng l = new LatLng(sinalGps.getLongitude(),sinalGps.getLatitude());
            mMap.addMarker(new MarkerOptions().position(l).title(sinalGps.getName()).snippet("localizacao")).setTag(sinalGps.getId());
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    Intent intent = new Intent(getApplicationContext(), DetailsMapActivity.class);
                    intent.putExtra("client_id",marker.getTag().toString());
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), marker.getTag()+"" , Toast.LENGTH_LONG).show();
                    return false;
                }
            });
        }

    }
}
