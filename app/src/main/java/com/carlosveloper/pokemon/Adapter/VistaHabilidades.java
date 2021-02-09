package com.carlosveloper.pokemon.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.carlosveloper.pokemon.Model.Response.Ability;
import com.carlosveloper.pokemon.R;


import java.util.ArrayList;
import java.util.List;

public class VistaHabilidades extends RecyclerView.Adapter<VistaHabilidades.Holder>{

     List<Ability> lst_normal;
     List<Ability> list_full;



    public VistaHabilidades(List<Ability> lst_normal) {
        this.lst_normal = lst_normal;
        list_full = new ArrayList<>(lst_normal);
    }


    @NonNull
    @Override
    public VistaHabilidades.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habilidades,
                parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VistaHabilidades.Holder holder, final int position) {

        holder.NombreHabilidad.setText(lst_normal.get(position).getAbility().getName());
        holder.Habilidad.setText("Habilidad " +(position+1));


    }



    public int getItemCount() {
        return lst_normal.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        TextView NombreHabilidad, Habilidad;

        public Holder(@NonNull View itemView) {
            super(itemView);
            NombreHabilidad = itemView.findViewById(R.id.TVNombreHabilidad);

            Habilidad = itemView.findViewById(R.id.TVHabilidad);


        }
    }



}
