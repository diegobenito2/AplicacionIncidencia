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
        super(c, R.layout.elemento_usuario);
        this.datos = usuario;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View VUsuario = mostrado.inflate(R.layout.elemento_usuario, parent, false);
        TextView txCodigg = VUsuario.findViewById(R.id.IdCodigoUsuario);
        TextView txNombre = VUsuario.findViewById(R.id.NombreUsuario);
        TextView txCorreo = VUsuario.findViewById(R.id.correoUsuario);
        TextView txTelefono = VUsuario.findViewById(R.id.telefonoUsuario);
        TextView txPassword = VUsuario.findViewById(R.id.passwordUsuario);
        TextView txRolUsuario = VUsuario.findViewById(R.id.rolUsuario);
        txCodigg.setText(datos[position].getCodigoUsuario());
        txNombre.setText(datos[position].getNombre());
        txCorreo.setText(datos[position].getCorreo());
        txTelefono.setText(datos[position].getTelefono());
        txPassword.setText(datos[position].getPassword());
        txRolUsuario.setText(datos[position].getRol());

        return VUsuario;
    }
}
