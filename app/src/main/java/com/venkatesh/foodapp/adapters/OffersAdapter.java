package com.venkatesh.foodapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.venkatesh.foodapp.AddOfferActivity;
import com.venkatesh.foodapp.R;
import com.venkatesh.foodapp.Utils;
import com.venkatesh.foodapp.pojos.Coupans;

import java.util.ArrayList;

/**
 * Created by Venkatesh on 18-12-2017.
 */

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder> {

    private final Activity activity;
    private static ArrayList<Coupans> dataSet;


    public void clearDataSet() {
        dataSet=new ArrayList<>();
//        notifyDataSetChanged();
    }

    public static void addDataTOList(ArrayList<Coupans> list) {
        if (dataSet!=null && dataSet.size()!=0){
            dataSet = list;
        }
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

    public OffersAdapter(ArrayList<Coupans> data, Activity allOffersFragment) {
        this.dataSet = data;
        activity = allOffersFragment;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(activity, AddOfferActivity.class);
                intent.putExtra("From_Offers_Adapter", true);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("offer_obj", dataSet.get(listPosition));
                intent.putExtra("bundle", dataSet.get(listPosition));
                activity.startActivityForResult(intent, Utils.REQ_CODE_EDIT_OFFER);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
