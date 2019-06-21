package org.amfoss.templeapp.classes.donation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonationResult {

    @SerializedName("donation")
    @Expose
    private Donation donation;

    /**
     * No args constructor for use in serialization
     *
     */
    public DonationResult() {
    }

    /**
     *
     * @param donation
     */
    public DonationResult(Donation donation) {
        super();
        this.donation = donation;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

}