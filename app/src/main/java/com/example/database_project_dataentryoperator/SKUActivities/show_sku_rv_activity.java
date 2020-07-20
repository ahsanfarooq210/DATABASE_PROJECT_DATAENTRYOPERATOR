package com.example.database_project_dataentryoperator.SKUActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.database_project_dataentryoperator.R;
import com.example.database_project_dataentryoperator.ShopActivities.DeleteShopRvAapter;
import com.example.database_project_dataentryoperator.ShopActivities.delete_shop;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class show_sku_rv_activity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private DatabaseReference skuReference;
    private List<Sku> skuArrayList;
    //search view
    private ActionBar actionBar;
    SearchManager searchManager;
    SearchView searchView;
    // Refresh menu item
    private MenuItem refreshMenuItem;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sku_rv_activity);
        actionBar = getActionBar();
        recyclerView=findViewById(R.id.show_sku_recycler_view);
        skuReference= FirebaseDatabase.getInstance().getReference("SKU");
        skuArrayList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    //action bar this ahead
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_for_delete, menu);
        // Associate searchable configuration with the SearchView
        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.Action_Search)
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
            case R.id.Action_Search:
                // search action
                return true;

            case R.id.Action_Refresh:
                // refresh
                refreshMenuItem = item;
                // load the data from server
                new SyncData().execute();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Async task to load the data from server
     * **/
    private class SyncData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // set the progress bar view
            refreshMenuItem.setActionView(R.layout.action_progressbar);
                    //.setActionView(R.drawable.progress_bar_ring);
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
            ((Sku_rv_adapter)recyclerView.getAdapter()).updateList(search,skuArrayList);
        }
        catch (Exception e){}
    }

    @Override
    public void onStart()
    {
        super.onStart();
        skuReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                skuArrayList.clear();
                for(DataSnapshot sku:dataSnapshot.getChildren())
                {
                    skuArrayList.add(sku.getValue(Sku.class));
                }
                Sku_rv_adapter sku_rv_adapter=new Sku_rv_adapter(skuArrayList,show_sku_rv_activity.this);
                recyclerView.setAdapter(sku_rv_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(show_sku_rv_activity.this, "Error in downloading the data", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
