package com.example.latihan_1_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {

    private Mahasiswa[] mahasiswaList = {
            new Mahasiswa("A22.2021.02859", "Fahaddina Fikroh", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/1200px-Cat03.jpg"),
            new Mahasiswa("A22.2021.02860", "Rio Ferdinand", "https://static.wixstatic.com/media/103d43_9ecc1086f64147b9afe8a9c3333b1a1a~mv2.jpg/v1/fill/w_640,h_620,al_c,q_85,usm_0.66_1.00_0.01,enc_avif,quality_auto/103d43_9ecc1086f64147b9afe8a9c3333b1a1a~mv2.jpg"),
            new Mahasiswa("A22.2021.02862", "Aditya Firyan Syah", "https://d2zp5xs5cp8zlg.cloudfront.net/image-79322-800.jpg")
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mahasiswa mahasiswa = mahasiswaList[position];
        holder.txtNim.setText(mahasiswa.getNim());
        holder.txtNama.setText(mahasiswa.getNama());

        // Gunakan Glide untuk memuat gambar dari URL
        Glide.with(holder.itemView.getContext())
                .load(mahasiswa.getFotoUrl())
                .error(R.drawable.ic_launcher_background)
                .centerCrop()
                .apply(new RequestOptions().override(1000, 1000))
                .transition(DrawableTransitionOptions.withCrossFade(2000))
                .into(holder.imgMahasiswa);
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNim, txtNama;
        ImageView imgMahasiswa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNim = itemView.findViewById(R.id.txtNim);
            txtNama = itemView.findViewById(R.id.txtNama);
            imgMahasiswa = itemView.findViewById(R.id.imgMahasiswa);
        }
    }
}
