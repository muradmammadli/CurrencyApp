package com.example.tayqaprojectv1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.viewHolder> {

    private Context context;
    private List<rates> ratesList;
    private onItemClickListener mListener;

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;
    }

    public RateAdapter(Context context, List<rates> ratesList) {
        this.context = context;
        this.ratesList = ratesList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false);
        return new viewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        rates rate = ratesList.get(position);
        holder.code.setText(rate.getCode());

        Intent intent = ((Activity) context).getIntent();
        int ratioValue = intent.getIntExtra("ratio", 1);
        Double rateValue = rate.getRate();
        Double sumValue = ratioValue * rateValue;
        holder.rate.setText(Double.toString(sumValue));
    }

    @Override
    public int getItemCount() {
        return ratesList.size();
    }

    void addData(List<rates> ratesList) {
        this.ratesList = ratesList;
        notifyDataSetChanged();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        private TextView code, rate;
        private CardView cardView;

        public viewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            code = itemView.findViewById(R.id.code);
            rate = itemView.findViewById(R.id.rate);
            cardView = itemView.findViewById(R.id.card);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }

                    }

                }
            });
        }
    }
}
