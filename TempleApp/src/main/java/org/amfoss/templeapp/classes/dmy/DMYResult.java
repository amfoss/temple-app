package org.amfoss.templeapp.classes.dmy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DMYResult {

    @SerializedName("dmypooja")
    @Expose
    private DMYPooja dmypooja;

    /**
     * No args constructor for use in serialization
     *
     */
    public DMYResult() {
    }

    /**
     *
     * @param dmypooja
     */
    public DMYResult(DMYPooja dmypooja) {
        super();
        this.dmypooja = dmypooja;
    }

    public DMYPooja getDMYPooja() {
        return dmypooja;
    }

    public void setDMYPooja(DMYPooja dmypooja) {
        this.dmypooja = dmypooja;
    }

}