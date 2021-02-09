
package com.carlosveloper.pokemon.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonEntry {

    @SerializedName("entry_number")
    @Expose
    private int entryNumber;
    @SerializedName("pokemon_species")
    @Expose
    private PokemonSpecies pokemonSpecies;

    public int getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
    }

    public PokemonSpecies getPokemonSpecies() {
        return pokemonSpecies;
    }

    public void setPokemonSpecies(PokemonSpecies pokemonSpecies) {
        this.pokemonSpecies = pokemonSpecies;
    }

}
