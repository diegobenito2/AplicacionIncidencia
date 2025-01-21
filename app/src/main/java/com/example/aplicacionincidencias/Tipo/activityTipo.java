package com.example.aplicacionincidencias.Tipo;

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
import com.example.aplicacionincidencias.Sala.Activity_Info_Sala;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntPrestamo;
import gestionincidencias.entidades.EntTipo;

public class activityTipo extends menutrespuntos {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tipo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listaTipo = (ListView) findViewById(R.id.ListaTipo);
        TipoHelper th = new TipoHelper(this, "bbddIncidencias", null, 1);
        ArrayList<EntTipo> tipos = th.obtenerTipos();
        AdaptadorTipo adaptadorTipo = new AdaptadorTipo(this, tipos.toArray(new EntTipo[0]));
        listaTipo.setAdapter(adaptadorTipo);
        listaTipo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EntTipo tipoSelecccionado = (EntTipo) adapterView.getItemAtPosition(i);

                Intent intentInfoTipo = new Intent(view.getContext(), Activity_Info_Tipo.class);
                intentInfoTipo.putExtra("codigo", tipoSelecccionado.getCodigoTipo());
                intentInfoTipo.putExtra("nombre", tipoSelecccionado.getNombre());
                startActivity(intentInfoTipo);
            }
        });
        FloatingActionButton AñadirTipo = findViewById(R.id.fabAñadirTipo);
        AñadirTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear una nueva Intent para abrir la actividad de información de la sala
                Intent intentInfoTipo = new Intent(view.getContext(), Activity_Info_Tipo.class);

                // Pasar datos a la nueva actividad mediante el uso de 'putExtra'. Estos datos corresponden a la sala seleccionada.

                intentInfoTipo.putExtra("codigo", 0);
                intentInfoTipo.putExtra("nombre","");
                startActivity(intentInfoTipo);
            }
        });


    }
}