package com.naaniz.naanizcustomer.ui.main.fragments.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.naaniz.naanizcustomer.R;

import java.util.List;

public class ProfileItemListAdapter extends ArrayAdapter<ProfileItem> {

    Context context;

    public ProfileItemListAdapter(@NonNull Context context, int resource, @NonNull List<ProfileItem> objects) {
        super(context, resource, objects);

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = null;
        if(position==0){
            itemView = LayoutInflater.from(context).inflate(R.layout.profile_fragment_header, parent, false);

            TextView name_tv = itemView.findViewById(R.id.name_textview);
            TextView ph_no_tv = itemView.findViewById(R.id.phone_number_textview);

            ProfileHeader hd  = (ProfileHeader) getItem(position);
            name_tv.setText(hd.getName());
            ph_no_tv.setText(hd.getPh_no());

            itemView.setOnClickListener(v -> {
                Navigation.findNavController(v).navigate(hd.navigateAction);
            });
        }
        else{
            itemView = LayoutInflater.from(context).inflate(R.layout.profile_fragment_list_item, parent, false);

            TextView label = itemView.findViewById(R.id.option_label);
            ProfileOption op  = (ProfileOption) getItem(position);
            label.setText(op.getOption_label());

            itemView.setOnClickListener( v -> {
                Navigation.findNavController(v).navigate(op.navigateAction);
            });
        }

        return itemView;
    }

}
