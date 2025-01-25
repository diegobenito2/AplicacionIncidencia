package com.example.aplicacionincidencias.Prestamo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.aplicacionincidencias.Bbdd.BbddIncidencias;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import gestionincidencias.entidades.EntElemento;
import gestionincidencias.entidades.EntPrestamo;


public class PrestamoHelper extends BbddIncidencias {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public PrestamoHelper(Context context, String nombreBBDD, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombreBBDD, factory, version);
    }

    public long crearPrestamo(EntPrestamo prestamo) {
        SQLiteDatabase db = getWritableDatabase();
        long prestamoId = -1;
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_COL_CODIGO, prestamo.getCodigoPrestamo());
            values.put("idUsuario", prestamo.getIdUsuario());
            values.put("idElemento", prestamo.getIdElemento());
            values.put("fechaInicio", formatter.format(prestamo.getFechaInicio()));
            values.put("fechaFin", formatter.format(prestamo.getFechaFin()));

            prestamoId = db.insert(TABLA_PRESTAMO, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(PrestamoHelper.class.getName(), e.getMessage());
        } finally {
            db.endTransaction();
        }
        return prestamoId;
    }

    public long actualizarPrestamo(EntPrestamo prestamo) {
        SQLiteDatabase db = getWritableDatabase();
        long prestamoId = -1;
        if (prestamo.getCodigoPrestamo() > 0) {
            db.beginTransaction();
            try {
                ContentValues values = new ContentValues();
                values.put(KEY_COL_CODIGO, prestamo.getCodigoPrestamo());
                values.put("idUsuario", prestamo.getIdUsuario());
                values.put("idElemento", prestamo.getIdElemento());
                values.put("fechaInicio", formatter.format(prestamo.getFechaInicio()));
                values.put("fechaFin", formatter.format(prestamo.getFechaFin()));

                int rows = db.update(TABLA_PRESTAMO, values, KEY_COL_CODIGO + " = ?",
                        new String[]{String.valueOf(prestamo.getCodigoPrestamo())});

                if (rows > 0) {
                    db.setTransactionSuccessful();
                    prestamoId = prestamo.getCodigoPrestamo();
                }
            } catch (Exception e) {
                Log.d(PrestamoHelper.class.getName(), "Error while trying to add or update prestamo");
            } finally {
                db.endTransaction();
            }
        }
        return prestamoId;
    }

    public ArrayList<EntPrestamo> obtenerPrestamos() {
        ArrayList<EntPrestamo> prestamos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();



        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_PRESTAMO, null);

        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                int idUsuario = cursor.getInt(1);
                int idElemento = cursor.getInt(2);
                String fechaInicioStr = cursor.getString(3);
                String fechaFinStr = cursor.getString(4);
                Date fechaInicio = null;
                Date fechaFin = null;
                try {
                    fechaInicio = formatter.parse(fechaInicioStr);
                    fechaFin = formatter.parse(fechaFinStr);
                } catch (ParseException e) {
                    Log.d(PrestamoHelper.class.getName(), e.getMessage());
                }

                EntPrestamo prestamo = new EntPrestamo(codigo, idUsuario, idElemento, fechaInicio, fechaFin);
                prestamos.add(prestamo);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return prestamos;
    }

    public EntPrestamo obtenerPrestamo(int idPrestamo) {

        SQLiteDatabase db = getReadableDatabase();
        EntPrestamo prestamo = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_PRESTAMO + " where " + KEY_COL_CODIGO + "=" + idPrestamo, null);

        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                int idUsuario = cursor.getInt(1);
                int idElemento = cursor.getInt(2);
                String fechaInicioStr = cursor.getString(3);
                String fechaFinStr = cursor.getString(4);
                Date fechaInicio = null;
                Date fechaFin = null;
                try {
                    fechaInicio = formatter.parse(fechaInicioStr);
                    fechaFin = formatter.parse(fechaFinStr);
                } catch (ParseException e) {
                    Log.d(PrestamoHelper.class.getName(), e.getMessage());
                }

                prestamo = new EntPrestamo(codigo, idUsuario, idElemento, fechaInicio, fechaFin);


            } while (cursor.moveToNext());
        }
        cursor.close();
        return prestamo;
    }

    public int borrarPrestamo(int codigoPrestamo) {
        SQLiteDatabase db = getWritableDatabase();
        int borrados = 0;

        db.beginTransaction();
        try {
            borrados = db.delete(TABLA_PRESTAMO, KEY_COL_CODIGO + "= ?",
                    new String[]{String.valueOf(codigoPrestamo)});
            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d(PrestamoHelper.class.getName(), e.getMessage());
        } finally {
            db.endTransaction();
        }
        return borrados;
    }
}
