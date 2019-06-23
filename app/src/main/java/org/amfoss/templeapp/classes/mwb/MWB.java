package org.amfoss.templeapp.classes.mwb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MWB {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("innertype")
    @Expose
    private String innertype;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private Integer date;

    /**
     * No args constructor for use in serialization
     *
     */
    public MWB() {
    }

    /**
     *
     * @param id
     * @param amount
     * @param innertype
     * @param remarks
     * @param date
     * @param type
     */
    public MWB(String type, String innertype, Integer amount, String remarks, Integer id, Integer date) {
        super();
        this.type = type;
        this.innertype = innertype;
        this.amount = amount;
        this.remarks = remarks;
        this.id = id;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInnertype() {
        return innertype;
    }

    public void setInnertype(String innertype) {
        this.innertype = innertype;
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

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

}