package com.example.newstak;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Model> modelArrayList;

    public Adapter(Context context, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.layout_item,null,false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, webView.class);
                intent.putExtra("url",modelArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });
        holder.mtime.setText("Published At :- "+modelArrayList.get(position).getPublishedAt());
        holder.mauthor.setText(modelArrayList.get(position).getAuthor());
        holder.mheading.setText(modelArrayList.get(position).getTitle());
        holder.mcontent.setText(modelArrayList.get(position).getDescription());

        Glide.with(context).load(modelArrayList.get(position).getUrlToImage()).into(holder.imageView);

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "This field is empty", Toast.LENGTH_SHORT).show();
            }
        });
        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "This field is empty", Toast.LENGTH_SHORT).show();
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,"News Tak \n\n\n"+modelArrayList.get(position).getTitle()+"\n\n"+modelArrayList.get(position).getDescription()
                        +modelArrayList.get(position).getUrlToImage()+"\n\n Auther :- "+modelArrayList.get(position).getAuthor()
                        +"\n\n Published At :- "+modelArrayList.get(position).getPublishedAt());
                view.getContext().startActivity(Intent.createChooser(intent , "share this news via"));

            }
        });


    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mheading,mcontent,mauthor,mtime;
        CardView cardView;
        ImageView imageView;
        ImageView like,share,save;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mheading = itemView.findViewById(R.id.mainheading);
            mauthor = itemView.findViewById(R.id.author);
            mcontent = itemView.findViewById(R.id.content);
            mtime = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.imageview);
            cardView = itemView.findViewById(R.id.cardview);

            like = itemView.findViewById(R.id.likeimg);
            share = itemView.findViewById(R.id.shareimg);
            save = itemView.findViewById(R.id.saveimg);



        }
    }
}
