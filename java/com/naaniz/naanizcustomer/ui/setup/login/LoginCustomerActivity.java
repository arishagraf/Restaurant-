package com.naaniz.naanizcustomer.ui.setup.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.naaniz.naanizcustomer.R;
import com.naaniz.naanizcustomer.network.Service;
import com.naaniz.naanizcustomer.network.objects.LoginResponse;
import com.naaniz.naanizcustomer.network.objects.LoginUserDetails;
import com.naaniz.naanizcustomer.network.objects.Test;
import com.naaniz.naanizcustomer.ui.main.MainActivity;
import com.naaniz.naanizcustomer.ui.setup.signup.SignUpCustomer1;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginCustomerActivity extends AppCompatActivity {

    private static final String TAG = "LoginCustomerActivity";
    private EditText phoneInput;
    private EditText otpInput;
    private Button button;
    private boolean otpSent = false, resending=false;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId, phone_number;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private TextView noteText, signupText;
    private CountDownTimer timer=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_customer);

        phoneInput = findViewById(R.id.cust_phone_login);
        otpInput = findViewById(R.id.cust_otp_login);
        button = findViewById(R.id.button_verify);
        otpInput.setVisibility(View.GONE);
        noteText = findViewById(R.id.note_text);
        signupText = findViewById(R.id.sign_up_instead);
        signupText.setPaintFlags(signupText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        mAuth = FirebaseAuth.getInstance();

        initCallback();
        Log.d(TAG, mResendToken+ " ");

        button.setOnClickListener(v -> {

            Log.d(TAG, "Clicked");

            if(!otpSent){
                String phoneNumber = phoneInput.getText().toString();

                if(verifyPhoneNumber(phoneNumber)){
                    phoneNumber = "+91" + phoneNumber;
                    otpInput.setVisibility(View.VISIBLE);
                    button.setText(R.string.verify);

                    phoneVerification(phoneNumber);
                }
            }
            else{

                String otp = otpInput.getText().toString();

                if(verifyOTPString(otp)){
                    verifyOTP(otp);
                }
                else{
                    Toast.makeText(this, "Enter 6 digit OTP", Toast.LENGTH_SHORT).show();
                }
                //TODO verify and go to next page
            }
        });

        signupText.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUpCustomer1.class);
            startActivity(intent);
        });
    }

    private void verifyOTP(String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");

                        FirebaseUser user = task.getResult().getUser();
                        if(user!=null)
                        Log.d(TAG, user.getUid());
                        login(mAuth.getCurrentUser().getUid());

                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean verifyOTPString(String otp) {
        otp = otp.trim();
        return otp.length() == 6;
    }

    private void initCallback() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                Log.d(TAG, "onVerificationCompleted:" + credential);
                signInWithPhoneAuthCredential(credential);

                //try{ Log.d(TAG, mAuth.getCurrentUser().getUid());}catch (Exception e){e.printStackTrace();}

                //testSignupPost(mAuth.getCurrentUser().getUid());

                //login(mAuth.getCurrentUser().getUid());

                if(timer!=null)
                    timer.cancel();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                 } else if (e instanceof FirebaseTooManyRequestsException) {
                }

                }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Log.d(TAG, "onCodeSent:" + verificationId);
                mVerificationId = verificationId;
                mResendToken = token;

                noteText.setText("Code Sent");
                otpSent = true;

                if(timer!=null){
                    timer.cancel();
                }

                timer = new CountDownTimer(60000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        noteText.setText(getResources().getString(R.string.resend_timer, Long.toString (millisUntilFinished / 1000)) );
                    }

                    public void onFinish() {
                        noteText.setText("");
                        button.setText(R.string.resend);
                        resending=true;
                        otpSent=false;
                    }
                };

                timer.start();

            }
        };
    }

    private void login(String uuid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://naaniz.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        Log.d(TAG, uuid);

        Call<LoginResponse> response = service.logInUser(new LoginUserDetails(uuid));

        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                Log.d(TAG, " Code = "+response.code());
                    if(response.code()==200){
                        Log.d(TAG, response.body().getAuthToken());
                        Toast.makeText(LoginCustomerActivity.this, "go to home", Toast.LENGTH_SHORT).show();

                        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("JWT", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(getString(R.string.jwt_token), response.body().getAuthToken());
                        editor.apply();

                        Intent intent = new Intent(LoginCustomerActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();

                    }
                    else if(response.code() == 404){
                        Toast.makeText(LoginCustomerActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Toast.makeText(LoginCustomerActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            login(user.getUid());
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void phoneVerification(String phoneNumber) {

        if(!resending){
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNumber,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    mCallbacks);        // OnVerificationStateChangedCallbacks
        }
        else{
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNumber,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    mCallbacks,
                    mResendToken);        // OnVerificationStateChangedCallbacks

            if(timer!=null)
                timer.start();
        }

    }

    private boolean verifyPhoneNumber(String phoneNumber) {

        phoneNumber = phoneNumber.trim();

        if(phoneNumber.length()!=10)
            return false;
        else
            return true;
    }


    private void testSignupPost(String uuid){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://naaniz.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        Call<LoginResponse> response = service.test(new Test(uuid, "Abhishek Jagushte"));

        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d(TAG, " Code = "+response.code());
                if(response.isSuccessful()){
                    Log.d(TAG, response.body().getAuthToken());

                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(getString(R.string.jwt_token), response.body().getAuthToken());
                    editor.apply();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

}
