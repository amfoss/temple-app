package org.amfoss.templeapp.classes.donation;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllDonationResult {

    @SerializedName("donations")
    @Expose
    private List<Donation> donations = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public AllDonationResult() {
    }

    /**
     *
     * @param donations
     */
    public AllDonationResult(List<Donation> donations) {
        super();
        this.donations = donations;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

}