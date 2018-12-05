package com.venkatesh.foodapp.food;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.venkatesh.foodapp.R;
import com.venkatesh.foodapp.pojos.DataModel;

import java.util.ArrayList;


public class FoodCategoriesAdapter extends RecyclerView.Adapter<FoodCategoriesAdapter.ViewHolder> {
    ArrayList<DataModel> mValues;
    Context mContext;
    protected ItemListener mListener;
    Activity activity;

    public FoodCategoriesAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        activity = (Activity) context;
        mListener=itemListener;
    }

    @Override
    public FoodCategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodCategoriesAdapter.ViewHolder holder, final int position) {
//        holder.setData(mValues.get(position));
        final DataModel item = mValues.get(position);
        holder.textView.setText(item.text);
        holder.imageView.setImageResource(item.drawable);
        holder.linearLayout.setBackgroundColor(Color.parseColor(item.color));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext, ""+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, CategoryItems.class);
                intent.putExtra("item_category", item.text);
                intent.putExtra("category_id", item.catId);
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
        public LinearLayout linearLayout;
        CardView cardView ;
        DataModel item;

        public ViewHolder(View v) {
            super(v);

//            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            linearLayout =  v.findViewById(R.id.linearLayout);
            cardView =  v.findViewById(R.id.cardView);

        }
    }


    public interface ItemListener {
        void onItemClick(DataModel item);
    }
}
