package com.example.aplicacionincidencias.Incidencia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aplicacionincidencias.R;

import gestionincidencias.entidades.Incidencia;

public class AdaptadorIncidencia extends ArrayAdapter<Incidencia> {
    private Incidencia[] datos;

    public AdaptadorIncidencia(Context c, Incidencia[] incidencias) {
        super(c, R.layout.elemento_incidencia, incidencias);
        this.datos = incidencias;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View VIncidencia = mostrado.inflate(R.layout.elemento_incidencia, parent, false);

        TextView txCodigo = VIncidencia.findViewById(R.id.codigoIncidencia);
        TextView txDescripcion = VIncidencia.findViewById(R.id.descripcionIncidencia);
        TextView txIdElemento = VIncidencia.findViewById(R.id.idElemento);
        TextView txIdUsuarioCreacion = VIncidencia.findViewById(R.id.IdUsuarioCreacion);
        TextView txIdResponsable = VIncidencia.findViewById(R.id.IdResponsable);

        txCodigo.setText(String.valueOf(datos[position].getCodigoIncidencia()));
        txDescripcion.setText(datos[position].getDescripcion());
        txIdElemento.setText(String.valueOf(datos[position].getIdElemento()));
        txIdUsuarioCreacion.setText(String.valueOf(datos[position].getIdUsuarioCreacion()));
        txIdResponsable.setText(String.valueOf(datos[position].getIdResponsable()));
        return VIncidencia;
    }
}
