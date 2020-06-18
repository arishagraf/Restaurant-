package com.naaniz.naanizcustomer.ui.main.fragments.favourites;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.common.api.Api;
import com.naaniz.naanizcustomer.R;
import com.naaniz.naanizcustomer.database.AppDatabase;
import com.naaniz.naanizcustomer.database.domainobjects.Profile;
import com.naaniz.naanizcustomer.models.favorites;
import com.naaniz.naanizcustomer.network.Service;
import com.naaniz.naanizcustomer.network.objects.LoginResponse;
import com.naaniz.naanizcustomer.ui.setup.signup.SignUpCustomer4;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SavedItemsFragment extends Fragment {

    private RecyclerView recyclerView;
    private SavedItemsAdapter adapter;
    private List<favorites> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        list = new ArrayList<>();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://naaniz.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        Call<List<favorites>> response = service.SavedItems();

        response.enqueue(new Callback<List<favorites>>() {
            @Override
            public void onResponse(Call<List<favorites>> call, Response<List<favorites>> response) {
               list = response.body() ;
               adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<favorites>> call, Throwable t) {

            }
        });


        View groupRootView = inflater.inflate(R.layout.activity_saved_items, container, false);

        adapter = new SavedItemsAdapter(getContext(), list);
        recyclerView = groupRootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        recyclerView.setAdapter(adapter);


        return groupRootView;

    }


}
