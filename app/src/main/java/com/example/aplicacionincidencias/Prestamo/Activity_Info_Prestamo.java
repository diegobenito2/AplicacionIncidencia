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

import com.example.aplicacionincidencias.R;

import java.util.Date;
import java.text.ParseException;
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

            Date fechaInicio=prestamo.getFechaInicio();

            if (fechaInicio!=null){
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                tvfechaInicio.setText(format.format(fechaInicio));
            }else{
                tvfechaInicio.setText("Fecha no disponible.");
            }

            Date fechaFin= prestamo.getFechaFin();

            if (fechaFin!=null){
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                tvfechaFin.setText(format.format(fechaFin));
            }else{
                tvfechaFin.setText("Fecha no disponible.");
            }
        }
        tvfechaInicio.setOnClickListener((View.OnClickListener) this);
        tvfechaFin.setOnClickListener((View.OnClickListener) this);
        btnVolver = findViewById(R.id.btnVolver);
        btnGuardar = findViewById(R.id.btnGuardar);
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

                TextView tvFechaInicio=findViewById(R.id.infotvfechaInicio);
                String FechaInicio= tvFechaInicio.getText().toString();
                SimpleDateFormat formatInicio = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

                TextView tvFechaFin=findViewById(R.id.infotvfechaFin);
                String fechaFin= tvFechaFin.getText().toString();
                SimpleDateFormat formatFin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

                prestamo.setIdUsuario(Integer.parseInt(edCodigoUsuario.getText().toString()));
                prestamo.setIdElemento(Integer.parseInt(edCodigoElemento.getText().toString()));
                try{
                    Date fechaInicio = (Date) formatInicio.parse(FechaInicio);
                    prestamo.setFechaInicio(fechaInicio);
                }catch (ParseException e){
                    e.printStackTrace();
                }

               try{
                   Date fechaFinal= (Date) formatFin.parse(fechaFin);
                   prestamo.setFechaFin(fechaFinal);
               }catch (ParseException e){
                   e.printStackTrace();
               }
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
                // Inicializar el calendario con la fecha de inicio del préstamo
                Calendar c = Calendar.getInstance();
                c.setTime(prestamo.getFechaInicio());

                // Crear el DatePickerDialog
                DatePickerDialog dialogo = new DatePickerDialog(Activity_Info_Prestamo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        // Buscar el TextView donde se guardará la nueva fecha
                        TextView guardaFechaInicio = findViewById(R.id.infotvfechaInicio);

                        // Obtener la fecha de inicio original como Date
                        Date fechaInicio = prestamo.getFechaInicio();

                        // Crear un SimpleDateFormat para formatear la fecha y hora
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

                        // Formatear la fecha original a un string y dividirla en fecha y hora
                        String[] fecha = format.format(fechaInicio).split(" ");

                        // Actualizar el TextView con la nueva fecha y la hora original
                        guardaFechaInicio.setText(y + "-" + (m + 1) + "-" + d + " " + fecha[1]);

                        // Aquí guardamos la fecha seleccionada en la variable de fechaInicio, si es necesario:
                        // Prestamo.setFechaInicio(new Date(...)) o algo similar si necesitas actualizar el objeto.

                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

                // Mostrar el dialogo
                dialogo.show();
            }
        });


        TextView fechaFin = findViewById(R.id.infotvfechaFin);
        fechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicializar el calendario con la fecha de fin del préstamo
                Calendar c = Calendar.getInstance();
                c.setTime(prestamo.getFechaFin());

                // Crear el DatePickerDialog
                DatePickerDialog dialogo = new DatePickerDialog(Activity_Info_Prestamo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        // Buscar el TextView donde se guardará la nueva fecha
                        TextView guardaFechaFin = findViewById(R.id.infotvfechaFin);

                        // Obtener la fecha de fin original como Date
                        Date fechaFin = (Date) prestamo.getFechaFin();

                        // Crear un SimpleDateFormat para formatear la fecha y hora
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

                        // Formatear la fecha original a un string y dividirla en fecha y hora
                        String[] fecha = format.format(fechaFin).split(" ");

                        // Actualizar el TextView con la nueva fecha y la hora original
                        guardaFechaFin.setText(y + "-" + (m + 1) + "-" + d + " " + fecha[1]);

//                        // Aquí guardamos la fecha seleccionada en la variable de fechaFin, si es necesario:
//                        // Prestamo.setFechaFin(new Date(...)) o algo similar si necesitas actualizar el objeto.
//                        Date datefechaFin = Date.valueOf(String.valueOf(guardaFechaFin));
//                        guardaFechaFin.setText((CharSequence) datefechaFin);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

                // Mostrar el dialogo
                dialogo.show();
            }
        });

    }
}