package com.example.pc.medineti.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.pc.medineti.R;

public class MainActivity extends AppCompatActivity {
    private Button btnRec,btnSugg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRec = (Button)findViewById(R.id.btnRec);
        btnSugg = (Button)findViewById(R.id.btnSugg);



    }
}
