package com.himanshu.hungercroton.login;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.activity.MainActivity;
import com.himanshu.hungercroton.helper.Common;
import com.himanshu.hungercroton.helper.SaveSharedPreference;
import com.himanshu.hungercroton.helper.SharedPreferencesData;
import com.himanshu.hungercroton.untils.FuncsVars;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;
    private TextView txForgot;
    private TextView textd;
    private TextView texts;
    private TextView textsign;
    private Button btnLogin;
    private TextView txSignup;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Common.isInternetAvailable(this)) {
            setContentView(R.layout.activity_login);
            inIt();

        } else {
            setContentView(R.layout.error_layout);
        }
        if (SaveSharedPreference.getLoggedStatus(this)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void inIt() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        txForgot = findViewById(R.id.txForgot);
        txSignup = findViewById(R.id.txSignup);
        btnLogin = findViewById(R.id.btnLogin);
        textd = findViewById(R.id.textd);
        texts = findViewById(R.id.texts);
        textsign = findViewById(R.id.textsign);
        mAuth = FirebaseAuth.getInstance();
        setFont();
        inItListener();

    }

    private void setFont() {
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, txForgot);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, txSignup);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, btnLogin);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, textd);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, texts);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, textsign);
    }

    private void inItListener() {
        btnLogin.setOnClickListener(this::onClick);
        txSignup.setOnClickListener(this::onClick);
        txForgot.setOnClickListener(this::onClick);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }


    @Override
    public void onClick(View view) {

        int vId = view.getId();
        if (vId == R.id.btnLogin) {

            validateLogin();
        } else if (vId == R.id.txSignup) {

            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
            finish();
        } else if (vId == R.id.txForgot) {

            Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void validateLogin() {

        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

//        if (email.isEmpty()) {
//            etEmail.setError("Enter your email");
//        } else if (Common.isEmail(email)) {
//            etEmail.setError("Vaild email address");
//        } else if (password.isEmpty()) {
//            etPassword.setError("Enter Password address");
//            txForgot.setVisibility(View.GONE);
//        } else {
        txForgot.setVisibility(View.VISIBLE);
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
//            Common.showProgressDialog(this);
//            mAuth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                Log.d("", "signInWithEmail:success");
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                updateUI(user);
//
//                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//                                startActivity(intent);
//                                finish();
//                                Toast.makeText(LoginActivity.this, "You are Login successfully", Toast.LENGTH_SHORT).show();
//                                Common.dismissProgressDialog();
//
//                            } else {
//                                Log.w("", "signInWithEmail:failure", task.getException());
//                                Toast.makeText(LoginActivity.this, "Authentication failed.",
//                                        Toast.LENGTH_SHORT).show();
//                                updateUI(null);
//                            }
//                        }
//                    });
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("PhoneActivityTAG", "Phone number: " + "");
                } else {
                    Toast.makeText(this, "Permission Denied. We can't get phone number.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    private void updateUI(FirebaseUser currentUser) {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            Log.e("userDetails", uid);
            db = FirebaseFirestore.getInstance();
            Query mQuery = db.collection("userDetails").whereEqualTo("userid",uid);
            mQuery.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                    SharedPreferencesData preferencesData=SharedPreferencesData.getInstance(LoginActivity.this);
                                    preferencesData.setUserID(Objects.requireNonNull(document.getData().get("userid")).toString().trim());
                                    preferencesData.setUserName(Objects.requireNonNull(document.getData().get("name")).toString().trim());
                                    preferencesData.setEmail(Objects.requireNonNull(document.getData().get("email")).toString().trim());
                                    preferencesData.setPhoneNumber(Objects.requireNonNull(document.getData().get("number")).toString().trim());
                                    preferencesData.setToken(Objects.requireNonNull(document.getData().get("token")).toString().trim());

                                }
                            } else {
                                Log.w("Details", "Error getting documents.", task.getException());
                            }
                        }
                    });


        }
    }
}
