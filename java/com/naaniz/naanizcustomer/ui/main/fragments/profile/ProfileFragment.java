package com.naaniz.naanizcustomer.ui.main.fragments.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.naaniz.naanizcustomer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        List<ProfileItem> profileItems = new ArrayList<>();
        profileItems.add(new ProfileHeader("Abhishek Jagushte", "9029256442"));
        profileItems.add(new ProfileOption("Notifications", 122));
        profileItems.add(new ProfileOption("Messages/Contact Us", 122));
        profileItems.add(new ProfileOption("Payments", 122));
        profileItems.add(new ProfileOption("Address Book", R.id.action_profileFragment2_to_address_fragment));
        profileItems.add(new ProfileOption("Settings", 122));
        profileItems.add(new ProfileOption("Edit Profile", R.id.action_profileFragment2_to_editProfile));
        profileItems.add(new ProfileOption("Help", 122));
        profileItems.add(new ProfileOption("Refer and Earn", 122));
        profileItems.add(new ProfileOption("About", 122));
        profileItems.add(new ProfileOption("Sign Out", 122));


        ProfileItemListAdapter adapter = new ProfileItemListAdapter(getContext(), 0, profileItems);
        ListView listView = view.findViewById(R.id.profile_frag_list);
        listView.setAdapter(adapter);

        return view;
    }
}
