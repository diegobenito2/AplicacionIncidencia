package com.example.aplicacionincidencias.Ubicacion;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.R;
import com.example.aplicacionincidencias.Tipo.AdaptadorTipo;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntTipo;
import gestionincidencias.entidades.EntUbicacion;

public class activityUbicacion extends AppCompatActivity {

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
        AdaptadorUbicacion adaptadorUbicacion = new AdaptadorUbicacion(this, GestionIncidencias.getArUbicaciones().toArray(new EntUbicacion[0]));
        listaUbicaciones.setAdapter(adaptadorUbicacion);
    }
}