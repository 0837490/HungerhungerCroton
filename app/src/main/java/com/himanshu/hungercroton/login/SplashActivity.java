package com.himanshu.hungercroton.login;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.untils.FuncsVars;
import com.himanshu.hungercroton.untils.MyApplication;

import javax.inject.Named;

import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @Named("Insecure")
    private Handler mWaitHandler = new Handler();
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        handler("0");
    }

    private void handler(String status) {
        mWaitHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (status.equals("0")) {


                        Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 2000);
    }


    private void versionCheck(int versionFromDB) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        assert info != null;
        int versionCodeInApp = info.versionCode;
        if (versionCodeInApp < versionFromDB) {
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setMessage("Update your app for a better viewing experience");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Update Later",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Update Now",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            updateAppIntent();
                        }
                    });
            alertDialog.show();

        } else {
            PreferenceManager.getDefaultSharedPreferences(MyApplication.getmContext()).edit()
                    .putInt(FuncsVars.SPF.APP_VERSION, versionFromDB).apply();
        }
    }

    private void updateAppIntent() {
        String url = "market://details?id=" + context.getPackageName();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
        ((Activity) context).finish();
        System.exit(0);
    }




}