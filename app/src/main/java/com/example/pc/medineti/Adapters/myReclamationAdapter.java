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
import com.example.pc.medineti.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Taha on 21/04/2017.
 */

public class myReclamationAdapter extends RecyclerView.Adapter<myReclamationAdapter.PostsViewHolder> {
    private List<Réclamation> postList;

    private DatabaseReference myRef;
    private FirebaseDatabase database;
    Context ctx;

    public myReclamationAdapter(List<Réclamation> postList, Context context) {
        this.postList = postList;
        this.ctx = context;
    }

    @Override
    public myReclamationAdapter.PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new myReclamationAdapter.PostsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.one_item_myreclamation, parent, false));
    }

    @Override
    public void onBindViewHolder(final myReclamationAdapter.PostsViewHolder holder, int position) {
        final Réclamation post = postList.get(position);
        holder.recTitre.setText(post.getTitre());
        holder.recDescription.setText(post.getDescription());
        Picasso.with(ctx).load(post.getImage()).into(holder.recImg);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        holder.btnRecAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"Clicked",Toast.LENGTH_SHORT).show();
                myRef.child("Reclamations").child(post.getKey()).removeValue();
                myRef.child("user-Reclamations").child(post.getId()).child(post.getKey()).removeValue();
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView recTitre, recDescription,score;
        ImageView recImg;
        Button btnRecAnnuler;

        public PostsViewHolder(View itemView) {
            super(itemView);
            recTitre = (TextView) itemView.findViewById(R.id.rec_titre);
            recDescription = (TextView) itemView.findViewById(R.id.rec_desc);
            recImg = (ImageView) itemView.findViewById(R.id.img_rec);
            btnRecAnnuler =(Button)itemView.findViewById(R.id.btnRecAnnuler);
        }
    }
}
