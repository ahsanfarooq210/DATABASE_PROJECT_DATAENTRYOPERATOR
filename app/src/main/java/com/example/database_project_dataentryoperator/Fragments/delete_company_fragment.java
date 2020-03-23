package com.example.database_project_dataentryoperator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.database_project_dataentryoperator.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class delete_company_fragment extends Fragment
{

    DatabaseReference skuReference;
    public delete_company_fragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_delete_company_fragment, container, false);

        //firebase database reference to delete the node
        skuReference= FirebaseDatabase.getInstance().getReference("SKU");
        skuReference.keepSynced(true);

        return v;
    }
}
