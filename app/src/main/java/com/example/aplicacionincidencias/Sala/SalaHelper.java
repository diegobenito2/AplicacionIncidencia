package com.example.aplicacionincidencias.Sala;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.aplicacionincidencias.Bbdd.BbddIncidencias;

import java.util.ArrayList;

import gestionincidencias.entidades.EntSala;

public class SalaHelper extends BbddIncidencias {
    public SalaHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public long crearSala(EntSala sala) {
        SQLiteDatabase db = getWritableDatabase();
        long salaId = -1;
        db.beginTransaction(); //Inicia la transacción y garantiza que no se acceden a los datos mientras se realiza la transaccion(una transacción es un conjunto de consultas.)
        try {
            ContentValues values = new ContentValues();
            if (sala.getCodigoSala() > 0) {
                values.put(KEY_COL_CODIGO, sala.getCodigoSala());
            }
            values.put(KEY_COL_NOMBRE, sala.getNombre());
            values.put(KEY_COL_DESCRIPCION, sala.getDescripcion());
            salaId = db.insertOrThrow(TABLA_SALA, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(SalaHelper.class.getName(), e.getMessage());
        } finally {
            db.endTransaction();
        }

        return salaId;
    }

    public long actualizarSala(EntSala sala) {
        SQLiteDatabase db = getWritableDatabase();
        long salaId = -1;
        if (sala.getCodigoSala() > 0) {
            db.beginTransaction(); //Inicia la transacción y garantiza que no se acceden a los datos mientras se realiza la transaccion(una transacción es un conjunto de consultas.)
            try {
                ContentValues values = new ContentValues();
                values.put(KEY_COL_CODIGO, sala.getCodigoSala() > 0 ? sala.getCodigoSala() : null);
                values.put(KEY_COL_NOMBRE, sala.getNombre());
                values.put(KEY_COL_DESCRIPCION, sala.getDescripcion());
                int rows = db.update(TABLA_SALA, values, KEY_COL_CODIGO + "= ?", new String[]{String.valueOf(sala.getCodigoSala())});
                if (rows > 0) {
                    salaId = sala.getCodigoSala();
                }
            } catch (Exception e) {
                Log.d(SalaHelper.class.getName(), e.getMessage());
            } finally {
                db.endTransaction();
            }

        }
        return salaId;
    }

    public ArrayList<EntSala> obtenerSalas() {
        ArrayList<EntSala> salas = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from sala", null);
        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String descripcion = cursor.getString(2);
                EntSala sala = new EntSala(codigo, nombre, descripcion);
                salas.add(sala);
            } while (cursor.moveToNext());
        }
        return salas;
    }

    public EntSala obtenerSala(int idSala) {
        EntSala sala = null;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from sala where " + KEY_COL_CODIGO + "=" + idSala, null);
        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String descripcion = cursor.getString(2);
                sala = new EntSala(codigo, nombre, descripcion);
            } while (cursor.moveToNext());
        }
        return sala;
    }

    public Integer borrarSala(int idSala) {
        SQLiteDatabase db = getWritableDatabase();
        int sala = db.delete(TABLA_SALA, "where " + KEY_COL_CODIGO + "=" + idSala, null);
        return sala;
    }



}
