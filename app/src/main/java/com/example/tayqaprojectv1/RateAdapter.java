package com.example.tayqaprojectv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.viewHolder> {

    private Context context;
    private List<rates> ratesList;

    public RateAdapter(Context context, List<rates> ratesList) {
        this.context = context;
        this.ratesList = ratesList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        rates rate = ratesList.get(position);

        holder.code.setText(rate.getCode());
        holder.rate.setText(Double.toString(rate.getRate()));
    }

    @Override
    public int getItemCount() {
        return ratesList.size();
    }

    void addData(List<rates> ratesList) {
        this.ratesList = ratesList;
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView code, rate;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            code = itemView.findViewById(R.id.code);
            rate = itemView.findViewById(R.id.rate);
        }
    }
}
