package com.example.non_jid.tabapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng centerLatLng = new LatLng(13.677017, 100.345347);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, 16));

        MapSynShop mapSynShop = new MapSynShop(this, mMap);
        mapSynShop.execute();

    }   // onMapReady

    private class MapSynShop extends AsyncTask<Void, Void, String> {

        //Explicit
        private Context context;
        private GoogleMap myGoogleMap;
        private static final String urlJSON = "http://swiftcodingthai.com/non/get_data_master.php";

        public MapSynShop(Context context, GoogleMap myGoogleMap) {
            this.context = context;
            this.myGoogleMap = myGoogleMap;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                return null;
            }

        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("22JulyV2", "map JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                for (int i=0;i<jsonArray.length();i+=1) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strShop = jsonObject.getString("Shop");
                    String strAddress = jsonObject.getString("Address");
                    double douLat = Double.parseDouble(jsonObject.getString("Lat"));
                    double douLng = Double.parseDouble(jsonObject.getString("Lng"));
                    LatLng latLng = new LatLng(douLat, douLng);
                    myGoogleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
                    .title(strShop)
                    .snippet(strAddress));

                }   //for

            } catch (Exception e) {
                e.printStackTrace();
            }


        }   // onPost

    }   // MaySynShop


    public void onZoom(View view) {
        if (view.getId() == R.id.btnZoomIn) {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if (view.getId() == R.id.btnZoomOut) {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }



}   // Main Class
