package com.himanshu.hungercroton.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.activity.MainActivity;
import com.himanshu.hungercroton.helper.AESUtils;
import com.himanshu.hungercroton.helper.Common;
import com.himanshu.hungercroton.untils.FuncsVars;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack;
    private TextView textd;
    private TextView texts;
    private TextView textaccountd;
    private EditText etUserName;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etPassword;
    private SecretKey secret;
    private Button btnSignUp;
    private String encrypted="";
    private TextView txSignin;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String username, email, phone, password;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                final String message = intent.getStringExtra("message");

//                TextView tv = (TextView) findViewById(R.id.txtview);
//                tv.setText(message);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Common.isInternetAvailable(this)) {
            setContentView(R.layout.activity_registration);
            inIt();

        } else {
            setContentView(R.layout.error_layout);
            Common.InternetError(this);

        }
    }

    private void inIt() {
        ivBack = findViewById(R.id.ivBack);
        textd = findViewById(R.id.textd);
        texts = findViewById(R.id.texts);
        textaccountd = findViewById(R.id.textaccountd);
        etUserName = findViewById(R.id.etUserName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        txSignin = findViewById(R.id.txSignin);

        setFont();
        inItListener();
    }

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private void setFont() {
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, textd);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, texts);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_REGULAR, textaccountd);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, btnSignUp);
        FuncsVars.setTypeface(this, FuncsVars.Fonts.PTSANS_BOLD, txSignin);
    }

    private void inItListener() {

        ivBack.setOnClickListener(this::onClick);
        btnSignUp.setOnClickListener(this::onClick);
        txSignin.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View view) {
        int vId = view.getId();

        if (vId == R.id.ivBack) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        } else if (vId == R.id.btnSignUp) {
            validateregistration();
        } else if (vId == R.id.txSignin) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void validateregistration() {
        username = etUserName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        phone = etPhone.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if (username.isEmpty()) {
            etUserName.setError("Enter user name");
        } else if (email.isEmpty()) {
            etEmail.setError("Enter you email");
        } else if (Common.isEmail(email)) {
            etEmail.setError("Enter vaild email");
        } else if (phone.isEmpty()) {
            etPhone.setError("Enter phone Number");
        } else if (password.isEmpty()) {
            etPassword.setError("Enter password");
        } else {
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
//            Common.showProgressDialog(this);
//            mAuth = FirebaseAuth.getInstance();
//            mAuth.getAccessToken(true);
//            mAuth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                Log.d("", "createUserWithEmail:success");
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                updateUI(user);
//                                Toast.makeText(RegistrationActivity.this, "Authentication successfully", Toast.LENGTH_SHORT).show();
//                                SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
//                                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
//                                startActivity(intent);
//                                finish();
//                                Common.dismissProgressDialog();
//                            } else {
//                                Log.w("", "createUserWithEmail:failure", task.getException());
//                                Toast.makeText(RegistrationActivity.this, "Authentication failed.",
//                                        Toast.LENGTH_SHORT).show();
//                                updateUI(null);
//                                Common.dismissProgressDialog();
//                            }
//
//                        }
//                    });
        }
    }


    private void updateUI(FirebaseUser currentUser) {


        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userid = currentUser.getUid();
            String email = currentUser.getEmail();
            Uri photoUrl = currentUser.getPhotoUrl();
            boolean emailVerified = currentUser.isEmailVerified();
            assert email != null;
            assert photoUrl != null;


            try {
                encrypted = AESUtils.encrypt(System.currentTimeMillis()+"");
            } catch (Exception e) {
                e.printStackTrace();
            }

            Map<String, Object> user = new HashMap<>();
            user.put("userid", userid);
            user.put("name", username);
            user.put("number", phone);
            user.put("email", email);
            user.put("password", password);
            user.put("token", encrypted);
            user.put("photoUrl", photoUrl);
            user.put("emailVerified", emailVerified);
            user.put("imei","" );
            user.put("rollStatus", "1");
            user.put("status", "1");
            db = FirebaseFirestore.getInstance();
            db.collection("userDetails").document(userid).collection("symptom_data").add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("", "Error adding document", e);
                }
            });
        }

    }
}
