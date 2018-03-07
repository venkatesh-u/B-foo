package com.venkatesh.businessoffers;

import android.app.Activity;
import android.widget.Toast;


import com.squareup.okhttp.ResponseBody;
import com.venkatesh.businessoffers.Dialogs.CustomDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by RameshK on 25-11-2015.
 *
 */
public class Listener implements Callback<ResponseBody> {

    Type tt;
    RetrofitService listner;
    CustomDialog dialog;
    BaseActivity mActivity;
    WeakReference<Activity> activity;

    public Listener(RetrofitService listner, String title, boolean showProgress, Activity activity) {
        //tt=t;
        this.listner = listner;
        dialog = ((BaseActivity)activity).dialog;
        //dialog = new CustomDialog(activity);
        dialog.setCancelable(false);
        this.activity =  new WeakReference<>(activity);
        mActivity = (BaseActivity)this.activity.get();
        if (title != null && title.length() > 0) {
            dialog.setTitle(title);
        }
        else
        {
            dialog.setTitle(null);
        }
        if (showProgress && !mActivity.isFinishing() && dialog!=null && !dialog.isShowing()) {
            dialog.show();
        }

    }

    @Override
    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {

        try {

            if(!mActivity.isFinishing() && dialog!=null && dialog.isShowing())
                dialog.dismiss();
            if (!response.isSuccess()) {
                Toast.makeText(mActivity, "Something went wrong", Toast.LENGTH_SHORT).show();
//                Utils.showSnackBarLongTime(mActivity, Utils.ERROR_SOMETHING+"..");
                listner.onSuccess(response.message(), 2, null);
            } else {
                String res = response.body().string();
                JSONObject obj = new JSONObject(res);

                if (!obj.has("success") || !obj.getBoolean("success")) {
                    showError(obj );
                } else{
                    listner.onSuccess(res, 0, null);
                }
            }
        } catch (IOException | JSONException  | IllegalArgumentException e) {
            e.printStackTrace();
            listner.onSuccess("", 2, null);
        }
    }

    public void showError(JSONObject obj) {
        if (!obj.isNull("message")) {
            try {
//                obj.getJSONArray("message").getString(0)
//                Utils.showSnackBarLongTime(mActivity, obj.getJSONArray("message").getString(0));
                Toast.makeText(mActivity, obj.getString("message"), Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(mActivity, "Something went wrong", Toast.LENGTH_SHORT).show();

            } finally {
                listner.onSuccess(obj.toString(), 2, null);
            }
        } else {

//            Utils.showSnackBar(mActivity, Utils.ERROR_SOMETHING);
            Toast.makeText(mActivity, "Something went wrong", Toast.LENGTH_SHORT).show();

            listner.onSuccess("", 2, null);
        }
    }


    @Override
    public void onFailure(Throwable t) {
        if(dialog!=null && dialog.isShowing())
        dialog.dismiss();
        listner.onSuccess("", 1, t);
        if(t!=null)
            t.printStackTrace();
    }

}
