package com.carlosveloper.pokemon.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.carlosveloper.pokemon.Common.Global;
import com.carlosveloper.pokemon.Model.Response.PokemonEntry;
import com.carlosveloper.pokemon.R;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class VistasMisPokemon extends RecyclerView.Adapter<VistasMisPokemon.MultiHolder>  implements Filterable {

    private OnItemClicListener itemClicListener;


    List<PokemonEntry> lst_normal;
    List<PokemonEntry> lst_full;

    Context contex;
    FragmentManager fragmentManager;

    public VistasMisPokemon( List<PokemonEntry> lst_normal, OnItemClicListener itemClicListener,Context contex) {
        this.lst_normal = lst_normal;
        this.itemClicListener = itemClicListener;
        lst_full=new ArrayList<>(lst_normal);
        this.contex=contex;

    }



    public VistasMisPokemon(   List<PokemonEntry> lst_normal, FragmentManager fragmentManager) {
        this.lst_normal = lst_normal;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public MultiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,parent,false);

        MultiHolder th= new MultiHolder(view);
        return th;    }

    @Override
    public void onBindViewHolder(@NonNull MultiHolder holder, final int position) {
       holder.nombre.setText(lst_normal.get(position).getPokemonSpecies().getName());


        String mDrawableName="k_";
       if(lst_normal.get(position).getEntryNumber()<100){
           Formatter obj = new Formatter();
           String numeroCeros = String.valueOf(obj.format("%03d", lst_normal.get(position).getEntryNumber()));
           Log.e("Numeros",numeroCeros);

           mDrawableName+=numeroCeros;
       }else{
           Log.e("Numeros",""+lst_normal.get(position).getEntryNumber());
           mDrawableName+=lst_normal.get(position).getEntryNumber();

       }

        int resID = contex.getResources().getIdentifier(mDrawableName , "drawable", contex.getPackageName());
       Glide
                .with(holder.imagen.getContext())
                .load(resID)
                .placeholder(R.drawable.ic_launcher_background)
               .diskCacheStrategy(DiskCacheStrategy.ALL )
                .error(R.drawable.ic_launcher_background)
               . skipMemoryCache(true)
                .into(holder.imagen);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       // //("click","Tienda");
        itemClicListener.onItemClick(lst_normal.get(position),position);



    }
});

    }

    @Override
    public int getItemCount() {

        if (lst_normal != null)
            return lst_normal.size();
        else
            return 0;
    }



    @Override
    public Filter getFilter() {
        return pokemons_filter;
    }

    public class MultiHolder extends RecyclerView.ViewHolder {
        ImageView imagen;
        TextView nombre;
        public MultiHolder(@NonNull View itemView) {

            super(itemView);
                imagen=itemView.findViewById(R.id.IVPokemonFoto);
                nombre=itemView.findViewById(R.id.TVPokemonNombre);



        }
    }

    public void setData(List<PokemonEntry> pokemons){
        lst_normal=pokemons;
        lst_full=pokemons;
        this.notifyDataSetChanged();

    }


    private Filter pokemons_filter =new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(lst_full.size()<=0){
                lst_full.addAll(Global.misPokemos);
            }

            //("adapter","filtro llegar" +constraint);
            List<PokemonEntry> filtro = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filtro.addAll(lst_full);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (PokemonEntry item : lst_full) {

                    if (item.getPokemonSpecies().getName().toLowerCase().trim().contains(filterPattern)) {
                        filtro.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtro;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if(lst_normal!=null){
                lst_normal.clear();
                lst_normal.addAll((List) filterResults.values);
                notifyDataSetChanged();
            }
        }
    };






    public  interface OnItemClicListener{

        void onItemClick(PokemonEntry pokemonEntry, int position);
    }




    }
