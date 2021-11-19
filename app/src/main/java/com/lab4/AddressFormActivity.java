package com.lab4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddressFormActivity extends FormActivity {
    
    private EditText inputCountry;
    private EditText inputCity;
    private EditText inputAddress;

    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_form);

        Intent intent = getIntent();
        address = intent.getStringExtra(MainActivity.ADDRESS);
        String[] separatedAddress = address.split(", ", 3);

        System.out.println(address);

        inputCountry = findViewById(R.id.input_country);
        inputCity = findViewById(R.id.input_city);
        inputAddress = findViewById(R.id.input_address);

        inputCountry.setText(separatedAddress[0]);
        if (separatedAddress.length > 1)
            inputCity.setText(separatedAddress[1]);
        if (separatedAddress.length > 2)
            inputAddress.setText(separatedAddress[2]);
    }

    public void save(View view) {
        Intent returnIntent = new Intent();
        address = inputCountry.getText().toString() + ", "
                + inputCity.getText().toString() + ", "
                + inputAddress.getText().toString();
        returnIntent.putExtra(MainActivity.ADDRESS, address);
        this.setResult(Activity.RESULT_OK, returnIntent);
        this.finish();
    }

    public void cancel(View view) {
        this.setResult(Activity.RESULT_CANCELED);
        this.finish();
    }
}