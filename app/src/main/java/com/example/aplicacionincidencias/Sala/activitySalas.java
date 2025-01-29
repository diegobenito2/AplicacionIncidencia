package com.example.aplicacionincidencias.Sala;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.MenuPrincipal.menutrespuntos;
import com.example.aplicacionincidencias.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntSala;

public class activitySalas extends menutrespuntos{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_salas);
        guardaActividad(getSharedPreferences("datos",MODE_PRIVATE),activitySalas.class.toString());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener la referencia al ListView con el ID 'ListaSalas' en el layout de la actividad
        ListView listaSalas = (ListView) findViewById(R.id.ListaSalas);
        //Para que coja los datos de la base de datos.
        SalaHelper sh = new SalaHelper(this, "bbddIncidencias", null, 1);
        ArrayList<EntSala> salas = sh.obtenerSalas();

        // Crear un adaptador personalizado 'AdaptadorSalas' pasando el contexto actual y el array de salas (convertido a un arreglo de EntSala)
        AdaptadorSalas adaptadorSalas = new AdaptadorSalas(this, salas.toArray(new EntSala[0]));

        // Establecer el adaptador al ListView para que cargue los datos de las salas en la interfaz
        listaSalas.setAdapter(adaptadorSalas);

        // Establecer un listener para detectar cuando un usuario hace clic en un ítem de la lista
        listaSalas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Obtener la sala seleccionada en la posición clickeada
                EntSala salaSeleccionado = (EntSala) adapterView.getItemAtPosition(position);


                // Crear una nueva Intent para abrir la actividad de información de la sala
                Intent intentInfoSala = new Intent(view.getContext(), Activity_Info_Sala.class);

                // Pasar datos a la nueva actividad mediante el uso de 'putExtra'. Estos datos corresponden a la sala seleccionada.

                intentInfoSala.putExtra("codigo", salaSeleccionado.getCodigoSala());
                intentInfoSala.putExtra("nombre", salaSeleccionado.getNombre());


                // Iniciar la actividad de información de la sala
                startActivity(intentInfoSala);
            }
        });

        FloatingActionButton AñadirSala = findViewById(R.id.fabAñadirSala);
        AñadirSala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear una nueva Intent para abrir la actividad de información de la sala
                Intent intentInfoSala = new Intent(view.getContext(), Activity_Info_Sala.class);

                // Pasar datos a la nueva actividad mediante el uso de 'putExtra'. Estos datos corresponden a la sala seleccionada.

                intentInfoSala.putExtra("codigo", 0);
                intentInfoSala.putExtra("nombre","");
                startActivity(intentInfoSala);
            }
        });
    }

}

















