package com.lerryrowy.directoryview;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class Utils {
	public static int DpToPx(Context context, int dp){
		//convert 100dp to px
		Resources r = context.getResources();
		return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
	}
}
