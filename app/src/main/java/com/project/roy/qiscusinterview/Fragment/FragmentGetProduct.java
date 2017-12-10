package com.project.roy.qiscusinterview.Fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.project.roy.qiscusinterview.Adapter.ProductsAdapter;
import com.project.roy.qiscusinterview.Entity.Product;
import com.project.roy.qiscusinterview.Helper.ProductLoader;
import com.project.roy.qiscusinterview.R;

import java.util.List;

/**
 * Created by roy on 12/6/2017.
 */

public class FragmentGetProduct extends Fragment implements View.OnClickListener, LoaderManager.LoaderCallbacks<List<Product>> {

    private ProductsAdapter productsAdapter;
    private Button btnReload;
    private TextView mEmptyStateTextView;
    private RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    View loadingIndicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_product, container, false);
        loadingIndicator =  view.findViewById(R.id.loading_indicator);
        btnReload = (Button) view.findViewById(R.id.btn_reload);
        mEmptyStateTextView = (TextView) view.findViewById(R.id.empty_view);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_event);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuffle();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        reloadData();
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnReload.setVisibility(View.GONE);
                reloadData();
            }
        });
        btnReload.setVisibility(View.GONE);
        //getLoaderManager().initLoader(1, null, this);

        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }


        return view;
    }

    public void shuffle(){
        reloadData();
    }

    public void reloadData(){
        ConnectivityManager cm =
                (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            getLoaderManager().restartLoader(1, null,this);
            getLoaderManager().initLoader(1, null, this);

        } else {

            loadingIndicator.setVisibility(View.GONE);

            btnReload.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText("Silahkan periksa koneksi internet anda");
        }

    }

    @Override
    public Loader<List<Product>> onCreateLoader(int id, Bundle args) {
        return new ProductLoader(this.getContext().getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Product>> loader, List<Product> data) {
        loadingIndicator.setVisibility(View.GONE);

        if (data != null && !data.isEmpty()) {

            productsAdapter = new ProductsAdapter(getActivity(), data);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(productsAdapter);

        }else{
            btnReload.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText("Data product not found or server error");

        }
    }

    @Override
    public void onLoaderReset(Loader<List<Product>> loader) {
        Log.d("Load Reset","1");
    }


    @Override
    public void onClick(View view) {

    }


}
