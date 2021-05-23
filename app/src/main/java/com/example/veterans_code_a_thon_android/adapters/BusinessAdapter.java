package com.example.veterans_code_a_thon_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
        TextView tvName;
        TextView tvEstablished;
        TextView tvPhone;
        TextView tvEmail;
        TextView tvAddress1;
        TextView tvAddress2;
        TextView tvState;
        TextView tvCity;
        TextView tvDisabled;
        TextView tvWoman;
        TextView tvMinority;
        RelativeLayout cBusiness;

        TextView tvCategories;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEstablished = itemView.findViewById(R.id.tvEstablishedText);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvAddress1 = itemView.findViewById(R.id.tvAddress1);
            tvAddress2 = itemView.findViewById(R.id.tvAddress2);
            tvState = itemView.findViewById(R.id.tvState);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvDisabled = itemView.findViewById(R.id.tvIsDisabled);
            tvWoman = itemView.findViewById(R.id.tvIsWoman);
            tvMinority = itemView.findViewById(R.id.tvIsMinority);
            cBusiness = itemView.findViewById(R.id.cBusiness);

            tvCategories = itemView.findViewById(R.id.tvCategoryList);

        }

        public void bind(final Business business) {
            //Set attributes of business model
            // to components above
            tvName.setText(business.getName());
            //tvEstablished.setText(business.getEstablished());
            tvPhone.setText(business.getPhone());
            tvEmail.setText(business.getEmail());
            tvAddress1.setText(business.getAddress1());
            tvAddress2.setText(business.getAddress2());
            tvState.setText(business.getState());
            tvCity.setText(business.getCity());
//            tvDisabled.setText(Boolean.toString(business.isDisabled()));
//            tvWoman.setText(Boolean.toString(business.isWoman()));
//            tvMinority.setText(Boolean.toString(business.isMinority()));
            tvCategories.setText(business.getCategories());
            //Include button that takes you to website? if exists
        }
    }
}
