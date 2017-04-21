package com.example.pc.medineti.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.medineti.Entities.Réclamation;
import com.example.pc.medineti.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Taha on 21/04/2017.
 */

public class ReclamationAdapter extends RecyclerView.Adapter<ReclamationAdapter.PostsViewHolder> {
    private List<Réclamation> postList;
    Context ctx;

    public ReclamationAdapter(List<Réclamation> postList, Context context) {
        this.postList = postList;
        this.ctx = context;
    }

    @Override
    public ReclamationAdapter.PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReclamationAdapter.PostsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.one_item_reclamation, parent, false));
    }

    @Override
    public void onBindViewHolder(final ReclamationAdapter.PostsViewHolder holder, int position) {
        final Réclamation post = postList.get(position);
        holder.recTitre.setText(post.getTitre());
        holder.recDescription.setText(post.getDescription());
        Picasso.with(ctx).load(post.getImage()).into(holder.recImg);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView recTitre, recDescription;
        ImageView recImg;
        protected ImageView playButton;

        public PostsViewHolder(View itemView) {
            super(itemView);
            recTitre = (TextView) itemView.findViewById(R.id.rec_titre);
            recDescription = (TextView) itemView.findViewById(R.id.rec_desc);
            recImg = (ImageView) itemView.findViewById(R.id.img_rec);
        }
    }
}
