package com.example.aplicacionincidencias.Incidencia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.MenuPrincipal.menutrespuntos;
import com.example.aplicacionincidencias.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntIncidencia;

public class activityIncidencia extends menutrespuntos {

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
        ListView listaIncidencia = findViewById(R.id.ListaIncidencia);
        AdaptadorIncidencia adaptadorIncidencia = new AdaptadorIncidencia(this, GestionIncidencias.getArIncidencias().toArray(new EntIncidencia[0]));
        listaIncidencia.setAdapter(adaptadorIncidencia);

        listaIncidencia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EntIncidencia IncidenciaSeleccionada = (EntIncidencia) adapterView.getItemAtPosition(i);

                Intent intentInfoIncidencia = new Intent(view.getContext(), Activity_Info_Incidencia.class);

                intentInfoIncidencia.putExtra("codigoIncidencia", IncidenciaSeleccionada.getCodigoIncidencia());
                intentInfoIncidencia.putExtra("descripcionIncidencia", IncidenciaSeleccionada.getDescripcion());

                startActivity(intentInfoIncidencia);
            }
        });
        FloatingActionButton AñadirIncidencia = findViewById(R.id.fabAñadirIncidencia);
        AñadirIncidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear una nueva Intent para abrir la actividad de información de la sala
                Intent intentInfoIncidencia = new Intent(view.getContext(), Activity_Info_Incidencia.class);

                // Pasar datos a la nueva actividad mediante el uso de 'putExtra'. Estos datos corresponden a la sala seleccionada.

                intentInfoIncidencia.putExtra("codigoIncidencia", 0);
                intentInfoIncidencia.putExtra("descripcionIncidencia", "");
                startActivity(intentInfoIncidencia);
            }
        });
    }
}