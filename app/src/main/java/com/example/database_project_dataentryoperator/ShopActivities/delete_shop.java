package com.example.database_project_dataentryoperator.ShopActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.database_project_dataentryoperator.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class delete_shop extends AppCompatActivity implements ActionBar.OnNavigationListener
{
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private List<ShopDetails> shopDetailsArrayList;

    //search view
    private ActionBar actionBar;
    SearchManager searchManager;
    SearchView searchView;
    // Refresh menu item
    private MenuItem refreshMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_shop);
        actionBar = getActionBar();

        recyclerView = findViewById(R.id.delete_shop_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("SHOP");
        shopDetailsArrayList = new ArrayList<>();

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
                searchViewRecycleView (query );

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

                return true;*/
            case R.id.action_refresh:
                // refresh
                refreshMenuItem = item;
                // load the data from server
                new SyncData().execute();

                return true;/*
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
            searchViewRecycleView("");
        }
    };
    public void searchViewRecycleView(String search) {

        try{
            //Toast.makeText(delete_shop.this.toString(),Toast.LENGTH_LONG).show();
            ((DeleteShopRvAapter)recyclerView.getAdapter()).updateList(search,shopDetailsArrayList);
        }
        catch (Exception e){}
    }

    @Override
    protected void onStart()
    {

        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                shopDetailsArrayList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    shopDetailsArrayList.add(snapshot.getValue(ShopDetails.class));
                }
                DeleteShopRvAapter deleteShopRvAapter=new DeleteShopRvAapter(shopDetailsArrayList,delete_shop.this);
                recyclerView.setAdapter(deleteShopRvAapter);
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}