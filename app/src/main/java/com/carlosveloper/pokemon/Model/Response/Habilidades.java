
package com.carlosveloper.pokemon.Model.Response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Habilidades {

    @SerializedName("abilities")
    @Expose
    private List<Ability> abilities = null;

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

}
