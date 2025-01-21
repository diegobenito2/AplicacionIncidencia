package com.example.aplicacionincidencias.Elemento;

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

import java.util.ArrayList;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntElemento;

public class activityElemento extends menutrespuntos {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_elemento);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView listaElemento = (ListView) findViewById(R.id.ListaElemento);
        ElementoHelper eh = new ElementoHelper(this, "bbddIncidencias", null, 1);
        ArrayList<EntElemento> elementos = eh.obtenerElementos();
        AdaptadorElemento adaptadorElemento = new AdaptadorElemento(this, elementos.toArray(new EntElemento[0]));
        listaElemento.setAdapter(adaptadorElemento);

        // Establecer un listener para detectar cuando un usuario hace clic en un ítem de la lista
        listaElemento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Obtener la sala seleccionada en la posición clickeada
                EntElemento elementoSeleccionado = (EntElemento) adapterView.getItemAtPosition(position);


                // Crear una nueva Intent para abrir la actividad de información de la sala
                Intent intentInfoElemento = new Intent(view.getContext(), Activity_Info_Elemento.class);

                // Pasar datos a la nueva actividad mediante el uso de 'putExtra'. Estos datos corresponden a la sala seleccionada.

                intentInfoElemento.putExtra("codigo", elementoSeleccionado.getCodigoElemento());
                intentInfoElemento.putExtra("nombre", elementoSeleccionado.getNombre());


                // Iniciar la actividad de información de la sala
                startActivity(intentInfoElemento);
            }
        });
        FloatingActionButton AñadirElemento = findViewById(R.id.fabAñadirElemento);
        AñadirElemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear una nueva Intent para abrir la actividad de información de la sala
                Intent intentInfoElemento = new Intent(view.getContext(), Activity_Info_Elemento.class);

                // Pasar datos a la nueva actividad mediante el uso de 'putExtra'. Estos datos corresponden a la sala seleccionada.

                intentInfoElemento.putExtra("codigo", 0);
                intentInfoElemento.putExtra("nombre","");
                startActivity(intentInfoElemento);
            }
        });


    }


}



































