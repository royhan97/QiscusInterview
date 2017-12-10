package com.project.roy.qiscusinterview.Adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.project.roy.qiscusinterview.Entity.Product;
import com.project.roy.qiscusinterview.Entity.Review;
import com.project.roy.qiscusinterview.R;

import java.util.ArrayList;


/**
 * Created by ASUS on 05/11/2017.
 */

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ListProductViewHolder> {
    private Context context;
    private ArrayList<Review> reviewsArrayList;

    public ListProductAdapter(Context context, ArrayList<Review> reviewsArrayList) {
        super();
        this.context = context;
        this.reviewsArrayList = reviewsArrayList;
    }

    @Override
    public ListProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_review,parent,false);
        return new ListProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListProductAdapter.ListProductViewHolder holder, int position) {
        holder.author.setText(reviewsArrayList.get(position).getAuthors());
        holder.contentReview.setText(reviewsArrayList.get(position).getBody());
        holder.ratingBar.setRating(Float.parseFloat(reviewsArrayList.get(position).getStars()));
        LayerDrawable stars = (LayerDrawable) holder.ratingBar.getProgressDrawable();
        stars.getDrawable(2)
                .setColorFilter(context.getResources().getColor(R.color.gold),
                        PorterDuff.Mode.SRC_ATOP); // for filled stars
        stars.getDrawable(0)
                .setColorFilter(context.getResources().getColor(R.color.grey_100),
                        PorterDuff.Mode.SRC_ATOP); // for empty stars
    }

    @Override
    public int getItemCount() {
        return reviewsArrayList.size();
    }

    public class ListProductViewHolder extends RecyclerView.ViewHolder {
        TextView contentReview;
        TextView author;
        RatingBar ratingBar;

        public ListProductViewHolder(View itemView) {
            super(itemView);

            contentReview = itemView.findViewById(R.id.contentReview);
            author = itemView.findViewById(R.id.author);
            ratingBar = itemView.findViewById(R.id.ratingBar);

        }
    }
}
