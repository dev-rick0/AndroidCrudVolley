package com.example.crudvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText gender, fullName, age, email, profession, address;
    String Gender, FullName, Age, Email, Profession, Address;
    Button button;
    Boolean valid = true;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gender = (EditText) findViewById(R.id.edtgender);
        fullName = (EditText) findViewById(R.id.edtfullName);
        age = (EditText) findViewById(R.id.edtage);
        email = (EditText) findViewById(R.id.edtemail);
        profession = (EditText) findViewById(R.id.edtprofession);
        address = (EditText) findViewById(R.id.edtaddress);
        progressDialog = new ProgressDialog(this);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gender = gender.getText().toString();
                FullName = fullName.getText().toString();
                Age = age.getText().toString();
                Email = email.getText().toString();
                Profession = profession.getText().toString();
                Address = address.getText().toString();

                if(TextUtils.isEmpty(Gender)){
                    gender.setError("Gender Cannot be Empty");
                    valid = false;
                }else {
                    valid = true;

                    if(TextUtils.isEmpty(FullName)){
                        fullName.setError("Full Name Cannot be Empty");
                        valid = false;
                    }else {
                        valid = true;

                        if(TextUtils.isEmpty(Age)){
                            age.setError("Age Cannot be Empty");
                            valid = false;
                        }else {
                            valid = true;

                            if(TextUtils.isEmpty(Email)){
                                email.setError("Email Cannot be Empty");
                                valid = false;
                            }else {
                                valid = true;

                                if (TextUtils.isEmpty(Profession)) {
                                    profession.setError("Profession Cannot be Empty");
                                    valid = false;
                                } else {
                                    valid = true;

                                    if (TextUtils.isEmpty(Address)) {
                                        address.setError("Address Cannot be Empty");
                                        valid = false;
                                    } else {
                                        valid = true;
                                    }
                                }

                            }
                    }
                }

            }
                if(valid){
                    progressDialog.setMessage("Loading");
                    progressDialog.show();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CREATE, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(MainActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                if(jsonObject.getString("message").equals("Data Added Successfully")){
                                    ListActivity.ma.refresh_list();
                                    finish();
                                }
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(MainActivity.this, "Failed to Get Data",Toast.LENGTH_SHORT).show();
                        }
                    }){
                        protected Map<String , String> getParams() throws AuthFailureError {
                            Map<String , String> params = new HashMap<>();
                            params.put("gender", Gender);
                            params.put("fullName", FullName);
                            params.put("age", Age);
                            params.put("email",Email);
                            params.put("profession", Profession);
                            params.put("address", Address);
                            return params;
                        }
                    };
                    RequestHandler.getInstance(MainActivity.this).addToRequestQueue(stringRequest);

                }
            }
        });
    }


}
