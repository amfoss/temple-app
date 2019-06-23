package org.amfoss.templeapp.classes.fd;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllFDResult {

    @SerializedName("fds")
    @Expose
    private List<FD> fds = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public AllFDResult() {
    }

    /**
     *
     * @param fds
     */
    public AllFDResult(List<FD> fds) {
        super();
        this.fds = fds;
    }

    public List<FD> getFDs() {
        return fds;
    }

    public void setFDs(List<FD> fds) {
        this.fds = fds;
    }

}