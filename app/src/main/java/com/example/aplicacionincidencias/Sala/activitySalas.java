package com.example.aplicacionincidencias.Sala;

import android.os.Bundle;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.R;

import gestionincidencias.entidades.Sala;

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
        Sala[] salas = new Sala[50];
        for (int j = 0; j < salas.length;j++) {
            salas[j] = new Sala(j+1,"Sala"+(j+1));
        }
        ListView listaSalas = (ListView) findViewById(R.id.ListaSalas);
        AdaptadorSalas adaptadorSalas = new AdaptadorSalas(this, salas);
        listaSalas.setAdapter(adaptadorSalas);
    }
}