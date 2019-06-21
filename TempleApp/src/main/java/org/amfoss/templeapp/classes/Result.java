package org.amfoss.templeapp.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("result")
    @Expose
    private String result;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param result
     */
    public Result(String result) {
        super();
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}