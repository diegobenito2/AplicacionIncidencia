package com.example.aplicacionincidencias.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.R;
import com.example.aplicacionincidencias.Sala.activitySalas;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntUsuario;

public class Activity_Info_Usuario extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar;
    private EntUsuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int codigoUsuario = getIntent().getExtras().getInt("codigo");
if (codigoUsuario >0 ){
    for (EntUsuario u : GestionIncidencias.getArUsuarios()){
        if (u.getCodigoUsuario()==codigoUsuario){
            usuario=u;
        }
    }
} else if (codigoUsuario == 0) {

}

        initComponentsVolverGuardar();
        initListenersVolverGuardar();


    }

    private void initComponentsVolverGuardar() {
        btnVolver = findViewById(R.id.btnVolver);
        btnGuardar = findViewById(R.id.btnGuardar);
    }

    private void initListenersVolverGuardar() {
        btnVolver.setOnClickListener((View.OnClickListener) this);
        btnGuardar.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityUsuario.class);
            startActivity(intent);
        });
        btnGuardar.setOnClickListener(v -> {

        });
    }
}