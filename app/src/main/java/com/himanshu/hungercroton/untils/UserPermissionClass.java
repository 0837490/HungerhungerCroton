package com.himanshu.hungercroton.untils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class UserPermissionClass {

    public static String getUniqueIMEIId(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                return "";
            }
            String imei = telephonyManager.getDeviceId();
            Log.e("imei", "=" + imei);
            if (imei != null && !imei.isEmpty()) {
                return imei;
            } else {
                return Build.SERIAL;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "not_found";
    }

    public static void getPonestatus(Activity activity){
        String TAG = "PhoneActivityTAG";
        final int PERMISSION_REQUEST_CODE = 1;
        if (!checkPermission(activity,Manifest.permission.READ_PHONE_STATE)) {
            requestPermission(activity,Manifest.permission.READ_PHONE_STATE);
        } else {
            Log.d(TAG, "Phone number: " + getPhone(activity));
        }

    }
    private static String getPhone(Activity activity) {
        TelephonyManager phoneMgr = (TelephonyManager)activity.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }
        return phoneMgr.getLine1Number();
    }

    private static void requestPermission(Activity activity, String permission){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)){
            Toast.makeText(activity, "Phone state permission allows us to get phone number. Please allow it for additional functionality.", Toast.LENGTH_LONG).show();
        }
        ActivityCompat.requestPermissions(activity, new String[]{permission},1);
    }

    private static boolean checkPermission(Activity activity,String permission){
        if (Build.VERSION.SDK_INT >= 23) {
            int result = ContextCompat.checkSelfPermission(activity, permission);
            return result == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    public static Boolean checkAndRequestPermissions(Activity activity) {
        int permissionSendMessage = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.SEND_SMS);
        int receiveSMS = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_SMS);
        int readSMS = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (receiveSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECEIVE_MMS);

        }

        if (readSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_SMS);
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
        }
        UserPermissionClass.getPonestatus(activity);
        UserPermissionClass.getUniqueIMEIId(activity);
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    1);
            return false;
        }
        return true;
    }
}
