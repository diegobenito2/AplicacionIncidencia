package com.example.aplicacionincidencias.Ubicacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.Elemento.Activity_Info_Elemento;
import com.example.aplicacionincidencias.Elemento.activityElemento;
import com.example.aplicacionincidencias.MenuPrincipal.menutrespuntos;
import com.example.aplicacionincidencias.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntElemento;
import gestionincidencias.entidades.EntUbicacion;

public class activityUbicacion extends menutrespuntos {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ubicacion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listaUbicaciones= (ListView) findViewById(R.id.ListaUbicacion);
        UbicacionHelper ubih = new UbicacionHelper(this, "bbddIncidencias", null, 1);
        ArrayList<EntUbicacion> ubicaciones = ubih.obtenerUbicaciones();
        AdaptadorUbicacion adaptadorUbicacion = new AdaptadorUbicacion(this, ubicaciones.toArray(new EntUbicacion[0]));
        listaUbicaciones.setAdapter(adaptadorUbicacion);
        listaUbicaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Obtener la sala seleccionada en la posición clickeada
                EntUbicacion elementoSeleccionado = (EntUbicacion) adapterView.getItemAtPosition(position);


                // Crear una nueva Intent para abrir la actividad de información de la sala
                Intent intentInfoUbicacion = new Intent(view.getContext(), Activity_Info_Ubicacion.class);

                // Pasar datos a la nueva actividad mediante el uso de 'putExtra'. Estos datos corresponden a la sala seleccionada.

                intentInfoUbicacion.putExtra("codigo", elementoSeleccionado.getCodigoUbicacion());


                // Iniciar la actividad de información de la sala
                startActivity(intentInfoUbicacion);
            }
        });
        FloatingActionButton AñadirUbicacion = findViewById(R.id.fabAñadirUbicacion);
        AñadirUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear una nueva Intent para abrir la actividad de información de la sala
                Intent intentInfoUbicacion = new Intent(view.getContext(), Activity_Info_Ubicacion.class);

                // Pasar datos a la nueva actividad mediante el uso de 'putExtra'. Estos datos corresponden a la sala seleccionada.

                intentInfoUbicacion.putExtra("codigo", 0);
                intentInfoUbicacion.putExtra("sala","");
                startActivity(intentInfoUbicacion);
            }
        });
    }
}