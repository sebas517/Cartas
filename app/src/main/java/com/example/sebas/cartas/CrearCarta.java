package com.example.sebas.cartas;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

public class CrearCarta extends AppCompatActivity {
    private String[] animales = {"Cebra","Hipopótamo","León","Tigre"};
    private String nombre;
    private int altura;
    private int peso;
    private int longitud;
    private int velocidad;
    private int imagen;
    private ImageView image;
    private TextInputLayout etNombre;
    private TextInputLayout etAltura;
    private TextInputLayout etPeso;
    private TextInputLayout etLongitud;
    private TextInputLayout etVelocidad;
    private Carta carta;
    private int c;
    private int id = R.drawable.animal0;
    private final int READ_REQUEST_CODE = 0;
    private Uri imageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_carta);
        etAltura = findViewById(R.id.etAlt);
        etPeso = findViewById(R.id.etPeso);
        etLongitud = findViewById(R.id.etLong);
        etVelocidad = findViewById(R.id.etVel);
        etNombre = findViewById(R.id.etNombre);
        image = findViewById(R.id.imageView2);
        image.setImageResource(id);

        c =0;



        Button bt = findViewById(R.id.btAgregar);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altura = Integer.parseInt(etAltura.getEditText().getText().toString());
                peso = Integer.parseInt(etPeso.getEditText().getText().toString());
                longitud = Integer.parseInt(etLongitud.getEditText().getText().toString());
                velocidad = Integer.parseInt(etVelocidad.getEditText().getText().toString());
                nombre = etNombre.getEditText().getText().toString();
                //imagen = imageUri;

                carta = new Carta(nombre, altura, peso, longitud, velocidad, imageUri);
                Intent i = getIntent();
                i.putExtra("nuevaCarta", carta);
                setResult(RESULT_OK, i);
                finish();
            }
        });

        ImageButton btGaleria = findViewById(R.id.btGaleria);
        btGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                if (gallery.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(gallery, READ_REQUEST_CODE);
                }

            }
        });

        setupToolBar();
    }

    private void setupToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar5);

        if (toolbar == null) return;

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // close this activity as oppose to navigating up

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == READ_REQUEST_CODE){
            imageUri = data.getData();
            image.setImageURI(imageUri);
        }
    }
}
