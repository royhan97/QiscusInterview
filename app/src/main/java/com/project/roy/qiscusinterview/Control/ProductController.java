package com.project.roy.qiscusinterview.Control;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.project.roy.qiscusinterview.Adapter.ListProductAdapter;
import com.project.roy.qiscusinterview.Entity.Detail;
import com.project.roy.qiscusinterview.Entity.Image;
import com.project.roy.qiscusinterview.Entity.Product;
import com.project.roy.qiscusinterview.Entity.Review;
import com.project.roy.qiscusinterview.Helper.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

//import static com.project.roy.qiscusinterview.Fragment.FragmentAddProduct.submitStatus;
import static com.project.roy.qiscusinterview.Helper.ProductPost.submitStatus;

/**
 * Created by roy on 12/6/2017.
 */

public class ProductController {

    public static List<Product> getAllProduct (Context context){
        final SyncHttpClient client = new SyncHttpClient();

        final List<Product> products = new ArrayList<>();

        client.get(Config.URL_GET_ALL_PRODUCT, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONArray productResults = new JSONArray(result);
                    for (int i=0; i < productResults.length(); i++){
                        final ArrayList<Image> images  = new ArrayList<>();
                        final ArrayList<Review> reviews = new ArrayList<>();
                        final List<Detail> details = new ArrayList<>();
                        JSONObject productObj = productResults.getJSONObject(i);
//                        Integer id = Integer.parseInt(productObj.get("id").toString());
                        String name = productObj.get("name").toString();
                        String price = productObj.get("price").toString();
                        String description = productObj.get("description").toString();
                        JSONArray imageArray = productObj.getJSONArray("images");
                        if (imageArray.length() > 0){
                            for (int j=0; j < imageArray.length(); j++){
                                JSONObject imageObj = imageArray.getJSONObject(j);
                                String fullImage = imageObj.get("full").toString();
                                String thumbImage = imageObj.get("thumb").toString();
                                Image image = new Image(fullImage,thumbImage);
                                images.add(image);
                            }
                        }

                        JSONArray reviewArray = productObj.getJSONArray("reviews");
                        if(reviewArray.length() > 0) {
                            for (int k=0; k < reviewArray.length(); k++){
                                JSONObject reviewObj = reviewArray.getJSONObject(k);
                                String stars = reviewObj.get("stars").toString();
                                String body = reviewObj.get("body").toString();
                                String author = reviewObj.get("author").toString();
                                Review review = new Review(stars, body, author);
                                reviews.add(review);
                            }
                        }

                        Detail detail = new Detail(description, reviews);
                        details.add(detail);
                        Product product = new Product(name, details ,price, images);
                        products.add(product);
                    }

                }
                catch (JSONException e){

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        return products;
    }

    public static void addProduct(final Context context, String name, String price){
        final SyncHttpClient client = new SyncHttpClient();

        RequestParams params = new RequestParams();
        params.put("product[name]", name);
        params.put("product[price]", price);

        client.post(context,Config.URL_GET_ALL_PRODUCT, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {

                super.onStart();
                setUseSynchronousMode(true);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject eventResult = new JSONObject(result);

                    String id = eventResult.getString("id");
                    // Check for error node in json
                    if (id != null || id != "" ) {
                        submitStatus = "New Product Added";
                    } else {
                        String errorMsg = eventResult.getString("error_msg");
                        Toast.makeText(context,
                                errorMsg, Toast.LENGTH_LONG).show();
                        submitStatus = "Error When Adding Product";
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println(error.getMessage());
            }
        });
    }

}
