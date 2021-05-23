package com.example.veterans_code_a_thon_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterans_code_a_thon_android.R;
import com.example.veterans_code_a_thon_android.models.Business;

import java.util.List;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.ViewHolder> {
    Context context;
    Business[] businesses;

    public BusinessAdapter (Context context, Business[] businesses) {
        this.context = context;
        this.businesses = businesses;
    }

    @NonNull
    @Override
    public BusinessAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View businessView = LayoutInflater.from(context).
                inflate(R.layout.item_business, parent, false);
        return new ViewHolder(businessView);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessAdapter.ViewHolder holder, int position) {
        Business business = businesses[position];
        holder.bind(business);
    }

    @Override
    public int getItemCount() {
        return businesses.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Components of a business, which of now
            // continue to remain unknown
            RelativeLayout cBusiness;
        }

        public void bind(final Business business) {
            //Set attributes of business model
            // to components above

            //Include button that takes you to website? if exists
        }
    }
}
