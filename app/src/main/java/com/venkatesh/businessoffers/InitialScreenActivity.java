package com.venkatesh.businessoffers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class InitialScreenActivity extends BaseActivity implements View.OnClickListener {

//    @BindView(R.id.business_account)
    Button businessAccount;
//    @BindView(R.id.customer_account)
    Button customerAccount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_screen);
//        ButterKnife.bind(this);
        businessAccount = findViewById(R.id.business_account);
        customerAccount = findViewById(R.id.customer_account);

        businessAccount.setOnClickListener(this);
        customerAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.business_account:
                startActivity(new Intent(InitialScreenActivity.this, LoginPage.class));
                break;
            case R.id.customer_account:

        }

    }
}
