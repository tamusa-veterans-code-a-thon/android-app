package com.example.veterans_code_a_thon_android.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterans_code_a_thon_android.BusinessesActivity;
import com.example.veterans_code_a_thon_android.R;
import com.example.veterans_code_a_thon_android.models.Business;
import com.example.veterans_code_a_thon_android.models.Category;

import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcels;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private static final String TAG = "CategoryAdapter";

    Context context;
    Category[] categories;
    List<Business> businesses;

    public CategoryAdapter (Context context, Category[] categories, List<Business> businesses) {
        this.context = context;
        this.categories = categories;
        this.businesses = businesses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryView = LayoutInflater.from(context).
                inflate(R.layout.item_category, parent, false);
        return new ViewHolder(categoryView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Category category = categories[position];
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        RelativeLayout cCategory;//Container of category item

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Get components of item_cateogry
            tvCategory = itemView.findViewById(R.id.tvCategory);
            cCategory = itemView.findViewById(R.id.cCategory);
        }

        public void bind (final Category category) {
            //Bind components
            tvCategory.setText(category.getCategory());

            //Why is this gray?
            cCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Clicked!");

                    List<Business> compatable = new ArrayList<>();

                    for (int i = 0; i < businesses.size(); i++) {
                        String categories = businesses.get(i).getCategories();
                        if (categories.contains(category.getCategory())) {
                            compatable.add(businesses.get(i));
                            Log.d(TAG, businesses.get(i).getCategories() + ", " + businesses.get(i).getName());
                        }
                    }

                    Intent intent = new Intent(context, BusinessesActivity.class);
                    //Pass a new Intent for Business activity
                    //putExtra a list of businesses with a category that
                    //matches the one being clicked; this will
                    //need to be done with knowledge of how Business is built
                    intent.putExtra("businesses", Parcels.wrap(compatable));
                    context.startActivity(intent);
                }
            });

        }
    }
}
