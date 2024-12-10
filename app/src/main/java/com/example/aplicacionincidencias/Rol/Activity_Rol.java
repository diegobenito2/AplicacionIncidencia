package com.example.aplicacionincidencias.Rol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.MenuPrincipal.menutrespuntos;
import com.example.aplicacionincidencias.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntRol;

public class Activity_Rol extends menutrespuntos {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rol);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView ListaRol = (ListView) findViewById(R.id.ListaRol);
        AdaptadorRol adaptadorRol = new AdaptadorRol(this, GestionIncidencias.getArRoles().toArray(new EntRol[0]));
        ListaRol.setAdapter(adaptadorRol);

        ListaRol.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EntRol RolSeleccionado = (EntRol) adapterView.getItemAtPosition(i);

                Intent intentRol = new Intent(view.getContext(), Activity_Info_Rol.class);

                intentRol.putExtra("codigo", RolSeleccionado.getCodigo());

                startActivity(intentRol);

            }
        });
        FloatingActionButton AñadirPrestamo = findViewById(R.id.fabAñadirRol);
        AñadirPrestamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear una nueva Intent para abrir la actividad de información de la sala
                Intent intentInfoRol = new Intent(view.getContext(), Activity_Info_Rol.class);

                // Pasar datos a la nueva actividad mediante el uso de 'putExtra'. Estos datos corresponden a la sala seleccionada.

                intentInfoRol.putExtra("codigo", 0);
                intentInfoRol.putExtra("nombre","");
                startActivity(intentInfoRol);
            }
        });
    }
}