package com.example.bdd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.shape.ShapePath;

import java.util.ArrayList;
import java.util.List;

public class MonRecyclerViewAdapter extends RecyclerView.Adapter<MonRecyclerViewAdapter.ConteneurDeDonnee> {
    private Context context;
    private List<Planete> planetes;

    public MonRecyclerViewAdapter(Context context, List<Planete> planetes) {
        this.planetes = planetes;
        this.context = context;
    }

    @Override
    public ConteneurDeDonnee onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ConteneurDeDonnee(view);
    }

    @Override
    public void onBindViewHolder(ConteneurDeDonnee conteneur, int position) {
        String name = context.getString(R.string.name) + " : " + planetes.get(position).getNom();
        conteneur.name.setText(name);
        String size = context.getString(R.string.size) + " : " + planetes.get(position).getTaille() + " k habitants.";
        conteneur.size.setText(size);
    }

    @Override
    public int getItemCount() {
        return planetes.size();
    }

    public static class ConteneurDeDonnee extends RecyclerView.ViewHolder {
        TextView name;
        TextView size;

        public ConteneurDeDonnee(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            size = (TextView) itemView.findViewById(R.id.size);
        }
    }

}
