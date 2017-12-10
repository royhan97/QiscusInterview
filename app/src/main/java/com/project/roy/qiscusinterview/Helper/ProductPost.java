package com.project.roy.qiscusinterview.Helper;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.project.roy.qiscusinterview.Control.ProductController;

/**
 * Created by roy on 12/8/2017.
 */

public class ProductPost extends AsyncTask<Void, Void, Void> {

    String nameProduct, priceProduct;
    Context context;
    public static String submitStatus;

    public ProductPost(Context context, String nameProduct, String priceProduct) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ProductController.addProduct(context, nameProduct, priceProduct);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Toast.makeText(context, submitStatus, Toast.LENGTH_SHORT).show();
        super.onPostExecute(aVoid);
    }
}
