package com.example.aplicacionincidencias.Tipo;

import android.os.Bundle;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.R;

import gestionincidencias.entidades.Tipo;

public class activityTipo extends AppCompatActivity {

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
        Tipo[] tipos = new Tipo[50];
        for (int i=0;i<tipos.length;i++){
            tipos[i]=new Tipo(i+1,"Tipo" +(i+1));
        }
        ListView listaTipo= (ListView) findViewById(R.id.ListaTipo);
        AdaptadorTipo adaptadorTipo = new AdaptadorTipo(this,tipos);
        listaTipo.setAdapter(adaptadorTipo);


    }
}