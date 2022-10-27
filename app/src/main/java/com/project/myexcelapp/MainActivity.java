package com.project.myexcelapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edt_name, edt_phone, edt_address;
    Button btn_insert,btn_view;
    ProgressDialog progressDialog;
    String Json_Url = "https://script.google.com/macros/s/AKfycby9gZ-rirpDydOt0OdMW-Jw7rnlxDCJMvJTu5vYnYr7epiEN9eHcq1oHfE-MeYTCARb/exec";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_name = findViewById(R.id.name);
        edt_phone = findViewById(R.id.phone);
        edt_address = findViewById(R.id.address);
        btn_insert = findViewById(R.id.btn_insert);
        btn_view = findViewById(R.id.btn_getdata);

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
                startActivity(new Intent(getApplicationContext(),SuccessActivity.class));
            }
        });
    }

    private void addDataIntoExcel() {
        String name = edt_name.getText().toString();
        String phone = edt_phone.getText().toString();
        String address = edt_address.getText().toString();

            int socketTimeOut = 50000;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Json_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(getApplicationContext(),SuccessActivity.class);
                startActivity(intent);
                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String>  param = new HashMap<>();
                param.put("action","addData");
                param.put("vName",name);
                param.put("vPhone",phone);
                param.put("vAddress", address);
                return param;
            }
        };
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}