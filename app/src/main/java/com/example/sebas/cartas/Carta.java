package com.example.sebas.cartas;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Carta implements Parcelable {
    private String nombre;
    private int altura;
    private int peso;
    private int longitud;
    private int velocidad;
    private Uri imagen;

    public Carta(String nombre, int altura, int peso, int longitud, int velocidad, Uri imagen) {
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
        this.longitud = longitud;
        this.velocidad = velocidad;
        this.imagen = imagen;
    }

    protected Carta(Parcel in) {
        nombre = in.readString();
        altura = in.readInt();
        peso = in.readInt();
        longitud = in.readInt();
        velocidad = in.readInt();
        imagen = (Uri) in.readValue(Uri.class.getClassLoader());
    }

    public static final Creator<Carta> CREATOR = new Creator<Carta>() {
        @Override
        public Carta createFromParcel(Parcel in) {
            return new Carta(in);
        }

        @Override
        public Carta[] newArray(int size) {
            return new Carta[size];
        }
    };

    @Override
    public String toString() {
        return "Carta{" +
                "nombre='" + nombre + '\'' +
                ", altura=" + altura +
                ", peso=" + peso +
                ", longitud=" + longitud +
                ", velocidad=" + velocidad +
                ", imagen=" + imagen +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public Uri getImagen() {
        return imagen;
    }

    public void setImagen(Uri imagen) {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeInt(altura);
        parcel.writeInt(peso);
        parcel.writeInt(longitud);
        parcel.writeInt(velocidad);
        parcel.writeValue(imagen);

    }
}
