package org.amfoss.templeapp.income.adapter;

import java.util.Comparator;

/**
* @author by Chromicle (ajayprabhakar369@gmail.com)
* @since 12/3/2019
*/
public class DonationModel {
    private String donationDate, pilgrimName, donationCause, donationAmount;

    public DonationModel() {}

    public DonationModel(
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

    public static final Comparator<DonationModel> BY_TITLE_NAME =
            new Comparator<DonationModel>() {
                @Override
                public int compare(DonationModel o1, DonationModel o2) {
                    return o1.getPilgrimName().compareTo(o2.getPilgrimName());
                }
            };

    public static final Comparator<DonationModel> BY_TITLE_DATE =
            new Comparator<DonationModel>() {
                @Override
                public int compare(DonationModel o1, DonationModel o2) {
                    return o1.getDonationDate().compareTo(o2.getDonationDate());
                }
            };
}
