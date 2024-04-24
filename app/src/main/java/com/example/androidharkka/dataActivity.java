package com.example.androidharkka;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidharkka.utils.Item;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class dataActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public String url = "https://avoindata.prh.fi/bis/v1?totalResults=false&maxResults=10&resultsFrom=0&name=xxx&companyRegistrationFrom=2000-01-01";
    private String[] results;
    private static  final String TAG = "dataActivity";
    RequestQueue requestQueue;
    ArrayList<Item> itemList;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            requestQueue = Volley.newRequestQueue(this);
            recyclerView = findViewById(R.id.recyclerView);

            return insets;
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        String searchInput = extras.getString("searchValue");
        if (searchInput != null){
            url = url.replaceFirst("xxx", searchInput);
            Log.i(TAG, "url:" + url);


        }


    }

private void retrieveJSON(){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with the response
                        itemList = new ArrayList<Item>();
                        try {
                            JSONArray responseItems = response.getJSONArray("results");

                            for(int i=0;i<responseItems.length();i++){
                                Item item = new Item();
                                JSONObject dataobj = responseItems.getJSONObject(i);
                            }
                        } catch (Exception e){

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });

}

}
