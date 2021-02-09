
package com.carlosveloper.pokemon.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("language")
    @Expose
    private Language_ language;
    @SerializedName("name")
    @Expose
    private String name;

    public Language_ getLanguage() {
        return language;
    }

    public void setLanguage(Language_ language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
