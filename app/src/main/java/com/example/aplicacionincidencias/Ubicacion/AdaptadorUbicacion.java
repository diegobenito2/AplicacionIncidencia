package com.example.aplicacionincidencias.Ubicacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aplicacionincidencias.R;

import gestionincidencias.entidades.EntUbicacion;


public class AdaptadorUbicacion extends ArrayAdapter<EntUbicacion> {
    private EntUbicacion[] datos;

    public AdaptadorUbicacion(Context c, EntUbicacion[] ubicacion) {
        super(c, R.layout.elemento_ubicacion, ubicacion);
        this.datos = ubicacion;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View Vubicacion = mostrado.inflate(R.layout.elemento_ubicacion, parent, false);


        TextView txCodigoUbicacion = Vubicacion.findViewById(R.id.codigoUbicacion);
        TextView txSalaUbicacion = Vubicacion.findViewById(R.id.SalaUbicacion);


        txCodigoUbicacion.setText("Codigo Ubicación:" + datos[position].getCodigoUbicacion());
        txSalaUbicacion.setText("Descripción: " + datos[position].getDescripcion());

        return Vubicacion;
    }
}
