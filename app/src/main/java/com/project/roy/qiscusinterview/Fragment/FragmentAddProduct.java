package com.project.roy.qiscusinterview.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.roy.qiscusinterview.Control.ProductController;
import com.project.roy.qiscusinterview.Helper.ProductPost;
import com.project.roy.qiscusinterview.R;

/**
 * Created by roy on 12/6/2017.
 */

public class FragmentAddProduct extends Fragment implements View.OnClickListener {

    private EditText name, price;
    private Button submit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        name = view.findViewById(R.id.name_product);
        price = view.findViewById(R.id.price_product);
        submit = view.findViewById(R.id.btn_submit);

        submit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        String nameProduct = name.getText().toString().trim();
        String priceProduct = price.getText().toString().trim();
        if (nameProduct.length() != 0 && priceProduct.length() != 0 ){
            new ProductPost(getContext().getApplicationContext(), nameProduct, priceProduct).execute();
        }
        else {
            Toast.makeText(getContext().getApplicationContext(), "Name product and / or price is required", Toast.LENGTH_SHORT).show();
        }

    }
}
