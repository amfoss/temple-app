package org.amfoss.templeapp.classes.dmy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DMYPooja {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("poojatype")
    @Expose
    private String poojatype;
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
    public DMYPooja() {
    }

    /**
     *
     * @param amount
     * @param poojatype
     * @param address
     * @param name
     * @param remarks
     * @param billno
     * @param date
     * @param type
     */
    public DMYPooja(String type, Integer date, String poojatype, Integer billno, String name, String address, Integer amount, String remarks) {
        super();
        this.type = type;
        this.date = date;
        this.poojatype = poojatype;
        this.billno = billno;
        this.name = name;
        this.address = address;
        this.amount = amount;
        this.remarks = remarks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getPoojatype() {
        return poojatype;
    }

    public void setPoojatype(String poojatype) {
        this.poojatype = poojatype;
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