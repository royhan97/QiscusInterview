package com.project.roy.qiscusinterview.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.project.roy.qiscusinterview.Entity.Product;
import com.project.roy.qiscusinterview.Helper.RetrieveImage;
import com.project.roy.qiscusinterview.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by roy on 12/10/2017.
 */

public class ProductViewHolder extends GroupViewHolder{

    private ImageView expandButton, icon;
    private Context context;
    private TextView namaProduct, harga;

    public ProductViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        namaProduct = (TextView) itemView.findViewById(R.id.name);
        icon = (ImageView) itemView.findViewById(R.id.iconImage);
        harga = (TextView) itemView.findViewById(R.id.price);
        expandButton = (ImageView) itemView.findViewById(R.id.expand_button);
    }

    public void setProduct (ExpandableGroup product){
        if (product instanceof Product){
            namaProduct.setText(((Product) product).getTitle());
            harga.setText(((Product) product).getPrice());
            int sizeImage = ((Product) product).getImages().size();
            if (sizeImage > 0){
                String urlImage = ((Product) product).getImages().get(0).getThummb();
                System.out.println("url image : " + urlImage);
                new RetrieveImage(context, icon).execute(urlImage);
            }
            else {
                Drawable drawable = context.getResources().getDrawable(R.drawable.no_image);
                icon.setImageDrawable(drawable);
            }
        }

    }

    @Override
    public void expand() {
       animateExpand();
    }

    @Override
    public void collapse() {
       animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        expandButton.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        expandButton.setAnimation(rotate);
    }
}
