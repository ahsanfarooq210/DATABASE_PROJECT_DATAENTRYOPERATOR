package com.example.database_project_dataentryoperator.ShopActivities;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database_project_dataentryoperator.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DeleteShopRvAapter extends RecyclerView.Adapter<DeleteShopRvAapter.ViewHolder>
{
    private ArrayList<ShopDetails> shopDetailsArrayList;
    private Activity activity;

    public DeleteShopRvAapter(ArrayList<ShopDetails> shopDetailsArrayList, Activity activity)
    {
        this.shopDetailsArrayList = shopDetailsArrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(activity).inflate(R.layout.show_shop_rv_layout, parent, false);
        return new ViewHolder(v);
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
        TextView shopName, ownerName, ownerCnic;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            shopName = itemView.findViewById(R.id.show_shop_rv_layout_shopname);
            ownerName = itemView.findViewById(R.id.show_shop_rv_layout_ownername);
            ownerCnic = itemView.findViewById(R.id.show_shop_rv_layout_cnic);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String id = shopDetailsArrayList.get(getAdapterPosition()).getId();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("SHOP").child(id);
                    reference.removeValue();
                    activity.finish();
                    activity.startActivity(new Intent(activity, delete_shop.class));
                    Toast.makeText(activity, "Successfully deleted", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}
