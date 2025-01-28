package com.example.aplicacionincidencias.Elemento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.aplicacionincidencias.Bbdd.BbddIncidencias;

import java.util.ArrayList;

import gestionincidencias.entidades.EntElemento;


public class ElementoHelper extends BbddIncidencias {

    public ElementoHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public long crearElemento(EntElemento elemento) {
        SQLiteDatabase db = getWritableDatabase();
        long elementoID = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            if (elemento.getCodigoElemento() > 0) {
                values.put(KEY_COL_CODIGO, elemento.getCodigoElemento());
            }
            values.put(KEY_COL_NOMBRE, elemento.getNombre());
            values.put(KEY_COL_DESCRIPCION, elemento.getDescripcion());
            values.put("idTipo", elemento.getIdTipo());

            elementoID = db.insertOrThrow(TABLA_ELEMENTO, null, values);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d(ElementoHelper.class.getName(), e.getMessage());
        } finally {
            db.endTransaction();
        }
        return elementoID;
    }

    public long actualizarElemento(EntElemento elemento) {
        SQLiteDatabase db = getWritableDatabase();
        long elementoID = -1;

        if (elemento.getCodigoElemento() > 0) {
            db.beginTransaction();
            try {
                ContentValues values = new ContentValues();
                if (elemento.getCodigoElemento() > 0) {
                    values.put(KEY_COL_CODIGO, elemento.getCodigoElemento());
                }
                values.put(KEY_COL_NOMBRE, elemento.getNombre());
                values.put(KEY_COL_DESCRIPCION, elemento.getDescripcion());
                values.put("idTipo", elemento.getIdTipo());

                int rows = db.update(TABLA_ELEMENTO, values, "codigo = ?",
                        new String[]{String.valueOf(elemento.getCodigoElemento())});

                if (rows > 0) {
                    db.setTransactionSuccessful();
                    elementoID = elemento.getCodigoElemento();
                }
            } catch (Exception e) {
                Log.d(ElementoHelper.class.getName(), e.getMessage());
            } finally {
                db.endTransaction();
            }
        }
        return elementoID;
    }

    public ArrayList<EntElemento> obtenerElementos() {
        ArrayList<EntElemento> elementos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM elemento", null);

        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String descripcion = cursor.getString(2);
                int idTipo = cursor.getInt(3);

                EntElemento elemento = new EntElemento(codigo, nombre, descripcion, idTipo);

                elementos.add(elemento);
            } while (cursor.moveToNext());
        }
        return elementos;
    }

    public EntElemento obtenerElemento(int idElemento) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_ELEMENTO + " where " + KEY_COL_CODIGO + "=" + idElemento, null);
        EntElemento elemento = null;
        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String descripcion = cursor.getString(2);
                int idTipo = cursor.getInt(3);

                elemento = new EntElemento(codigo, nombre, descripcion, idTipo);


            } while (cursor.moveToNext());
        }
        return elemento;
    }

    public int borrarElemento(int codigo) {
        SQLiteDatabase db = getWritableDatabase();
        int borrados = 0;

        db.beginTransaction();
        try {
            borrados = db.delete(TABLA_ELEMENTO, "codigo = ?", new String[]{String.valueOf(codigo)});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(ElementoHelper.class.getName(), e.getMessage());
        } finally {
            db.endTransaction();
        }
        return borrados;
    }

    public EntElemento buscarNombreElemento(String nombreElemento) {
        EntElemento elemento = null;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLA_ELEMENTO + " where " + KEY_COL_NOMBRE + "='" + nombreElemento + "'", null);
        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String descripcion = cursor.getString(2);
                int idTipo = cursor.getInt(3);
                elemento = new EntElemento(codigo, nombre, descripcion, idTipo);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return elemento;
    }

}
