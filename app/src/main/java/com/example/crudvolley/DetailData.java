package com.example.crudvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailData extends AppCompatActivity {

    TextView gender, fullName, age, email, profession, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        gender = (TextView) findViewById(R.id.tvgender);
        fullName = (TextView) findViewById(R.id.tvfullName);
        age = (TextView) findViewById(R.id.tvage);
        email = (TextView) findViewById(R.id.tvemail);
        profession = (TextView) findViewById(R.id.tvprofession);
        address = (TextView) findViewById(R.id.tvaddress);

        gender.setText(getIntent().getStringExtra("gender"));
        fullName.setText(getIntent().getStringExtra("fullname"));
        age.setText(getIntent().getStringExtra("age"));
        email.setText(getIntent().getStringExtra("email"));
        profession.setText(getIntent().getStringExtra("profession"));
        address.setText(getIntent().getStringExtra("address"));

    }
}