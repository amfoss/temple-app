package org.amfoss.templeapp.classes.ie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IEResult {

    @SerializedName("ie")
    @Expose
    private IE ie;

    /**
     * No args constructor for use in serialization
     *
     */
    public IEResult() {
    }

    /**
     *
     * @param ie
     */
    public IEResult(IE ie) {
        super();
        this.ie = ie;
    }

    public IE getIE() {
        return ie;
    }

    public void setIE(IE ie) {
        this.ie = ie;
    }

}