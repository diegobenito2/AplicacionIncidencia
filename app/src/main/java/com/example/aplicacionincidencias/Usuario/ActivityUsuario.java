package com.example.aplicacionincidencias.Usuario;

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
import com.example.aplicacionincidencias.Ubicacion.Activity_Info_Ubicacion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntUbicacion;
import gestionincidencias.entidades.EntUsuario;

public class ActivityUsuario extends menutrespuntos {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView ListaUsuario = (ListView) findViewById(R.id.ListaUsuario);
        AdaptadorUsuario adaptadorUsuario = new AdaptadorUsuario(this, GestionIncidencias.getArUsuarios().toArray(new EntUsuario[0]));
        ListaUsuario.setAdapter(adaptadorUsuario);
        ListaUsuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Obtener la sala seleccionada en la posición clickeada
                EntUsuario elementoSeleccionado = (EntUsuario) adapterView.getItemAtPosition(position);


                // Crear una nueva Intent para abrir la actividad de información de la sala
                Intent intentInfoUsuario = new Intent(view.getContext(), Activity_Info_Usuario.class);

                // Pasar datos a la nueva actividad mediante el uso de 'putExtra'. Estos datos corresponden a la sala seleccionada.

                intentInfoUsuario.putExtra("codigo", elementoSeleccionado.getCodigoUsuario());
                intentInfoUsuario.putExtra("nombre", elementoSeleccionado.getNombre());


                // Iniciar la actividad de información de la sala
                startActivity(intentInfoUsuario);
            }
        });
        FloatingActionButton AñadirUsuario = findViewById(R.id.fabAñadirUsuario);
        AñadirUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear una nueva Intent para abrir la actividad de información de la sala
                Intent intentInfoUsuario = new Intent(view.getContext(), Activity_Info_Usuario.class);

                // Pasar datos a la nueva actividad mediante el uso de 'putExtra'. Estos datos corresponden a la sala seleccionada.

                intentInfoUsuario.putExtra("codigo", 0);
                intentInfoUsuario.putExtra("nombre","");
                startActivity(intentInfoUsuario);
            }
        });
    }
}