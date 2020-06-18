package com.naaniz.naanizcustomer.ui.main.fragments.addressbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naaniz.naanizcustomer.R;
import com.naaniz.naanizcustomer.models.addressmodel.Address;

import java.util.ArrayList;
import java.util.List;

public class address_fragment extends Fragment {
    List<Address> addressList;
    RecyclerView recyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addressList = new ArrayList<>();
        View rootView = inflater.inflate(R.layout.fragment_address, container, false);
         recyclerView= rootView.findViewById(R.id.addressRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addressList.add(new Address("HOME","Uday","ABC apartment fifth floor,Vuda layout" +
                "visakhapatnam,andhrapradesh", R.drawable.ic_home_black_24dp));
        addressList.add(new Address("Office","Dinesh","ABC apartment fifth floor,Vuda layout" +
                "visakhapatnam,andhrapradesh", R.drawable.ic_cancel_black_24dp));
        addressList.add(new Address("HOME","Ramachari","ABC apartment fifth floor,Vuda layout" +
                "visakhapatnam,andhrapradesh", R.drawable.ic_home_black_24dp));
        addressList.add(new Address("HOME","Sirisha","ABC apartment fifth floor,Vuda layout" +
                "visakhapatnam,andhrapradesh", R.drawable.ic_cancel_black_24dp));
        AddressListAdapter addressListAdapter=new AddressListAdapter(getActivity(),addressList);
        recyclerView.setAdapter(addressListAdapter);

        return rootView;
    }
}
