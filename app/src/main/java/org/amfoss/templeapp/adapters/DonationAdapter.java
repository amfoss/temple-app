package org.amfoss.templeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.amfoss.templeapp.R;
import org.amfoss.templeapp.utils.DonationUtils;

import java.util.List;

/**
 * @author by Chromicle (ajayprabhakar369@gmail.com)
 * @since 12/4/2019
 */
public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationViewHolder> {


    Context context;
    List<DonationUtils> DonationList;


    public DonationAdapter(Context context, List<DonationUtils> donationList) {
        this.context = context;
        DonationList = donationList;
    }

    @NonNull
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DonationAdapter.DonationViewHolder(
                LayoutInflater.from(context).inflate(R.layout.individual_income, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {

        DonationUtils donation = DonationList.get(position);
        holder.listPilgrimName.setText(donation.getPilgrimName());
        holder.listDonationCause.setText(donation.getDonationCause());
        holder.listDonationAmount.setText(donation.getDonationAmount());
        holder.listDonationDate.setText(donation.getDonationDate());

    }

    @Override
    public int getItemCount() {
        return DonationList.size();
    }

    public class DonationViewHolder extends RecyclerView.ViewHolder {

        TextView listPilgrimName;
        TextView listDonationCause;
        TextView listDonationDate;
        TextView listDonationAmount;

        public DonationViewHolder(@NonNull View itemView) {
            super(itemView);

            listPilgrimName = itemView.findViewById(R.id.listPilgrimName);
            listDonationCause = itemView.findViewById(R.id.listDonationName);
            listDonationDate = itemView.findViewById(R.id.listDonationDate);
            listDonationAmount = itemView.findViewById(R.id.listDonationAmount);
        }
    }
}
