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

import com.example.aplicacionincidencias.Elemento.ElementoHelper;
import com.example.aplicacionincidencias.MenuPrincipal.menutrespuntos;
import com.example.aplicacionincidencias.R;
import com.example.aplicacionincidencias.Usuario.UsuarioHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import gestionincidencias.entidades.EntElemento;
import gestionincidencias.entidades.EntPrestamo;
import gestionincidencias.entidades.EntUsuario;

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
        PrestamoHelper ph = new PrestamoHelper(this, "bbddIncidencias", null, 1);
        ArrayList<EntPrestamo> prestamos = ph.obtenerPrestamos();
        AdaptadorPrestamo adaptadorPrestamo = new AdaptadorPrestamo(this, prestamos.toArray(new EntPrestamo[0]));
        ElementoHelper eh = new ElementoHelper(this, "bbddIncidencias", null, 1);
        ArrayList<EntElemento> elementos = eh.obtenerElementos();
        UsuarioHelper uh = new UsuarioHelper(this, "bbddIncidencias", null, 1);
        ArrayList<EntUsuario> usuarios = uh.obtenerUsuarios();
        for (EntPrestamo p : prestamos) {
            for (EntElemento e : elementos) {
                if (p.getIdElemento() == e.getCodigoElemento()) {
                    p.setElemento(e);
                }
            }
            for (EntUsuario u : usuarios) {
                if (p.getIdUsuario() == u.getCodigoUsuario()) {
                    p.setUsuario(u);
                }
            }
        }

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

                intentInfoPrestamo.putExtra("codigoPrestamo", 0);
                startActivity(intentInfoPrestamo);
            }
        });
    }


}