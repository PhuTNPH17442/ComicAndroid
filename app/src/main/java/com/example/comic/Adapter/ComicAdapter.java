package com.example.comic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.comic.Activity.ChiTietActivity;
import com.example.comic.Interface.ItemClickListener;
import com.example.comic.Model.Comic;
import com.example.comic.R;

import java.text.DecimalFormat;
import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.MyViewHolder> {
     Context context;
     List<Comic> array;

    public ComicAdapter(Context context, List<Comic> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic,parent,false);

        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Comic comic = array.get(position);
           holder.txtNameCM.setText(comic.getName());
           holder.txtNameAt.setText(comic.getAuthor());
           DecimalFormat decimalFormat = new DecimalFormat("####,####,####");
           holder.txtYear.setText("Năm SX:"+decimalFormat.format(Double.parseDouble(comic.year)));
           Glide.with(context).load(comic.getAvatar()).into(holder.imgAvatar);
           myViewHolder.setItemClickListener(new ItemClickListener() {
               @Override
               public void onClick(View view, int pos, boolean isLongClick) {
                   if(!isLongClick){
                       Intent intent = new Intent(context, ChiTietActivity.class);
                       intent.putExtra("chitiet",comic);
                       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       context.startActivity(intent);
                   }
               }
           });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtNameCM,txtNameAt,txtYear;
        ImageView imgAvatar;
        private ItemClickListener itemClickListener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameCM = itemView.findViewById(R.id.tvNameCM);
            txtNameAt = itemView.findViewById(R.id.tvNameAt);
            txtYear = itemView.findViewById(R.id.tvYear);
            imgAvatar = itemView.findViewById(R.id.comic_img);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view , getAdapterPosition(),false);
        }
    }
}
