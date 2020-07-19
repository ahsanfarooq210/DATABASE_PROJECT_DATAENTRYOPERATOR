package com.example.database_project_dataentryoperator.SKUActivities;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database_project_dataentryoperator.R;
import com.example.database_project_dataentryoperator.ShopActivities.ShopDetails;

import java.util.ArrayList;
import java.util.List;

public class Sku_rv_adapter extends RecyclerView.Adapter<Sku_rv_adapter.ViewHolder>
{

    private List<Sku> skuArrayList;
    Activity context;

    public Sku_rv_adapter(List<Sku> skuArrayList,Activity context)
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
        String data="Company : "+skuArrayList.get(position).getCompany().getName()
                   +"\n" +"Category : "+skuArrayList.get(position).getCatagory().getName()
                    +"\n"+"Product Name : " +skuArrayList.get(position).getProductName()
                    +"\n"+"Product Size : "+skuArrayList.get(position).getSize();
        holder.description.setText(data);
    }

    @Override
    public int getItemCount()
    {
        return skuArrayList.size();
    }
    public void updateList(String search, List<Sku>  skuArray ) {
        if(search.equals(""))
        {
            if(!(skuArrayList.size()==skuArray.size()))
            {
                this.skuArrayList.clear();
                List<Sku> empty = new ArrayList<>();
                for (int i=0; i< skuArray.size(); i++) {
                    empty.add(skuArray.get(i));
                }
                this.skuArrayList=empty;
                notifyDataSetChanged();
            }



        }
        if(!search.equals(""))
        {

            List<Sku>  temps = new ArrayList<>();
            for (int i=0; i< skuArray.size(); i++) {
                /*if (skuArray.get(i).getCompany().getName().toLowerCase().contains(search.toLowerCase())) {
                    temps.add(skuArray.get(i));
                }*/
                if (skuArray.get(i).getProductName().toLowerCase().contains(search.toLowerCase())) {
                    temps.add(skuArray.get(i));
                }
               /*else if (skuArray.get(i).getSize()==Integer.parseInt(search)) {
                    temps.add(skuArray.get(i));
                }*/
            }
            this.skuArrayList = temps;
            notifyDataSetChanged();
        }

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
