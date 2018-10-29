package com.example.sebas.cartas;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    public interface OnItemClickListener{
        public void onClick(ViewHolder holder, int id);
    }

    private OnItemClickListener oyente;
    private List<Carta> cartas;

    public Adaptador(OnItemClickListener oyente, List<Carta> cartas) {
        this.oyente = oyente;
        this.cartas = cartas;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Carta carta = cartas.get(i);

        viewHolder.tvNombre.setText(carta.getNombre());
        viewHolder.tvAltura.setText(String.valueOf(carta.getAltura()));
        viewHolder.tvPeso.setText(String.valueOf(carta.getPeso()));
        viewHolder.tvLongitud.setText(String.valueOf(carta.getLongitud()));
        viewHolder.tvVelocidad.setText(String.valueOf(carta.getVelocidad()));
        viewHolder.img.setImageResource(carta.getImagen());


    }

    @Override
    public int getItemCount() {
        if (cartas == null){
            return 0;
        }else {
            return cartas.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public TextView tvNombre;
        public TextView tvAltura;
        public TextView tvPeso;
        public TextView tvLongitud;
        public TextView tvVelocidad;
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvAltura = itemView.findViewById(R.id.tvAltura);
            tvPeso = itemView.findViewById(R.id.tvPeso);
            tvLongitud = itemView.findViewById(R.id.tvLongitud);
            tvVelocidad = itemView.findViewById(R.id.tvVelocidad);
            img = itemView.findViewById(R.id.imgCarta);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id = getAdapterPosition();
            oyente.onClick(this, id);
        }

    }
}
