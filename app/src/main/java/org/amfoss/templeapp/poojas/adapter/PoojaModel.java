package org.amfoss.templeapp.poojas.adapter;

/**
* @author by Chromicle (ajayprabhakar369@gmail.com)
* @since 12/12/2019
*/
public class PoojaModel {
    private String poojaDate, pilgrimName, poojaName, poojaAmount;

    public PoojaModel() {}

    public PoojaModel(String poojaDate, String pilgrimName, String poojaName, String poojaAmount) {
        this.poojaDate = poojaDate;
        this.pilgrimName = pilgrimName;
        this.poojaName = poojaName;
        this.poojaAmount = poojaAmount;
    }

    public void setPoojaDate(String poojaDate) {
        this.poojaDate = poojaDate;
    }

    public void setPilgrimName(String pilgrimName) {
        this.pilgrimName = pilgrimName;
    }

    public void setPoojaName(String poojaName) {
        this.poojaName = poojaName;
    }

    public void setPoojaAmount(String poojaAmount) {
        this.poojaAmount = poojaAmount;
    }

    public String getPoojaDate() {
        return poojaDate;
    }

    public String getPilgrimName() {
        return pilgrimName;
    }

    public String getPoojaName() {
        return poojaName;
    }

    public String getPoojaAmount() {
        return poojaAmount;
    }
}
