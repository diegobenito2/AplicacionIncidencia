package com.example.aplicacionincidencias.Elemento;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aplicacionincidencias.R;

import gestionincidencias.entidades.EntElemento;
import gestionincidencias.entidades.EntSala;

public class AdaptadorElemento extends ArrayAdapter<EntElemento> {
    private EntSala[] datos;
    public AdaptadorElemento(Context c, EntSala[] elemento){
        super(c, R.layout.elemento_Elemento);
        this.datos=elemento;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

            LayoutInflater mostrado = LayoutInflater.from(getContext());
            View VElemento = mostrado.inflate(R.layout.elemento_Elemento,parent,false);


        TextView txCodigo = VElemento.findViewById(R.id.codigoElemento);
        TextView txNombre = VElemento.findViewById(R.id.nombreElemento);

        txCodigo.setText(String.valueOf(datos[position].getCodigoSala()));
        txNombre.setText(datos[position].getNombre());
        return VElemento;
    }

}
