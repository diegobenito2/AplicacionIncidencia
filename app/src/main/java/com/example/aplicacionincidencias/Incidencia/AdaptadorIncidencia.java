package com.example.aplicacionincidencias.Incidencia;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.example.aplicacionincidencias.R;
import gestionincidencias.entidades.EntIncidencia;

public class AdaptadorIncidencia extends ArrayAdapter<EntIncidencia> {
    private EntIncidencia[] datos;


    public AdaptadorIncidencia(Context c, EntIncidencia[] incidencias) {
        super(c, R.layout.elemento_incidencia, incidencias);
        this.datos = incidencias;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View VIncidencia = mostrado.inflate(R.layout.elemento_incidencia, parent, false);

        TextView txCodigo = VIncidencia.findViewById(R.id.codigoIncidencia);
        TextView txDescripcion = VIncidencia.findViewById(R.id.descripcionIncidencia);


        txCodigo.setText("Código Incidencia: " + datos[position].getCodigoIncidencia());
        txDescripcion.setText("Descripción: " + datos[position].getDescripcion());

        return VIncidencia;
    }
}
