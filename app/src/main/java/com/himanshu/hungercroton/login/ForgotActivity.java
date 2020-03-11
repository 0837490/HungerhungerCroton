package com.himanshu.hungercroton.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.activity.MainActivity;
import com.himanshu.hungercroton.helper.Common;
import com.himanshu.hungercroton.untils.FuncsVars;

public class ForgotActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack;
    private EditText etEmail;
    private Button btnForgot;
    private TextView txSignin;
    private TextView textforgot;
    private TextView tvforgotdetails;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Common.isInternetAvailable(this)) {
            setContentView(R.layout.activity_forgot);
            inIt();
        } else {
            Common.InternetError(this);
            setContentView(R.layout.error_layout);
        }

    }

    private void inIt() {
        ivBack = findViewById(R.id.ivBack);
        etEmail = findViewById(R.id.etEmail);
        btnForgot = findViewById(R.id.btnForgot);
        txSignin = findViewById(R.id.txSignin);
        textforgot = findViewById(R.id.textforgot);
        tvforgotdetails = findViewById(R.id.tvforgotdetails);
        setFont();
        clickListener();

    }

    private void setFont() {
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, txSignin);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, textforgot);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, tvforgotdetails);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, btnForgot);
    }

    private void clickListener() {
        ivBack.setOnClickListener(this::onClick);
        btnForgot.setOnClickListener(this::onClick);
        txSignin.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {

        int vId = view.getId();

        if (vId == R.id.ivBack) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);


        } else if (vId == R.id.btnForgot) {
            validateForgot();


        } else if (vId == R.id.txSignin) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    }

    private void validateForgot() {
        email = etEmail.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Enter you mail");
        } else if (Common.isEmail(email)) {
            etEmail.setError("Enter vaild Email");
        } else {
//            SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
