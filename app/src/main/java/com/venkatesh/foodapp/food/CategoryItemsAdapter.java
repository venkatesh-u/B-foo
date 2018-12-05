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


public class CategoryItemsAdapter extends RecyclerView.Adapter<CategoryItemsAdapter.ViewHolder> {
    ArrayList<DataModel> mValues;
    Context mContext;
    protected ItemListener mListener;
    Activity activity;
    public static final int REQ_CODE_1 = 1;

    public CategoryItemsAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        activity = (Activity) context;
        mListener=itemListener;
    }

    @Override
    public CategoryItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryItemsAdapter.ViewHolder holder, final int position) {
//        holder.setData(mValues.get(position));
        final DataModel item = mValues.get(position);
        holder.textView.setText(item.text);
        holder.imageView.setImageResource(item.drawable);
        holder.linearLayout.setBackgroundColor(Color.parseColor(item.color));
        holder.tv_item_price.setText(String.valueOf(item.price));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext, ""+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, AddItemToCart.class);
                intent.putExtra("Item", item.text);
                intent.putExtra("item_Cost", item.price);
                activity.startActivityForResult(intent, REQ_CODE_1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView, tv_item_price;
        public ImageView imageView;
        public LinearLayout linearLayout;
        CardView cardView ;
        DataModel item;

        public ViewHolder(View v) {
            super(v);

//            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.textView);
            tv_item_price = v.findViewById(R.id.tv_item_price);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            linearLayout =  v.findViewById(R.id.linearLayout);
            cardView =  v.findViewById(R.id.cardView);

        }
    }


    public interface ItemListener {
        void onItemClick(DataModel item);
    }
}
