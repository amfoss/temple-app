package org.amfoss.templeapp.poojas.adapter;

import android.content.Context;
import android.util.Log;
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
import java.util.List;
import org.amfoss.templeapp.R;

/**
* @author by Chromicle (ajayprabhakar369@gmail.com)
* @since 12/3/2019
*/
public class PoojaAdapter extends RecyclerView.Adapter<PoojaAdapter.PoojaViewHolder>
        implements Filterable {

    Context context;
    private List<PoojaModel> poojaList;
    private List<PoojaModel> poojaListFiltered;

    public PoojaAdapter(Context context, List<PoojaModel> poojaList) {
        this.context = context;
        this.poojaList = poojaList;
        this.poojaListFiltered = poojaList;
    }

    @NonNull
    @Override
    public PoojaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PoojaViewHolder(
                LayoutInflater.from(context).inflate(R.layout.individual_pooja, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PoojaViewHolder holder, int position) {
        PoojaModel pooja = poojaList.get(position);
        holder.listPilgrimName.setText(pooja.getPilgrimName());
        holder.listPoojaName.setText(pooja.getPoojaName());
        holder.listPoojaAmount.setText(pooja.getPoojaAmount());
        holder.listPoojaDate.setText(pooja.getPoojaDate());
    }

    @Override
    public int getItemCount() {
        return poojaList.size();
    }

    public class PoojaViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout viewBackground, viewForeground;
        TextView listPilgrimName;
        TextView listPoojaName;
        TextView listPoojaDate;
        TextView listPoojaAmount;

        public PoojaViewHolder(@NonNull View itemView) {
            super(itemView);

            listPilgrimName = itemView.findViewById(R.id.listPilgrimName);
            listPoojaName = itemView.findViewById(R.id.listPoojaName);
            listPoojaDate = itemView.findViewById(R.id.listPoojaDate);
            listPoojaAmount = itemView.findViewById(R.id.listPoojaAmount);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
        }
    }

    public void removeItem(int position) {
        poojaList.remove(position);
        poojaList.clear();
        notifyDataSetChanged();
    }

    public String getName(int position) {
        PoojaModel pooja = poojaList.get(position);
        return pooja.getPilgrimName();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    poojaList = poojaListFiltered;
                } else {
                    List<PoojaModel> filteredList = new ArrayList<>();
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (PoojaModel item : poojaListFiltered) {
                        if (item.getPilgrimName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                    poojaList = filteredList;
                }
                FilterResults results = new FilterResults();
                results.values = poojaList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                poojaList = (ArrayList<PoojaModel>) results.values;
                Log.d("size", String.valueOf(poojaList.size()));
                notifyDataSetChanged();
            }
        };
    }
}
