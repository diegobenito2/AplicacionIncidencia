package com.example.aplicacionincidencias.Rol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aplicacionincidencias.R;
import gestionincidencias.entidades.EntRol;

public class AdaptadorRol extends ArrayAdapter<EntRol> {
    private EntRol[] datos;
    public AdaptadorRol(Context c, EntRol[] rol){
        super(c, R.layout.elemento_rol,rol);
        this.datos=rol;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View VRol = mostrado.inflate(R.layout.elemento_rol,parent,false);


        TextView txCodigo = VRol.findViewById(R.id.codigoRol);
        TextView txNombre = VRol.findViewById(R.id.nombreRol);



        txCodigo.setText("Código: "+datos[position].getCodigo());
        txNombre.setText("Nombre: "+datos[position].getNombre());
        return VRol;
    }
}
