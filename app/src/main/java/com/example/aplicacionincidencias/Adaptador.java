package com.example.aplicacionincidencias;
import com.example.aplicacionincidencias.activitySalas;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class Adaptador extends ArrayAdapter<Sala> {
    private Sala[] datos;
    public Adaptador(Context c, Sala[] salas){
        super(c,R.layout.elemento_sala,salas);
        this.datos=salas;
    }

}
