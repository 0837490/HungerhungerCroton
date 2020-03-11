package com.himanshu.hungercroton.activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.helper.InitializeList;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        inIt();
    }

    private void inIt() {
        ActionBar actionBar = getSupportActionBar();
        InitializeList.baseInitialize(actionBar);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
