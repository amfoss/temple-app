package org.amfoss.templeapp.classes.ie;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllIEResult {

    @SerializedName("ies")
    @Expose
    private List<IE> ies = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public AllIEResult() {
    }

    /**
     *
     * @param ies
     */
    public AllIEResult(List<IE> ies) {
        super();
        this.ies = ies;
    }

    public List<IE> getIEs() {
        return ies;
    }

    public void setIEs(List<IE> ies) {
        this.ies = ies;
    }

}