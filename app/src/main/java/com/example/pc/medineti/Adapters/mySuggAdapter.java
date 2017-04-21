package com.example.pc.medineti.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.medineti.Entities.Réclamation;
import com.example.pc.medineti.Entities.Suggestion;
import com.example.pc.medineti.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by PC on 21/04/2017.
 */

public class mySuggAdapter extends RecyclerView.Adapter<mySuggAdapter.PostsViewHolder> {
    private List<Suggestion> postList;

    private DatabaseReference myRef;
    private FirebaseDatabase database;
    Context ctx;

    public mySuggAdapter(List<Suggestion> postList, Context context) {
        this.postList = postList;
        this.ctx = context;
    }

    @Override
    public mySuggAdapter.PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new mySuggAdapter.PostsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.one_item_myreclamation, parent, false));
    }

    @Override
    public void onBindViewHolder(final mySuggAdapter.PostsViewHolder holder, int position) {
        final Suggestion post = postList.get(position);
        holder.recTitre.setText(post.getTitre());
        holder.recDescription.setText(post.getDescription());
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        holder.btnRecAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"Clicked",Toast.LENGTH_SHORT).show();
                myRef.child("Suggestions").child(post.getKey()).removeValue();
                myRef.child("user-Suggestions").child(post.getId()).child(post.getKey()).removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView recTitre, recDescription,score;
        Button btnRecAnnuler;

        public PostsViewHolder(View itemView) {
            super(itemView);
            recTitre = (TextView) itemView.findViewById(R.id.rec_titre);
            recDescription = (TextView) itemView.findViewById(R.id.rec_desc);
            btnRecAnnuler =(Button)itemView.findViewById(R.id.btnRecAnnuler);
        }
    }
}