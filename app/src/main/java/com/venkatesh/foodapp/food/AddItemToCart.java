package com.venkatesh.foodapp.food;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.venkatesh.foodapp.PreferencesData;
import com.venkatesh.foodapp.R;
import com.venkatesh.foodapp.foodpojos.CartItemsPojo;

import java.util.ArrayList;

public class AddItemToCart extends AppCompatActivity {

    private ImageButton iv_minus, iv_plus;
    TextView tv_item_count, tv_cart_items_count, tv_cart_value, tv_item_name;
    int item_count=1;
    int item_cost;
    String cartValue;
    Snackbar snackbar;
    View parentLayout;
    RelativeLayout rl_cartviewBottom;


    View.OnClickListener itemCountListener  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.iv_minus){

                if (item_count > 1){
                    item_count -= 1;
                    tv_item_count.setText(String.valueOf(item_count));
                    cartValue = initialCartValue(item_count, item_cost);
                    showCartValue();

//                   Snackbar snackbar= Snackbar.make(view, "Item value "+ val, Snackbar.LENGTH_INDEFINITE);
//                   snackbar.setAction("Value", new View.OnClickListener() {
//                       @Override
//                       public void onClick(View view) {
//
//                       }
//                   });

                }else {
//                    iv_minus.setBackgroundColor(ContextCompat.getColor(AddItemToCart.this, R.color.red_color_dark));
//                    Toast.makeText(AddItemToCart.this, "min is 1", Toast.LENGTH_SHORT).show();
                }

            }else {
                item_count += 1;
                    tv_item_count.setText(String.valueOf(item_count));
                   cartValue = initialCartValue(item_count, item_cost);
//                iv_minus.setBackgroundColor(ContextCompat.getColor(AddItemToCart.this, R.color.black));
                showCartValue();

//                Toast.makeText(AddItemToCart.this, "min is 1", Toast.LENGTH_SHORT).show();


            }
        }
    };
    private String item_name;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_to_cart);
        activity = this;

        item_name = getIntent().getStringExtra("Item");
        item_cost = getIntent().getIntExtra("item_Cost", 0);
        if (getSupportActionBar()!=null){
            if(item_name!=null)
                getSupportActionBar().setTitle(item_name);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        tv_item_name = findViewById(R.id.tv_item_name);
        tv_item_name.setText(item_name);

        iv_minus =  findViewById(R.id.iv_minus);
        iv_plus = findViewById(R.id.iv_plus);
        tv_item_count = findViewById(R.id.item_count);
        parentLayout = findViewById(android.R.id.content);

        rl_cartviewBottom = findViewById(R.id.rl_cartviewBottom);
        bottomCartviewActionListener();
        tv_cart_items_count = findViewById(R.id.tv_cart_items_count);
        tv_cart_value = findViewById(R.id.tv_cart_value);

        tv_item_count.setText(String.valueOf(item_count));

        cartValue = initialCartValue(item_count, item_cost);

//        showSnackBar();
        showCartValue();



        iv_minus.setOnClickListener(itemCountListener);
        iv_plus.setOnClickListener(itemCountListener);


    }

    private void bottomCartviewActionListener() {
        rl_cartviewBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//               SharedPreferences sp = PreferencesData.getSharedPreferences();
//                sp.putCartItemsCount(item_count, activity);
//                PreferencesData.putCartValue(cartValue, activity);


                CartItemsPojo obj = new CartItemsPojo();
                obj.item_count = item_count;
                obj.item_cost = item_cost;
                obj.item_name = item_name;
                obj.cart_value = item_cost * item_count;

                Bundle bundle = new Bundle();
                bundle.putSerializable("obj", obj);

//                Toast.makeText(AddItemToCart.this, "Okay", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("item_count", item_count);
                intent.putExtra("cart_value", cartValue);
                intent.putExtra("cartObjBundle", bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void showCartValue() {
        tv_cart_items_count.setText("Add "+item_count+ " item to cart");
        tv_cart_value.setText(getResources().getString(R.string.Rs)+" "+ cartValue);
    }

    private void showSnackBar() {
        snackbar= Snackbar.make(parentLayout, "Add "+ item_count+" to cart", Snackbar.LENGTH_INDEFINITE);
        View mySbView = snackbar.getView();
        mySbView.setBackgroundColor(ContextCompat.getColor(this, R.color.red_color_light));

        TextView textView = (TextView) mySbView.findViewById(android.support.design.R.id.snackbar_text);

        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    private String initialCartValue(int item_count, int item_cost) {
        int total_value = item_count * item_cost;
        return String.valueOf(total_value);
    }
}
