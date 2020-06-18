package com.naaniz.naanizcustomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LoyaltyPoints extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private TextView goToLoyaltyProgram;

    //Cardview
    List<ImagesLoyalty> lstBook ;

    private OnFragmentInteractionListener mListener;

    public LoyaltyPoints() {

    }


    public static LoyaltyPoints newInstance(String param1, String param2) {
        LoyaltyPoints fragment = new LoyaltyPoints();
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
        View view=  inflater.inflate(R.layout.activity_loyalty_points, container, false);
        goToLoyaltyProgram = view.findViewById(R.id.loyalty_program_btn);
        goToLoyaltyProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoyaltyPoints.this.getActivity(), LoyaltyProgram.class);
                startActivity(intent);
            }
        });

        lstBook = new ArrayList<>();
        lstBook.add(new ImagesLoyalty(R.drawable.naanizwon));
        lstBook.add(new ImagesLoyalty(R.drawable.naanizwon));
        lstBook.add(new ImagesLoyalty(R.drawable.naanizwon));
        lstBook.add(new ImagesLoyalty(R.drawable.naanizwon));

        RecyclerView myrv = (RecyclerView) view.findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this.getActivity(),lstBook);
        myrv.setLayoutManager(new GridLayoutManager(this.getActivity(),2));
        myrv.setAdapter(myAdapter);
        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
