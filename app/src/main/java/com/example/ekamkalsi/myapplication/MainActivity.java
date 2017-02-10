package com.example.ekamkalsi.myapplication;

import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ekamkalsi.myapplication.databinding.ActivityMainBinding;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        final EditText t = (EditText) findViewById(R.id.search);
        t.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE )
                {
                    String query = t.getText().toString();
                    search(query,binding);
                }
                return false;
            }
        });

        final FloatingActionButton add = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Item was added to cart",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void search(String query, final ActivityMainBinding binding)
    {

        String[] url = {"https://api.zappos.com/Search?term=","&key=b743e26728e16b81da139182bb2094357c31d331"};
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url[0] + query + url[1], new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray resultsJsonArray = response.optJSONArray("results");
                try {
                    JSONObject o = (JSONObject) resultsJsonArray.get(0);
                    if(o==null)
                    {
                        Toast.makeText(MainActivity.this,"Sorry, no results were found",Toast.LENGTH_SHORT).show();
                    }
                    Product product = new Product(o);
                    product.setDetails();
                    binding.setProduct(product);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode,  Header[] headers, Throwable throwable, JSONArray errorResponse) {

            }

        });
    }


}
