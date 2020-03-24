package com.example.database_project_dataentryoperator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.database_project_dataentryoperator.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Delete_company_fragment extends Fragment
{

    public Delete_company_fragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_company_fragment, container, false);
    }
}
