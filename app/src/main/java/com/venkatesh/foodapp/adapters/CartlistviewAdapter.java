package com.venkatesh.foodapp.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.venkatesh.foodapp.R;
import com.venkatesh.foodapp.foodpojos.CartItemsPojo;

import java.util.ArrayList;
import java.util.HashMap;

public class CartlistviewAdapter extends BaseAdapter {

    public ArrayList<CartItemsPojo> list;
    Activity activity;

    public CartlistviewAdapter(Activity activity, ArrayList<CartItemsPojo> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {
        TextView tv_item_name;
        TextView tv_item_count;
        TextView tv_item_value;
//        TextView txtFourth;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_item, null);
            holder = new ViewHolder();
            holder.tv_item_name = (TextView) convertView.findViewById(R.id.tv_item_name);
            holder.tv_item_count = (TextView) convertView.findViewById(R.id.tv_item_count);
            holder.tv_item_value = (TextView) convertView.findViewById(R.id.tv_item_value);
//            holder.txtFourth = (TextView) convertView.findViewById(R.id.FourthText);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        CartItemsPojo pojo = list.get(position);
        holder.tv_item_name.setText(pojo.item_name);
        holder.tv_item_value.setText(String.valueOf(pojo.item_cost* pojo.item_count));
        holder.tv_item_count.setText(String.valueOf(pojo.item_count));
//        holder.txtFourth.setText(map.get(FOURTH_COLUMN));

        return convertView;
    }

}
