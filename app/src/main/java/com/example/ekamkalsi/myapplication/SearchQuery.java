package com.example.ekamkalsi.myapplication;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.*;
import org.json.*;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Ekam Kalsi on 2/5/2017.
 */

public class SearchQuery {
    private String query;
    private String[] url = {"https://api.zappos.com/Search?term=","&key=b743e26728e16b81da139182bb2094357c31d331"};


    public SearchQuery(String query)
    {
        this.query = query;
    }


    public JSONObject func()
    {
        final JSONObject[] obj = new JSONObject[1];
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url[0] + query + url[1], new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray resultsJsonArray = response.optJSONArray("results");
                try {
                    JSONObject o = (JSONObject) resultsJsonArray.get(0);
                    obj[0] = o;
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode,  Header[] headers, Throwable throwable, JSONArray errorResponse) {

            }

        });
        return obj[0];
    }





}
