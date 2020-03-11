package com.himanshu.hungercroton.untils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.OpenableColumns;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.himanshu.hungercroton.R;

import java.io.File;
import java.lang.reflect.Field;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@Singleton
public class FuncsVars {
	private static final String TAG = FuncsVars.class.getSimpleName();
	private static Snackbar snackbar;
	public final String dateFormatForApp = "dd MMM yyyy";
	public final String serverDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private final String timeFormatForApp = "hh:mm a";
	public String INTENT_DATA = "intent_data";
	public String INTENT_DATA1 = "intent_data1";
	public String INTENT_DATA2 = "intent_data2";
	Gson gson = new Gson();

	@Inject
	public FuncsVars() {
		super();
	}

	public static void hideSnackbar() {
		if (snackbar != null) {
			snackbar.dismiss();
			snackbar = null;
		}
	}

//	public void setImage(Context context, String url, ImageView imageView, @Nullable Runnable errorListener) {
//		GlideApp.with(context).load(url)
////				.error(R.drawable.ic_image_placeholder).skipMemoryCache(false)
//				.listener(new RequestListener<Drawable>() {
//					@Override
//					public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//						if (errorListener != null) {
//							errorListener.run();
//						}
//						return false;
//					}
//
//					@Override
//					public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//						return false;
//					}
//				})
//				.diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
//	}

//	public void setImageClickListener(final Context context, ImageView imageView, final String url) {
//		imageView.setOnClickListener(view -> {
//			if (url.length() > 1) {
//				ImagePreviewActivity.start(context, addIpInFrontOfUrl(url));
//			}
//		});
//	}

//	public String addIpInFrontOfUrl(String imageUrl) {
//		String formatted = imageUrl;
//		if (!TextUtils.isEmpty(formatted) && !formatted.startsWith("http://") && !formatted
//				.startsWith("https://"))
//			formatted = AppModule.API_BASE_IMAGE_URL + imageUrl;
//		return formatted;
//	}

	public static void setTypeface(Context context, String type, TextView... textViews) {
		Typeface custom_font;
		custom_font = Typeface.createFromAsset(context.getAssets(), type);
		for (TextView textView : textViews) {
			textView.setTypeface(custom_font);
		}
	}

	public static boolean isConnectedToInternet(Context context) {
		ConnectivityManager cm =
				(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnectedOrConnecting();
	}

	public void addFilterForMaxLength(EditText edittext, int maxLength) {
		InputFilter[] filters = edittext.getFilters();
		InputFilter[] filtersFinal = new InputFilter[filters.length + 1];
		System.arraycopy(filters, 0, filtersFinal, 0, filters.length);
		filtersFinal[filters.length] = new InputFilter.LengthFilter(maxLength);
		edittext.setFilters(filtersFinal);
	}

	public String getCommaSeperatedValues(ArrayList<String> strings) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < strings.size(); i++) {
			String s = strings.get(i);
			if (!TextUtils.isEmpty(s)) {
				stringBuilder.append(s);
				if (i != strings.size() - 1) {
					stringBuilder.append(", ");
				}
			}
		}
		return stringBuilder.toString();
	}

	public String formatFileUrlForServer(String fileName) {
		return "/api/read_the_file_stream/" + fileName;
	}

	public  boolean isValidEmail(CharSequence target) {
		return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
	}

	public void Toasts(String message,Context context) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	public <T> boolean isNotEmpty(Collection<T> collection) {
		return collection != null && collection.size() > 0;
	}

	public String getFileNameFromUri(Context context, Uri uri) {
		try {
			String result = null;
			if (uri.getScheme() != null && uri.getScheme().equals("content")) {
				Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
				try {
					if (cursor != null && cursor.moveToFirst()) {
						result = cursor
								.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
					}
				} finally {
					cursor.close();
				}
			}
			if (result == null) {
				result = uri.getPath();
				int cut = result.lastIndexOf('/');
				if (cut != -1) {
					result = result.substring(cut + 1);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "java.lang.Exception---> :", e);
		}
		return "defaultFileName";
	}

	public String formatDateAndTimeForServer(Calendar calendar) {
		return new SimpleDateFormat(serverDateFormat).format(calendar.getTime());
	}

	public void setSwipeRefresh(Context context, SwipeRefreshLayout refreshLayout, SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
		refreshLayout.setColorSchemeColors(ContextCompat
				.getColor(context, R.color.black), ContextCompat
				.getColor(context, R.color.black));
		refreshLayout.setDistanceToTriggerSync(500);
		refreshLayout.setOnRefreshListener(onRefreshListener);
	}

	public void openKeyboard(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm != null) {
			imm.toggleSoftInputFromWindow(view
					.getWindowToken(), InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	public void setSearchViewFontAndLengthBlack(MenuItem searchItem, Context context) {
		SearchView searchView = (SearchView) searchItem.getActionView();
		searchView.setMaxWidth(Integer.MAX_VALUE);
		AutoCompleteTextView searchTextView = searchView
				.findViewById(androidx.appcompat.R.id.search_src_text);
		Typeface myCustomFont = Typeface.createFromAsset(context.getAssets(), Fonts.PTSANS_REGULAR);
		View v = searchView.findViewById(androidx.appcompat.R.id.search_plate);
		v.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
		searchTextView.setTypeface(myCustomFont);
		searchTextView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryText));
		searchTextView.setHintTextColor(ContextCompat.getColor(context, R.color.hint_color));
		final ImageView searchClose = searchView
				.findViewById(androidx.appcompat.R.id.search_close_btn);
		searchClose.setMaxHeight((int) context.getResources()
				.getDimension(R.dimen.image_icon_size_14dp));
		searchClose.setMaxWidth((int) context.getResources()
				.getDimension(R.dimen.image_icon_size_14dp));
		int padding = (int) context.getResources().getDimension(R.dimen.margin_5_dp);
		searchClose.setPadding(padding, padding, padding, padding);
//		searchClose.setImageResource(R.drawable.ic_close);
		searchClose.setColorFilter(ContextCompat.getColor(context, R.color.colorSecondaryText));
		try {
			Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
			mCursorDrawableRes.setAccessible(true);
//			mCursorDrawableRes.set(searchTextView, R.drawable.cursor_primary_color);
		} catch (Exception ignored) {

		}
	}

	public void saveToken(String token) {
		if (!TextUtils.isEmpty(token))
			PreferenceManager.getDefaultSharedPreferences(MyApplication.getmContext()).edit()
					.putString(SPF.TOKEN, token).apply();
	}

	public Integer getAppVersion() {
		return PreferenceManager.getDefaultSharedPreferences(MyApplication.getmContext())
				.getInt(FuncsVars.SPF.APP_VERSION, 0);
	}

	public String getUserId() {
		return PreferenceManager.getDefaultSharedPreferences(MyApplication.getmContext())
				.getString(FuncsVars.SPF.USER_ID, "");
	}


	public boolean saveUserDetails(Integer app_version, String id, String userName, Integer active_status, Integer delete_status, String designation, Integer admin_type, Integer _V, String mobile) {
		try {
			PreferenceManager.getDefaultSharedPreferences(MyApplication.getmContext()).edit()
					.putInt(SPF.APP_VERSION, app_version)
					.putString(SPF.USER_ID, id).putString(SPF.USER_NAME, userName)
					.apply();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "java.lang.Exception---> :", e);
		}
		return false;
	}

	public void removeUserDetails() {
		PreferenceManager.getDefaultSharedPreferences(MyApplication.getmContext()).edit()
				.remove(SPF.APP_VERSION)
				.remove(SPF.USER_ID).remove(SPF.USER_NAME)
				.apply();
	}

	public String getToken() {
		return PreferenceManager.getDefaultSharedPreferences(MyApplication.getmContext())
				.getString(SPF.TOKEN, "");
	}

	public void showDatePicker(final Context context, Calendar calendar, DatePickerDialog.OnDateSetListener listener) {
		DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.PickerTheme, listener, calendar
				.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar
				.get(Calendar.DAY_OF_MONTH));
		datePickerDialog.show();
		setDialogFontAndColor(context, datePickerDialog);
	}

	public void showTimePicker(final Context context, Calendar calendar, TimePickerDialog.OnTimeSetListener listener) {
		TimePickerDialog timePickerDialog = new TimePickerDialog(context, R.style.PickerTheme, listener, calendar
				.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
		timePickerDialog.show();
		setDialogFontAndColor(context, timePickerDialog);
	}

	public void setTypeface(Context context, String font, TextInputLayout... layouts) {
		Typeface custom_font;
		custom_font = Typeface.createFromAsset(context.getAssets(), font);
		for (TextInputLayout layout : layouts) {
			layout.setTypeface(custom_font);
		}
	}

	public String getFalseObjectId() {
		return "5c6e561059bd893fa47b9b1e";
	}

	public Snackbar showSnack(Context context, String text, View view, View.OnClickListener callback, String actionText) {
		Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE)
				.setAction(actionText, callback);
		setSnackbarView(context, snackbar);
		snackbar.setActionTextColor(ContextCompat
				.getColor(context, android.R.color.holo_red_light));
		snackbar.show();
		return snackbar;
	}

	public Snackbar showSnack(Context context, String text, View view) {
		Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
		setSnackbarView(context, snackbar);
		snackbar.show();
		return snackbar;
	}

	public  void onShowSnack(Context context, final View view, final View.OnClickListener callback) {
		try {
			Snackbar snackbar = showSnack(context, context
					.getString(R.string.network_error), view, callback, context
					.getString(R.string.retry));
			snackbar.show();
		} catch (Exception e) {
			Log.e(TAG, "networkFailure: ", e);
		}
	}

	private void setSnackbarView(Context context, Snackbar snackbar) {
		View sbView = snackbar.getView();
		sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
		TextView tv = sbView
				.findViewById(com.google.android.material.R.id.snackbar_text);
		TextView tvw = sbView
				.findViewById(com.google.android.material.R.id.snackbar_action);
		tv.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryText));
		setTypeface(context, Fonts.PTSANS_BOLD, tv, tvw);

	}

	public void confirmDataUpdate(Context context, String title, DialogInterface.OnClickListener saveDataListener, DialogInterface.OnClickListener ignoreDataListener) {

		AlertDialog dialog = new AlertDialog.Builder(context)
				.setTitle(getTypeFaceSpannable(context, title, Fonts.PTSANS_SEMIBOLD))
				.setPositiveButton(R.string.yes, saveDataListener)
				.setNegativeButton(R.string.no, ignoreDataListener).create();
		dialog.show();
		setDialogFontAndColor(context, dialog);
	}

	public SpannableString getTypeFaceSpannable(Context context, String title, String font) {
		SpannableString spannable = new SpannableString(title);
		spannable.setSpan(new CustomTypefaceSpan("", Typeface
				.createFromAsset(context.getAssets(), font)), 0, title
				.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		return spannable;
	}

	private void setDialogFontAndColor(Context context, AlertDialog dialog) {
		dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
				.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryText));
		dialog.getButton(DialogInterface.BUTTON_POSITIVE)
				.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryText));
		dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
				.setTypeface(Typeface.createFromAsset(context.getAssets(), Fonts.PTSANS_BOLD));
		dialog.getButton(DialogInterface.BUTTON_POSITIVE)
				.setTypeface(Typeface.createFromAsset(context.getAssets(), Fonts.PTSANS_BOLD));
	}

	public AlertDialog showDialog(Context context, String title, DialogInterface.OnClickListener possitiveListener, DialogInterface.OnClickListener negativeListener, String positiveActionText, String negativeActionText) {
		AlertDialog dialog = new AlertDialog.Builder(context)
				.setMessage(getTypeFaceSpannable(context, title, Fonts.PTSANS_SEMIBOLD))
				.setPositiveButton(positiveActionText, possitiveListener)
				.setNegativeButton(negativeActionText, negativeListener).create();
		dialog.show();
		setDialogFontAndColor(context, dialog);
		return dialog;
	}

	public String formatDateFromDatePickerValues(int year, int month, int dayOfMonth) {
		month = month + 1;
		String str = year + "-";
		str += ((month > 10) ? month : "0" + month) + "-";
		str += (dayOfMonth > 10) ? dayOfMonth : "0" + dayOfMonth;
		str += "T00:00:00.000Z";
		return formatDate(str, serverDateFormat, dateFormatForApp);
	}

	public String formatTimeFromTimePickerValues(int hourOfDay, int minute) {
		return formatDate(hourOfDay + ":" + minute, "hh:mm", timeFormatForApp);
	}

	public Calendar getCalendarInstanceOfDate(String strDate, String format) {
		Calendar cal = Calendar.getInstance();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = sdf.parse(strDate);
			cal.setTime(date);
			return cal;
		} catch (Exception e) {
			Log.e(TAG, "getCalendarInstanceOfDate: ", e);
			return cal;
		}
	}

	public String formatDateOnlyFromServer(String serverDate) {
		return formatDate(serverDate, serverDateFormat, dateFormatForApp).toUpperCase();
	}

//	public void toggleMenuItemVisibility(final MenuItem menuItem, final boolean visibility) {//0 to hide, 1 to show
//		Runnable runnable = () -> {
//			if (menuItem != null) {
//				menuItem.setVisible(visibility);
//			}
//		};
//		if (menuItem != null) {                  // can update now itself
//			runnable.run();
//		} else {                                 // wait 100 millisecs to update
//			new Handler().postDelayed(runnable, 100);
//		}
//	}

	public String formatDateForServer(String formattedDateString) {
		return formatDate(formattedDateString, dateFormatForApp, serverDateFormat);
	}

	public String formatDate(String formattedDateString, String inputFormat, String oututFormat) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
			SimpleDateFormat format = new SimpleDateFormat(oututFormat);
			Date input = sdf.parse(formattedDateString);
			DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
			symbols.setAmPmStrings(new String[]{"AM", "PM"});
			return format.format(input).toUpperCase();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "";
	}

	public void setView(ViewGroup container, View inflate, int position) {
		View childAt = container.getChildAt(position);
		if (childAt != null) {
			container.removeView(childAt);
		}
		container.addView(inflate, position);
	}

	public String formatTimeFromServer(String serverDate) {
		return formatDate(serverDate, serverDateFormat, timeFormatForApp);
	}

	public String formatDateAndTimeFromServer(String serverDate) {
		return formatDateOnlyFromServer(serverDate) + " " + formatTimeFromServer(serverDate);
	}

	public void onServerFailure(Context context, Throwable t, final View view, final View.OnClickListener callback) {
		try {
			Snackbar snackbar = showSnack(context, context
					.getString(R.string.network_error), view, callback, context
					.getString(R.string.retry));
			if (t.getLocalizedMessage() != null) {
				Log.e("serverFailureFuncVars", t.getLocalizedMessage() + ":" + t.getCause());
				t.printStackTrace();
			}
			t.printStackTrace();
			snackbar.show();
		} catch (Exception e) {
			Log.e(TAG, "onServerFailure: ", e);
		}
	}

	public void closeKeyboard(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm != null) {
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	public boolean isValidNumber(String num) {
		if (!num.isEmpty()) {
			if (num.length() == 10) {
				return num.matches("^[6789]\\d{9}$");
			}
		}
		return false;
	}

	public MultipartBody.Part getMultiPartData(Context context, Uri uri, String type, String name) {
		if (uri != null) {
			File file = new File(uri.getPath());
			RequestBody requestFile = RequestBody
					.create(MediaType.parse(type), file);
			return MultipartBody.Part.createFormData(name, file.getName(), requestFile);
		}
		return null;
	}

	public interface SPF {
		String TOKEN = "token";
		String USER_ID = "user_id";
		String USER_NAME = "user_name";
		String APP_VERSION = "app_version";
	}

	public interface Fonts {
		String PTSANS_REGULAR  = "PTSans-Regular.ttf";
		String PTSANS_SEMIBOLD = "PTSans-SemiBold.ttf";//not PTSANS SEMI BOLD ITS OPEN SANS.. add just for oother error
		String PTSANS_BOLD 	   = "PTSans-Bold.ttf";
	}
}
