package com.example.aplicacionincidencias.Elemento;

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
import com.example.aplicacionincidencias.Sala.activitySalas;

public class Activity_Info_Elemento extends AppCompatActivity implements View.OnClickListener{
    private Button btnVolver, btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_elemento);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Se obtiene el Intent que lanzó la actividad actual.
        Intent infoElemento = this.getIntent();

        // Se recuperan los datos extras enviados en el Intent.
        Bundle bnd = infoElemento.getExtras();

        // Se extrae el valor asociado a la clave desde el Bundle y se guarda en una variable
        int codigoElemento = bnd.getInt("codigo");
        String nombreElemento = bnd.getString("nombre");
        String descripcionElemento = bnd.getString("descripcion");
        int codigoTipo = bnd.getInt("tipo");

        // Se busca el TextView en el layout de la actividad donde se mostrarán los elementos pasados en el intent.
        EditText tvCodigoElemento = findViewById(R.id.edinfoCodigoElemento);
        EditText edNombreElemento = findViewById(R.id.editNombreElemento);
        EditText edDescripcionElemento = findViewById(R.id.editDescripcionElemento);
        EditText edCodigoTipoElemento = findViewById(R.id.editcodigoTipoElemento);

        // Se establece el texto al elemento con el valor obtenido en el Intent.
        tvCodigoElemento.setText(String.valueOf(codigoElemento));
        edNombreElemento.setText(nombreElemento);
        edDescripcionElemento.setText(descripcionElemento);
        edCodigoTipoElemento.setText(String.valueOf(codigoTipo));


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
            Intent intent = new Intent(this, activityElemento.class);
            startActivity(intent);
        });
        btnGuardar.setOnClickListener(v -> {

        });
    }
}