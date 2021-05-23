package com.example.veterans_code_a_thon_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

public class InformationPopUp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_pop_up);
        TextView name = (TextView) findViewById(R.id.pop_name);
        TextView address = (TextView) findViewById(R.id.pop_address);
        TextView phone = (TextView) findViewById(R.id.pop_phone);
        TextView email = (TextView) findViewById(R.id.pop_email);
        TextView web = (TextView) findViewById(R.id.pop_web);

        Intent code = getIntent();
        String getName = code.getStringExtra("userPassed");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9), (int)(height*.4));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);


        name.setText(getName);
        address.setText("Address");
        phone.setText("Phone Number");
        email.setText("Email");
        web.setText("URL");
    }
}
