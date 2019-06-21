package org.amfoss.templeapp.classes.pooja;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pooja {

    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("billno")
    @Expose
    private Integer billno;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("remarks")
    @Expose
    private String remarks;

    /**
     * No args constructor for use in serialization
     *
     */
    public Pooja() {
    }

    /**
     *
     * @param amount
     * @param address
     * @param name
     * @param remarks
     * @param billno
     * @param type
     * @param date
     */
    public Pooja(Integer date, String type, Integer billno, String name, String address, Integer amount, String remarks) {
        super();
        this.date = date;
        this.type = type;
        this.billno = billno;
        this.name = name;
        this.address = address;
        this.amount = amount;
        this.remarks = remarks;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBillno() {
        return billno;
    }

    public void setBillno(Integer billno) {
        this.billno = billno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

}