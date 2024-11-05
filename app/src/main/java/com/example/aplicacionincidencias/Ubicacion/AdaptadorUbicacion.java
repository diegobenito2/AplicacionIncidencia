package com.example.aplicacionincidencias.Ubicacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aplicacionincidencias.R;

import gestionincidencias.entidades.EntTipo;
import gestionincidencias.entidades.EntUbicacion;


public class AdaptadorUbicacion extends ArrayAdapter<EntUbicacion> {
    private EntUbicacion[] datos;

    public AdaptadorUbicacion(Context c, EntUbicacion[] ubicacion) {
        super(c, R.layout.elemento_ubicacion);
        this.datos = ubicacion;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View Vubicacion = mostrado.inflate(R.layout.elemento_ubicacion, parent, false);


        TextView txCodigoUbicacion = Vubicacion.findViewById(R.id.codigoUbicacion);
        TextView txCodigoElementoUbicacion = Vubicacion.findViewById(R.id.codigoElementoUbicacion);
        TextView txCodigoSalaUbicacion = Vubicacion.findViewById(R.id.codigoSalaUbicacion);
        TextView txCodigoUbicacionFechaInicio = Vubicacion.findViewById(R.id.codigoUbicacionFechaInicio);
        TextView txCodigoUbicacionFechaFin = Vubicacion.findViewById(R.id.codigoUbicacionFechaFin);

        txCodigoUbicacion.setText(String.valueOf(datos[position].getCodigoUbicacion()));
        txCodigoElementoUbicacion.setText(datos[position].getIdElemento());
        txCodigoSalaUbicacion.setText(datos[position].getIdSala());
        txCodigoUbicacionFechaInicio.setText(String.valueOf(datos[position].getFechaInicio()));
        txCodigoUbicacionFechaFin.setText(String.valueOf(datos[position].getFechaFin()));
        return Vubicacion;
    }
}
