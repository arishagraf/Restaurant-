package com.naaniz.naanizcustomer.ui.main.fragments.addressbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naaniz.naanizcustomer.R;
import com.naaniz.naanizcustomer.models.addressmodel.Address;

import java.util.List;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ListViewHolder>{

    private Context mCtx;
    private List<Address> AddressList;
    public AddressListAdapter(Context mCtx, List<Address> AddressList) {
        this.mCtx = mCtx;
        this.AddressList = AddressList;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.address_list_item, null);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Address address=AddressList.get(position);
        holder.address.setText(address.getAddress());
        holder.name.setText(address.getName());
        holder.title.setText(address.getTitle());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(address.getImage()));
    }
    @Override
    public int getItemCount() {
        return AddressList.size();
    }
    public static class ListViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        private TextView title;
        private  TextView name,address;
        ImageView imageView;
        View itemView;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            title=itemView.findViewById(R.id.address_textview_title);
            name=itemView.findViewById(R.id.address_textview_name);
            address=itemView.findViewById(R.id.address_textview_address);
            imageView=itemView.findViewById(R.id.AddressIcon);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
        }
    }
}
