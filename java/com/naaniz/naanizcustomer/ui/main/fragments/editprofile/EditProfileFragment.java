package com.naaniz.naanizcustomer.ui.main.fragments.editprofile;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.naaniz.naanizcustomer.R;
import com.naaniz.naanizcustomer.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button backButton, doneButton;
    ImageButton calenderButton;
    ImageView profilePic;
    EditText nameEdit, gmailEdit, dobEdit, phoneEdit;
    Calendar c;
    Spinner languageSpinner;

    DatePickerDialog dpd;
    String[] languagesArray = {"English", "Hindi" ,"Bengali","Telugu"};
    private String language;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        backButton = v.findViewById(R.id.backButton);

        doneButton = v.findViewById(R.id.done);
        profilePic = v.findViewById(R.id.profile_pic);
        nameEdit = v.findViewById(R.id.name_edit);
        gmailEdit = v.findViewById(R.id.gmail_edit);
        dobEdit = v.findViewById(R.id.dob_edit);
        languageSpinner=v.findViewById(R.id.spinner);
        calenderButton=v.findViewById(R.id.calendar_button);
        phoneEdit = v.findViewById(R.id.phone_edit);





        ArrayAdapter languageAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, languagesArray);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        languageSpinner.setAdapter(languageAdapter);
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                language = languagesArray[position];


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        calenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker , int year, int month, int dayOfMonth) {
                        dobEdit.setText(year + "/" + (month+1) + "/" + dayOfMonth);
                    }
                }, year, month, day);
                dpd.show();
            }
        });


        return v;
    }


}


//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        String date=month + "/" + dayOfMonth + "/" +  year;
//        dobEdit.setText(date);
//    }
//}


/*
*/

/*private void showDatePickerDailog() {
        DatePicker datePickerDailog = new DatePicker(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDailog.show();
    }

*/








