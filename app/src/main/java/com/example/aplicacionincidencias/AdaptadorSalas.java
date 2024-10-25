package com.example.aplicacionincidencias;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import gestionincidencias.entidades.Sala;

public class AdaptadorSalas extends ArrayAdapter<Sala> {
    private Sala[] datos;
    public AdaptadorSalas(Context c, Sala[] salas){
        super(c,R.layout.elemento_sala,salas);
        this.datos=salas;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

            LayoutInflater mostrado = LayoutInflater.from(getContext());
            View VSala = mostrado.inflate(R.layout.elemento_sala,parent,false);


        TextView txCodigo = VSala.findViewById(R.id.codigoSala);
        TextView txNombre = VSala.findViewById(R.id.nombreSala);

        txCodigo.setText(String.valueOf(datos[position].getCodigoSala()));
        txNombre.setText(datos[position].getNombre());
        return VSala;
    }

}
