package com.example.aplicacionincidencias.Prestamo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplicacionincidencias.Incidencia.Activity_Info_Incidencia;
import com.example.aplicacionincidencias.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import gestionincidencias.GestionIncidencias;
import gestionincidencias.entidades.EntPrestamo;

public class Activity_Info_Prestamo extends AppCompatActivity implements View.OnClickListener {
    private Button btnVolver, btnGuardar;
    private EntPrestamo prestamo;
    private TextView tvfechaInicio;
    private TextView tvfechaFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_prestamo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        int idPrestamo = getIntent().getExtras().getInt("codigoPrestamo");
        if (idPrestamo > 0) {
            for (EntPrestamo e : GestionIncidencias.getArPrestamos()) {
                if (e.getCodigoPrestamo() == idPrestamo) {
                    prestamo = e;
                }
            }

        } else {
            prestamo = new EntPrestamo(0, 0, 0, null, null);
        }
        if (prestamo != null) {
            EditText edCodigoPrestamo = findViewById(R.id.infoedCodigoPrestamo);
            EditText edCodigoUsuario = findViewById(R.id.infotvCodigoUsuario);
            EditText edCodigoElemento = findViewById(R.id.infotvCodigoElemento);
            tvfechaInicio = findViewById(R.id.infotvfechaInicio);
            tvfechaFin = findViewById(R.id.infotvfechaFin);

            edCodigoPrestamo.setText(String.valueOf(prestamo.getCodigoPrestamo()));
            edCodigoUsuario.setText(String.valueOf(prestamo.getIdUsuario()));
            edCodigoElemento.setText(String.valueOf(prestamo.getIdElemento()));
            tvfechaInicio.setText(String.valueOf(prestamo.getFechaInicio()));
            tvfechaFin.setText(String.valueOf(prestamo.getFechaFin()));
        }

        initComponentsVolverGuardar();
        initListenersVolverGuardar();

    }

    private void initComponentsVolverGuardar() {
        btnVolver = findViewById(R.id.btnVolver);
        btnGuardar = findViewById(R.id.btnGuardar);
    }

    private void initListenersVolverGuardar() {
        tvfechaInicio.setOnClickListener((View.OnClickListener) this);
        tvfechaFin.setOnClickListener((View.OnClickListener) this);
        btnVolver.setOnClickListener((View.OnClickListener) this);
        btnGuardar.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityPrestamo.class);
            startActivity(intent);
        });
        btnGuardar.setOnClickListener(v -> {
            if (prestamo != null) {
                EditText edCodigoUsuario = findViewById(R.id.infotvCodigoUsuario);
                EditText edCodigoElemento = findViewById(R.id.infotvCodigoElemento);
                tvfechaInicio = findViewById(R.id.infotvfechaInicio);
                tvfechaFin = findViewById(R.id.infotvfechaFin);
                prestamo.setIdUsuario(Integer.parseInt(edCodigoUsuario.getText().toString()));
                prestamo.setIdElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                prestamo.setFechaInicio(Date.valueOf(tvfechaInicio.getText().toString()));
                prestamo.setFechaFin(Date.valueOf(tvfechaFin.getText().toString()));
                if (prestamo.getCodigoPrestamo() == 0) {
                    prestamo.setCodigoPrestamo(GestionIncidencias.getArPrestamos().size() + 1);
                    GestionIncidencias.getArPrestamos().add(0, prestamo);
                }
                Intent intentVolverPrestamos = new Intent(view.getContext(), ActivityPrestamo.class);
                startActivity(intentVolverPrestamos);
            }
        });
        tvfechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                c.setTime(prestamo.getFechaInicio());
                DatePickerDialog dialogo = new DatePickerDialog(Activity_Info_Prestamo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        Date fechaInicio = (Date) prestamo.getFechaInicio();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        String[] fecha = format.format(fechaInicio).split(" ");
                        tvfechaInicio.setText(y + "-" + (m + 1) + "-" + d + " " + fecha[1]);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialogo.show();
            }
        });
        tvfechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                c.setTime(prestamo.getFechaFin());
                DatePickerDialog dialogo = new DatePickerDialog(Activity_Info_Prestamo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        Date fechaFin = (Date) prestamo.getFechaFin();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        String[] fecha = format.format(fechaFin).split(" ");
                        tvfechaFin.setText(y + "-" + (m + 1) + "-" + d + " " + fecha[1]);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialogo.show();
            }
        });
    }
}