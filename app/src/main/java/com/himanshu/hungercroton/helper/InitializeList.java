package com.himanshu.hungercroton.helper;

import androidx.appcompat.app.ActionBar;

public class InitializeList {

    public static void baseInitialize(ActionBar actionBar){

        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }



}
