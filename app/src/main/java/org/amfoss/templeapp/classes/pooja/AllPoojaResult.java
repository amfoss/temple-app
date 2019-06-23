package org.amfoss.templeapp.classes.pooja;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllPoojaResult {

    @SerializedName("poojas")
    @Expose
    private List<Pooja> poojas = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public AllPoojaResult() {
    }

    /**
     *
     * @param poojas
     */
    public AllPoojaResult(List<Pooja> poojas) {
        super();
        this.poojas = poojas;
    }

    public List<Pooja> getPoojas() {
        return poojas;
    }

    public void setPoojas(List<Pooja> poojas) {
        this.poojas = poojas;
    }

}