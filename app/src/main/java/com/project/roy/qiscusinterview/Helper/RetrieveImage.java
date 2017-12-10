package com.project.roy.qiscusinterview.Helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.roy.qiscusinterview.R;
import com.project.roy.qiscusinterview.Util.ImageUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by roy on 10/16/2017.
 */

public class RetrieveImage extends AsyncTask<String, Void, Bitmap> {

    private Map<ImageView, String> imageViews= Collections.synchronizedMap(new WeakHashMap<ImageView, String>());

    private ProgressDialog mDialog;
    private Context context;
    private ImageView bmImage;
    private String urlDisplay;

    public RetrieveImage(Context context, ImageView bmImage) {
        this.bmImage = bmImage;
        this.context = context;
    }

    public RetrieveImage(Context context){
        this.context = context;
        this.bmImage = null;
    }

    protected void onPreExecute(){
//        mDialog = ProgressDialog.show(context, "Please wait..", "Retrieving data..",false,true);
    }

    @Override
    protected Bitmap doInBackground(String... urls) {

        urlDisplay = urls[0];
        Bitmap liteImg = null;
        try {
            URL url = new URL(urlDisplay);
            URLConnection con = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) con;
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);
            httpConnection.connect();
            System.out.println("http : " + httpConnection.getResponseCode());
            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream stream = httpConnection.getInputStream();
                Bitmap img = BitmapFactory.decodeStream(stream);
                img = ImageUtil.cropToSquare(img);
                InputStream is = ImageUtil.convertBitmapToInputStream(img);
                liteImg = ImageUtil.makeImageLite(is,
                        img.getWidth(), img.getHeight(),
                        ImageUtil.AVATAR_WIDTH, ImageUtil.AVATAR_HEIGHT);
            }
            else{
                Bitmap default_bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.no_image);
                liteImg = default_bmp;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return liteImg;
    }

    protected void onPostExecute (Bitmap liteImg){
        if (liteImg != null ){
            if(bmImage != null){
                bmImage.setImageBitmap(liteImg);
            }

        }
        else{
            Toast.makeText(context,"Error during retrieve image", Toast.LENGTH_SHORT).show();
        }
    }

}
