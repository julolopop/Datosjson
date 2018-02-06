package com.example.usuario.datosjson.pojo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.datosjson.R;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * Created by usuario on 6/02/18.
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {
    private ArrayList<Git> git;

    public ReposAdapter() {
        this.git = new ArrayList<>();
    }

    public void setGit(ArrayList<Git> git) {
        this.git = git;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View contactView = inflater.inflate(R.layout.item_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Git git = this.git.get(position);


        holder.txv_name.setText(git.getName());
        holder.txv_description.setText(git.getDescription().toString());
        holder.txv_createAT.setText(git.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return this.git.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txv_name;
        TextView txv_description;
        TextView txv_createAT;


        public ViewHolder(View itemView) {
            super(itemView);


            txv_name = itemView.findViewById(R.id.textView2);
            txv_description = itemView.findViewById(R.id.textView3);
            txv_createAT = itemView.findViewById(R.id.textView4);
        }
    }
}
