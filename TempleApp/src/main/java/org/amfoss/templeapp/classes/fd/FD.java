
package org.amfoss.templeapp.classes.fd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FD {

    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("bank")
    @Expose
    private String bank;
    @SerializedName("fdno")
    @Expose
    private String fdno;
    @SerializedName("maturitydate")
    @Expose
    private Integer maturitydate;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public FD() {
    }

    /**
     *
     * @param amount
     * @param maturitydate
     * @param fdno
     * @param name
     * @param value
     * @param bank
     * @param date
     */
    public FD(Integer date, String bank, String fdno, Integer maturitydate, Integer amount, Integer value, String name) {
        super();
        this.date = date;
        this.bank = bank;
        this.fdno = fdno;
        this.maturitydate = maturitydate;
        this.amount = amount;
        this.value = value;
        this.name = name;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getFdno() {
        return fdno;
    }

    public void setFdno(String fdno) {
        this.fdno = fdno;
    }

    public Integer getMaturitydate() {
        return maturitydate;
    }

    public void setMaturitydate(Integer maturitydate) {
        this.maturitydate = maturitydate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}