package com.example.aplicacionincidencias.Sala;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.R;

public class Activity_Info_Sala extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_sala);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Se obtiene el Intent que lanzó la actividad actual.
        Intent infoSala = this.getIntent();

        // Se recuperan los datos extras enviados en el Intent.
        Bundle bnd = infoSala.getExtras();

        // Se extrae el valor asociado a la clave desde el Bundle y se guarda en una variable.
        int codigoSala = bnd.getInt("codigo");
        String nombreSala = bnd.getString("nombre");
        String descripcionSala = bnd.getString("descripcion");

        // Se busca el TextView en el layout de la actividad donde se mostrará el código de la sala.
        TextView tvCodigoSala = findViewById(R.id.tvinfoCodigoSala);
        EditText edNombreSala = findViewById(R.id.editNombreSala);
        EditText edDescripcion = findViewById(R.id.editDescripcionSala);

        // Se establece el texto del TextView con el valor obtenido del Intent (el código de la sala).
        tvCodigoSala.setText(String.valueOf(codigoSala));
        edNombreSala.setText(nombreSala);
        edDescripcion.setText(descripcionSala);

        initComponentsVolverGuardar();
        initListenersVolverGuardar();



    }

    private void initComponentsVolverGuardar() {
        btnVolver = findViewById(R.id.btnVolver);
        btnGuardar = findViewById(R.id.btnGuardar);
    }
    private void initListenersVolverGuardar(){
        btnVolver.setOnClickListener((View.OnClickListener) this);
        btnGuardar.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View view) {
        btnVolver.setOnClickListener(v->{
            Intent intent = new Intent(this, activitySalas.class);
            startActivity(intent);
        });
        btnGuardar.setOnClickListener(v->{

        });
    }
}