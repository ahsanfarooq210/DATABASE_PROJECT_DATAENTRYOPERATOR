package com.example.database_project_dataentryoperator;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class add_shops_activity  extends FragmentActivity implements OnMapReadyCallback
{

    private GoogleMap mMap;
    private Geocoder geo;
    private List<Address> address;
    private LatLng coordinates;
    private Button cnfrml;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shops_activity);
        cnfrml=findViewById(R.id.cnfrm_coordinates);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        cnfrml.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(add_shops_activity.this,Shop_details_activity.class);
                intent.putExtra("latlan",coordinates);

                startActivity(intent);

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        if (mMap != null) {
            geo = new Geocoder(add_shops_activity.this, Locale.getDefault());

            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    coordinates=latLng;
                    try {

                            if (geo == null)
                            {
                                geo = new Geocoder(add_shops_activity.this, Locale.getDefault());
                            }

                             address= geo.getFromLocation(latLng.latitude, latLng.longitude, 1);
                            mMap.clear();
                                mMap.addMarker(new MarkerOptions().position(latLng).title("Name:" + address.get(0).getCountryName()
                                        + ". Address:" + address.get(0).getAddressLine(0)));
                                Toast.makeText(add_shops_activity.this, "Name:" + address.get(0).getCountryName() + ". Address:" + address.get(0).getAddressLine(0), Toast.LENGTH_SHORT).show();
                                 cnfrml.setVisibility(View.VISIBLE);
                        } catch (IOException ex) {
                            if (ex != null)
                                Toast.makeText(add_shops_activity.this, "Error:" + ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                }
            });

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Toast.makeText(add_shops_activity.this, marker.getTitle().toString() + " Lat:" + marker.getPosition().latitude + " Long:" + marker.getPosition().longitude, Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
    }
}
