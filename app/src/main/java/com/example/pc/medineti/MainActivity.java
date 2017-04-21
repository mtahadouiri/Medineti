package com.example.pc.medineti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button btnRec,btnSugg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRec = (Button)findViewById(R.id.btnRec);
        btnSugg = (Button)findViewById(R.id.btnSugg);
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
