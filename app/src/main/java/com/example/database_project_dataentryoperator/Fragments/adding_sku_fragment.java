package com.example.database_project_dataentryoperator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.database_project_dataentryoperator.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class adding_sku_fragment extends Fragment
{

    public adding_sku_fragment()
    {
        // Required empty public constructor
    }


    Spinner catagorySpinner,companySpinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_adding_sku_fragment, container, false);
        catagorySpinner=v.findViewById(R.id.sku_catagory_spinner);
        companySpinner=v.findViewById(R.id.sku_company_spinner);

        return v;
    }
}
