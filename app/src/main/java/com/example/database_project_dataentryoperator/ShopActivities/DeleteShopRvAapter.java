package com.example.database_project_dataentryoperator.ShopActivities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database_project_dataentryoperator.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DeleteShopRvAapter extends RecyclerView.Adapter<DeleteShopRvAapter.ViewHolder>
{
    private List<ShopDetails> shopDetailsArrayList;
    private Activity activity;

    public DeleteShopRvAapter(List<ShopDetails> shopDetailsArrayList, Activity activity)
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
        TextView shopName, ownerName, ownerCnic;

        public ViewHolder(@NonNull final View itemView)
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
                    ViewGroup viewGroup = itemView.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.dialog_for_delete_confirmation, viewGroup, false);
                    final AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setView(dialogView);


                    final AlertDialog alertDialog = builder.create();
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    Button dialog_yes_Button = (Button)dialogView.findViewById(R.id.yes_button);
                    dialog_yes_Button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                            String id = shopDetailsArrayList.get(getAdapterPosition()).getId();
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("SHOP").child(id);
                            reference.removeValue();
                            activity.finish();
                            activity.startActivity(new Intent(activity, delete_shop.class));
                            Toast.makeText(activity, "Successfully deleted", Toast.LENGTH_SHORT).show();

                        }
                    }); Button dialog_no_Button = (Button)dialogView.findViewById(R.id.No_button);
                    dialog_no_Button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                            //Fragment fragment=new View_Today_Report_Fragment();


                        }
                    });
                    Button dialog_cancel_Button = (Button)dialogView.findViewById(R.id.cancel_button);
                    dialog_cancel_Button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();

                        }
                    });

                    alertDialog.show();
                }
            });


        }
    }
}
