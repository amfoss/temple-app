package org.amfoss.templeapp.classes.assets;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllAssetsResult {

    @SerializedName("assets")
    @Expose
    private List<Asset> assets = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public AllAssetsResult() {
    }

    /**
     *
     * @param assets
     */
    public AllAssetsResult(List<Asset> assets) {
        super();
        this.assets = assets;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

}