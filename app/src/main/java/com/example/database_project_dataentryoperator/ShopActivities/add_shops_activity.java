package com.example.database_project_dataentryoperator.ShopActivities;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.database_project_dataentryoperator.ActioBarAdapter.TitleNavigationAdapter;
import com.example.database_project_dataentryoperator.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class add_shops_activity  extends AppCompatActivity implements ActionBar.OnNavigationListener, OnMapReadyCallback
{
    // action bar
    private ActionBar actionBar;

    private TitleNavigationAdapter adapter;

    SearchManager searchManager;
    SearchView searchView;

    // Refresh menu item
    private MenuItem refreshMenuItem;
    
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

        actionBar = getActionBar();

        cnfrml=findViewById(R.id.cnfrm_coordinates);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.add_shop_map);
        mapFragment.getMapAsync(this);
        cnfrml.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(add_shops_activity.this, Shop_details_activity.class);
                intent.putExtra("latlan",coordinates);

                startActivity(intent);

            }
        });
    }
    //action bar this ahead
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
        // Associate searchable configuration with the SearchView
        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                //Here u can get the value "query" which is entered in the search box.
                searchView.setIconified(true);
                searchView.onActionViewCollapsed();
                onMapSearch(query );

                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onCreateOptionsMenu(menu);

    }
    /**
     * On selecting action bar icons
     **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_search:
                // search action
                return true;
           /* case R.id.action_location_found:
                // location found

                return true;
            case R.id.action_refresh:
                // refresh
                refreshMenuItem = item;
                // load the data from server
                new SyncData().execute();

                return true;
            case R.id.action_help:
                // help action
                return true;
            case R.id.action_check_updates:
                // check for updates action
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Actionbar navigation item select listener
     * */
    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        // Action to be taken after selecting a spinner item
        return false;
    }


    /**
     * Async task to load the data from server
     * **/
    private class SyncData extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            // set the progress bar view
            refreshMenuItem.setActionView(R.layout.action_progressbar);
            refreshMenuItem.expandActionView();
        }

        @Override
        protected String doInBackground(String... params) {
            // not making real request in this demo
            // for now we use a timer to wait for sometime
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            refreshMenuItem.collapseActionView();
            // remove the progress bar view
            refreshMenuItem.setActionView(null);
        }
    };
    public void onMapSearch(String location) {

        List<Address> addressList = null;
        /*if(location.isEmpty())
        {
            locationSearch.setError(getResources().getString(R.string.error));
        }*/
        if (!location.isEmpty()) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title(location);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            // mMap.addMarker(new MarkerOptions().position(latLng).title(location));
            mMap.addMarker(markerOptions);
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }
    //location thing ahead
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        if (mMap != null) {
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setZoomGesturesEnabled(true);
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
