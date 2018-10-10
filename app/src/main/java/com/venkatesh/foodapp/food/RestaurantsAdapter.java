package com.venkatesh.foodapp.food;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.venkatesh.foodapp.R;
import com.venkatesh.foodapp.pojos.DataModel;

import java.util.ArrayList;


public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder> {
    ArrayList<DataModel> mValues;
    Context mContext;
    protected ItemListener mListener;
    Activity activity;

    public RestaurantsAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        activity = (Activity) context;
        mListener=itemListener;
    }

    @Override
    public RestaurantsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantsAdapter.ViewHolder holder, final int position) {
//        holder.setData(mValues.get(position));
        final DataModel item = mValues.get(position);
        holder.textView.setText(item.text);
        holder.imageView.setImageResource(item.drawable);
        holder.frameLayout.setBackgroundColor(Color.parseColor(item.color));
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, FoodItems.class);
                intent.putExtra("restaurant_name", item.text);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;
        public FrameLayout frameLayout;
        DataModel item;

        public ViewHolder(View v) {
            super(v);

//            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            frameLayout = (FrameLayout) v.findViewById(R.id.relativeLayout);
        }
    }


    public interface ItemListener {
        void onItemClick(DataModel item);
    }
}
