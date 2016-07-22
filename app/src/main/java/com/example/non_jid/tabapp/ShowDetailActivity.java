package com.example.non_jid.tabapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowDetailActivity extends FragmentActivity implements OnMapReadyCallback {

    //Explicit
    private GoogleMap mMap;
    private String shopString, addressString, phoneString,
            promoteString, latString, lngString;
    private TextView shopTextView, addressTextView, phoneTextView, promoteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_show_detail);

        //Bind Widget
        bindWidget();

        //Show Text
        showText();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }   // onCreate

    public void clickBackShowDetail(View view) {
        finish();
    }


    private void showText() {

        shopString = getIntent().getStringExtra("Shop");
        addressString = getIntent().getStringExtra("Address");
        phoneString = getIntent().getStringExtra("Phone");
        promoteString = getIntent().getStringExtra("Promote");

        shopTextView.setText(shopString);
        addressTextView.setText("Address : " + addressString);
        phoneTextView.setText("Phone : " + phoneString);
        promoteTextView.setText("Promotion : " + promoteString);

    }

    private void bindWidget() {

        shopTextView = (TextView) findViewById(R.id.textView5);
        addressTextView = (TextView) findViewById(R.id.textView6);
        phoneTextView = (TextView) findViewById(R.id.textView7);
        promoteTextView = (TextView) findViewById(R.id.textView8);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double douLat = Double.parseDouble(getIntent().getStringExtra("Lat"));
        double douLng = Double.parseDouble(getIntent().getStringExtra("Lng"));

        LatLng latLng = new LatLng(douLat, douLng);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        mMap.addMarker(new MarkerOptions()
        .position(latLng)
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
        .title(shopString)
        .snippet(phoneString));

    }   // onMapReady

}   //Main Class
