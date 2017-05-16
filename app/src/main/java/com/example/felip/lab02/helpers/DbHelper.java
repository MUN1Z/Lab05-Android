package com.example.felip.lab02.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "dbPedido";
    public static final int VERSAO_BANCO = 2;

    public DbHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format(
                "CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT NOT NULL," +
                "%s TEXT NOT NULL)",
                DbContract.Pedido.TABELA,
                DbContract.Pedido.CAMPO_ID,
                DbContract.Pedido.CAMPO_PAO,
                DbContract.Pedido.CAMPO_RECHEIO));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.Pedido.TABELA);
        onCreate(db);
    }
}