package com.example.felip.lab02.helpers;

public interface DbContract {
    interface Pedido {
        String TABELA = "pedido";
        String CAMPO_ID = "_id";
        String CAMPO_PAO = "pao";
        String CAMPO_RECHEIO = "recheio";
    }
}