package com.venkatesh.foodapp.food;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.venkatesh.foodapp.PreferencesData;
import com.venkatesh.foodapp.R;
import com.venkatesh.foodapp.adapters.CartlistviewAdapter;
import com.venkatesh.foodapp.foodpojos.CartItemsPojo;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Cart extends AppCompatActivity {

    TextView tv_restaurant_name, tv_item_value;
    String restaurant_name, cart_value;
    RelativeLayout rl_cartviewBottom;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        restaurant_name = PreferencesData.getRestaurantName(this);
        tv_restaurant_name = findViewById(R.id.tv_restaurant_name);
        tv_restaurant_name.setText(restaurant_name);
        rl_cartviewBottom = findViewById(R.id.rl_cartviewBottom);

        rl_cartviewBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new SweetAlertDialog(Cart.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
//                        .setTitleText("Order")
//                        .setContentText("Successful")
//                        .setCustomImage(R.drawable.quantum_ic_play_arrow_grey600_36)
//                        .show();
                new SweetAlertDialog(Cart.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Info")
                        .setContentText("Your order placed Successfully.")
                        .show();
            }
        });

        Bundle bundle = getIntent().getBundleExtra("bundle_list_cart_items");
        cart_value = getIntent().getStringExtra("cart_value");
        ArrayList<CartItemsPojo> list = (ArrayList<CartItemsPojo>) bundle.getSerializable("list");

        tv_item_value = findViewById(R.id.tv_item_value);
        tv_item_value.setText(cart_value);

        ListView lview = (ListView) findViewById(R.id.listView);
//        populateList();
        CartlistviewAdapter adapter = new CartlistviewAdapter(this, list);
        lview.setAdapter(adapter);
    }
}
