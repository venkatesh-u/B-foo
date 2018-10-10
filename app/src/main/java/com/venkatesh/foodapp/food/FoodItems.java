package com.venkatesh.foodapp.food;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.venkatesh.foodapp.R;
import com.venkatesh.foodapp.pojos.DataModel;

import java.util.ArrayList;

public class FoodItems extends AppCompatActivity implements FoodItemsAdapter.ItemListener{

    private String restaurant_name;
    RecyclerView recyclerView;
    ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_items);

        restaurant_name = getIntent().getStringExtra("restaurant_name");
        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle(restaurant_name);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }




        recyclerView =  findViewById(R.id.recyclerView);
        arrayList = new ArrayList();
        arrayList.add(new DataModel("Biryani's", R.drawable.food1, "#ffffff"));
        arrayList.add(new DataModel("Starter's", R.drawable.food3, "#ffffff"));
        arrayList.add(new DataModel("Chinese", R.drawable.food2, "#ffffff"));
        arrayList.add(new DataModel("Main Course", R.drawable.food4, "#ffffff"));
        arrayList.add(new DataModel("Desserts", R.drawable.food1, "#ffffff"));
        arrayList.add(new DataModel("Drinks", R.drawable.food2, "#ffffff"));
        arrayList.add(new DataModel("Tifins", R.drawable.food3, "#ffffff"));
        arrayList.add(new DataModel("Snacks", R.drawable.food4, "#ffffff"));
        arrayList.add(new DataModel("Rice Items", R.drawable.food1, "#ffffff"));
        arrayList.add(new DataModel("Juices", R.drawable.food3, "#ffffff"));
        arrayList.add(new DataModel("Curries", R.drawable.food2, "#ffffff"));
        arrayList.add(new DataModel("Sea Food", R.drawable.food4, "#ffffff"));

        FoodItemsAdapter adapter = new FoodItemsAdapter(this, arrayList, this);
        recyclerView.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    public void onItemClick(DataModel item) {

    }





}
