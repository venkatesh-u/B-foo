package com.venkatesh.foodapp.food;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.venkatesh.foodapp.PreferencesData;
import com.venkatesh.foodapp.R;
import com.venkatesh.foodapp.pojos.DataModel;

import java.util.ArrayList;

public class FoodCategories extends AppCompatActivity implements FoodCategoriesAdapter.ItemListener{

    private String restaurant_name;
    RecyclerView recyclerView;
    ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_items);

        restaurant_name = getIntent().getStringExtra("restaurant_name");
        PreferencesData.putRestaurantName(this, restaurant_name);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle(restaurant_name);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }




        recyclerView =  findViewById(R.id.recyclerView);
        arrayList = new ArrayList();
        arrayList.add(new DataModel("Biryani's", R.drawable.mutton_biryani, "#ffffff", 220, 1));
        arrayList.add(new DataModel("Starter's", R.drawable.murg_thangidi, "#ffffff", 220, 2));
        arrayList.add(new DataModel("Chinese", R.drawable.jalapenos_chicken, "#ffffff", 220, 3));
        arrayList.add(new DataModel("Main Course", R.drawable.beef_cheeks, "#ffffff", 220, 4));
        arrayList.add(new DataModel("Desserts", R.drawable.strayberries_icecream, "#ffffff", 220, 5));
        arrayList.add(new DataModel("Drinks", R.drawable.margarita, "#ffffff", 220, 6));
        arrayList.add(new DataModel("Tiffins", R.drawable.food3, "#ffffff", 220, 7));
        arrayList.add(new DataModel("Snacks", R.drawable.cheese_burger, "#ffffff", 220, 8));
        arrayList.add(new DataModel("Sea Food", R.drawable.fish_starter, "#ffffff", 220, 9));
        arrayList.add(new DataModel("Juices", R.drawable.watermelon_juice, "#ffffff", 220, 10));

        FoodCategoriesAdapter adapter = new FoodCategoriesAdapter(this, arrayList, this);
        recyclerView.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    public void onItemClick(DataModel item) {

    }





}
