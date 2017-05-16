package com.example.felip.lab02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    private String pao = "", salada = "", recheio = "";
    private double valorTotal = 0;

    private TextView mTvPao;
    private TextView mTvRecheio;
    private TextView mTvSalada;
    private TextView mTvValor;
    private Button mBtnPedido;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mTvPao = (TextView) findViewById(R.id.resultTvPao);
        mTvSalada = (TextView) findViewById(R.id.resultTvSalada);
        mTvRecheio = (TextView) findViewById(R.id.resultTvRecheio);
        mTvValor = (TextView) findViewById(R.id.resultTvValor);

        mBtnPedido = (Button) findViewById(R.id.resultBtnNovo);

        if(getIntent() != null){
            Bundle b = getIntent().getExtras();
            pao = b.getString("pao");
            salada = b.getString("salada");
            recheio = b.getString("recheio");
            valorTotal = b.getDouble("valor");
        }

        mTvPao.setText("Pão: " + pao);
        mTvSalada.setText("Salada: " + salada);
        mTvRecheio.setText("Recheio: " + recheio);
        mTvValor.setText("Valor Total: R$ " + valorTotal);

        mBtnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finish();
            }
        });

    }
}
