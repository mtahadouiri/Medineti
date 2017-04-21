package com.example.pc.medineti;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button btnRec,btnSugg;
    public static String carrierName,imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRec = (Button)findViewById(R.id.btnRec);
        btnSugg = (Button)findViewById(R.id.btnSugg);
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

         carrierName = manager.getNetworkOperatorName();
         imei= manager.getSimSerialNumber();
        btnRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,RecActivity.class);
                startActivity(i);
            }
        });
        btnSugg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SuggActivity.class);
                startActivity(i);
            }
        });


    }

}
