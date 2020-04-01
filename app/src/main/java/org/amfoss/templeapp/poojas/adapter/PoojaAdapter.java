package org.amfoss.templeapp.poojas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import org.amfoss.templeapp.R;

/**
* @author by Chromicle (ajayprabhakar369@gmail.com)
* @since 12/3/2019
*/
public class PoojaAdapter extends RecyclerView.Adapter<PoojaAdapter.PoojaViewHolder> {

    Context context;
    private List<PoojaModel> poojaList;

    public PoojaAdapter(Context context, List<PoojaModel> poojaList) {
        this.context = context;
        this.poojaList = poojaList;
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
        }
    }
}
