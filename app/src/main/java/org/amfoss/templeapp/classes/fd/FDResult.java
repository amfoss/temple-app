package org.amfoss.templeapp.classes.fd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FDResult {

    @SerializedName("fd")
    @Expose
    private FD fd;

    /**
     * No args constructor for use in serialization
     *
     */
    public FDResult() {
    }

    /**
     *
     * @param fd
     */
    public FDResult(FD fd) {
        super();
        this.fd = fd;
    }

    public FD getFD() {
        return fd;
    }

    public void setFD(FD fd) {
        this.fd = fd;
    }

}