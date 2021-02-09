
package com.carlosveloper.pokemon.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Description {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("language")
    @Expose
    private Language language;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

}
