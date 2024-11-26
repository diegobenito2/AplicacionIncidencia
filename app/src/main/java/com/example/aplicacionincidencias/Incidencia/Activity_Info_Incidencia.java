package com.example.aplicacionincidencias.Incidencia;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.aplicacionincidencias.MenuPrincipal.menutrespuntos;
import com.example.aplicacionincidencias.R;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntIncidencia;


public class Activity_Info_Incidencia extends menutrespuntos implements View.OnClickListener {
    private Button btnVolver, btnGuardar;
    private EntIncidencia incidencia;
    private TextView tvFechaCreacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_incidencia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        int codigoIncidencia = getIntent().getExtras().getInt("codigoIncidencia");
        String descripcionIncidencia = getIntent().getExtras().getString("descripcionIncidencia");
        if (codigoIncidencia > 0) {
            for (EntIncidencia i : GestionIncidencias.getArIncidencias()) {
                if (i.getCodigoIncidencia() == codigoIncidencia) {
                    incidencia = i;
                }
            }
        } else if (codigoIncidencia == 0 && descripcionIncidencia.isEmpty()) {
            incidencia = new EntIncidencia(0, "", 0, null, 0);
        } else if (codigoIncidencia == 0) {
            for (EntIncidencia i : GestionIncidencias.getArIncidencias()) {
                if (i.getCodigoIncidencia() == codigoIncidencia) {
                    incidencia = i;
                }
            }
        }
        if (incidencia != null) {

            EditText edCodigoIncidencia = findViewById(R.id.edinfoCodigoIncidencia);
            EditText edDescripcionIncidencia = findViewById(R.id.edDescripcionIncidencia);
            EditText edCodigoElemento = findViewById(R.id.edInfoCodigoElemento);
            EditText edCodigoUsuarioCreacion = findViewById(R.id.edInfoCodigoUsuarioCreacion);
            tvFechaCreacion = findViewById(R.id.tvInfoFechaCreacion);

            edCodigoIncidencia.setText(String.valueOf(incidencia.getCodigoIncidencia()));
            edDescripcionIncidencia.setText(incidencia.getDescripcion());
            edCodigoElemento.setText(String.valueOf(incidencia.getIdElemento()));
            edCodigoUsuarioCreacion.setText(String.valueOf(incidencia.getIdUsuarioCreacion()));

            Date fechaCreacion=incidencia.getFechaCreacion();

            if (fechaCreacion!=null){
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                tvFechaCreacion.setText(format.format(fechaCreacion));
            }else{
                tvFechaCreacion.setText("Fecha no disponible.");
            }

        }
        btnVolver = findViewById(R.id.btnVolverIncidencia);
        btnGuardar = findViewById(R.id.btnGuardarIncidencia);

        tvFechaCreacion.setOnClickListener((View.OnClickListener) this);
        btnVolver.setOnClickListener((View.OnClickListener) this);
        btnGuardar.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, activityIncidencia.class);
            startActivity(intent);
        });
        btnGuardar.setOnClickListener(v -> {
            if (incidencia != null) {
                EditText edCodigoIncidencia = findViewById(R.id.edinfoCodigoIncidencia);
                EditText edDescripcionIncidencia = findViewById(R.id.edDescripcionIncidencia);
                EditText edCodigoElemento = findViewById(R.id.edInfoCodigoElemento);
                EditText edCodigoUsuarioCreacion = findViewById(R.id.edInfoCodigoUsuarioCreacion);
                tvFechaCreacion = findViewById(R.id.tvInfoFechaCreacion);

                if (incidencia.getCodigoIncidencia() != 0) {
                    incidencia.setCodigoIncidencia(Integer.parseInt(edCodigoIncidencia.getText().toString()));
                    incidencia.setDescripcion(edDescripcionIncidencia.getText().toString());
                    incidencia.setIdElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                    incidencia.setIdUsuarioCreacion(Integer.parseInt(edCodigoUsuarioCreacion.getText().toString()));
                    tvFechaCreacion =findViewById(R.id.tvInfoFechaCreacion);
                    String FechaCreacion= tvFechaCreacion.getText().toString();
                    SimpleDateFormat formatCreacion = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    try{
                        Date fechaCreacion = formatCreacion.parse(FechaCreacion);
                        incidencia.setFechaCreacion(fechaCreacion);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                } else if (incidencia.getCodigoIncidencia() == 0 && incidencia.getDescripcion().isEmpty()) {
                    incidencia.setCodigoIncidencia(Integer.parseInt(edCodigoIncidencia.getText().toString()));
                    incidencia.setDescripcion(edDescripcionIncidencia.getText().toString());
                    incidencia.setIdElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                    incidencia.setIdUsuarioCreacion(Integer.parseInt(edCodigoUsuarioCreacion.getText().toString()));
                    tvFechaCreacion =findViewById(R.id.tvInfoFechaCreacion);
                    String FechaCreacion= tvFechaCreacion.getText().toString();
                    SimpleDateFormat formatCreacion = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    try{
                        Date fechaCreacion = formatCreacion.parse(FechaCreacion);
                        incidencia.setFechaCreacion(fechaCreacion);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                    GestionIncidencias.getArIncidencias().add(GestionIncidencias.getArIncidencias().size(), incidencia);
                } else if (incidencia.getCodigoIncidencia() == 0) {
                    incidencia.setCodigoIncidencia(Integer.parseInt(edCodigoIncidencia.getText().toString()));
                    incidencia.setDescripcion(edDescripcionIncidencia.getText().toString());
                    incidencia.setIdElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                    incidencia.setIdUsuarioCreacion(Integer.parseInt(edCodigoUsuarioCreacion.getText().toString()));
                    tvFechaCreacion =findViewById(R.id.tvInfoFechaCreacion);
                    String FechaCreacion= tvFechaCreacion.getText().toString();
                    SimpleDateFormat formatCreacion = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    try{
                        Date fechaCreacion = formatCreacion.parse(FechaCreacion);
                        incidencia.setFechaCreacion(fechaCreacion);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                }
                Intent intentVolverIncidencias = new Intent(view.getContext(), activityIncidencia.class);
                startActivity(intentVolverIncidencias);
            }
        });
        tvFechaCreacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                c.setTime(incidencia.getFechaCreacion());
                DatePickerDialog dialogo = new DatePickerDialog(Activity_Info_Incidencia.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        Date fechaCreacion = incidencia.getFechaCreacion();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        String[] fecha = format.format(fechaCreacion).split(" ");
                        tvFechaCreacion.setText(y + "-" + (m + 1) + "-" + d + " " + fecha[1]);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialogo.show();
            }
        });
    }
}