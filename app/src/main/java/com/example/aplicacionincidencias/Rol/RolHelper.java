package com.example.aplicacionincidencias.Rol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.aplicacionincidencias.Bbdd.BbddIncidencias;

import java.util.ArrayList;

import gestionincidencias.entidades.EntRol;

public class RolHelper extends BbddIncidencias {
    public RolHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public long crearRol(EntRol rol) {
        SQLiteDatabase db = getWritableDatabase();
        long rolID = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            if (rol.getCodigo() > 0) {
                values.put(KEY_COL_CODIGO, rol.getCodigo());
            }
            values.put(KEY_COL_NOMBRE, rol.getNombre());
            values.put(KEY_COL_DESCRIPCION, rol.getDescripcion());
            values.put("nivel_acceso", rol.getNivel_acceso());

            rolID = db.insertOrThrow(TABLA_ROL, null, values);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d(RolHelper.class.getName(), e.getMessage());
        } finally {
            db.endTransaction();
        }
        return rolID;
    }

    public long actualizarRol(EntRol rol) {
        SQLiteDatabase db = getWritableDatabase();
        long rolID = -1;

        if (rol.getCodigo() > 0) {
            db.beginTransaction();
            try {
                ContentValues values = new ContentValues();
                if (rol.getCodigo() > 0) {
                    values.put(KEY_COL_CODIGO, rol.getCodigo());
                }
                values.put(KEY_COL_NOMBRE, rol.getNombre());
                values.put(KEY_COL_DESCRIPCION, rol.getDescripcion());
                values.put("nivel_acceso", rol.getNivel_acceso());


                int rows = db.update(TABLA_ROL, values, "codigo = ?",
                        new String[]{String.valueOf(rol.getCodigo())});

                if (rows > 0) {
                    db.setTransactionSuccessful();
                    rolID = rol.getCodigo();
                }
            } catch (Exception e) {
                Log.d(RolHelper.class.getName(), e.getMessage());
            } finally {
                db.endTransaction();
            }
        }
        return rolID;
    }

    public ArrayList<EntRol> obtenerRoles() {
        ArrayList<EntRol> roles = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM rol", null);

        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String descripcion = cursor.getString(2);
                int nivelAcceso = cursor.getInt(3);

                EntRol rol = new EntRol(codigo, nombre, descripcion, nivelAcceso);

                roles.add(rol);
            } while (cursor.moveToNext());
        }
        return roles;
    }

    public EntRol obtenerRol(int idRol) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_ROL + " where " + KEY_COL_CODIGO + "=" + idRol, null);
        EntRol rol = null;
        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String descripcion = cursor.getString(2);
                int nivelAcceso = cursor.getInt(3);

                rol = new EntRol(codigo, nombre, descripcion, nivelAcceso);


            } while (cursor.moveToNext());
        }
        return rol;
    }

    public int borrarRol(int codigo) {
        SQLiteDatabase db = getWritableDatabase();
        int borrados = 0;

        db.beginTransaction();
        try {
            borrados = db.delete(TABLA_ROL, "codigo = ?", new String[]{String.valueOf(codigo)});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(RolHelper.class.getName(), e.getMessage());
        } finally {
            db.endTransaction();
        }
        return borrados;
    }
}
