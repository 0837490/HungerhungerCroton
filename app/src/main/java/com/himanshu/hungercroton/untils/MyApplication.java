package com.himanshu.hungercroton.untils;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.lang.reflect.Field;

public class MyApplication extends Application {

	private static final String TAG = MyApplication.class.getSimpleName();
	private static MyApplication mInstance;

	public static Application getmContext() {
		return mInstance;
	}

	public static synchronized MyApplication getInstance() {
		return mInstance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		overrideFont(getApplicationContext(), "SERIF", FuncsVars.Fonts.PTSANS_REGULAR);
	}

	public void overrideFont(Context context, String defaultFontNameToOverride, String customFontFileNameInAssets) {
		try {
			final Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), customFontFileNameInAssets);
			final Field defaultFontTypefaceField = Typeface.class.getDeclaredField(defaultFontNameToOverride);
			defaultFontTypefaceField.setAccessible(true);
			defaultFontTypefaceField.set(null, customFontTypeface);
		} catch (Exception e) {
			Log.e("Font","Can not set custom font " + customFontFileNameInAssets + " instead of " + defaultFontNameToOverride,e);
		}
	}


}
