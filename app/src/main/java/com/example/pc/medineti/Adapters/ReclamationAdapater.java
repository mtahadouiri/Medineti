package com.example.pc.medineti.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.medineti.Entities.Réclamation;
import com.example.pc.medineti.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by PC on 21/04/2017.
 */

public class ReclamationAdapater  extends RecyclerView.Adapter<ReclamationAdapater.PostsViewHolder> {
    private List<Réclamation> postList;

    private DatabaseReference myRef;
    private FirebaseDatabase database;
    Context ctx;

    public ReclamationAdapater(List<Réclamation> postList, Context context) {
        this.postList = postList;
        this.ctx = context;
    }

    @Override
    public ReclamationAdapater.PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReclamationAdapater.PostsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.one_item_myreclamation, parent, false));
    }

    @Override
    public void onBindViewHolder(final ReclamationAdapater.PostsViewHolder holder, int position) {
        final Réclamation post = postList.get(position);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        holder.recTitre.setText(post.getTitre());
        holder.recDescription.setText(post.getDescription());
        Picasso.with(ctx).load(post.getImage()).into(holder.recImg);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        holder.btnRecAnnuler.setVisibility(View.GONE);
        holder.lly.setVisibility(View.VISIBLE);
        holder.score.setText(""+post.getScore());
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"Clicked",Toast.LENGTH_SHORT).show();
                post.setScore(post.getScore()+1);
                myRef.child("Reclamations").child(post.getKey()).setValue(post);
                myRef.child("user-Reclamations").child(post.getId()).child(post.getKey()).setValue(post);
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"Clicked",Toast.LENGTH_SHORT).show();
                post.setScore(post.getScore()-1);
                myRef.child("Reclamations").child(post.getKey()).setValue(post);
                myRef.child("user-Reclamations").child(post.getId()).child(post.getKey()).setValue(post);
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
        Button plus,minus;
        LinearLayout lly;

        public PostsViewHolder(View itemView) {
            super(itemView);
            recTitre = (TextView) itemView.findViewById(R.id.rec_titre);
            recDescription = (TextView) itemView.findViewById(R.id.rec_desc);
            recImg = (ImageView) itemView.findViewById(R.id.img_rec);
            btnRecAnnuler =(Button)itemView.findViewById(R.id.btnRecAnnuler);
            plus=(Button)itemView.findViewById(R.id.btnPlus);
            minus=(Button)itemView.findViewById(R.id.btnMoin);
            score = (TextView)itemView.findViewById(R.id.txtScore);
            lly=(LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}
