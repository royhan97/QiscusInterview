package com.project.roy.qiscusinterview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.roy.qiscusinterview.Entity.Detail;
import com.project.roy.qiscusinterview.Entity.Product;
import com.project.roy.qiscusinterview.Entity.Review;
import com.project.roy.qiscusinterview.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 12/10/2017.
 */

public class ProductsAdapter extends ExpandableRecyclerViewAdapter<ProductViewHolder, DetailViewHolder> {

    private Context context;

    public ProductsAdapter(Context context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_product, parent, false);
        return new ProductViewHolder(context, view);
    }

    @Override
    public DetailViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_detail, parent, false);
        return new DetailViewHolder(context, view);
    }

    @Override
    public void onBindChildViewHolder(DetailViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Detail detail = ((Product) group).getItems().get(childIndex);
        String deskripsi = detail.getDescription();
        if (deskripsi == "null"){
            deskripsi = "No Description";
        }
        else {
            deskripsi = detail.getDescription();
        }
        System.out.println("deskripsi produk : " + deskripsi);
        holder.setContentDeatail(deskripsi, detail.getReviews());
    }

    @Override
    public void onBindGroupViewHolder(ProductViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setProduct(group);
    }


}
