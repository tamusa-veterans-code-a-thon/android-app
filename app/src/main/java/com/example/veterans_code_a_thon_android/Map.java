package com.example.veterans_code_a_thon_android;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
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

public class Map extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private GoogleMap gMap;
    private GoogleApiClient googleApi;
    private LocationRequest Request;
    private Location lastLocation;
    private Marker userLocation;
    private static final int Request_User_Location_Code = 99;
    private boolean active = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_map);

        // Check user permission for location
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkUserLocationPermission();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

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

        gMap.setOnMarkerClickListener(marker -> {
            Intent i = new Intent(this, InformationPopUp.class);
            startActivity(i);
            gMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
            return true;
        });
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
        lastLocation = location;

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
        gMap.addMarker(options);

        if (googleApi != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApi, this);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Request = new LocationRequest();
        Request.setInterval(1100);
        Request.setFastestInterval(1100);
        Request.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApi, Request, this);
        }
    }

    //  @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}