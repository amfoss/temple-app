package org.amfoss.templeapp.classes.mwb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AllMWBResult {

    @SerializedName("mwbs")
    @Expose
    private List<MWB> mwbs = null;

    /** No args constructor for use in serialization */
    public AllMWBResult() {}

    /** @param mwbs */
    public AllMWBResult(List<MWB> mwbs) {
        super();
        this.mwbs = mwbs;
    }

    public List<MWB> getMWBs() {
        return mwbs;
    }

    public void setMWBs(List<MWB> mwbs) {
        this.mwbs = mwbs;
    }
}
