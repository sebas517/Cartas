package com.example.sebas.cartas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Adaptador adaptador;
    public List<Carta> cartas = new ArrayList<>();
    private final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CrearCarta.class);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        crearRecicler();


    }

    private void crearRecicler(){
        Adaptador.OnItemClickListener oyente = new Adaptador.OnItemClickListener() {
            @Override
            public void onClick(Adaptador.ViewHolder holder, int id) {
                for (Carta c : cartas) {
                    if (c.getNombre().equals(holder.tvNombre.getText().toString())){
                        Intent i = new Intent(MainActivity.this, Detalle.class);
                        i.putExtra("carta", c);
                        startActivityForResult(i, REQUEST_CODE);
                    }
                }
            }
        };
        RecyclerView rv = findViewById(R.id.reciclerView);
        adaptador = new Adaptador(oyente, cartas);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adaptador);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //Crear carta
            Intent i = new Intent(MainActivity.this, CrearCarta.class);
            startActivityForResult(i, REQUEST_CODE);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.help){
            Intent i = new Intent(MainActivity.this, ayuda.class);
            startActivityForResult(i, REQUEST_CODE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Carta nuevaCarta = data.getParcelableExtra("nuevaCarta");
                    System.out.println("nuebvacarta: " + nuevaCarta.toString());
                    cartas.add(nuevaCarta);
                    adaptador.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle estado) {
        if (cartas.size() > 0){
            System.out.println("guardamos ------ " + cartas.toString());
            estado.putSerializable("cartas", (Serializable) cartas);
            adaptador.notifyDataSetChanged();
        }
        super.onSaveInstanceState(estado);
    }

    @Override
    protected void onRestoreInstanceState(Bundle guardado) {
        if (guardado != null){
            cartas = (List<Carta>) guardado.getSerializable("cartas");
            crearRecicler();
            adaptador.notifyDataSetChanged();
        }
        super.onRestoreInstanceState(guardado);
    }

    @Override
    protected void onResume() {
        super.onResume();
        crearRecicler();
        adaptador.notifyDataSetChanged();
    }
}
