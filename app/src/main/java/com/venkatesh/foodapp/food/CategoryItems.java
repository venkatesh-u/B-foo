package com.venkatesh.foodapp.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.venkatesh.foodapp.R;
import com.venkatesh.foodapp.foodpojos.CartItemsPojo;
import com.venkatesh.foodapp.pojos.DataModel;

import java.util.ArrayList;

import static com.venkatesh.foodapp.food.CategoryItemsAdapter.REQ_CODE_1;

public class CategoryItems extends AppCompatActivity implements CategoryItemsAdapter.ItemListener{

    private String item_category;
    RecyclerView recyclerView;
    ArrayList arrayList;
    private RelativeLayout rl_cartviewBottom;
    TextView tv_cart_value, tv_cart_count;
    Activity activity;
    ArrayList<CartItemsPojo> list = new ArrayList<>();
    int cartV;
    private int category_id;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_items);
        activity = this;

        item_category = getIntent().getStringExtra("item_category");
        category_id = getIntent().getIntExtra("category_id", 0);

        if (getSupportActionBar()!=null){
            if(item_category!=null)
            getSupportActionBar().setTitle(item_category);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        rl_cartviewBottom = findViewById(R.id.rl_cartviewBottom);
        tv_cart_value = findViewById(R.id.tv_cart_value);
        tv_cart_count = findViewById(R.id.tv_cart_count);

        setListener();

        recyclerView =  findViewById(R.id.recyclerView);

        arrayList = prepareDataModelAsPerCategories();

//        arrayList = new ArrayList();
//
//        arrayList.add(new DataModel("Chicken Dum Biryani", R.drawable.food1, "#ffffff", 220, 210));
//        arrayList.add(new DataModel("Chicken Fry Biryani", R.drawable.food3, "#ffffff", 220, 200));
//        arrayList.add(new DataModel("Mutton Biryani", R.drawable.food2, "#ffffff", 220, 250));
//        arrayList.add(new DataModel("Prawn Biryani", R.drawable.food4, "#ffffff", 220, 220));
//        arrayList.add(new DataModel("Dhupudu Biryani", R.drawable.food1, "#ffffff", 220, 220));
//        arrayList.add(new DataModel("Fish Biryani", R.drawable.food2, "#ffffff", 220, 200));
//        arrayList.add(new DataModel("Kalimi Biryani", R.drawable.food3, "#ffffff", 220, 200));
//        arrayList.add(new DataModel("Mughalai Biryani", R.drawable.food4, "#ffffff", 220, 210));
//        arrayList.add(new DataModel("Chicken Pot Biryani", R.drawable.food1, "#ffffff", 220, 220));
//        arrayList.add(new DataModel("Fish Pot Biryani", R.drawable.food3, "#ffffff", 220, 220));
//        arrayList.add(new DataModel("Mutton Kheema Biryani", R.drawable.food2, "#ffffff", 220, 220));
//        arrayList.add(new DataModel("Kodi Pulav", R.drawable.food4, "#ffffff", 220, 200));

        CategoryItemsAdapter adapter = new CategoryItemsAdapter(this, arrayList,  this);
        recyclerView.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

    }

    private ArrayList<DataModel> prepareDataModelAsPerCategories() {
        arrayList = new ArrayList();

        if (category_id == 1){
            //Biryani's
            arrayList.add(new DataModel("Chicken Dum Biryani", R.drawable.dum_biryani, "#ffffff", 210, 210));
            arrayList.add(new DataModel("Chicken Fry Biryani", R.drawable.chicken_fry_briyani, "#ffffff", 200, 200));
            arrayList.add(new DataModel("Mutton Biryani", R.drawable.mutton_biryani, "#ffffff", 250, 250));
            arrayList.add(new DataModel("Prawn Biryani", R.drawable.prawn_briyani, "#ffffff", 220, 220));
            arrayList.add(new DataModel("Dhupudu Biryani", R.drawable.chicken_fry_briyani, "#ffffff", 220, 220));
            arrayList.add(new DataModel("Fish Biryani", R.drawable.fish_biryani, "#ffffff", 200, 200));
            arrayList.add(new DataModel("Kalimi Biryani", R.drawable.keema_biryani, "#ffffff", 200, 200));
            arrayList.add(new DataModel("Mughalai Biryani", R.drawable.food4, "#ffffff", 210, 210));
            arrayList.add(new DataModel("Chicken Pot Biryani", R.drawable.food1, "#ffffff", 220, 220));
            arrayList.add(new DataModel("Fish Pot Biryani", R.drawable.fish_biryani, "#ffffff", 220, 220));
            arrayList.add(new DataModel("Mutton Kheema Biryani", R.drawable.keema_biryani, "#ffffff", 220, 220));
            arrayList.add(new DataModel("Kodi Pulav", R.drawable.food4, "#ffffff", 200, 200));
        }else if (category_id == 2){
            //Starter's
            arrayList.add(new DataModel("Kalimi Kebab", R.drawable.kalmi_kebab, "#ffffff", 210, 180));
            arrayList.add(new DataModel("Thandoori", R.drawable.murg_thangidi, "#ffffff", 300, 300));
            arrayList.add(new DataModel("Chicken Tikka", R.drawable.chicken_lollypop, "#ffffff", 180, 190));
            arrayList.add(new DataModel("Cheese Prawn", R.drawable.mint_chicken, "#ffffff", 250, 220));
            arrayList.add(new DataModel("Fish 65", R.drawable.fish_starter, "#ffffff", 200, 200));
        }else if (category_id == 3){
            //Chinese
            arrayList.add(new DataModel("Fried Shrimp", R.drawable.fried_shrimp, "#ffffff", 210, 210));
            arrayList.add(new DataModel("Jalapenos Chicken", R.drawable.jalapenos_chicken, "#ffffff", 300, 300));
            arrayList.add(new DataModel("Chicken Tikka", R.drawable.food2, "#ffffff", 180, 250));
            arrayList.add(new DataModel("Cheese Prawn", R.drawable.food4, "#ffffff", 250, 220));
            arrayList.add(new DataModel("kow_chow_fun", R.drawable.kow_chow_fun, "#ffffff", 200, 220));

        }else if (category_id == 4){
            //Main Course
            arrayList.add(new DataModel("Grilled Supreme Chicken Breast", R.drawable.grilled_supreme_chicken_breast, "#ffffff", 410, 210));
            arrayList.add(new DataModel("Duck Breast", R.drawable.duck_breast, "#ffffff", 300, 350));
            arrayList.add(new DataModel("Beef Cheeks", R.drawable.beef_cheeks, "#ffffff", 330, 330));
            arrayList.add(new DataModel("Chicken Family Pack", R.drawable.food4, "#ffffff", 550, 600));
            arrayList.add(new DataModel("Mutton Family Pack", R.drawable.kow_chow_fun, "#ffffff", 600, 220));


        }else if(category_id == 5){
            //Desserts
            arrayList.add(new DataModel("MarshMallow Icecream", R.drawable.marshmallow_icecream, "#ffffff", 120, 210));
            arrayList.add(new DataModel("Toffee", R.drawable.tofee, "#ffffff", 80, 350));
            arrayList.add(new DataModel("StrawBerry Icecream", R.drawable.strayberries_icecream, "#ffffff", 130, 330));
            arrayList.add(new DataModel("Chocolate Icecream", R.drawable.chocolate_icecream, "#ffffff", 150, 600));
            arrayList.add(new DataModel("Brown Cake", R.drawable.brown_cake, "#ffffff", 70, 220));


        }else if (category_id == 6){
            //Drinks
            arrayList.add(new DataModel("Blue Creamsicle", R.drawable.blue_creamsicle, "#ffffff", 150, 210));
            arrayList.add(new DataModel("Orange", R.drawable.orange, "#ffffff", 80, 350));
            arrayList.add(new DataModel("Coke Pack", R.drawable.coke_pack, "#ffffff", 180, 330));
            arrayList.add(new DataModel("Margarita", R.drawable.margarita, "#ffffff", 150, 600));
            arrayList.add(new DataModel("Apple Vinegar", R.drawable.apple_vinegar, "#ffffff", 120, 220));


        }else if (category_id == 7){
            //Tifins
            arrayList.add(new DataModel("Idly", R.drawable.idly, "#ffffff", 50, 50));
            arrayList.add(new DataModel("Dosa", R.drawable.dosa, "#ffffff", 40, 40));
            arrayList.add(new DataModel("Chapathi", R.drawable.chapathi, "#ffffff", 70, 330));
            arrayList.add(new DataModel("Parota", R.drawable.parota, "#ffffff", 50, 600));
            arrayList.add(new DataModel("Pesarattu", R.drawable.pesarattu, "#ffffff", 80, 220));
            arrayList.add(new DataModel("Vada", R.drawable.vada, "#ffffff", 60, 600));
            arrayList.add(new DataModel("Poori", R.drawable.poori, "#ffffff", 70, 220));


        }else if (category_id == 8){
            //Snacks
            arrayList.add(new DataModel("Cheese Burger", R.drawable.cheese_burger, "#ffffff", 150, 210));
            arrayList.add(new DataModel("Vada", R.drawable.vada, "#ffffff", 120, 120));
            arrayList.add(new DataModel("Cheese Pizza", R.drawable.cheese_pizza, "#ffffff", 130, 130));
            arrayList.add(new DataModel("Mix Veg Soup", R.drawable.mix_veg_soup, "#ffffff", 40, 40));
            arrayList.add(new DataModel("Panipuri", R.drawable.panipuri, "#ffffff", 50, 50));


        }else if (category_id == 9){
            //Sea Food
            arrayList.add(new DataModel("Prawn Platter", R.drawable.prawn_briyani, "#ffffff", 150, 210));
            arrayList.add(new DataModel("Crab Platter", R.drawable.crab_platter, "#ffffff", 120, 120));
            arrayList.add(new DataModel("Cocktail Platter", R.drawable.cocktail_platter, "#ffffff", 130, 130));
            arrayList.add(new DataModel("Fish Chips", R.drawable.fish_chips, "#ffffff", 110, 40));
            arrayList.add(new DataModel("Fish Wings", R.drawable.fish_wings, "#ffffff", 110, 50));


        }else if (category_id == 10){
            //Juices
            arrayList.add(new DataModel("Mango Juice", R.drawable.mango_juice, "#ffffff", 70, 210));
            arrayList.add(new DataModel("Pineapple Juice", R.drawable.pineapple_juice, "#ffffff", 80, 120));
            arrayList.add(new DataModel("Apple Juice", R.drawable.apple_juice, "#ffffff", 80, 130));
            arrayList.add(new DataModel("WaterMelon Juice", R.drawable.watermelon_juice, "#ffffff", 90, 40));
            arrayList.add(new DataModel("Orange Juice", R.drawable.orange, "#ffffff", 70, 50));

        }

        return arrayList;
    }

    private void setListener() {
        rl_cartviewBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Cart.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", list);
                intent.putExtra("bundle_list_cart_items", bundle);
                intent.putExtra("cart_value", String.valueOf(cartV));
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemClick(DataModel item) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("OnActivityRes: ", resultCode +", REq: "+requestCode+", Data: "+data);

        if (requestCode == REQ_CODE_1 && resultCode == RESULT_OK){
           int item_count = data.getIntExtra("item_count", 0);
//           String cart_value = data.getStringExtra("cart_value");
            if (item_count > 0){
                Bundle bundle = data.getBundleExtra("cartObjBundle");
                CartItemsPojo obj = (CartItemsPojo) bundle.getSerializable("obj");
                list.add(obj);
            }

            int c=0;
            cartV=0 ;
            for (CartItemsPojo objectCart: list) {
                c += objectCart.item_count;
                cartV +=  objectCart.cart_value;
            }

//           int item_count = PreferencesData.getCartItemsCount(activity);
//           String cart_value = PreferencesData.getCartValue(activity);

            if (list.size() > 0){
                rl_cartviewBottom.setVisibility(View.VISIBLE);
                tv_cart_value.setText(String.valueOf(cartV));
                tv_cart_count.setText(String.valueOf(c));
           }
        }

    }
}
