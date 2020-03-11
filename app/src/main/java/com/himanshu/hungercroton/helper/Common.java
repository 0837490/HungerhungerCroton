package com.himanshu.hungercroton.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import static com.smarteist.autoimageslider.IndicatorView.utils.DensityUtils.dpToPx;

public class Common {
    private static ProgressDialog dialog;
    private static ProgressBar progressBar;


    public static boolean isEmail(String email) {

        //Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        return !Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }


    public static byte[] encryptMsg(String message, SecretKey secret)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        /* Encrypt the message. */
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        byte[] cipherText = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return cipherText;
    }

    public static String decryptMsg(byte[] cipherText, SecretKey secret)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidParameterSpecException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        /* Decrypt the message, given derived encContentValues and initialization vector. */
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret);
        String decryptString = new String(cipher.doFinal(cipherText), StandardCharsets.UTF_8);
        return decryptString;
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLog(String TAG, String Message) {
        Log.d(TAG, Message);
    }

    public static String imageToString(Bitmap BitmapData) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        BitmapData.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] byte_arr = bos.toByteArray();
        String file = Base64.encodeToString(byte_arr, Base64.DEFAULT);
        return file;
    }


    public static void whichPermisionNotGranted(Context context, String[] permissions, int[] grantResults) {
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                showToast(context, "Authentication Permission Not Enabled");
            }
        }
    }

    public static void InternetError(Context context) {
        Toast.makeText(context, "Please Check Your Internet Connections...!", Toast.LENGTH_SHORT).show();
    }

    public static String getConvertDate(String sourceFormat, String destFormat, String strDate) {
        String finalDate = "";
        try {

            DateFormat srcDf = new SimpleDateFormat(sourceFormat);
            Date date = srcDf.parse(strDate);
            DateFormat destDf = new SimpleDateFormat(destFormat);
            finalDate = destDf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalDate;

    }

    public static String ConvertToDate(String dateString) {
        String newDateStr = "";
        SimpleDateFormat form = null;
        Date date = null;

        form = new SimpleDateFormat("MM/dd/yyyy");
        try {
            date = form.parse(dateString);
            SimpleDateFormat postFormater = new SimpleDateFormat("dd/MM/yyyy");
            newDateStr = postFormater.format(date).toUpperCase();
            System.out.println("Date  : " + newDateStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDateStr;
    }


    public static void showProgressDialog(Context context) {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog = new ProgressDialog(context);
            dialog.setIndeterminate(true);
            dialog.setMessage("Please Wait...!");
            dialog.setCancelable(false);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dismissProgressDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void toastMessage(Context context) {
        Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_LONG).show();
    }

    public static boolean isInternetAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return netInfo != null && netInfo.isConnected() && netInfo.isAvailable();
    }


    public static long getDateTimeStamp(String format, String date) {
        long timeStamp = 0;
        DateFormat formatter = new SimpleDateFormat(format);
        Date mDate = null;
        try {
            mDate = formatter.parse(date);
            timeStamp = mDate.getTime();
        } catch (ParseException e) {
            timeStamp = 0;
            e.printStackTrace();
        }
        return timeStamp;
    }

    public static long getCurrentTimeStamp() {

        Calendar c = Calendar.getInstance();
        String date = c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR);
        //return getDateTimeStamp("yyyy-MM-dd", date);
        return getDateTimeStamp("dd/MM/yyyy", date);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isAutomaticTimeZoneOff(Context context) {
        int autoTime = Settings.Global.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME, 0);
        int autoTimeZone = Settings.Global.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME_ZONE, 0);

        return autoTime == 0 && autoTimeZone == 0;
    }

    public static String getDateFromTimeStamp(long timeStamp, String dateFormat) {
        DateFormat objFormatter = new SimpleDateFormat(dateFormat);

        Calendar objCalendar = Calendar.getInstance();
        objCalendar.setTimeInMillis(timeStamp);
        String result = objFormatter.format(objCalendar.getTime());
        objCalendar.clear();
        return result;
    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if (listItem != null) {
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    public static int getDeviceWidth(Activity activity) {

        WindowManager wm = activity.getWindowManager();
        Point point = new Point();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            wm.getDefaultDisplay().getSize(point);
            return point.x;
        } else {
            return wm.getDefaultDisplay().getWidth();
        }
    }

    public static int getDeviceHeight(Activity activity) {
        WindowManager wm = activity.getWindowManager();
        Point point = new Point();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            wm.getDefaultDisplay().getSize(point);
            return point.y;
        } else {
            return wm.getDefaultDisplay().getHeight();
        }
    }

    public static boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    public static void openKeyBoard(Context context) {
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).
                toggleSoftInput(InputMethodManager.SHOW_FORCED,
                        InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void closeKeyBoard(Context activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public static void hideKeyboard(Context context) {
        Activity activity = (Activity) context;

        InputMethodManager inputManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        View focus = activity.getCurrentFocus();
        if (focus != null)
            inputManager.hideSoftInputFromWindow(focus.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void gridRecyclerView(Context context, RecyclerView recyclerView) {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacing.GridSpacingItemDecoration(1, dpToPx(0), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public static void horizentalRecyclerView(Context context, RecyclerView recyclerView) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacing.GridSpacingItemDecoration(1, dpToPx(0), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public static void verticalRecyclerView(Context context, RecyclerView recyclerView) {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacing.GridSpacingItemDecoration(1, dpToPx(0), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


  /*  public static void hideKeyboardCall(Context mcontenxt, EditText edtm) {
        try {
            InputMethodManager imm = (InputMethodManager) mcontenxt.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtm.getWindowToken(), 0);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("ConstantUTIL", " error " + ex.toString());
        }
    }

    public static void showKeyboardForced(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

    }*/


    public static int getDisplayWidth(Activity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return width;
    }

    public static int getDisplayHeight(Activity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return height;
    }

    public static void Logcat(String TAG, String Message) {
        Log.d(TAG, Message);

    }


}
