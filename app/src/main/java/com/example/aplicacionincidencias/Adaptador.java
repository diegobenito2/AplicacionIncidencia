package com.example.aplicacionincidencias;
import com.example.aplicacionincidencias.activitySalas;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class Adaptador extends ArrayAdapter<Sala> {
    private Sala[] datos;
    public Adaptador(Context c, Sala[] salas){
        super(c,R.layout.elemento_sala,salas);
        this.datos=salas;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View vSala = convertView;
        if (vSala ==null){
            LayoutInflater mostrado = LayoutInflater.from(getContext());
            View VSala = mostrado.inflate(R.layout.elemento_sala,parent,false);
        }

        TextView txCodigo = vSala.findViewById(R.id.codigoSala);
        TextView txNombre = vSala.findViewById(R.id.nombreSala);

        txCodigo.setText(String.valueOf(datos[positon].getCodigoSala()));
        txNombre.setText(datos[position].getNombre());
        return VSala;
    }

}
