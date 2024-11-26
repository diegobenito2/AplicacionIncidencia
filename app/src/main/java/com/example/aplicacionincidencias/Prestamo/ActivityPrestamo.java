package com.example.aplicacionincidencias.Prestamo;

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
import com.example.aplicacionincidencias.Sala.Activity_Info_Sala;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntPrestamo;

public class ActivityPrestamo extends menutrespuntos {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prestamo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView ListaPrestamo = (ListView) findViewById(R.id.ListaPrestamo);
        AdaptadorPrestamo adaptadorPrestamo = new AdaptadorPrestamo(this, GestionIncidencias.getArPrestamos().toArray(new EntPrestamo[0]));
        ListaPrestamo.setAdapter(adaptadorPrestamo);

        ListaPrestamo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EntPrestamo PrestamoSeleccionado = (EntPrestamo) adapterView.getItemAtPosition(i);

                Intent intentPrestamo = new Intent(view.getContext(), Activity_Info_Prestamo.class);

                intentPrestamo.putExtra("codigoPrestamo", PrestamoSeleccionado.getCodigoPrestamo());

                startActivity(intentPrestamo);

            }
        });
        FloatingActionButton AñadirPrestamo = findViewById(R.id.fabAñadirPrestamo);
        AñadirPrestamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear una nueva Intent para abrir la actividad de información de la sala
                Intent intentInfoPrestamo = new Intent(view.getContext(), Activity_Info_Prestamo.class);

                // Pasar datos a la nueva actividad mediante el uso de 'putExtra'. Estos datos corresponden a la sala seleccionada.

                intentInfoPrestamo.putExtra("codigo", 0);
                intentInfoPrestamo.putExtra("descripcion","");
                startActivity(intentInfoPrestamo);
            }
        });
    }



}