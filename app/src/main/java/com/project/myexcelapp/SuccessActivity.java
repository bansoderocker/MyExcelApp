package com.project.myexcelapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.project.myexcelapp.Model.ModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SuccessActivity extends AppCompatActivity {


    List<ModelClass> list;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    // Post data String Json_Url = "https://script.google.com/macros/s/AKfycby9gZ-rirpDydOt0OdMW-Jw7rnlxDCJMvJTu5vYnYr7epiEN9eHcq1oHfE-MeYTCARb/exec";
    String Json_Url = "https://script.google.com/macros/s/AKfycbzcYN8gt_y_uN-gZeYzCOqr6NQRREqopbb3nwvcP2F-ug369IJOgS0KX56JcgHMBqht/exec";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(SuccessActivity.this));
        recyclerView.setHasFixedSize(true);


        GetData();
    }

    public void GetData() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Json_Url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        list.add(new ModelClass(
                           ""+jsonObject.getString("Name"),
                           ""+jsonObject.getString("Address"),
                           ""+jsonObject.getString("Phone")
                        ));
                        progressDialog.dismiss();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }

                    customAdapter = new CustomAdapter(SuccessActivity.this,list);
                    recyclerView.setAdapter(customAdapter);
                    customAdapter.notifyDataSetChanged();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(SuccessActivity.this, " Error " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    private void putDataIntoRecyclerView(List<ModelClass> list) {
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(SuccessActivity.this));
        recyclerView.setAdapter(customAdapter);

    }
}
