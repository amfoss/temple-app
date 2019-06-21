package org.amfoss.templeapp.classes.assets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Asset {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("id")
    @Expose
    private Integer id;

    /**
     * No args constructor for use in serialization
     *
     */
    public Asset() {
    }

    /**
     *
     * @param id
     * @param amount
     * @param remarks
     * @param type
     */
    public Asset(String type, Integer amount, String remarks, Integer id) {
        super();
        this.type = type;
        this.amount = amount;
        this.remarks = remarks;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}