package com.example.aplicacionincidencias.Prestamo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aplicacionincidencias.R;

import gestionincidencias.entidades.EntPrestamo;

public class AdaptadorPrestamo extends ArrayAdapter<EntPrestamo> {
    private EntPrestamo[] datos;

    public AdaptadorPrestamo(Context c, EntPrestamo[] prestamo) {
        super(c, R.layout.elemento_prestamo, prestamo);
        this.datos = prestamo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View VPrestamo = mostrado.inflate(R.layout.elemento_prestamo, parent, false);

        TextView txCodigo = VPrestamo.findViewById(R.id.CodigoPrestamo);
        TextView txIdUsuario = VPrestamo.findViewById(R.id.idUsuarioPrestamo);
        TextView txIdElemento = VPrestamo.findViewById(R.id.idElementoPrestamo);

        txCodigo.setText("Nº: " + datos[position].getCodigoPrestamo());
        String usuario = "--";
        if (datos[position].getUsuario() != null) {
            usuario = datos[position].getUsuario().getNombre();
        }
        String elemento = "--";
        if (datos[position].getElemento() != null) {
            elemento = datos[position].getElemento().getNombre();
        }
        txIdUsuario.setText("Nombre Usuario: " + usuario);
        txIdElemento.setText("Nombre Elemento: " + elemento);


        return VPrestamo;
    }
}
