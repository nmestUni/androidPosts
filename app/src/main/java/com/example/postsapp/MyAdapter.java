package com.example.postsapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<ItemData> itemsData;
    private Context context;

    public MyAdapter(Context context, ArrayList<ItemData> itemsData) {
        this.itemsData = itemsData;
        this.context = context;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View itemLayoutView = LayoutInflater.from(context)
                .inflate(R.layout.recyclerview_row, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.txtViewTitle.setText(itemsData.get(position).getTitle());
        viewHolder.txtViewBody.setText(itemsData.get(position).getBody());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtViewTitle;
        public TextView txtViewBody;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.item_title);
            txtViewBody = (TextView) itemLayoutView.findViewById(R.id.item_body);
        }
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }
}