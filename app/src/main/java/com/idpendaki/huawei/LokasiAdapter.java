package com.idpendaki.huawei;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LokasiAdapter extends RecyclerView.Adapter<LokasiAdapter.LokasiAdapterViewHolder>{

    ArrayList<String> lokasis = new ArrayList<>();
    LokasiAdapter.onItemClick onItemClick;

    public LokasiAdapter(ArrayList<String> lokasis) {
        this.lokasis = lokasis;
    }

    @NonNull
    @Override
    public LokasiAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LokasiAdapter.LokasiAdapterViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_layout_lokasi, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull LokasiAdapterViewHolder holder, int position) {
        holder.bind(lokasis.get(position), position);
    }

    @Override
    public int getItemCount() {
        return lokasis.size();
    }

    public class LokasiAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvLokasi;
        ImageView imageLokasi;
        CardView cvListLokasi;
        public LokasiAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLokasi = itemView.findViewById(R.id.tvLokasi);
            imageLokasi = itemView.findViewById(R.id.imageLokasi);
            cvListLokasi = itemView.findViewById(R.id.cvListLokasi);
        }

        public void bind(String lokasi, int position){
            if (lokasi.equalsIgnoreCase("Jawa Timur")) imageLokasi.setImageResource(R.drawable.ic_jatim);
            else if (lokasi.equalsIgnoreCase("Jawa Tengah")) imageLokasi.setImageResource(R.drawable.ic_jateng);
            else if (lokasi.equalsIgnoreCase("Jawa Barat")) imageLokasi.setImageResource(R.drawable.ic_jabar);
            else if (lokasi.equalsIgnoreCase("Luar Pulau Jawa")) imageLokasi.setImageResource(R.drawable.ic_luar_jawa);

            tvLokasi.setText(lokasi);

            cvListLokasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onClick(view, lokasi);
                }
            });
        }
    }

    public interface onItemClick{
        void onClick(View view, String lokasi);
    }
}
