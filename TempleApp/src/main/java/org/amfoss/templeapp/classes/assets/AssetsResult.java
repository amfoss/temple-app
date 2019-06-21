package org.amfoss.templeapp.classes.assets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssetsResult {

    @SerializedName("asset")
    @Expose
    private Asset asset;

    /**
     * No args constructor for use in serialization
     *
     */
    public AssetsResult() {
    }

    /**
     *
     * @param asset
     */
    public AssetsResult(Asset asset) {
        super();
        this.asset = asset;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

}