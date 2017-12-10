package com.project.roy.qiscusinterview.Helper;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.project.roy.qiscusinterview.Control.ProductController;
import com.project.roy.qiscusinterview.Entity.Product;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ASUS on 10/10/2017.
 */

public class ProductLoader extends AsyncTaskLoader<List<Product>> {
    private List<Product> mData;
    public boolean hasResult = false;

    public ProductLoader(final Context context) {
        super(context);
        onContentChanged();
    }



    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (hasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(final List<Product> data) {
        mData = data;
        hasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (hasResult) {
            onReleaseResources(mData);
            mData = null;
            hasResult = false;
        }
    }

    @Override
    public List<Product> loadInBackground() {
        List<Product> products = new ArrayList<>();
        products = ProductController.getAllProduct(getContext());
        System.out.println("product size : " + products.size());

        return products;
    }

    protected void onReleaseResources(List<Product> data) {
        //nothing to do.
    }

    public List<Product> getResult() {
        return mData;
    }

}
