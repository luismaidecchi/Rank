package com.example.luismaidecchi.rank;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class Ranking extends Activity {

    private String IDCodigoRanking;
    private int[] vCodigoRanking;

    Button ButtonInserirRanking ;
    Button ButtonAlterarRanking ;
    Button ButtonExcluirRanking;
    Button ButtonListaRanking ;

    private EditText descricao;
    private EditText data;

    private Spinner spinnerListaRanking;

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_ranking);

        ButtonInserirRanking = (Button) findViewById(R.id.buttonInserirRanking);
        ButtonAlterarRanking = (Button) findViewById(R.id.buttonAlterarRanking);
        ButtonExcluirRanking = (Button) findViewById(R.id.buttonExcluirRanking);
        ButtonListaRanking = (Button) findViewById(R.id.buttonListaRanking);

        descricao = (EditText) findViewById(R.id.editDescricaoRanking);

        ButtonInserirRanking.setEnabled(false);
        ButtonAlterarRanking.setEnabled(false);
        ButtonExcluirRanking.setEnabled(false);

        CarregaSpinnerRanking();

        descricao.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String TextoDigitado = s.toString();

                if (TextoDigitado.isEmpty()) {
                    ButtonInserirRanking.setEnabled(false);
                } else {
                    ButtonInserirRanking.setEnabled(true);
                }
            }

            public void afterTextChanged(Editable s) {
            }
        });

        ButtonInserirRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BancoController crud = new BancoController(getBaseContext());

                descricao = (EditText) findViewById(R.id.editDescricaoRanking);
                data = (EditText) findViewById((R.id.editDataRanking));

                String Descricao = descricao.getText().toString();
                String Data = data.getText().toString();

                String resultado;

                resultado = crud.insereDadoRanking(Descricao, Data);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                CarregaSpinnerRanking();

                descricao.setText("");
                data.setText("");

                descricao.requestFocus();
            }
        });

        ButtonAlterarRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BancoController crud = new BancoController(getBaseContext());

                descricao = (EditText) findViewById(R.id.editDescricaoRanking);
                data = (EditText) findViewById((R.id.editDataRanking));

                String Descricao = descricao.getText().toString();
                String Data = data.getText().toString();

                String resultado;

                resultado = crud.AlteraDadoRanking(Descricao, Data, Integer.parseInt(IDCodigoRanking));

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                CarregaSpinnerRanking();

                descricao.setText("");
                data.setText("");

                descricao.requestFocus();
            }
        });

        ButtonExcluirRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BancoController crud = new BancoController(getBaseContext());

                String resultado;

                resultado = crud.ExcluiDadoRanking(Integer.parseInt(IDCodigoRanking));

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                CarregaSpinnerRanking();

                descricao.setText("");
                data.setText("");

                descricao.requestFocus();
            }
        });

        ButtonListaRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.layout_ranking_lista);

                BancoController crud = new BancoController(getBaseContext());

                Cursor cursor = crud.carregaDadosRanking();

                String[] nomeCampos = new String[]{CriaBanco.DESCRICAORANKING, CriaBanco.IDRANKING, CriaBanco.DATARANKING};
                int[] idViews = new int[]{R.id.DescricaoRanking_Ranking, R.id._id_Ranking_Ranking, R.id.DataRanking_Ranking};

                SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.layout_ranking_lista_lista, cursor, nomeCampos, idViews, 0);

                lista = (ListView) findViewById(R.id.ListaRanking);
                lista.setAdapter(adaptador);
            }
        });

        spinnerListaRanking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                descricao = (EditText) findViewById(R.id.editDescricaoRanking);
                data = (EditText) findViewById((R.id.editDataRanking));

                if (id != 0) {
                    IDCodigoRanking = String.valueOf(vCodigoRanking[(int) id]);

                    BancoController crud = new BancoController(getBaseContext());

                    Cursor cursor = crud.carregaDadosRankingPorId(Integer.parseInt(IDCodigoRanking));

                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            cursor.moveToFirst();
                            descricao.setText(cursor.getString(cursor.getColumnIndex("DescricaoRanking")));
                            data.setText(cursor.getString(cursor.getColumnIndex("DataRanking")));

                            ButtonAlterarRanking.setEnabled(true);
                            ButtonExcluirRanking.setEnabled(true);
                        }
                    }
                }
                else
                {
                    ButtonInserirRanking.setEnabled(false);
                    ButtonAlterarRanking.setEnabled(false);
                    ButtonExcluirRanking.setEnabled(false);

                    descricao.setText("");
                    data.setText("");
                }

                descricao.requestFocus();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void CarregaSpinnerRanking() {

        int TotalRegistrosRanking = 0;

        spinnerListaRanking = (Spinner) findViewById(R.id.spinnerRanking);

        BancoController crud = new BancoController(getBaseContext());

        Cursor cursor = crud.carregaDadosRanking();

        List<String> vListaRanking = new ArrayList<>();

        vListaRanking.add("Selecione o Ranking");

        if (cursor != null) {
            cursor.moveToFirst();

            vCodigoRanking = new int[cursor.getCount() + 1];
            vCodigoRanking[0] = 0;

            while (TotalRegistrosRanking <= (cursor.getCount() - 1)) {
                vListaRanking.add(cursor.getString(cursor.getColumnIndex("DescricaoRanking")).trim() );
                TotalRegistrosRanking++;
                vCodigoRanking[TotalRegistrosRanking] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")).toString().trim());
                cursor.moveToNext();

            }

            ArrayAdapter<String> adaptadorSpinnerRanking = new ArrayAdapter<String>(Ranking.this, android.R.layout.simple_gallery_item, vListaRanking);

            spinnerListaRanking.setAdapter(adaptadorSpinnerRanking);

            descricao.requestFocus();
        }
    }
}
