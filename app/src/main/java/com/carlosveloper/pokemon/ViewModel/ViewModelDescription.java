package com.carlosveloper.pokemon.ViewModel;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.carlosveloper.pokemon.Common.MyApp;
import com.carlosveloper.pokemon.Model.Response.DescriptionPokemon;
import com.carlosveloper.pokemon.Model.Response.Habilidades;
import com.carlosveloper.pokemon.Model.Response.Pokemons;
import com.carlosveloper.pokemon.Repository.ApiService;
import com.carlosveloper.pokemon.Repository.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelDescription extends ViewModel {


    RetrofitClient PokemonClient;
    ApiService apiService;

    private MutableLiveData<DescriptionPokemon> pokemons;
    private MutableLiveData<Habilidades> habilidades;


    private RetrofitClient retrofitClient;

    public ViewModelDescription(){
        RetrofitInit();
        retrofitClient=new RetrofitClient();
        this.pokemons=new MutableLiveData<>();
        this.habilidades=new MutableLiveData<>();

    }


    public LiveData<DescriptionPokemon> getPokemons(){
        return  pokemons;
    }

    public LiveData<Habilidades> getHabilidades(){
        return  habilidades;
    }

    private void RetrofitInit(){
        PokemonClient = RetrofitClient.getInstance();
        apiService = PokemonClient.getMiniService();
    }



    public void peticionMisPokemons(String id) {
        Call<DescriptionPokemon> call = apiService.getDescriptionPokemon(id);
        call.enqueue(new Callback<DescriptionPokemon>() {
            @Override
            public void onResponse(Call<DescriptionPokemon> call, Response<DescriptionPokemon> response) {
                if (response.isSuccessful()) {

                    pokemons.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<DescriptionPokemon> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }


        });
    }


    public void peticionMisHabilidades(String id) {
        Call<Habilidades> call = apiService.getHabilidades(id);
        call.enqueue(new Callback<Habilidades>() {
            @Override
            public void onResponse(Call<Habilidades> call, Response<Habilidades> response) {
                if (response.isSuccessful()) {

                    habilidades.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<Habilidades> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }


        });
    }





}
