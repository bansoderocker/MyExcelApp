package com.project.myexcelapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.project.myexcelapp.Adapter.PartyAdapter;
import com.project.myexcelapp.Model.ModelClass;
import com.project.myexcelapp.Model.Party;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartyActivity extends AppCompatActivity {

    EditText edt_name, edt_phone, edt_address, edt_page;
    Button btn_insert, btn_view;
    ProgressDialog progressDialog;
    PartyAdapter partyAdapter;

    String Json_Url = "https://script.google.com/macros/s/AKfycbytc_UaoR80ikERg73Le4ulrQCr0aywaV9zDik-ziPyLrxTyhnTvEBpCsVHinZ34Lp7/exec";

    List<Party> list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);

        edt_name = findViewById(R.id.PName);
        edt_phone = findViewById(R.id.PContact);
        edt_address = findViewById(R.id.PAddress);
        edt_page = findViewById(R.id.PageNumber);
        btn_insert = findViewById(R.id.btn_insertParty);
        btn_view = findViewById(R.id.btn_search);

        recyclerView = findViewById(R.id.recyclerView_party);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(PartyActivity.this));
        recyclerView.setHasFixedSize(true);


        GetPartyDataList();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                addDataIntoExcel();
            }


        });
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SuccessActivity.class));
            }
        });
    }

    private void addDataIntoExcel() {
           String name = edt_name.getText().toString();
          String phone = edt_phone.getText().toString();
        String address = edt_address.getText().toString();
           String page = edt_page.getText().toString();

        int socketTimeOut = 50000;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Json_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //   Intent intent = new Intent(getApplicationContext(),SuccessActivity.class);
                // startActivity(intent);
                Toast.makeText(getApplicationContext(), "Added Party successfully", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
                GetPartyDataList();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<>();
                param.put("action", "addParty");
                param.put("vName", name);
                param.put("vPhone", phone);
                param.put("vAddress", address);
                param.put("vPage", page);
                return param;
            }
        };
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void GetPartyDataList() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait party list loading...");
        progressDialog.show();


        edt_name.setText("");
        edt_phone.setText("");
        edt_address.setText("");
        edt_page.setText("");
        list= new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getString(R.string.get_party), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        list.add(new Party(
                            //    srNo, String PName, String PContact, String PAddress, String pageNumber
                              //  "" + jsonObject.getInt("srNo"),
                                "" + jsonObject.getString("PName"),
                                "" + jsonObject.getString("PContact"),
                                "" + jsonObject.getString("PAddress"),
                                "" + jsonObject.getString("PageNumber")
                        ));
                        progressDialog.dismiss();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }

                    partyAdapter = new PartyAdapter(getApplicationContext(), list);
                    recyclerView.setAdapter(partyAdapter);
                    partyAdapter.notifyDataSetChanged();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), " Error " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

}