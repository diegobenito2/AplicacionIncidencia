package com.example.aplicacionincidencias.Incidencia;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.R;

import gestionincidencias.entidades.Incidencia;

public class activityIncidencia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_incidencia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Incidencia[] incidencias = new Incidencia[50];
        for (int i = 0; i < incidencias.length; i++) {
            incidencias[i] = new Incidencia(i+1,"Hola",(i+2),(i+3),(i+4));
        }
        ListView listaIncidencia = (ListView) findViewById(R.id.ListaIncidencia);
        AdaptadorIncidencia adaptadorIncidencia = new AdaptadorIncidencia(this, incidencias);
        listaIncidencia.setAdapter(adaptadorIncidencia);

    }
}