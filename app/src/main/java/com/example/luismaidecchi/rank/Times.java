package com.example.luismaidecchi.rank;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import android.text.TextWatcher;

public class Times extends Activity {

    private String IDCodigoTimes;
    private int[] vCodigoTimes;

    Button ButtonInserirTimes;
    Button ButtonAlterarTimes;
    Button ButtonExcluirTimes;
    Button ButtonListaTimes;

    private EditText descricao;
    private EditText fundacao;

    private Spinner spinnerListaTimes;

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_times);

        ButtonInserirTimes = (Button) findViewById(R.id.buttonInserirTimes);
        ButtonAlterarTimes = (Button) findViewById(R.id.buttonAlterarTimes);
        ButtonExcluirTimes = (Button) findViewById(R.id.buttonExcluirTimes);
        ButtonListaTimes = (Button) findViewById(R.id.buttonListaTimes);

        descricao = (EditText) findViewById(R.id.editDescricaoTimes);

        ButtonInserirTimes.setEnabled(false);
        ButtonAlterarTimes.setEnabled(false);
        ButtonExcluirTimes.setEnabled(false);

        CarregaSpinnerTimes();

        descricao.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String TextoDigitado = s.toString();

                if (TextoDigitado.isEmpty()) {
                    ButtonInserirTimes.setEnabled(false);
                } else {
                    ButtonInserirTimes.setEnabled(true);
                }
            }

            public void afterTextChanged(Editable s) {
            }
        });

        ButtonInserirTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BancoController crud = new BancoController(getBaseContext());

                descricao = (EditText) findViewById(R.id.editDescricaoTimes);
                fundacao = (EditText) findViewById((R.id.editFundacaoTimes));

                String Descricao = descricao.getText().toString();
                String Fundacao = fundacao.getText().toString();

                String resultado;

                resultado = crud.insereDadoTimes(Descricao, Fundacao);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                CarregaSpinnerTimes();

                descricao.setText("");
                fundacao.setText("");

                descricao.requestFocus();
            }
        });

        ButtonAlterarTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BancoController crud = new BancoController(getBaseContext());

                descricao = (EditText) findViewById(R.id.editDescricaoTimes);
                fundacao = (EditText) findViewById((R.id.editFundacaoTimes));

                String Descricao = descricao.getText().toString();
                String Fundacao = fundacao.getText().toString();

                String resultado;

                resultado = crud.AlteraDadoTimes(Descricao, Fundacao, Integer.parseInt(IDCodigoTimes));

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                CarregaSpinnerTimes();

                descricao.setText("");
                fundacao.setText("");

                descricao.requestFocus();
            }
        });

        ButtonExcluirTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BancoController crud = new BancoController(getBaseContext());

                String resultado;

                resultado = crud.ExcluiDadoTimes(Integer.parseInt(IDCodigoTimes));

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                CarregaSpinnerTimes();

                descricao.setText("");
                fundacao.setText("");

                descricao.requestFocus();
            }
        });

        ButtonListaTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.layout_times_lista);

                BancoController crud = new BancoController(getBaseContext());

                Cursor cursor = crud.carregaDadosTimes();

                String[] nomeCampos = new String[]{CriaBanco.DESCRICAOTIMES, CriaBanco.IDTIMES, CriaBanco.FUNDACAOTIMES};
                int[] idViews = new int[]{R.id.DescricaoTimes_Times, R.id._id_Times_Times, R.id.FundacaoTimes_Times};

                SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.layout_times_lista_lista, cursor, nomeCampos, idViews, 0);

                lista = (ListView) findViewById(R.id.ListaTimes);
                lista.setAdapter(adaptador);
            }
        });

        spinnerListaTimes = (Spinner) findViewById(R.id.spinnerTimes);

        spinnerListaTimes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                descricao = (EditText) findViewById(R.id.editDescricaoTimes);
                fundacao = (EditText) findViewById((R.id.editFundacaoTimes));

                if (id != 0) {
                    IDCodigoTimes = String.valueOf(vCodigoTimes[(int) id]);

                    BancoController crud = new BancoController(getBaseContext());

                    Cursor cursor = crud.carregaDadosTimesPorId(Integer.parseInt(IDCodigoTimes));

                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            cursor.moveToFirst();
                            descricao.setText(cursor.getString(cursor.getColumnIndex("DescricaoTimes")));
                            fundacao.setText(cursor.getString(cursor.getColumnIndex("FundacaoTimes")));

                            ButtonAlterarTimes.setEnabled(true);
                            ButtonExcluirTimes.setEnabled(true);
                        }
                    }
                } else {
                    ButtonInserirTimes.setEnabled(false);
                    ButtonAlterarTimes.setEnabled(false);
                    ButtonExcluirTimes.setEnabled(false);

                    descricao.setText("");
                    fundacao.setText("");
                }

                descricao.requestFocus();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void CarregaSpinnerTimes() {

        int TotalRegistrosTimes = 0;

        spinnerListaTimes = (Spinner) findViewById(R.id.spinnerTimes);

        BancoController crud = new BancoController(getBaseContext());

        Cursor cursor = crud.carregaDadosTimes();

        List<String> vListaTimes = new ArrayList<>();

        vListaTimes.add("Selecione o Time");

        if (cursor != null) {
            cursor.moveToFirst();

            vCodigoTimes = new int[cursor.getCount() + 1];
            vCodigoTimes[0] = 0;

            while (TotalRegistrosTimes <= (cursor.getCount() - 1)) {
                vListaTimes.add(cursor.getString(cursor.getColumnIndex("DescricaoTimes")).trim());
                TotalRegistrosTimes++;
                vCodigoTimes[TotalRegistrosTimes] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")).toString().trim());
                cursor.moveToNext();
            }

            ArrayAdapter<String> adaptadorSpinnerTimes = new ArrayAdapter<String>(Times.this, android.R.layout.simple_gallery_item, vListaTimes);

            spinnerListaTimes.setAdapter(adaptadorSpinnerTimes);

            descricao.requestFocus();
        }
    }
}