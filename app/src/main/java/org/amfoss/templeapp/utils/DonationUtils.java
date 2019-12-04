package org.amfoss.templeapp.utils;

/**
* @author by Chromicle (ajayprabhakar369@gmail.com)
* @since 12/3/2019
*/
public class DonationUtils {
    private String donationDate, pilgrimName, donationCause, donationAmount;

    public DonationUtils(
            String donationDate, String pilgrimName, String donationCause, String donationAmount) {
        this.donationDate = donationDate;
        this.pilgrimName = pilgrimName;
        this.donationCause = donationCause;
        this.donationAmount = donationAmount;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public String getPilgrimName() {
        return pilgrimName;
    }

    public String getDonationCause() {
        return donationCause;
    }

    public String getDonationAmount() {
        return donationAmount;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    public void setPilgrimName(String pilgrimName) {
        this.pilgrimName = pilgrimName;
    }

    public void setDonationCause(String donationCause) {
        this.donationCause = donationCause;
    }

    public void setDonationAmount(String donationAmount) {
        this.donationAmount = donationAmount;
    }
}
