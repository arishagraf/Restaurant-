package com.naaniz.naanizcustomer.ui.main.fragments.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.naaniz.naanizcustomer.R;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    List<String> items;

    TestAdapter(List<String> items){
        this.items = items;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item, parent, false);
        NavController navController = Navigation.findNavController(parent);

        return new TestViewHolder(view, navController);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        holder.textView.setText("Position "+ position);
        holder.itemView.setOnClickListener(v -> {
            holder.navController.navigate(R.id.action_collectionDemoFragment_to_menuFragment);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        NavController navController;
        View itemView;

        public TestViewHolder(@NonNull View itemView, NavController navController) {
            super(itemView);
            this.itemView = itemView;
            textView = itemView.findViewById(R.id.test_tv);
            this.navController = navController;
        }
    }

}


