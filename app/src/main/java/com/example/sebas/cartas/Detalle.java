package com.example.sebas.cartas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Detalle extends AppCompatActivity {
    private TextView tvNombre;
    private TextView tvAltura;
    private TextView tvPeso;
    private TextView tvLongitud;
    private TextView tvVelocidad;
    private ImageView img;
    private Carta c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        tvNombre = findViewById(R.id.tvNombreDet);
        tvAltura = findViewById(R.id.tvAlturaDet);
        tvPeso = findViewById(R.id.tvPesoDet);
        tvLongitud = findViewById(R.id.tvLongDet);
        tvVelocidad = findViewById(R.id.tvVelDet);
        img = findViewById(R.id.ivDet);

        Bundle extras = getIntent().getExtras();
        c = extras.getParcelable("carta");

        tvNombre.setText(c.getNombre());
        tvAltura.setText(String.valueOf(c.getAltura()));
        tvPeso.setText(String.valueOf(c.getPeso()));
        tvLongitud.setText(String.valueOf(c.getLongitud()));
        tvVelocidad.setText(String.valueOf(c.getVelocidad()));
        img.setImageResource(c.getImagen());



    }
}
