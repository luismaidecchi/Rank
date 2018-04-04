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

public class Torneios extends Activity {

    private String IDCodigoTorneios;
    private int[] vCodigoTorneios;

    Button ButtonInserirTorneios;
    Button ButtonAlterarTorneios;
    Button ButtonExcluirTorneios;
    Button ButtonListaTorneios;

    private EditText descricao;
    private EditText data;
    private EditText pontos1lugar;
    private EditText pontos2lugar;
    private EditText pontos3lugar;

    private Spinner spinnerListaTorneios;

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_torneios);

        ButtonInserirTorneios = (Button) findViewById(R.id.buttonInserirTorneios);
        ButtonAlterarTorneios = (Button) findViewById(R.id.buttonAlterarTorneios);
        ButtonExcluirTorneios = (Button) findViewById(R.id.buttonExcluirTorneios);
        ButtonListaTorneios = (Button) findViewById(R.id.buttonListaTorneios);

        descricao = (EditText) findViewById(R.id.editDescricaoTorneios);

        ButtonInserirTorneios.setEnabled(false);
        ButtonAlterarTorneios.setEnabled(false);
        ButtonExcluirTorneios.setEnabled(false);

        CarregaSpinnerTorneios();

        descricao.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String TextoDigitado = s.toString();

                if (TextoDigitado.isEmpty()) {
                    ButtonInserirTorneios.setEnabled(false);
                } else {
                    ButtonInserirTorneios.setEnabled(true);
                }
            }

            public void afterTextChanged(Editable s) {
            }
        });

        ButtonInserirTorneios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BancoController crud = new BancoController(getBaseContext());

                descricao = (EditText) findViewById(R.id.editDescricaoTorneios);
                data = (EditText) findViewById((R.id.editDataTorneios));
                pontos1lugar = (EditText) findViewById((R.id.editPontos1LugarTorneios));
                pontos2lugar = (EditText) findViewById((R.id.editPontos2LugarTorneios));
                pontos3lugar = (EditText) findViewById((R.id.editPontos3LugarTorneios));

                String Descricao = descricao.getText().toString();
                String Data = data.getText().toString();
                String Pontos1Lugar = pontos1lugar.getText().toString();
                String Pontos2Lugar = pontos2lugar.getText().toString();
                String Pontos3Lugar = pontos3lugar.getText().toString();

                String resultado;

                resultado = crud.insereDadoTorneios(Descricao, Data, Pontos1Lugar, Pontos2Lugar, Pontos3Lugar);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                CarregaSpinnerTorneios();

                descricao.setText("");
                data.setText("");
                pontos1lugar.setText("");
                pontos2lugar.setText("");
                pontos3lugar.setText("");

                descricao.requestFocus();
            }
        });

        ButtonAlterarTorneios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BancoController crud = new BancoController(getBaseContext());

                descricao = (EditText) findViewById(R.id.editDescricaoTorneios);
                data = (EditText) findViewById((R.id.editDataTorneios));
                pontos1lugar = (EditText) findViewById((R.id.editPontos1LugarTorneios));
                pontos2lugar = (EditText) findViewById((R.id.editPontos2LugarTorneios));
                pontos3lugar = (EditText) findViewById((R.id.editPontos3LugarTorneios));

                String Descricao = descricao.getText().toString();
                String Data = data.getText().toString();
                String Pontos1Lugar = pontos1lugar.getText().toString();
                String Pontos2Lugar = pontos2lugar.getText().toString();
                String Pontos3Lugar = pontos3lugar.getText().toString();

                String resultado;

                resultado = crud.AlteraDadoTorneios(Descricao, Data, Pontos1Lugar, Pontos2Lugar, Pontos3Lugar, Integer.parseInt(IDCodigoTorneios));

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                CarregaSpinnerTorneios();

                descricao.setText("");
                data.setText("");
                pontos1lugar.setText("");
                pontos2lugar.setText("");
                pontos3lugar.setText("");

                descricao.requestFocus();
            }
        });

        ButtonExcluirTorneios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BancoController crud = new BancoController(getBaseContext());

                String resultado;

                resultado = crud.ExcluiDadoTorneios(Integer.parseInt(IDCodigoTorneios));

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                CarregaSpinnerTorneios();

                descricao.setText("");
                data.setText("");
                pontos1lugar.setText("");
                pontos2lugar.setText("");
                pontos3lugar.setText("");

                descricao.requestFocus();
            }
        });

        ButtonListaTorneios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.layout_torneios_lista);

                BancoController crud = new BancoController(getBaseContext());

                Cursor cursor = crud.carregaDadosTorneios();

                String[] nomeCampos = new String[]{CriaBanco.DESCRICAOTORNEIOS, CriaBanco.IDTORNEIOS, CriaBanco.DATATORNEIOS, CriaBanco.PONTOS1LUGARTORNEIOS, CriaBanco.PONTOS2LUGARTORNEIOS, CriaBanco.PONTOS3LUGARTORNEIOS};
                int[] idViews = new int[]{R.id.DescricaoTorneios_Torneios, R.id._id_Torneios_Torneios, R.id.DataTorneios_Torneios, R.id.Pontos1Lugar_Torneios, R.id.Pontos2Lugar_Torneios, R.id.Pontos3Lugar_Torneios};

                SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.layout_torneios_lista_lista, cursor, nomeCampos, idViews, 0);

                lista = (ListView) findViewById(R.id.ListaTorneios);
                lista.setAdapter(adaptador);
            }
        });

        spinnerListaTorneios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                descricao = (EditText) findViewById(R.id.editDescricaoTorneios);
                data = (EditText) findViewById((R.id.editDataTorneios));
                pontos1lugar = (EditText) findViewById((R.id.editPontos1LugarTorneios));
                pontos2lugar = (EditText) findViewById((R.id.editPontos2LugarTorneios));
                pontos3lugar = (EditText) findViewById((R.id.editPontos3LugarTorneios));

                 if (id != 0) {
                    IDCodigoTorneios = String.valueOf(vCodigoTorneios[(int) id]);

                    BancoController crud = new BancoController(getBaseContext());

                    Cursor cursor = crud.carregaDadosTorneiosPorId(Integer.parseInt(IDCodigoTorneios));

                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            cursor.moveToFirst();
                            descricao.setText(cursor.getString(cursor.getColumnIndex("DescricaoTorneios")));
                            data.setText(cursor.getString(cursor.getColumnIndex("DataTorneios")));
                            pontos1lugar.setText(cursor.getString(cursor.getColumnIndex("Pontos1Lugar")));
                            pontos2lugar.setText(cursor.getString(cursor.getColumnIndex("Pontos2Lugar")));
                            pontos3lugar.setText(cursor.getString(cursor.getColumnIndex("Pontos3Lugar")));

                            ButtonAlterarTorneios.setEnabled(true);
                            ButtonExcluirTorneios.setEnabled(true);
                        }
                    }
                } else {
                    ButtonInserirTorneios.setEnabled(false);
                    ButtonAlterarTorneios.setEnabled(false);
                    ButtonExcluirTorneios.setEnabled(false);

                    descricao.setText("");
                    data.setText("");
                    pontos1lugar.setText("");
                    pontos2lugar.setText("");
                    pontos3lugar.setText("");
                }

                descricao.requestFocus();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void CarregaSpinnerTorneios() {

        int TotalRegistrosTorneios = 0;

        spinnerListaTorneios = (Spinner) findViewById(R.id.spinnerTorneios);

        BancoController crud = new BancoController(getBaseContext());

        Cursor cursor = crud.carregaDadosTorneios();

        List<String> vListaTorneios = new ArrayList<>();

        vListaTorneios.add("Selecione o Torneio");

        if (cursor != null) {
            cursor.moveToFirst();

            vCodigoTorneios = new int[cursor.getCount() + 1];
            vCodigoTorneios[0] = 0;

            while (TotalRegistrosTorneios <= (cursor.getCount() - 1)) {
                vListaTorneios.add(cursor.getString(cursor.getColumnIndex("DescricaoTorneios")).trim() );
                TotalRegistrosTorneios++;
                vCodigoTorneios[TotalRegistrosTorneios] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")).toString().trim());
                cursor.moveToNext();
            }

            ArrayAdapter<String> adaptadorSpinnerTorneios = new ArrayAdapter<String>(Torneios.this, android.R.layout.simple_gallery_item, vListaTorneios);

            spinnerListaTorneios.setAdapter(adaptadorSpinnerTorneios);

            descricao.requestFocus();
        }
    }

}
