package com.example.aplicacionincidencias.Prestamo;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.R;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntPrestamo;

public class ActivityPrestamo extends AppCompatActivity {

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
        ListView ListaPrestamo =(ListView) findViewById(R.id.ListaPrestamo);
        AdaptadorPrestamo adaptadorPrestamo= new AdaptadorPrestamo(this, GestionIncidencias.getArPrestamos().toArray(new EntPrestamo[0]));
        ListaPrestamo.setAdapter(adaptadorPrestamo);
    }
}