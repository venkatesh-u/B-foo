package com.venkatesh.businessoffers.utilities;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by Zhukai on 2014/5/29 0029.
 */
public class Density {
	public static int dp2px(Context context, float dp) {
		Resources r = context.getResources();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
		return (int) px;
	}
	 public static float dp2px(Resources resources, float dp) {
	        final float scale = resources.getDisplayMetrics().density;
	        return  dp * scale + 0.5f;
	    }

	    public static float sp2px(Resources resources, float sp){
	        final float scale = resources.getDisplayMetrics().scaledDensity;
	        return sp * scale;
	    }
}