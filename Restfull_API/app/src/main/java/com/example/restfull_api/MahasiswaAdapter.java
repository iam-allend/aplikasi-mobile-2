package com.example.restfull_api;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {
    private List<Mahasiswa> mahasiswaList;

    public MahasiswaAdapter(List<Mahasiswa> mahasiswaList) {
        this.mahasiswaList = mahasiswaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mahasiswa mahasiswa = mahasiswaList.get(position);
        holder.nim.setText(mahasiswa.getNim());
        holder.nama.setText(mahasiswa.getNama());
        holder.telp.setText(mahasiswa.getTelp());
        holder.email.setText(mahasiswa.getEmail());
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nim, nama, telp, email;

        public ViewHolder(View itemView) {
            super(itemView);
            nim = itemView.findViewById(R.id.nim);
            nama = itemView.findViewById(R.id.nama);
            telp = itemView.findViewById(R.id.telp);
            email = itemView.findViewById(R.id.email);
        }
    }
}