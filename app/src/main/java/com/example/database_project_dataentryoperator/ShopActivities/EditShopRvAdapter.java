package com.example.database_project_dataentryoperator.ShopActivities;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database_project_dataentryoperator.R;

import java.util.ArrayList;

public class EditShopRvAdapter extends RecyclerView.Adapter<EditShopRvAdapter.ViewHolder>
{
    private ArrayList<ShopDetails> shopDetailsArrayList;
    private Activity activity;

    public EditShopRvAdapter(ArrayList<ShopDetails> shopDetailsArrayList, Activity activity)
    {
        this.shopDetailsArrayList = shopDetailsArrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v= LayoutInflater.from(activity).inflate(R.layout.show_shop_rv_layout,parent,false);
        return new EditShopRvAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.shopName.setText(shopDetailsArrayList.get(position).getShopName());
        holder.ownerName.setText(shopDetailsArrayList.get(position).getOwnerName());
        holder.ownerCnic.setText(shopDetailsArrayList.get(position).getOwnerCnic());
    }

    @Override
    public int getItemCount()
    {
        return shopDetailsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView shopName,ownerName,ownerCnic;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            shopName=itemView.findViewById(R.id.show_shop_rv_layout_shopname);
            ownerName=itemView.findViewById(R.id.show_shop_rv_layout_ownername);
            ownerCnic=itemView.findViewById(R.id.show_shop_rv_layout_cnic);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String id=shopDetailsArrayList.get(getAdapterPosition()).getId();
                    Intent intent=new Intent(activity, Edit_shop_form.class);
                    intent.putExtra("shop_id",id);
                    activity.startActivity(intent);

                }
            });

        }
    }
}
