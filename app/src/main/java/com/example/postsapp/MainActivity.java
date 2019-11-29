package com.example.postsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity {

    MyAdapter adapter;
    ArrayList<ItemData> itemsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsData = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rvPosts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(getApplicationContext(), itemsData);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecor = new DividerItemDecoration(recyclerView.getContext(), VERTICAL);
        recyclerView.addItemDecoration(itemDecor);

        JsonArrayRequest postArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "https://jsonplaceholder.typicode.com/posts",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0; i<response.length(); i++){
                            try {
                                JSONObject item = response.getJSONObject(i);
                                ItemData data = new ItemData(item.getString("title"), item.getString("body"));
                                itemsData.add(data);
                            } catch (JSONException e) { e.printStackTrace(); }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) { }
                }
        );

        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        newRequestQueue.add(postArrayRequest);

    }
}
