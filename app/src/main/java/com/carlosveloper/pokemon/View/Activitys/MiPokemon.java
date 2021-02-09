package com.carlosveloper.pokemon.View.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.carlosveloper.pokemon.Adapter.VistaHabilidades;
import com.carlosveloper.pokemon.Common.Global;
import com.carlosveloper.pokemon.Model.Response.Ability;
import com.carlosveloper.pokemon.Model.Response.DescriptionPokemon;
import com.carlosveloper.pokemon.Model.Response.FlavorTextEntry;
import com.carlosveloper.pokemon.Model.Response.Habilidades;
import com.carlosveloper.pokemon.Model.Response.PokemonEntry;
import com.carlosveloper.pokemon.R;
import com.carlosveloper.pokemon.ViewModel.ViewModelDescription;
import com.carlosveloper.pokemon.ViewModel.ViewModelPokemon;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class MiPokemon extends AppCompatActivity {

    private ViewModelDescription viewModel;

    RecyclerView recyclerView;

    List<Ability> lst_habilidades=new ArrayList<>();
    VistaHabilidades adapter;

    ImageView imagen;
    TextView nombre,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_pokemon);
        ui();
        UI();
        presentarDatos();
    }

    private void ui(){
        imagen=findViewById(R.id.IVPokemonFoto);
        nombre=findViewById(R.id.TVPokemonNombre);
        description=findViewById(R.id.TVdescripcion);


        viewModel = ViewModelProviders.of(this).get(ViewModelDescription.class);
        final Observer<DescriptionPokemon> observerConsulta = new Observer<DescriptionPokemon>() {
            @Override
            public void onChanged(DescriptionPokemon result) {
                for(FlavorTextEntry text :result.getFlavorTextEntries() ){

                    if(text.getLanguage().getName().equals("es")){
                        description.setText(text.getFlavorText());
                    }
                }



            }
        };


        final Observer<Habilidades> observerHabilidades= new Observer<Habilidades>() {
            @Override
            public void onChanged(Habilidades result) {
                lst_habilidades.addAll(result.getAbilities());
                Log.e("habilidades",""+lst_habilidades.size());
                adapter.notifyDataSetChanged();
            }
        };


        viewModel.getPokemons().observe(this, observerConsulta);
        viewModel.getHabilidades().observe(this, observerHabilidades);

        viewModel.peticionMisPokemons(""+Global.selectPokemon.getEntryNumber());
        viewModel.peticionMisHabilidades(""+Global.selectPokemon.getEntryNumber());

    }

    private void  presentarDatos(){
        nombre.setText(Global.selectPokemon.getPokemonSpecies().getName());


        String mDrawableName="k_";
        if(Global.selectPokemon.getEntryNumber()<100){
            Formatter obj = new Formatter();
            String numeroCeros = String.valueOf(obj.format("%03d", Global.selectPokemon.getEntryNumber()));
            Log.e("Numeros",numeroCeros);

            mDrawableName+=numeroCeros;
        }else{
            mDrawableName+=Global.selectPokemon.getEntryNumber();

        }

        int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
        Glide
                .with(getApplicationContext())
                .load(resID)
                .placeholder(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL )
                .error(R.drawable.ic_launcher_background)
                . skipMemoryCache(true)
                .into(imagen);

    }

    private void UI(){
        recyclerView= findViewById(R.id.Recycler_misHabilidades);
        adapter=new VistaHabilidades(lst_habilidades);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}