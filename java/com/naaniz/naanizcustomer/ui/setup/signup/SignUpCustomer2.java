package com.naaniz.naanizcustomer.ui.setup.signup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.naaniz.naanizcustomer.R;

import java.util.concurrent.TimeUnit;

public class SignUpCustomer2 extends AppCompatActivity {
    private String Verificationid;
    private EditText enterotp;
    private CheckBox terms;
    private FirebaseAuth mAuth;
    TextView senttophonenumber;
    String phonenumber, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupcustomer2);
        mAuth=FirebaseAuth.getInstance();
        enterotp=findViewById(R.id.cust_otp_login);
        terms=findViewById(R.id.checkBox);

        senttophonenumber=findViewById(R.id.senttophonenumber);
        phonenumber=getIntent().getStringExtra("phonenumber");
        name = getIntent().getStringExtra("name");

//        senttophonenumber.setText("Verification code is sent to"+" "+phonenumber);
        sendVerificationCode(phonenumber);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=enterotp.getText().toString().trim();
                if(code.isEmpty()||code.length()<6 && !terms.isChecked()){
                    enterotp.setError("Entercode...");
                    enterotp.requestFocus();
                    return;
                }
                verifycode(code);
            }
        });
    }
    private void verifycode(String code){
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(Verificationid,code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Intent intent=new Intent(SignUpCustomer2.this,SignUpCustomer3.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("name", name);
                    intent.putExtra("phonenumber", phonenumber);
                    startActivity(intent);

                }else{
                    Toast.makeText(SignUpCustomer2.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void sendVerificationCode(String number){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(

                number,
                60,
                TimeUnit.SECONDS,
               this,
                mCallBack
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            Verificationid=s;
            senttophonenumber.setText("Verification code is sent to"+" "+phonenumber);
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Toast.makeText(SignUpCustomer2.this, "Completed", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(SignUpCustomer2.this, SignUpCustomer3.class);
            startActivity(intent);
            finish();

            String code=phoneAuthCredential.getSmsCode();
            if(code!=null){
                enterotp.setText(code);
//                verifycode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.v("hii" , e.getMessage()) ;
            Toast.makeText(SignUpCustomer2.this,e.getMessage() + "//////////////////",Toast.LENGTH_LONG).show();
        }
    };

}
