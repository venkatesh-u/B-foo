package com.venkatesh.businessoffers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.okhttp.ResponseBody;
import com.venkatesh.businessoffers.intlphoneinput.IntlPhoneInput;
import com.venkatesh.businessoffers.pojos.BusinessAccountPojo;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    @BindView(R.id.login_progress)
    ProgressBar loginProgress;
//    @BindView(R.id.et_category)
    EditText etCategory;
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.et_service)
    EditText etService;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.email_sign_in_button)
    Button emailSignInButton;
    @BindView(R.id.email_login_form)
    LinearLayout emailLoginForm;
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    //    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private EditText editTextPhone, editTextname;
    private IntlPhoneInput primaryNumber;
    BusinessAccountPojo pojo;
    boolean isValidPhonneNumber;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mEmailView = findViewById(R.id.email);
        populateAutoComplete();

        editTextname = findViewById(R.id.et_username);
        primaryNumber = findViewById(R.id.my_phone_input);
        etCategory = findViewById(R.id.et_category);
        etService = findViewById(R.id.et_service);
        etKeyword = findViewById(R.id.et_keyword);
        etAddress = findViewById(R.id.et_address);

        primaryNumber.setOnValidityChange(new IntlPhoneInput.IntlPhoneInputListener() {
            @Override
            public void done(View view, boolean isValid) {
                if (isValid){
                    isValidPhonneNumber = true;
                    Toast.makeText(mMyApp, "Valid number", Toast.LENGTH_SHORT).show();

                }else {
                    isValidPhonneNumber = false;
                    primaryNumber.setError("Invalid Number..");
                    Toast.makeText(mMyApp, "InValid number", Toast.LENGTH_SHORT).show();

                }

            }
        });

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**Validate phone number at server side*/
//    private void validatePhoneNumber(String phoneNumber) {
////        Toast.makeText(mMyApp, ""+phoneNumber, Toast.LENGTH_SHORT).show();
//        Call<ResponseBody> call = MyApplication.getSerivce().validatePhoneNumber(phoneNumber);
//        call.enqueue(new Listener(new RetrofitService() {
//            @Override
//            public void onSuccess(String result, int pos, Throwable t) {
//                JSONObject obj = null;
//                try {
//                    obj = new JSONObject(result);
//                    if (pos==0){
//                        validPhoneNumber = true;
//                    }else {
//                        validPhoneNumber = false;
//                        Utils.shakeView(SignupActivity.this, primaryNumber);
//                        primaryNumber.setError(obj.getJSONArray("message").getString(0));
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "Validating", true, this));
//
//
//    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }
        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        editTextname.setError(null);

        pojo   = new BusinessAccountPojo();
                // Store values at the time of the login attempt.
        pojo.email  = mEmailView.getText().toString();
        pojo.name = editTextname.getText().toString();
//        pojo.phone_number = editTextPhone.getText().toString();
//        pojo.phone_number = String.valueOf(primaryNumber.getNationalNumber());


       String num = primaryNumber.getNumber();
        pojo.phone_number = num.substring(1);

//        String phone = primaryNumber.getNationalNumber();
//        primaryNumber.getNumber()

        pojo.category= etCategory.getText().toString();
        pojo.services = etService.getText().toString();
        pojo.keywords= etKeyword.getText().toString();
        pojo.address = etAddress.getText().toString();
        pojo.latitude = "17.68";
        pojo.longitude = "83.21";

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty( pojo.email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid( pojo.email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (TextUtils.isEmpty(pojo.name)) {
            editTextname.setError(getString(R.string.error_username));
            focusView = editTextname;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(pojo.phone_number)) {
            primaryNumber.setError(getString(R.string.error_field_required));
            focusView = primaryNumber;
            cancel = true;


        }else if (!isValidPhonneNumber){
            cancel = true;
            primaryNumber.setError(getString(R.string.error_invalid_phone));
            focusView = primaryNumber;
        }


        // Check for a valid email address.
        if (TextUtils.isEmpty(pojo.category)) {
            etCategory.setError(getString(R.string.error_field_required));
            focusView = etCategory;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(pojo.keywords)) {
            etKeyword.setError(getString(R.string.error_field_required));
            focusView = etKeyword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(pojo.services)) {
            etService.setError(getString(R.string.error_field_required));
            focusView = etService;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(pojo.address)) {
            etAddress.setError(getString(R.string.error_field_required));
            focusView = etAddress;
            cancel = true;
        }



        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);


           createBusinessAccount(pojo);


           //            confirmOtp();
//            mAuthTask = new UserLoginTask(email, "password");
//            mAuthTask.execute((Void) null);
        }
    }


    private void createBusinessAccount(BusinessAccountPojo pojo) {
        Call<ResponseBody> call = MyApplication.getSerivce().createBusinessAccountServer(pojo);
        call.enqueue(new Listener(new RetrofitService() {
            @Override
            public void onSuccess(String result, int pos, Throwable t) {

                if (pos == 0) {
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    showProgress(false);

                } else {
                    showProgress(false);

                }
            }
        }, "Registering...", true, this));
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                showProgress(false);

                confirmOtp();
//                finish();
//                startActivity(new Intent(LoginActivity.this, Main2Activity.class));

            } else {
//                mPasswordView.setError(getString(R.string.error_incorrect_password));
//                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }


    //this method will register the user
    private void register() {

        //Displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Registering",
                "Please wait...", false, false);


        //Getting user data
//        username = editTextUsername.getText().toString().trim();
//        password = editTextPassword.getText().toString().trim();
//        phone = editTextPhone.getText().toString().trim();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.dismiss();
                confirmOtp();
            }
        }, 3000);


        //Again creating the string request
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.REGISTER_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        loading.dismiss();
//                        try {
//                            //Creating the json object from the response
//                            JSONObject jsonResponse = new JSONObject(response);
//
//                            //If it is success
//                            if(jsonResponse.getString(Config.TAG_RESPONSE).equalsIgnoreCase("Success")){
//                                //Asking user to confirm otp
//                                confirmOtp();
//                            }else{
//                                //If not successful user may already have registered
//                                Toast.makeText(LoginActivity.this, "Username or Phone number already registered", Toast.LENGTH_LONG).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        loading.dismiss();
//                        Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                //Adding the parameters to the request
//                params.put(Config.KEY_USERNAME, username);
//                params.put(Config.KEY_PASSWORD, password);
//                params.put(Config.KEY_PHONE, phone);
//                return params;
//            }
//        };

        //Adding request the the queue
//        requestQueue.add(stringRequest);
    }


    private void confirmOtp() {
//         throws JSONException
//    }
        //Creating a LayoutInflater object for the dialog box
        LayoutInflater li = LayoutInflater.from(this);
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.dialog_confirm, null);

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
        buttonConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hiding the alert dialog
                alertDialog.dismiss();

                //Displaying a progressbar
                final ProgressDialog loading = ProgressDialog.show(LoginActivity.this, "Authenticating",
                        "Please wait while we check the entered code", false, false);

                loading.dismiss();

                //Starting a new activity
                startActivity(new Intent(LoginActivity.this, Main2Activity.class));


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
}