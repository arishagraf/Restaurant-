package com.naaniz.naanizcustomer.ui.main.fragments.favourites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naaniz.naanizcustomer.R;
import com.naaniz.naanizcustomer.models.favorites;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SavedItemsAdapter extends RecyclerView.Adapter<SavedItemsAdapter.ViewHolder> {
   private Context mContext ;
   private List<favorites> list ;

    public SavedItemsAdapter(Context mContext, List<favorites> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.favorites , parent  , false) ;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.dishName.setText(list.get(position).getDish()) ;
        holder.price.setText(list.get(position).getPrice() + " RS") ;
        Picasso.get().load(list.get(position).getImage_url()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder  extends  RecyclerView.ViewHolder {

       private TextView dishName ;
       private TextView price ;
       private ImageView imageView ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dishName = itemView.findViewById(R.id.kadai_panee) ;
            price =  itemView.findViewById(R.id.price) ;
            imageView = itemView.findViewById(R.id.image) ;
        }
    }
}
