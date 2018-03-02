package com.venkatesh.businessoffers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.ResponseBody;
import com.venkatesh.businessoffers.intlphoneinput.IntlPhoneInput;

import retrofit.Call;

public class LoginPage extends BaseActivity {

    private IntlPhoneInput primaryNumber;
    Activity activity;
    private boolean isValidPhonneNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        activity = this;

        primaryNumber = findViewById(R.id.my_phone_input);

        primaryNumber.setOnValidityChange(new IntlPhoneInput.IntlPhoneInputListener() {
            @Override
            public void done(View view, boolean isValid) {
                if (isValid){
                    isValidPhonneNumber = true;
                    Toast.makeText(activity, "Valid number", Toast.LENGTH_SHORT).show();

                }else {
                    isValidPhonneNumber = false;
                    primaryNumber.setError("Invalid Number..");
                    Toast.makeText(activity, "InValid number", Toast.LENGTH_SHORT).show();

                }

            }
        });

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });



    }



    private void attemptLogin() {
//        if (mAuthTask != null) {
//            return;
//        }
//
//        // Reset errors.
//        mEmailView.setError(null);
//        editTextname.setError(null);
//
//        pojo   = new BusinessAccountPojo();
//        // Store values at the time of the login attempt.
//        pojo.email  = mEmailView.getText().toString();
//        pojo.name = editTextname.getText().toString();
//        pojo.phone_number = editTextPhone.getText().toString();
//        pojo.phone_number = String.valueOf(primaryNumber.getNationalNumber());


        String num = primaryNumber.getNumber();
        String phone_num = num.substring(1);

//        String phone = primaryNumber.getNationalNumber();
//        primaryNumber.getNumber()
//
//        pojo.category= etCategory.getText().toString();
//        pojo.services = etService.getText().toString();
//        pojo.keywords= etKeyword.getText().toString();
//        pojo.address = etAddress.getText().toString();
//        pojo.latitude = "17.68";
//        pojo.longitude = "83.21";

        boolean cancel = false;
        View focusView = null;

//        // Check for a valid email address.
//        if (TextUtils.isEmpty( pojo.email)) {
//            mEmailView.setError(getString(R.string.error_field_required));
//            focusView = mEmailView;
//            cancel = true;
//        } else if (!isEmailValid( pojo.email)) {
//            mEmailView.setError(getString(R.string.error_invalid_email));
//            focusView = mEmailView;
//            cancel = true;
//        }
//
//        if (TextUtils.isEmpty(pojo.name)) {
//            editTextname.setError(getString(R.string.error_username));
//            focusView = editTextname;
//            cancel = true;
//        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(phone_num)) {
            primaryNumber.setError(getString(R.string.error_field_required));
            focusView = primaryNumber;
            cancel = true;


        }else if (!isValidPhonneNumber){
            cancel = true;
            primaryNumber.setError(getString(R.string.error_invalid_phone));
            focusView = primaryNumber;
        }


//        // Check for a valid email address.
//        if (TextUtils.isEmpty(pojo.category)) {
//            etCategory.setError(getString(R.string.error_field_required));
//            focusView = etCategory;
//            cancel = true;
//        }
//
//        // Check for a valid email address.
//        if (TextUtils.isEmpty(pojo.keywords)) {
//            etKeyword.setError(getString(R.string.error_field_required));
//            focusView = etKeyword;
//            cancel = true;
//        }
//
//        // Check for a valid email address.
//        if (TextUtils.isEmpty(pojo.services)) {
//            etService.setError(getString(R.string.error_field_required));
//            focusView = etService;
//            cancel = true;
//        }
//
//        // Check for a valid email address.
//        if (TextUtils.isEmpty(pojo.address)) {
//            etAddress.setError(getString(R.string.error_field_required));
//            focusView = etAddress;
//            cancel = true;
//        }



        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
//            showProgress(true);


            loginIntoAccount(phone_num);


            //            confirmOtp();
//            mAuthTask = new UserLoginTask(email, "password");
//            mAuthTask.execute((Void) null);
        }
    }

    private void loginIntoAccount(String phone_num) {
        Call<ResponseBody> call = MyApplication.getSerivce().loginIntoBusinessAccountServer(phone_num);
        call.enqueue(new Listener(new RetrofitService() {
            @Override
            public void onSuccess(String result, int pos, Throwable t) {

                if (pos == 0) {
                    Toast.makeText(LoginPage.this, "Success", Toast.LENGTH_SHORT).show();
//                    showProgress(false);

                    confirmOtp();

                } else {
//                    showProgress(false);

                }
            }
        }, "Logging...", false, this));



    }


    private void confirmOtp() {
//         throws JSONException
//    }
        //Creating a LayoutInflater object for the dialog box
        LayoutInflater li = LayoutInflater.from(this);
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.dialog_confirm, null);

             final TextView editTextOtp = confirmDialog.findViewById(R.id.editTextOtp);

        //Initizliaing confirm button fo dialog box and edittext of dialog box
        AppCompatButton buttonConfirm = (AppCompatButton) confirmDialog.findViewById(R.id.buttonConfirm);
//        editTextConfirmOtp = (EditText) confirmDialog.findViewById(R.id.editTextOtp);

        //Creating an alertdialog builder
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        //Adding our dialog box to the view of alert dialog
        alert.setView(confirmDialog);
        alert.setCancelable(false);

        //Creating an alert dialog
        final AlertDialog alertDialog = alert.create();

        //Displaying the alert dialog
        alertDialog.show();

        //On the click of the confirm button from alert dialog
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hiding the alert dialog
                alertDialog.dismiss();

               String num = editTextOtp.getText().toString();
                if (num.length()==4){


//                    //Displaying a progressbar
//                    final ProgressDialog loading = ProgressDialog.show(LoginPage.this, "Authenticating",
//
//                            "Please wait while we check the entered code", false, false);
//                    loading.dismiss();


                    sendOtp(num);

                    //Starting a new activity


                }else {

                    Toast.makeText(activity, "invalid length", Toast.LENGTH_SHORT).show();
                }




//                //Getting the user entered otp from edittext
//                final String otp = editTextConfirmOtp.getText().toString().trim();
//
//                //Creating an string request
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.CONFIRM_URL,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                //if the server response is success
//                                if(response.equalsIgnoreCase("success")){
//                                    //dismissing the progressbar
//                                    loading.dismiss();
//
//                                    //Starting a new activity
//                                    startActivity(new Intent(MainActivity.this, Success.class));
//                                }else{
//                                    //Displaying a toast if the otp entered is wrong
//                                    Toast.makeText(MainActivity.this,"Wrong OTP Please Try Again",Toast.LENGTH_LONG).show();
//                                    try {
//                                        //Asking user to enter otp again
//                                        confirmOtp();
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                alertDialog.dismiss();
//                                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
//                            }

//

            }

        });

    }

    private void sendOtp(String num) {

        Call<ResponseBody> call = MyApplication.getSerivce().sendOtpToServer(num);
        call.enqueue(new Listener(new RetrofitService() {
            @Override
            public void onSuccess(String result, int pos, Throwable t) {

                if (pos == 0) {
                    Toast.makeText(LoginPage.this, "Login Success", Toast.LENGTH_SHORT).show();
//                    showProgress(false);
                    startActivity(new Intent(LoginPage.this, Main2Activity.class));

//                    confirmOtp();

                } else {
//                    showProgress(false);

                }
            }
        }, "Registering...", true, this));



    }

}
