package com.jameriah.pocketnutritionist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class RecipeList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://api.yummly.com/v1/api/recipes?_app_id=b4153eca&_app_key=421616f420ee8242b616b635c0873df4";
        url += getIntent().getStringExtra("keywords");
        url += getIntent().getStringExtra("options");


        Log.i("-------",url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));

                        JSONObject object = null;
                        try {

                            ArrayList<Recipe> recipes = new ArrayList<>();

                            object = (JSONObject) new JSONTokener(response).nextValue();
                            JSONArray array = object.getJSONArray("matches");

                            for(int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);


                                recipes.add(new Recipe(
                                        obj.getString("smallImageUrls").replace("\\","")
                                        ,obj.getString("recipeName")
                                        ,obj.getString("sourceDisplayName")
                                ));
                            }

                            Log.i("-------",response);



                            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
                            RecipeAdapter recipeAdapter = new RecipeAdapter(recipes, getApplicationContext());

                            recyclerView.setAdapter(recipeAdapter);

                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);



    }
}
