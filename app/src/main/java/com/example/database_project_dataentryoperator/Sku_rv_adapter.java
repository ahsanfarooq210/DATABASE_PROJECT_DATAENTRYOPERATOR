package com.example.database_project_dataentryoperator;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Sku_rv_adapter extends RecyclerView.Adapter<Sku_rv_adapter.ViewHolder>
{

    private ArrayList<Sku> skuArrayList;
    Activity context;

    public Sku_rv_adapter(ArrayList<Sku> skuArrayList,Activity context)
    {
        this.skuArrayList = skuArrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.sku_rv_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.id.setText(skuArrayList.get(position).getId());
        holder.description.setText(skuArrayList.get(position).toString());
    }

    @Override
    public int getItemCount()
    {
        return skuArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView id,description;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            id=itemView.findViewById(R.id.sku_id);
            description=itemView.findViewById(R.id.sku_definition);
        }
    }
}
