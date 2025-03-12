package com.example.restfull_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {
    private final Context context;
    private List<DataMahasiswa> results;

    public MahasiswaAdapter(Context context, List<DataMahasiswa> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mahasiswa, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataMahasiswa data = results.get(position);
        holder.tvNim.setText(data.getNim());
        holder.tvNama.setText(data.getNama());
        holder.tvTelp.setText(data.getTelp());
        holder.tvEmail.setText(data.getEmail());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNim, tvNama, tvTelp, tvEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNim = itemView.findViewById(R.id.tvNim);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvTelp = itemView.findViewById(R.id.tvTelp);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}