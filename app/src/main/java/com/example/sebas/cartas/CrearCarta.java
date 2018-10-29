package com.example.sebas.cartas;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CrearCarta extends AppCompatActivity {
    private String[] animales = {"Cebra","Hipopótamo","León","Tigre"};
    private String nombre;
    private int altura;
    private int peso;
    private int longitud;
    private int velocidad;
    private int imagen;
    private TextInputLayout etAltura;
    private TextInputLayout etPeso;
    private TextInputLayout etLongitud;
    private TextInputLayout etVelocidad;
    private Carta carta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_carta);
        Spinner spinner = findViewById(R.id.spinner);
        etAltura = findViewById(R.id.etAlt);
        etPeso = findViewById(R.id.etPeso);
        etLongitud = findViewById(R.id.etLong);
        etVelocidad = findViewById(R.id.etVel);

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, animales));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                adapterView.getItemAtPosition(pos);
                if ("Hipopótamo".equals(adapterView.getItemAtPosition(pos))){
                    nombre = (String) adapterView.getItemAtPosition(pos);
                    imagen = R.drawable.hipo;
                }
                if ("Cebra".equals(adapterView.getItemAtPosition(pos))){
                    nombre = (String) adapterView.getItemAtPosition(pos);
                    imagen = R.drawable.cebra;
                }
                if ("León".equals(adapterView.getItemAtPosition(pos))){
                    nombre = (String) adapterView.getItemAtPosition(pos);
                    imagen = R.drawable.leon;
                }
                if ("Tigre".equals(adapterView.getItemAtPosition(pos))){
                    nombre = (String) adapterView.getItemAtPosition(pos);
                    imagen = R.drawable.leon;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });

        Button bt = findViewById(R.id.btAgregar);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altura = Integer.parseInt(etAltura.getEditText().getText().toString());
                peso = Integer.parseInt(etPeso.getEditText().getText().toString());
                longitud = Integer.parseInt(etLongitud.getEditText().getText().toString());
                velocidad = Integer.parseInt(etVelocidad.getEditText().getText().toString());

                carta = new Carta(nombre, altura, peso, longitud, velocidad, imagen);
                Intent i = getIntent();
                i.putExtra("nuevaCarta", carta);
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }
}
