package com.venkatesh.businessoffers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.ResponseBody;
import com.venkatesh.businessoffers.pojos.CouponsPojo;
import com.venkatesh.businessoffers.storage.PreferencesData;

import java.io.File;

import retrofit.Call;

public class AddOfferActivity extends BaseActivity implements View.OnClickListener {
    EditText et_description, et_title;
    Button btn_add_offer;


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_add_offer){

            validateOfferData();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);

       et_title = findViewById(R.id.et_offer_title);
       et_description = findViewById(R.id.et_offer_desc);
        btn_add_offer = findViewById(R.id.btn_add_offer);
        btn_add_offer.setOnClickListener(this);
    }


    private void validateOfferData() {
        boolean cancel = false;
        View focusView = null;

        CouponsPojo pojo = new CouponsPojo();
        String title = et_title.getText().toString();
        String desc = et_description.getText().toString();

        if (TextUtils.isEmpty(title)) {
            et_title.setError(getString(R.string.error_field_required));
            focusView = et_title;
            cancel = true;
        }else if (TextUtils.isEmpty(desc)){
            et_description.setError(getString(R.string.error_field_required));
            focusView = et_description;
            cancel = true;
        }

        if (cancel){
            focusView.requestFocus();
        }else {

            String path = "/home/venkatesh/Pictures/business_people.jpg";
            File file_name = new File(path);
            pojo.title = title;
            pojo.description = desc;
            pojo.avatar = file_name;
            pojo.token = PreferencesData.getToken(this);
            pojo.business_account_id = "1";
            addOffer(pojo);
        }
    }

    private void addOffer(final CouponsPojo pojo) {
       Call<ResponseBody> call = MyApplication.getSerivce().createCoupon(pojo);
       call.enqueue(new Listener(new RetrofitService() {
           @Override
           public void onSuccess(String result, int pos, Throwable t) {
               if (pos==0){

               }
           }
       }, "", true, this));

    }
}
