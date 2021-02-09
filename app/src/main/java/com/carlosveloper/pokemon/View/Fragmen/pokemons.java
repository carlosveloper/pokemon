package com.carlosveloper.pokemon.View.Fragmen;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.carlosveloper.pokemon.Adapter.VistasMisPokemon;
import com.carlosveloper.pokemon.Common.Global;
import com.carlosveloper.pokemon.Model.Response.PokemonEntry;
import com.carlosveloper.pokemon.R;
import com.carlosveloper.pokemon.View.Activitys.MiPokemon;
import com.carlosveloper.pokemon.ViewModel.ViewModelPokemon;

import java.util.ArrayList;
import java.util.List;


public class pokemons extends Fragment {


    RecyclerView recyclerView;
    View vista;
    VistasMisPokemon adapter;
    private ViewModelPokemon viewModel;
    List<PokemonEntry> ls_listado = new ArrayList<>();
    EditText buscar;


    public pokemons() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_pokemons, container, false);
        UI();
        iniciar_recycler();
        loadPokemons();
        return vista;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.peticionMisPokemons();

    }

    private void UI() {
        recyclerView = vista.findViewById(R.id.Recycler_pokemons);
        viewModel = ViewModelProviders.of(this).get(ViewModelPokemon.class);
        buscar=vista.findViewById(R.id.escribir_busqueda);
        buscar.clearFocus();
        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                if(editable.length()<=0){
                    ls_listado.clear();
                    ls_listado.addAll(Global.misPokemos);
                    adapter.setData(ls_listado);
                }else{
                    filtro(editable.toString());

                }

            }
        });

    }

    private void  iniciar_recycler(){
        adapter=new VistasMisPokemon(ls_listado, new VistasMisPokemon.OnItemClicListener() {
            @Override
            public void onItemClick(PokemonEntry product, int position) {
                Global.selectPokemon=product;
                Intent intent = new Intent(getActivity(), MiPokemon.class);
                startActivity(intent);
            }

        },getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


    private void loadPokemons() {
        final Observer<List<PokemonEntry>> observerConsulta = new Observer<List<PokemonEntry>>() {
            @Override
            public void onChanged(List<PokemonEntry> result) {
              /*  ls_listado.clear();
                ls_listado.addAll(result);
                adapter.notifyDataSetChanged();*/
                Global.misPokemos=result;
                adapter.setData(result);
            }
        };


        viewModel.getPokemons().observe(this, observerConsulta);


    }
    public void filtro(String S){
        if(adapter!=null)
            adapter.getFilter().filter(S);
    }



}