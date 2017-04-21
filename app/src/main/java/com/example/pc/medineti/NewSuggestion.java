package com.example.pc.medineti;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pc.medineti.Entities.Réclamation;
import com.example.pc.medineti.Entities.Suggestion;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.pc.medineti.MainActivity.carrierName;
import static com.example.pc.medineti.MainActivity.imei;

public class NewSuggestion extends AppCompatActivity {
    private Button btnRec;
    private EditText txtTitre, txtDesc;
    private Spinner ville;
    private Uri file;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private Uri downloadUrl;
    public static LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_suggestion);
        btnRec = (Button) findViewById(R.id.btnRec);
        txtTitre = (EditText) findViewById(R.id.recTitle);
        txtDesc = (EditText) findViewById(R.id.recDesc);
        ville = (Spinner) findViewById(R.id.spVilles);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        btnRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtTitre.getText().toString().trim().length() > 0) {
                    if (txtDesc.getText().toString().trim().length() > 0) {
                            Suggestion rec = new Suggestion();
                            rec.setDate(new Date());
                            rec.setDescription(txtDesc.getText().toString());
                            Log.d("ID", carrierName + imei);
                            rec.setId(carrierName + imei);
                            rec.setTitre(txtTitre.getText().toString());
                            rec.setVille(ville.getSelectedItem().toString());
                            rec.setCount(0);
                            postRec(rec);
                    } else {
                        Toast.makeText(NewSuggestion.this, "Veuillez saisir la description", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(NewSuggestion.this, "Veuillez saisir le titre", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void postRec(Suggestion rec) {
        String key = myRef.child("Suggestions").push().getKey();
        rec.setKey(key);
        Map<String, Object> postValues = rec.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Suggestions/" + key, postValues);
        childUpdates.put("/user-Suggestions/" + rec.getId() + "/" + key, postValues);
        myRef.updateChildren(childUpdates);
        finish();
    }
}
