package com.example.aplicacionincidencias.Usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;


import com.example.aplicacionincidencias.Bbdd.BbddIncidencias;


import java.util.ArrayList;

import gestionincidencias.entidades.EntUsuario;

public class UsuarioHelper extends BbddIncidencias {
    public UsuarioHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public long crearUsuario(EntUsuario usuario) {
        SQLiteDatabase db = getWritableDatabase();
        long usuarioID = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            if (usuario.getCodigoUsuario() > 0) {
                values.put(KEY_COL_CODIGO, usuario.getCodigoUsuario());
            }
            values.put(KEY_COL_NOMBRE, usuario.getNombre());
            values.put("correo", usuario.getCorreo());
            values.put("telefono", usuario.getTelefono());
            values.put("password", usuario.getPassword());
            values.put("rol", usuario.getRol());

            usuarioID = db.insertOrThrow(TABLA_USUARIO, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(UsuarioHelper.class.getName(), e.getMessage());
        } finally {
            db.endTransaction();
        }
        return usuarioID;
    }

    public long actualizarUsuario(EntUsuario usuario) {
        SQLiteDatabase db = getWritableDatabase();
        long usuarioID = -1;

        if (usuario.getCodigoUsuario() > 0) {
            db.beginTransaction();

            try {
                ContentValues values = new ContentValues();

                if (usuario.getCodigoUsuario() > 0) {
                    values.put(KEY_COL_CODIGO, usuario.getCodigoUsuario());
                }
                values.put(KEY_COL_NOMBRE, usuario.getNombre());
                values.put("correo", usuario.getCorreo());
                values.put("telefono", usuario.getTelefono());
                values.put("password", usuario.getPassword());
                values.put("rol", usuario.getRol());

                int rows = db.update(TABLA_USUARIO, values, "codigo = ?",
                        new String[]{String.valueOf(usuario.getCodigoUsuario())});

                if (rows > 0) {
                    db.setTransactionSuccessful();
                    usuarioID = usuario.getCodigoUsuario();
                }

            } catch (Exception e) {
                Log.d(UsuarioHelper.class.getName(), e.getMessage());
            } finally {
                db.endTransaction();
            }
        }
        return usuarioID;
    }

    public ArrayList<EntUsuario> obtenerUsuarios() {
        ArrayList<EntUsuario> usuarios = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_USUARIO, null);

        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String correo = cursor.getString(2);
                String telefono = cursor.getString(3);
                String password = cursor.getString(4);
                int codigoRol = cursor.getInt(5);

                EntUsuario usuario = new EntUsuario(codigo, nombre, correo, telefono, password, codigoRol);
                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        return usuarios;
    }

    public EntUsuario obtenerUsuario(int idUsuario) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_USUARIO + " where " + KEY_COL_CODIGO + "=" + idUsuario, null);
        EntUsuario usuario = null;
        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String correo = cursor.getString(2);
                String telefono = cursor.getString(3);
                String password = cursor.getString(4);
                int codigoRol = cursor.getInt(5);

                usuario = new EntUsuario(codigo, nombre, correo, telefono, password, codigoRol);

            } while (cursor.moveToNext());
        }
        return usuario;
    }

    public int borrarUsuario(int codigo) {
        SQLiteDatabase db = getWritableDatabase();
        int borrados = 0;

        db.beginTransaction();

        try {
            borrados = db.delete(TABLA_USUARIO, "codigo = ?", new String[]{String.valueOf(codigo)});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(UsuarioHelper.class.getName(), e.getMessage());
        } finally {
            db.endTransaction();
        }
        return borrados;
    }

    public EntUsuario obtenerNombreUsuario(String nombreUsuario) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_USUARIO + " where " + KEY_COL_NOMBRE + "='" + nombreUsuario + "'", null);
        EntUsuario usuario = null;
        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String correo = cursor.getString(2);
                String telefono = cursor.getString(3);
                String password = cursor.getString(4);
                int codigoRol = cursor.getInt(5);

                usuario = new EntUsuario(codigo, nombre, correo, telefono, password, codigoRol);
                System.out.println("kia");

            } while (cursor.moveToNext());
        }

        return usuario;
    }


}
