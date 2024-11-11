package com.example.aplicacionincidencias.Elemento;

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

import com.example.aplicacionincidencias.R;
import com.example.aplicacionincidencias.Sala.Activity_Info_Sala;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntElemento;
import gestionincidencias.entidades.EntSala;

public class activityElemento extends AppCompatActivity {
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
        AdaptadorElemento adaptadorElemento = new AdaptadorElemento(this, GestionIncidencias.getArElementos().toArray(new EntElemento[0]));
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
                intentInfoElemento.putExtra("descripcion", elementoSeleccionado.getDescripcion());
                intentInfoElemento.putExtra("tipo", elementoSeleccionado.getIdTipo());


                // Iniciar la actividad de información de la sala
                startActivity(intentInfoElemento);
            }
        });


    }


}



































