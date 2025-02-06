package com.example.aplicacionincidencias.Incidencia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.MenuPrincipal.menutrespuntos;
import com.example.aplicacionincidencias.R;
import com.example.aplicacionincidencias.Usuario.UsuarioHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import gestionincidencias.entidades.EntIncidencia;
import gestionincidencias.entidades.EntUsuario;

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
        IncidenciaHelper ih = new IncidenciaHelper(this, "bbddIncidencias", null, 1);
        Bundle extras = getIntent().getExtras();
        String filtro = null;
        if (extras != null) {
            filtro = extras.getString("filtro");
        }
        ArrayList<EntIncidencia> incidencias = null;
        if (filtro != null) {
            incidencias = ih.obtenerIncidencias(filtro);
            AdaptadorIncidencia adaptadorIncidencia = new AdaptadorIncidencia(this, incidencias.toArray(new EntIncidencia[0]));
            listaIncidencia.setAdapter(adaptadorIncidencia);
        } else {
            incidencias = ih.obtenerIncidencias(null);
            AdaptadorIncidencia adaptadorIncidencia = new AdaptadorIncidencia(this, incidencias.toArray(new EntIncidencia[0]));
            listaIncidencia.setAdapter(adaptadorIncidencia);
        }
        UsuarioHelper uh = new UsuarioHelper(this, "bbddIncidencias", null, 1);
        ArrayList<EntUsuario> usuarios = uh.obtenerUsuarios();
        for (EntIncidencia i : incidencias) {
            for (EntUsuario u : usuarios) {
                if (i.getIdUsuarioCreacion() == u.getCodigoUsuario()) {
                    i.setUsuarioCreacion(u);
                    break;
                }
            }
        }

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