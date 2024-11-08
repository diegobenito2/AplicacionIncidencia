package com.example.aplicacionincidencias.Sala;

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

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntSala;

public class activitySalas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_salas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listaSalas = (ListView) findViewById(R.id.ListaSalas);
        AdaptadorSalas adaptadorSalas = new AdaptadorSalas(this, GestionIncidencias.getArSalas().toArray(new EntSala[0]));
        listaSalas.setAdapter(adaptadorSalas);

        listaSalas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                EntSala salaSeleccionado = (EntSala) adapterView.getItemAtPosition(position);
                Intent intentInfoSala = new Intent(view.getContext(),Activity_Info_Sala.class);
                intentInfoSala.putExtra("sala",salaSeleccionado.getCodigoSala());
                startActivity(intentInfoSala);
            }
        });
    }
}