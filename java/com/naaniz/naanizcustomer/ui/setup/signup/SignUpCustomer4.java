package com.naaniz.naanizcustomer.ui.setup.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.naaniz.naanizcustomer.R;
import com.naaniz.naanizcustomer.database.AppDatabase;
import com.naaniz.naanizcustomer.database.domainobjects.Profile;
import com.naaniz.naanizcustomer.network.Service;
import com.naaniz.naanizcustomer.network.objects.LoginResponse;
import com.naaniz.naanizcustomer.network.objects.LoginUserDetails;
import com.naaniz.naanizcustomer.network.objects.UserDetails;
import com.naaniz.naanizcustomer.ui.setup.login.LoginCustomerActivity;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpCustomer4 extends AppCompatActivity {

    private static final String TAG = "SignUpCustomer4";
    private EditText email;
    private EditText dob;
    private EditText city;

    private String mailId, name, phonenumber;
    private String birthDate;
    private String cityName;
    private Spinner languageSpinner;
    private Spinner stateSpinner;
    private FirebaseAuth mAuth;
    private DatePicker picker;
    private Button displayDate;
    private Button calender;

    String[] languagesArray = {"English", "Hindi"};
    String[] stateArray = {"Andhra Pradesh",
            "Arunachal Pradesh",
            "Assam",
            "Bihar",
            "Chhattisgarh",
            "Goa",
            "Gujarat",
            "Haryana",
            "Himachal Pradesh",
            "Jammu and Kashmir",
            "Jharkhand",
            "Karnataka",
            "Kerala",
            "Madhya Pradesh",
            "Maharashtra",
            "Manipur",
            "Meghalaya",
            "Mizoram",
            "Nagaland",
            "Odisha",
            "Punjab",
            "Rajasthan",
            "Sikkim",
            "Tamil Nadu",
            "Telangana",
            "Tripura",
            "Uttarakhand",
            "Uttar Pradesh",
            "West Bengal",
            "Andaman and Nicobar Islands",
            "Chandigarh",
            "Dadra and Nagar Haveli",
            "Daman and Diu",
            "Delhi",
            "Lakshadweep",
            "Puducherry"};

    private String state;
    private String language;
    private Button button;
    private Button skip;
    private LinearLayout linearLayout;
    private Button close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupcustomer4);

        email = (EditText) findViewById(R.id.email);
        dob = findViewById(R.id.dob);
        city = findViewById(R.id.city);
        languageSpinner = findViewById(R.id.language_spinner);
        stateSpinner = findViewById(R.id.state_spinner);
        button = findViewById(R.id.button);
        skip = findViewById(R.id.skip);
        picker = (DatePicker) findViewById(R.id.datePicker);
        displayDate = (Button) findViewById(R.id.button1);
        calender = findViewById(R.id.calender);
        linearLayout = findViewById(R.id.layout);
        close = findViewById(R.id.close);

        mAuth = FirebaseAuth.getInstance();


        phonenumber = getIntent().getStringExtra("phonenumber");
        name = getIntent().getStringExtra("name");

        picker.setMaxDate(System.currentTimeMillis());
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.GONE);
                picker.setVisibility(View.VISIBLE);
                displayDate.setVisibility(View.VISIBLE);
                close.setVisibility(View.VISIBLE);
                calender.setVisibility(View.GONE);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.setVisibility(View.GONE);
                displayDate.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                close.setVisibility(View.INVISIBLE);
                calender.setVisibility(View.VISIBLE);
            }
        });


        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dob.setText(getCurrentDate());
                picker.setVisibility(View.GONE);
                displayDate.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                calender.setVisibility(View.VISIBLE);
                close.setVisibility(View.GONE);

            }

        });

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                language = languagesArray[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter languageAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, languagesArray);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        languageSpinner.setAdapter(languageAdapter);

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state = stateArray[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter statesAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stateArray);

        statesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        stateSpinner.setAdapter(statesAdapter);


        button.setOnClickListener(v -> {
            mailId = email.getText().toString();
            cityName = city.getText().toString();
            birthDate = dob.getText().toString();
            if (mailId.equals("")) {
                Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_LONG).show();

            } else if (cityName.equals("")) {
                Toast.makeText(getApplicationContext(), "Please Enter City", Toast.LENGTH_LONG).show();
            } else {
                //signUp(mAuth.getCurrentUser().getUid());
                Toast.makeText(getApplicationContext(), "Signed Up", Toast.LENGTH_LONG).show();
            }
        });

        skip.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Skip", Toast.LENGTH_LONG).show();
            // signUpSkip(mAuth.getCurrentUser().getUid());
        });


    }

    public String getCurrentDate() {
        StringBuilder builder = new StringBuilder();

        builder.append(picker.getYear() + "/");
        builder.append(picker.getMonth() + 1 + "/");
        builder.append(picker.getDayOfMonth());
        return builder.toString();
    }

    private void signUp(String uuid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://naaniz.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);


        UserDetails userDetails = new UserDetails(name, phonenumber, uuid,
                dob.getText().toString(),
                language
                , state);


        Call<LoginResponse> response = service.signUpUser(userDetails);

        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                Log.d(TAG, " Code = " + response.code());
                if (response.code() == 200) {
                    Log.d(TAG, response.body().getAuthToken());
                    Toast.makeText(SignUpCustomer4.this, "go to home", Toast.LENGTH_SHORT).show();


                    Profile profile = new Profile();
                    profile.UID = uuid;
                    profile.dob = birthDate;
                    profile.email = mailId;
                    profile.foodpref = "";
                    profile.name = name;
                    profile.state = state;
                    profile.language = language;

                    AppDatabase appDatabase = AppDatabase.getDatabase(getApplicationContext());
                    AppDatabase.databaseWriteExecutor.execute(() -> {
                        appDatabase.dataDao().insertProfile(profile);
                    });


                } else if (response.code() == 404) {
                    Toast.makeText(SignUpCustomer4.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Toast.makeText(SignUpCustomer4.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void signUpSkip(String uuid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://naaniz.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);


        //***************************************************************check this once

        UserDetails userDetails = new UserDetails(name, phonenumber, "",
                uuid,
                language
                , state);


        Call<LoginResponse> response = service.signUpUser(userDetails);

        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                Log.d(TAG, " Code = " + response.code());
                if (response.code() == 200) {
                    Log.d(TAG, response.body().getAuthToken());
                    Toast.makeText(SignUpCustomer4.this, "go to home", Toast.LENGTH_SHORT).show();

                    Profile profile = new Profile();
                    profile.UID = uuid;
                    profile.dob = "";
                    profile.email = "";
                    profile.foodpref = "";
                    profile.name = name;
                    profile.state = state;
                    profile.language = language;

                    AppDatabase appDatabase = AppDatabase.getDatabase(getApplicationContext());
                    AppDatabase.databaseWriteExecutor.execute(() -> {
                        appDatabase.dataDao().insertProfile(profile);//getMyProfile
                    });
                } else if (response.code() == 404) {
                    Toast.makeText(SignUpCustomer4.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Toast.makeText(SignUpCustomer4.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }


}


//package com.naaniz.naanizcustomer.ui.setup.signup;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//public class SignUpCust4 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
//
//    private EditText email;
//    private EditText dob;
//    private EditText city;
//
//    private String mailId;
//    private String birthDate;
//    private String cityName;
//    private Spinner languageSpinner;
//    private Spinner stateSpinner;
//    String[] languagesArray = {"English", "Hindi"};
//    String[] stateArray = {"Delhi", "Tamil Naidu"};
//    private String state;
//    private String language;
//    private Button button;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signupcustomer4);
//
//        email = (EditText) findViewById(R.id.email);
//        dob = findViewById(R.id.dob);
//        city = findViewById(R.id.city);
//        languageSpinner = findViewById(R.id.language_spinner);
//        stateSpinner = findViewById(R.id.state_spinner);
//        button = findViewById(R.id.button);
//
//
//        birthDate = dob.getText().toString();
//
//        languageSpinner.setOnItemSelectedListener(this);
//
//
//        ArrayAdapter languageAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, languagesArray);
//        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        languageSpinner.setAdapter(languageAdapter);
//
//        stateSpinner.setOnItemSelectedListener(this);
//
//
//        ArrayAdapter statesAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stateArray);
//
//        statesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        stateSpinner.setAdapter(statesAdapter);
//
//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                mailId = email.getText().toString();
//                cityName = city.getText().toString();
//                if (mailId.equals("")) {
//                    Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_LONG).show();
//
//                } else if (cityName.equals(""))  {
//                    Toast.makeText(getApplicationContext(), "Please Enter City", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        state = stateArray[position];
//        language = languagesArray[position];
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
//
//
//}
