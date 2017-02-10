package com.example.ekamkalsi.myapplication;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ekam Kalsi on 2/5/2017.
 */

public class Product {

    public String brandName;
    public String thumbnailImageUrl;
    public String productId;
    public String originalPrice;
    public String styleId;
    public String colorId;
    public String price;
    public String percentOff;
    public String productUrl;
    public String productName;
    JSONObject obj;
    public Product(JSONObject obj)
    {
        this.obj = obj;
    }

    public void setDetails()
    {
        try {
            this.brandName = obj.getString("brandName");
            this.thumbnailImageUrl = obj.getString("thumbnailImageUrl");
            this.productId = obj.getString("productId");
            this.originalPrice = obj.getString("originalPrice");
            this.styleId = obj.getString("styleId");
            this.colorId = obj.getString("colorId");
            this.price = obj.getString("price");
            this.percentOff = obj.getString("percentOff");
            this.productUrl = obj.getString("productUrl");
            this.productName = obj.getString("productName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }


}
