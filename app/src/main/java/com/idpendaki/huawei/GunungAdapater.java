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

public class GunungAdapater extends RecyclerView.Adapter<GunungAdapater.GunungAdapaterViewHolder>{

    ArrayList<Gunung> gunungs = new ArrayList<>();
    GunungAdapater.onItemClick onItemClick;
    Context context;

    public GunungAdapater(ArrayList<Gunung> gunungs, Context context) {
        this.gunungs = gunungs;
        this.context = context;
    }

    @NonNull
    @Override
    public GunungAdapaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GunungAdapater.GunungAdapaterViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_layout_gunung, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull GunungAdapaterViewHolder holder, int position) {
        holder.bind(gunungs.get(position), position);
    }

    @Override
    public int getItemCount() {
        return gunungs.size();
    }

    public class GunungAdapaterViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaGunung, tvLokasiGunung;
        ImageView imageGunung;
        CardView cvListGunung;
        public GunungAdapaterViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaGunung = itemView.findViewById(R.id.tvNamaGunung);
            tvLokasiGunung = itemView.findViewById(R.id.tvLokasiGunung);
            imageGunung = itemView.findViewById(R.id.imageGunung);
            cvListGunung = itemView.findViewById(R.id.cvListGunung);
        }

        public void bind(Gunung g, int pos){
            tvNamaGunung.setText(g.strNamaGunung);
            tvLokasiGunung.setText(g.strLokasiGunung);

            cvListGunung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onClick(view, g);
                }
            });

            Glide.with(context)
                    .load(g.strImageGunung)
                    .into(imageGunung);
        }
    }

    public interface onItemClick{
        void onClick(View view, Gunung gunung);
    }
}
