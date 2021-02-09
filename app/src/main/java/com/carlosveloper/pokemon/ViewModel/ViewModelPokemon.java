package com.carlosveloper.pokemon.ViewModel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.carlosveloper.pokemon.Common.MyApp;
import com.carlosveloper.pokemon.Model.Response.PokemonEntry;
import com.carlosveloper.pokemon.Model.Response.Pokemons;
import com.carlosveloper.pokemon.Repository.ApiService;
import com.carlosveloper.pokemon.Repository.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelPokemon extends ViewModel {


    RetrofitClient PokemonClient;
    ApiService apiService;
    private MutableLiveData<Boolean> isViewLoading;

    private MutableLiveData<List<PokemonEntry>> pokemons;

    private RetrofitClient retrofitClient;

    public ViewModelPokemon(){
        RetrofitInit();
        retrofitClient=new RetrofitClient();
        this.isViewLoading = new MutableLiveData<>();
        this.pokemons=new MutableLiveData<>();

    }


    public LiveData<List<PokemonEntry>> getPokemons(){
        return  pokemons;
    }
    public LiveData<Boolean> getIsViewLoading(){
        return  isViewLoading;
    }


    private void RetrofitInit(){
        PokemonClient = RetrofitClient.getInstance();
        apiService = PokemonClient.getMiniService();
    }



    public void peticionMisPokemons() {
        Call<Pokemons> call = apiService.getPokemos();
        call.enqueue(new Callback<Pokemons>() {
            @Override
            public void onResponse(Call<Pokemons> call, Response<Pokemons> response) {
                if (response.isSuccessful()) {

                    pokemons.setValue(response.body().getPokemonEntries());

                }
            }

            @Override
            public void onFailure(Call<Pokemons> call, Throwable t) {
                isViewLoading.setValue(false);
                Toast.makeText(MyApp.getContext(), "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }


        });
    }





}
