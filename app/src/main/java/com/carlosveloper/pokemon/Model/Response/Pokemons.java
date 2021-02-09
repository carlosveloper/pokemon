
package com.carlosveloper.pokemon.Model.Response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pokemons {

    @SerializedName("descriptions")
    @Expose
    private List<Description> descriptions = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("is_main_series")
    @Expose
    private Boolean isMainSeries;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("names")
    @Expose
    private List<Name> names = null;
    @SerializedName("pokemon_entries")
    @Expose
    private List<PokemonEntry> pokemonEntries = null;
    @SerializedName("region")
    @Expose
    private Region region;
    @SerializedName("version_groups")
    @Expose
    private List<VersionGroup> versionGroups = null;

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsMainSeries() {
        return isMainSeries;
    }

    public void setIsMainSeries(Boolean isMainSeries) {
        this.isMainSeries = isMainSeries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    public List<PokemonEntry> getPokemonEntries() {
        return pokemonEntries;
    }

    public void setPokemonEntries(List<PokemonEntry> pokemonEntries) {
        this.pokemonEntries = pokemonEntries;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<VersionGroup> getVersionGroups() {
        return versionGroups;
    }

    public void setVersionGroups(List<VersionGroup> versionGroups) {
        this.versionGroups = versionGroups;
    }

}
