package com.idpendaki.huawei;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PeralatanAdapter extends RecyclerView.Adapter<PeralatanAdapter.PeralatanAdapterViewHolder> {

    ArrayList<Peralatan> peralatans;
    Context context;
    PeralatanAdapter.onItemClick onItemClick;

    public PeralatanAdapter(ArrayList<Peralatan> peralatans, Context context) {
        this.peralatans = peralatans;
        this.context = context;
    }

    @NonNull
    @Override
    public PeralatanAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PeralatanAdapter.PeralatanAdapterViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_layout_peralatan_tips, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PeralatanAdapterViewHolder holder, int position) {
        holder.bind(peralatans.get(position), position);
    }

    @Override
    public int getItemCount() {
        return peralatans.size();
    }

    public class PeralatanAdapterViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePeralatan;
        TextView tvNamaAlat, tvTipeAlat;
        CardView cvListPeralatan;

        public PeralatanAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imagePeralatan = itemView.findViewById(R.id.imagePeralatan);
            tvNamaAlat = itemView.findViewById(R.id.tvNamaAlat);
            tvTipeAlat = itemView.findViewById(R.id.tvTipeAlat);
            cvListPeralatan = itemView.findViewById(R.id.cvListPeralatan);
        }

        public void bind(Peralatan p, int pos) {
            tvNamaAlat.setText(p.strNamaPeralatan);
            tvTipeAlat.setText(p.strTipePeralatan);

            Glide.with(context)
                    .load(p.strImagePeralatan)
                    .into(imagePeralatan);

            cvListPeralatan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onClick(view, p);
                }
            });
        }
    }

    public interface onItemClick{
        void onClick(View view, Peralatan p);
    }
}
