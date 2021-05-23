package com.example.veterans_code_a_thon_android;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Map extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnMarkerClickListener {
    private GoogleMap gMap;
    private GoogleApiClient googleApi;
    private Marker userLocation;
    private static final int Request_User_Location_Code = 99;
    //FirebaseDatabase firebaseDatabase;
    //DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_map);
        //firebaseDatabase = FirebaseDatabase.getInstance();

        for(int i = 0; i < 185; i++) {
            //databaseReference = firebaseDatabase.getReference(String.valueOf(i));
            //getdata();
        }

        // Check user permission for location
        checkUserLocationPermission();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /*private void getdata() {
       databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(Map.this, "DATA", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Map.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    // Check client location permission
    public boolean checkUserLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            return false;
        }
        else {
            return true;
        }
    }

    // When maps first starts up
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            gMap.setMyLocationEnabled(true);
        }
        gMap.setOnMarkerClickListener(this);
    }

    // Accessing google services
    protected synchronized void buildGoogleApiClient() {
        googleApi = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        googleApi.connect();
    }

    // Go to last location, and pin store location
    @SuppressWarnings("deprecation")
    public void onLocationChanged(Location location) {

        if (userLocation != null) {
            userLocation.remove();
        }

        // Get user's location
        LatLng lastLocation = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(lastLocation, 15.5f);
        gMap.moveCamera(update);
        // Add marker and snippet
        MarkerOptions options = new MarkerOptions();
        options.position(lastLocation);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        options.snippet("You");
        gMap.addMarker(options);

        String[] businessNames = {"Business1","Business2","Business3","Business4","Business5"};
        double[] newLatitude = {29.427368305394978,29.426910419300256,29.427358960801435,29.425536748631185,29.426574011874475};
        double[] newLongitude = {-98.49412054593566,-98.49495739514481,-98.49416346127971,-98.49474281842453,-98.49167437132428};

        // Create marks of the preset locations
        for (int i = 0; i < newLatitude.length; i++) {
            LatLng businessLocation = new LatLng(newLatitude[i], newLongitude[i]);
            // Add marker and snippet
            MarkerOptions info = new MarkerOptions();
            info.position(businessLocation);
            info.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            info.snippet(businessNames[i]);
            gMap.addMarker(info);
        }

        if (googleApi != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApi, this);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest request = new LocationRequest();
        request.setInterval(1100);
        request.setFastestInterval(1100);
        request.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApi, request, this);
        }
    }

    //  @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.hideInfoWindow();
        String passName = marker.getSnippet();
        Intent i = new Intent(this, InformationPopUp.class);
        i.putExtra("businessPassed", passName);
        startActivity(i);
        gMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
        return false;
    }
}