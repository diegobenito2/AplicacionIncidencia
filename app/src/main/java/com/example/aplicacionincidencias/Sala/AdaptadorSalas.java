package com.example.aplicacionincidencias.Sala;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aplicacionincidencias.R;

import gestionincidencias.entidades.EntSala;

public class AdaptadorSalas extends ArrayAdapter<EntSala> {
    private EntSala[] datos;

    public AdaptadorSalas(Context c, EntSala[] salas) {
        super(c, R.layout.elemento_sala, salas);
        this.datos = salas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View VSala = mostrado.inflate(R.layout.elemento_sala, parent, false);


        TextView txCodigo = VSala.findViewById(R.id.codigoSala);
        TextView txNombre = VSala.findViewById(R.id.nombreSala);

        txCodigo.setText("Nº Sala " + String.valueOf(datos[position].getCodigoSala()));
        txNombre.setText("Nombre Sala "+datos[position].getNombre());
        return VSala;
    }

}
