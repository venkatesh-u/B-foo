package com.venkatesh.businessoffers.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.venkatesh.businessoffers.R;
import com.venkatesh.businessoffers.pojos.OffersPojo;

import java.util.ArrayList;

/**
 * Created by Venkatesh on 18-12-2017.
 */

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder> {

    private ArrayList<OffersPojo> dataSet;

    public void clearDataSet() {
        dataSet=new ArrayList<>();
//        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_desc;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_desc = (TextView) itemView.findViewById(R.id.tv_desc);

        }
    }

    public OffersAdapter(ArrayList<OffersPojo> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_offers, parent, false);



        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView tv_title = holder.tv_title;
        TextView tv_desc = holder.tv_desc;
        tv_title.setText(dataSet.get(listPosition).title);
        tv_desc.setText(dataSet.get(listPosition).description);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
