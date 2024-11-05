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
import java.text.SimpleDateFormat;
import java.util.Date;
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
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy ");
        String fechaFormateada = formato.format(fechaActual);
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View VIncidencia = mostrado.inflate(R.layout.elemento_incidencia, parent, false);

        TextView txCodigo = VIncidencia.findViewById(R.id.codigoIncidencia);
        TextView txDescripcion = VIncidencia.findViewById(R.id.descripcionIncidencia);
        TextView txIdElemento = VIncidencia.findViewById(R.id.idElemento);
        TextView txIdUsuarioCreacion = VIncidencia.findViewById(R.id.IdUsuarioCreacion);
        TextView txIdResponsable = VIncidencia.findViewById(R.id.IdResponsable);
        TextView txFechaCreacion = VIncidencia.findViewById(R.id.FechaCreacion);

        txCodigo.setText("Código Incidencia: " + String.valueOf(datos[position].getCodigoIncidencia()));
        txDescripcion.setText("Descripción: " + datos[position].getDescripcion());
        txIdElemento.setText("Id Elemento: " + String.valueOf(datos[position].getIdElemento()));
        txIdUsuarioCreacion.setText("Id Usuario Creación: " + String.valueOf(datos[position].getIdUsuarioCreacion()));
        txIdResponsable.setText("Id Responsable: " + String.valueOf(datos[position]));
        txFechaCreacion.setText("Fecha Creación: " + String.valueOf(fechaFormateada));
        return VIncidencia;
    }
}
