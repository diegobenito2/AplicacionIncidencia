package com.example.aplicacionincidencias.Ubicacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.aplicacionincidencias.Bbdd.BbddIncidencias;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import gestionincidencias.entidades.EntUbicacion;

public class UbicacionHelper extends BbddIncidencias {
    public UbicacionHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public long crearUbicacion(EntUbicacion ubicacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ubicacionId = -1;


        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            if (ubicacion.getCodigoUbicacion() > 0) {
                values.put(KEY_COL_CODIGO, ubicacion.getCodigoUbicacion());
            }

            values.put("idSala", ubicacion.getIdSala());
            values.put("idElemento", ubicacion.getIdElemento());
            values.put(KEY_COL_DESCRIPCION, ubicacion.getDescripcion());
            if (ubicacion.getFechaInicio() != null) {
                values.put("fechaInicio", formatter.format(ubicacion.getFechaInicio()));
            } else {
                values.put("fechaInicio", "");
            }
            if (ubicacion.getFechaFin() != null) {
                values.put("fechaFin", formatter.format(ubicacion.getFechaFin()));
            } else {
                values.put("fechaFin", "");
            }

            //si no existe y no se puede actualizar entonces lo creamos
            ubicacionId = db.insertOrThrow(TABLA_UBICACION, null, values);
            Log.d("UbicacionDatabaseHelper", "Ubicación insertada con éxito. ID generado: " + ubicacionId);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.e("UbicacionDatabaseHelper", "Error al añadir ubicación: " + e.getMessage());
        } finally {
            db.endTransaction();
        }
        return ubicacionId;
    }

    public long actualizarUbicacion(EntUbicacion ubicacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        long ubicacionId = -1;

        db.beginTransaction();
        if (ubicacion.getCodigoUbicacion() > 0) {
            try {
                ContentValues values = new ContentValues();

                values.put(KEY_COL_CODIGO, ubicacion.getCodigoUbicacion());
                values.put("idSala", ubicacion.getIdSala());
                values.put("idElemento", ubicacion.getIdElemento());
                values.put(KEY_COL_DESCRIPCION, ubicacion.getDescripcion());
                values.put("fechaInicio", formatter.format(ubicacion.getFechaInicio()));
                values.put("fechaFin", formatter.format(ubicacion.getFechaFin()));


                //Primero intento actualizar elemento concreto
                int rows = db.update(TABLA_UBICACION, values, KEY_COL_CODIGO + " = ?",
                        new String[]{String.valueOf(ubicacion.getCodigoUbicacion())});
                if (rows > 0) {
                    ubicacionId = ubicacion.getCodigoUbicacion();
                    db.setTransactionSuccessful();
                }
            } catch (Exception e) {
                Log.e("UbicacionDatabaseHelper", "Error al modificar ubicacion: " + e.getMessage());
            } finally {
                db.endTransaction();
            }
        }
        return ubicacionId;
    }

    public ArrayList<EntUbicacion> obtenerUbicaciones() {
        ArrayList<EntUbicacion> ubicaciones = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Consulta para obtener todas las ubicaciones
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_UBICACION, null);


        if (cursor.moveToFirst()) {
            do {
                int codigoUbicacion = cursor.getInt(0);
                int idSala = cursor.getInt(1);
                int idElemento = cursor.getInt(5);
                String descripcion = cursor.getString(2);
                String fechaInicioString = cursor.getString(3);
                String fechaFinString = cursor.getString(4);
                Date fechaInicioFormat = null;
                Date fechaFinFormat = null;

                if (fechaInicioString != null && !fechaInicioString.isEmpty()) {
                    try {
                        fechaInicioFormat = formatter.parse(fechaInicioString);
                    } catch (ParseException e) {
                        Log.e("UbicacionDatabaseHelper", "Error al formatear fecha Inicio ubicacion: " + e.getMessage());
                    }
                }
                if (fechaFinString != null && !fechaFinString.isEmpty()) {
                    try {
                        fechaFinFormat = formatter.parse(fechaFinString);
                    } catch (ParseException e) {
                        Log.e("UbicacionDatabaseHelper", "Error al formatear fecha Fin ubicacion: " + e.getMessage());
                    }
                }

                EntUbicacion ubicacion = new EntUbicacion(codigoUbicacion, idSala, idElemento, descripcion, fechaInicioFormat, fechaFinFormat);

                ubicaciones.add(ubicacion);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ubicaciones;
    }

    public EntUbicacion obtenerUbicacion(int idUbicacion) {

        SQLiteDatabase db = this.getReadableDatabase();
        EntUbicacion ubicacion = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_UBICACION + " where " + KEY_COL_CODIGO + "=" + idUbicacion, null);


        if (cursor.moveToFirst()) {
            do {
                int codigoUbicacion = cursor.getInt(0);
                int idSala = cursor.getInt(1);
                int idElemento = cursor.getInt(5);
                String descripcion = cursor.getString(2);
                String fechaInicioString = cursor.getString(3);
                String fechaFinString = cursor.getString(4);
                Date fechaInicioFormat = null;
                Date fechaFinFormat = null;

                if (fechaInicioString != null && !fechaInicioString.isEmpty()) {
                    try {
                        fechaInicioFormat = formatter.parse(fechaInicioString);
                    } catch (ParseException e) {
                        Log.e("UbicacionDatabaseHelper", "Error al formatear fecha Inicio ubicacion: " + e.getMessage());
                    }
                }
                if (fechaFinString != null && !fechaFinString.isEmpty()) {
                    try {
                        fechaFinFormat = formatter.parse(fechaFinString);
                    } catch (ParseException e) {
                        Log.e("UbicacionDatabaseHelper", "Error al formatear fecha Fin ubicacion: " + e.getMessage());
                    }
                }

                ubicacion = new EntUbicacion(codigoUbicacion, idSala, idElemento, descripcion, fechaInicioFormat, fechaFinFormat);


            } while (cursor.moveToNext());
        }
        cursor.close();
        return ubicacion;
    }

    public int borrarUbicacion(int codigoUbicacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        int borrados = 0;

        db.beginTransaction();
        try {
            borrados = db.delete(TABLA_UBICACION, KEY_COL_CODIGO + " = ?",
                    new String[]{String.valueOf(codigoUbicacion)});
            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d(UbicacionHelper.class.getName(), "Error al intentar borrar ubicacion");
        } finally {
            db.endTransaction();
        }
        return borrados;
    }
}
