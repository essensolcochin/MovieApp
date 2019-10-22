package com.sample.movieapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sample.movieapp.Model.MovieModel;
import com.sample.movieapp.R;

import java.util.List;

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private Context context;
//    private List<String> menu_list;

    private List<MovieModel> mData;

    public MovieAdapter(Context context,  List<MovieModel> mData) {
        this.context = context;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movielist, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText(mData.get(position).getOriginal_title());
        holder.genre.setText(mData.get(position).getOverview());
        holder.language.setText(mData.get(position).getOriginal_language());
        holder.vote.setText(Integer.toString(mData.get(position).getVote_count()));

        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+mData.get(position).getPoster_path())
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.poster);

    }




    public  void SetProducts(List<MovieModel>movies)
    {
        this.mData=movies;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        CardView view;
        TextView date,title,genre,language,vote;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            poster =itemView.findViewById(R.id.poster);
            view =itemView.findViewById(R.id.view);
            title =itemView.findViewById(R.id.name);
            genre =itemView.findViewById(R.id.genre);
            language=itemView.findViewById(R.id.lang);
            vote=itemView.findViewById(R.id.votes);

        }
    }



}
