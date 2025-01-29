package com.example.aplicacionincidencias.Usuario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aplicacionincidencias.R;

import gestionincidencias.entidades.EntElemento;
import gestionincidencias.entidades.EntUsuario;

public class AdaptadorUsuario extends ArrayAdapter<EntUsuario> {
    private EntUsuario[] datos;

    public AdaptadorUsuario(Context c, EntUsuario[] usuario) {
        super(c, R.layout.elemento_usuario, usuario);
        this.datos = usuario;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View VUsuario = mostrado.inflate(R.layout.elemento_usuario, parent, false);
        TextView txCodigg = VUsuario.findViewById(R.id.IdCodigoUsuario);
        TextView txNombre = VUsuario.findViewById(R.id.NombreUsuario);

        txCodigg.setText("Codigo: " + datos[position].getCodigoUsuario());
        txNombre.setText("Nombre: " + datos[position].getNombre());


        return VUsuario;
    }
}
