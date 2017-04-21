package com.example.pc.medineti;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.pc.medineti.Entities.Réclamation;
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


// TODO: Controle saisie + Loading .!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// TODO: Design .!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class NewReclamation extends AppCompatActivity {
    private Button btnRec;
    private EditText txtTitre, txtDesc;
    private Spinner ville;
    private ImageButton recImg,imgMaps;
    private ImageView imgRec;
    private int PICK_IMAGE_REQUEST = 1;
    private StorageReference mStorageRef;
    private Uri file;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private Uri downloadUrl;
    public static LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reclamation);
        btnRec = (Button) findViewById(R.id.btnRec);
        txtTitre = (EditText) findViewById(R.id.recTitle);
        txtDesc = (EditText) findViewById(R.id.recDesc);
        ville = (Spinner) findViewById(R.id.spVilles);
        recImg = (ImageButton) findViewById(R.id.recImg);
        imgRec = (ImageView) findViewById(R.id.imgRec);
        imgMaps=(ImageButton)findViewById(R.id.imgMaps);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        btnRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Réclamation rec = new Réclamation();
                rec.setDate(new Date());
                rec.setDescription(txtDesc.getText().toString());
                rec.setImage(downloadUrl.toString());
                Log.d("ID",carrierName+imei);
                rec.setId(carrierName+imei);
                rec.setLatt(latLng.latitude);
                rec.setLongitude(latLng.longitude);
                rec.setTitre(txtTitre.getText().toString());
                rec.setVille(ville.getSelectedItem().toString());
                postRec(rec);
            }
        });
        imgMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewReclamation.this,Maps.class);
                startActivity(i);
            }
        });
        recImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
// Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

             file = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), file);
                // Log.d(TAG, String.valueOf(bitmap));

                imgRec.setImageBitmap(bitmap);
                imgRec.setVisibility(View.VISIBLE);
                StorageReference riversRef = mStorageRef.child("images/"+Math.random()* SystemClock.currentThreadTimeMillis());

                riversRef.putFile(file)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get a URL to the uploaded content
                                downloadUrl = taskSnapshot.getDownloadUrl();
                                Log.d("url",downloadUrl.toString());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {

                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void postRec(Réclamation rec) {
        String key = myRef.child("Reclamations").push().getKey();

        Map<String, Object> postValues = rec.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Reclamations/" + key, postValues);
        childUpdates.put("/user-Reclamations/" + rec.getId() + "/" + key, postValues);
        myRef.updateChildren(childUpdates);
        finish();
    }
}
