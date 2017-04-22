package com.example.pc.medineti.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.medineti.Entities.Suggestion;
import com.example.pc.medineti.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by PC on 22/04/2017.
 */

public class SuggAdapter extends RecyclerView.Adapter<SuggAdapter.PostsViewHolder> {
    private List<Suggestion> postList;

    private DatabaseReference myRef;
    private FirebaseDatabase database;
    Context ctx;

    public SuggAdapter(List<Suggestion> postList, Context context) {
        this.postList = postList;
        this.ctx = context;
    }

    @Override
    public SuggAdapter.PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SuggAdapter.PostsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.one_item_mysugg, parent, false));
    }

    @Override
    public void onBindViewHolder(final SuggAdapter.PostsViewHolder holder, int position) {
        final Suggestion post = postList.get(position);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        holder.recTitre.setText(post.getTitre());
        holder.recDescription.setText(post.getDescription());
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        holder.btnRecAnnuler.setVisibility(View.GONE);
        holder.lly.setVisibility(View.VISIBLE);
        holder.score.setText(""+post.getCount());
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"Clicked",Toast.LENGTH_SHORT).show();
                post.setCount(post.getCount()+1);
                myRef.child("Suggestions").child(post.getKey()).setValue(post);
                myRef.child("user-Suggestions").child(post.getId()).child(post.getKey()).setValue(post);
                holder.plus.setEnabled(false);
                holder.minus.setEnabled(true);
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"Clicked",Toast.LENGTH_SHORT).show();
                post.setCount(post.getCount()-1);
                myRef.child("Suggestions").child(post.getKey()).setValue(post);
                myRef.child("user-Suggestions").child(post.getId()).child(post.getKey()).setValue(post);
                holder.minus.setEnabled(false);
                holder.plus.setEnabled(true);


            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView recTitre, recDescription, score;
        Button btnRecAnnuler;
        Button plus, minus;
        LinearLayout lly;

        public PostsViewHolder(View itemView) {
            super(itemView);
            recTitre = (TextView) itemView.findViewById(R.id.rec_titre);
            recDescription = (TextView) itemView.findViewById(R.id.rec_desc);
            btnRecAnnuler = (Button) itemView.findViewById(R.id.btnRecAnnuler);
            plus = (Button) itemView.findViewById(R.id.btnPlus);
            minus = (Button) itemView.findViewById(R.id.btnMoin);
            score = (TextView) itemView.findViewById(R.id.txtScore);
            lly = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }

}
