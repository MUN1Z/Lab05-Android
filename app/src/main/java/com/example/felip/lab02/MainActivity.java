package com.example.felip.lab02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Spinner mSpnPao;
    private Button mBtnConfirmar;

    private CheckBox mChkFrango;
    private CheckBox mChkCarne;
    private CheckBox mChkBacon;
    private CheckBox mChkOvo;
    private CheckBox mChkPeixe;

    private RadioGroup mRgpSalada;

    private ArrayAdapter<String> mAdpPao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChkBacon = (CheckBox) findViewById(R.id.mainChkBacon);
        mChkCarne = (CheckBox) findViewById(R.id.mainChkCarne);
        mChkFrango = (CheckBox) findViewById(R.id.mainChkFrango);
        mChkOvo = (CheckBox) findViewById(R.id.mainChkOvo);
        mChkPeixe = (CheckBox) findViewById(R.id.mainChkPeixe);

        mRgpSalada = (RadioGroup) findViewById(R.id.mainRgpSalada);

        mSpnPao = (Spinner) findViewById(R.id.mainSpnPao);
        mBtnConfirmar = (Button) findViewById(R.id.mainBtnConfirmar);

        mAdpPao = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);

        mAdpPao.add("Bola - R$ 5,50");
        mAdpPao.add("Quadrado - R$ 5,50");
        mAdpPao.add("Triangulo - R$ 4,00");
        mAdpPao.add("Xis - R$ 4,00");

        mAdpPao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpnPao.setAdapter(mAdpPao);

        mBtnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double valorTotal = 0.0;

                String pao = mSpnPao.getSelectedItem().toString();

                if(pao.equals("Bola") || pao.equals("Quadrado"))
                    valorTotal += 5.50;
                else
                    valorTotal += 4;

                RadioButton mRbtnSelecionado = (RadioButton)mRgpSalada.findViewById(mRgpSalada.getCheckedRadioButtonId());

                String salada = "";

                if(mRbtnSelecionado != null){
                    salada = mRbtnSelecionado.getText().toString();
                    valorTotal += 4;
                }else{
                    Toast.makeText(getBaseContext(), "Selecione a porra da salada!", Toast.LENGTH_LONG).show();
                    return;
                }

                String recheio = "";

                if(mChkBacon.isChecked()){
                    recheio += ", " + mChkBacon.getText().toString();
                    valorTotal += 1;
                }
                if(mChkCarne.isChecked()) {
                    recheio += ", " + mChkCarne.getText().toString();
                    valorTotal += 2;
                }
                if(mChkFrango.isChecked()) {
                    recheio += ", " + mChkFrango.getText().toString();
                    valorTotal += 3;
                }
                if(mChkOvo.isChecked()) {
                    recheio += ", " + mChkOvo.getText().toString();
                    valorTotal += 4;
                }
                if(mChkPeixe.isChecked()) {
                    recheio += ", " + mChkPeixe.getText().toString();
                    valorTotal += 5;
                }

                if(recheio.length() > 1)
                    recheio = recheio.substring(2);
                else{
                    Toast.makeText(getBaseContext(), "Selecione a porra do recheio!", Toast.LENGTH_LONG).show();
                    return;
                }

                pao = pao.split(" - ")[0];

                startActivity(new Intent(getBaseContext(), ResultActivity.class)
                        .putExtra("pao", pao)
                        .putExtra("recheio", recheio)
                        .putExtra("salada", salada)
                        .putExtra("valor", valorTotal)
                );

                finish();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.teste, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,  ConfiguracoesActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
