package com.example.aplicacionincidencias.Tipo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aplicacionincidencias.R;

import gestionincidencias.entidades.Tipo;


public class AdaptadorTipo extends ArrayAdapter<Tipo> {
    private Tipo[] datos;

    public AdaptadorTipo(Context c, Tipo[] tipo) {
        super(c, R.layout.elemento_tipo, tipo);
        this.datos = tipo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View Vtipo = mostrado.inflate(R.layout.elemento_tipo, parent, false);


        TextView txCodigo = Vtipo.findViewById(R.id.codigoTipo);
        TextView txNombre = Vtipo.findViewById(R.id.nombreTipo);

        txCodigo.setText(String.valueOf(datos[position].getCodigoTipo()));
        txNombre.setText(datos[position].getNombre());
        return Vtipo;
    }
}
