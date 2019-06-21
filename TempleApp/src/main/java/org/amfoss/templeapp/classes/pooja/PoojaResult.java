package org.amfoss.templeapp.classes.pooja;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PoojaResult {

    @SerializedName("pooja")
    @Expose
    private Pooja pooja;

    /**
     * No args constructor for use in serialization
     *
     */
    public PoojaResult() {
    }

    /**
     *
     * @param pooja
     */
    public PoojaResult(Pooja pooja) {
        super();
        this.pooja = pooja;
    }

    public Pooja getPooja() {
        return pooja;
    }

    public void setPooja(Pooja pooja) {
        this.pooja = pooja;
    }

}