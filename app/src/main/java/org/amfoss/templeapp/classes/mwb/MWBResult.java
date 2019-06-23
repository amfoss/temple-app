package org.amfoss.templeapp.classes.mwb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MWBResult {

    @SerializedName("mwb")
    @Expose
    private MWB mwb;

    /**
     * No args constructor for use in serialization
     *
     */
    public MWBResult() {
    }

    /**
     *
     * @param mwb
     */
    public MWBResult(MWB mwb) {
        super();
        this.mwb = mwb;
    }

    public MWB getMWB() {
        return mwb;
    }

    public void setMWB(MWB mwb) {
        this.mwb = mwb;
    }

}