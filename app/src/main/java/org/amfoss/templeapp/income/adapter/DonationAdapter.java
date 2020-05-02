package org.amfoss.templeapp.income.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.amfoss.templeapp.R;

/**
* @author by Chromicle (ajayprabhakar369@gmail.com)
* @since 12/4/2019
*/
public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationViewHolder>
        implements Filterable {

    Context context;
    List<DonationModel> DonationList;
    private List<DonationModel> donationListFiltered;

    public DonationAdapter(Context context, List<DonationModel> donationList) {
        this.context = context;
        this.DonationList = donationList;
        this.donationListFiltered = donationList;
    }

    @NonNull
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DonationAdapter.DonationViewHolder(
                LayoutInflater.from(context).inflate(R.layout.individual_income, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {

        DonationModel donation = DonationList.get(position);
        holder.listPilgrimName.setText(donation.getPilgrimName());
        holder.listDonationCause.setText(donation.getDonationCause());
        holder.listDonationAmount.setText(donation.getDonationAmount());
        holder.listDonationDate.setText(donation.getDonationDate());
    }

    @Override
    public int getItemCount() {
        return DonationList.size();
    }

    public void sortFilter() {
        final String[] sort = {"Name", "Date"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Sort By");
        builder.setItems(
                sort,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Collections.sort(DonationList, DonationModel.BY_TITLE_NAME);
                            notifyDataSetChanged();
                        }
                        if (which == 1) {
                            Collections.sort(DonationList, DonationModel.BY_TITLE_DATE);
                            notifyDataSetChanged();
                        }
                    }
                });
        builder.create().show();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    DonationList = donationListFiltered;
                } else {
                    List<DonationModel> filteredList = new ArrayList<>();
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (DonationModel item : donationListFiltered) {
                        if (item.getPilgrimName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                    DonationList = filteredList;
                }
                FilterResults results = new FilterResults();
                results.values = DonationList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                DonationList = (ArrayList<DonationModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class DonationViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout viewBackground, viewForeground;
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
            viewBackground = itemView.findViewById(R.id.view_background_inc);
            viewForeground = itemView.findViewById(R.id.view_foreground_inc);
        }
    }

    public void removeItem(int position) {
        DonationList.remove(position);
        DonationList.clear();
        notifyDataSetChanged();
    }

    public String getName(int position) {
        DonationModel donation = DonationList.get(position);
        return donation.getPilgrimName();
    }
}
