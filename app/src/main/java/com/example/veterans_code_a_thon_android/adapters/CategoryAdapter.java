package com.example.veterans_code_a_thon_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterans_code_a_thon_android.R;
import com.example.veterans_code_a_thon_android.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    Category[] categories;

    public CategoryAdapter (Context context, Category[] categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryView = LayoutInflater.from(context).
                inflate(R.layout.item_category,
                        parent, false);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Get components of item_cateogry
        }

        public void bind (final Category category) {
            //Bind components
        }
    }
}
