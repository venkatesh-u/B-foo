package com.venkatesh.businessoffers.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.venkatesh.businessoffers.R;
import com.venkatesh.businessoffers.customViews.ProgressWheel;


public class CustomDialog extends Dialog {

    //CircleProgress progress;
    ProgressWheel progress;
    Context con;
    Activity act;
    String title;
    LinearLayout layout;

    public CustomDialog(Activity act) {
        super(act);
        con = act;
        this.act = act;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        createView();
    }

    public CustomDialog(Activity act, String title) {
        super(act);
        con = act;
        this.act = act;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.title = title;
        createView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCanceledOnTouchOutside(false);
        setContentView(layout);

    }

    private void createView() {
        layout = new LinearLayout(con);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(80, 80);
        layout.setLayoutParams(lp);
        layout.setBackgroundColor(con.getResources().getColor(R.color.white));
        layout.setGravity(Gravity.CENTER);
        progress = new ProgressWheel(con);
        lp.height = 150;
        lp.width = 150;
        progress.setLayoutParams(lp);
        progress.setBarColor(act.getResources().getColor(R.color.bluee));


		/*timer.schedule(new TimerTask() {
            @Override 
            public void run() {
                act.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progress.setProgress(progress.getProgress() + 10);
                        
                    }
                });
            }
        }, 10, 100);*/
        progress.requestFocus();
        progress.spin();
        layout.addView(progress);
        if (title != null) {
            TextView tv = new TextView(con);
            tv.setText(title);

            tv.setTextColor(act.getResources().getColor(R.color.black_overlay));
            tv.setPadding(5, 0, 20, 0);
            tv.setGravity(Gravity.CENTER);
            tv.setId(R.id.tvTitle);
            layout.addView(tv);
        }


    }

    public void setTitle(String title) {
        if (title != null) {
            if(layout.findViewById(R.id.tvTitle)!=null) {
                layout.removeView(layout.findViewById(R.id.tvTitle));
            }
            TextView tv = new TextView(con);
            tv.setText(title);
            tv.setTextColor(act.getResources().getColor(R.color.black_overlay));
            tv.setPadding(5, 0, 20, 0);
            tv.setGravity(Gravity.CENTER);
            tv.setId(R.id.tvTitle);
            layout.addView(tv);
            //Log.i("childdddd", String.valueOf(layout.getChildCount()));
        }
        else{
            if(layout.findViewById(R.id.tvTitle)!=null) {
                layout.removeView(layout.findViewById(R.id.tvTitle));
            }
        }
    }

    @Override
    public void setOnDismissListener(OnDismissListener listener) {
//		if(timer!=null)
//			timer.cancel();	
        super.setOnDismissListener(listener);

    }

    @Override
    protected void onStop() {
//		if(timer!=null)
//			timer.cancel();	
        super.onStop();
    }
/*
    public void dismissDialog() {
        dismiss();
    }*/

}
