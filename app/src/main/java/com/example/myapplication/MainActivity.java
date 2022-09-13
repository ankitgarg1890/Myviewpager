package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAut;
    private EditText medit, pinView,nedit;
    Button loginbt, otpbin;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        medit = findViewById(R.id.medit);
        Number number;
        pinView = findViewById(R.id.pinView);
        mAut = FirebaseAuth.getInstance();
        nedit= findViewById(R.id.nedit);
        loginbt = findViewById(R.id.loginbt);
        otpbin = findViewById(R.id.otpbtn);

        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(medit.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    String phone = "+91" + medit.getText().toString();
                    sendVerificationCode(phone);
                }
            }

        });

        medit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        otpbin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(pinView.getText().toString())) {

                    Toast.makeText(MainActivity.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    verifyCode(pinView.getText().toString());
                }
            }
        });
    }

    private void signInWithCredential(PhoneAuthCredential credential) {

        mAut.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("taskkk", "onComplete: " + task.toString());
                if (task.isSuccessful()) {
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void sendVerificationCode(String number) {
        Log.d("nubmer", "sendVerificationCode: " + number);
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAut)
                .setPhoneNumber(number)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallBack)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            Log.d("onCodesend", "onCodeSent: " + s);
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            Log.d("onComplete", "onCodeSent: " + phoneAuthCredential.getSmsCode());
            final String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                pinView.setText(code);
                verifyCode(code);

            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Log.d("onFaild", "onCodeSent: " + e);
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    };

    private void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        signInWithCredential(credential);

    }
}