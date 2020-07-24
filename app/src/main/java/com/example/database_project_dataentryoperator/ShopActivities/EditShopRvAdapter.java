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
import java.util.List;

public class EditShopRvAdapter extends RecyclerView.Adapter<EditShopRvAdapter.ViewHolder>
{
    private List<ShopDetails> shopDetailsArrayList;
    private Activity activity;

    public EditShopRvAdapter(List<ShopDetails> shopDetailsArrayList, Activity activity)
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

    public void updateList(String search, List<ShopDetails>  searchList ) {
        if(search.equals(""))
        {
            if(!(searchList.size()==shopDetailsArrayList.size()))
            {
                this.shopDetailsArrayList.clear();
                List<ShopDetails> empty = new ArrayList<>();
                for (int i=0; i< searchList.size(); i++) {
                    empty.add(searchList.get(i));
                }
                this.shopDetailsArrayList=empty;
                notifyDataSetChanged();
            }

        }
        if(!search.equals(""))
        {
            List<ShopDetails>  temps = new ArrayList<>();
            for (int i=0; i< searchList.size(); i++) {
                String shop=searchList.get(i).getShopName().toLowerCase();
                if (shop.compareToIgnoreCase(search)==0 ) {
                    temps.add(searchList.get(i));
                }
                if(searchList.get(i).getOwnerName().toLowerCase().contains(search.toLowerCase()))
                {
                    temps.add(searchList.get(i));
                }

            }
            this.shopDetailsArrayList = temps;

            notifyDataSetChanged();
        }

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
