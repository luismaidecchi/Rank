package com.example.luismaidecchi.rank;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Lancamentos extends Activity {

    private String IDCodigoLancamentos;
    private int[] vIDCodigoLancamentos;

    private String IDCodigoRankingLancamentos;
    private String IDAtualCodigoRankingLancamentos = "";
    private int[] vIDCodigoRankingLancamentos;
    private int PosicaospinnerRankingLancamentos = 0;

    private String IDCodigoTorneiosLancamentos;
    private String IDAtualCodigoTorneiosLancamentos = "";
    private int[] vIDCodigoTorneiosLancamentos;
    private int PosicaospinnerTorneiosLancamentos = 0;

    private String IDCodigoTimesLancamentos;
    private String IDAtualCodigoTimesLancamentos = "";
    private int[] vIDCodigoTimesLancamentos;
    private int PosicaospinnerTimesLancamentos = 0;

    private String ClassificacaoTorneiosLancamentos;

    private Button ButtonInserirLancamentos;
    private Button ButtonAlterarLancamentos;
    private Button ButtonExcluirLancamentos;
    private Button ButtonListaLancamentos;

    private Spinner spinnerListaLancamentos;
    private Spinner spinnerListaRankingLancamentos;
    private Spinner spinnerListaTorneiosLancamentos;
    private Spinner spinnerListaTimesLancamentos;

    private RadioButton radioButtonClassificacao1Lancamentos;
    private RadioButton radioButtonClassificacao2Lancamentos;
    private RadioButton radioButtonClassificacao3Lancamentos;

    private EditText TextPontosLancamentos;

    private double Pontos1LugarLancamentos;
    private double Pontos2LugarLancamentos;
    private double Pontos3LugarLancamentos;

    private Rotinas RT = new Rotinas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_lancamentos);

        ButtonInserirLancamentos = (Button) findViewById(R.id.buttonInserirLancamentos);
        ButtonAlterarLancamentos = (Button) findViewById(R.id.buttonAlterarLancamentos);
        ButtonExcluirLancamentos = (Button) findViewById(R.id.buttonExcluirLancamentos);
        ButtonListaLancamentos = (Button) findViewById(R.id.buttonListaLancamentos);

        spinnerListaRankingLancamentos = (Spinner) findViewById(R.id.spinnerLancamentosRanking);
        spinnerListaTorneiosLancamentos = (Spinner) findViewById(R.id.spinnerLancamentosTorneios);
        spinnerListaTimesLancamentos = (Spinner) findViewById(R.id.spinnerLancamentosTimes);

        radioButtonClassificacao1Lancamentos = (RadioButton) findViewById(R.id.radioButtonClassificacao1LugarLancamentos);
        radioButtonClassificacao2Lancamentos = (RadioButton) findViewById(R.id.radioButtonClassificacao2LugarLancamentos);
        radioButtonClassificacao3Lancamentos = (RadioButton) findViewById(R.id.radioButtonClassificacao3LugarLancamentos);

        TextPontosLancamentos = (EditText) findViewById(R.id.editPontosLancamentos);

        ButtonInserirLancamentos.setEnabled(false);
        ButtonAlterarLancamentos.setEnabled(false);
        ButtonExcluirLancamentos.setEnabled(false);

        spinnerListaTorneiosLancamentos.setEnabled(false);
        spinnerListaTimesLancamentos.setEnabled(false);

        radioButtonClassificacao1Lancamentos.setEnabled(false);
        radioButtonClassificacao2Lancamentos.setEnabled(false);
        radioButtonClassificacao3Lancamentos.setEnabled(false);

        TextPontosLancamentos.setEnabled(false);

        CarregaSpinnerLancamentos();
        CarregaSpinnerRankingLancamentos();
        CarregaSpinnerTorneiosLancamentos();
        CarregaSpinnerTimesLancamentos();

        ButtonInserirLancamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextPontosLancamentos = (EditText) findViewById(R.id.editPontosLancamentos);

                BancoController crud = new BancoController(getBaseContext());

                String resultado;

                resultado = crud.insereDadoLancamentos(IDCodigoRankingLancamentos, IDCodigoTorneiosLancamentos, IDCodigoTimesLancamentos, ClassificacaoTorneiosLancamentos, TextPontosLancamentos.getText().toString());

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                ButtonInserirLancamentos.setEnabled(false);
                ButtonAlterarLancamentos.setEnabled(false);
                ButtonExcluirLancamentos.setEnabled(false);

                CarregaSpinnerLancamentos();
                CarregaSpinnerRankingLancamentos();
                CarregaSpinnerTorneiosLancamentos();
                CarregaSpinnerTimesLancamentos();

                spinnerListaTorneiosLancamentos.setEnabled(false);
                spinnerListaTimesLancamentos.setEnabled(false);

                radioButtonClassificacao1Lancamentos.setChecked(false);
                radioButtonClassificacao2Lancamentos.setChecked(false);
                radioButtonClassificacao3Lancamentos.setChecked(false);

                radioButtonClassificacao1Lancamentos.setEnabled(false);
                radioButtonClassificacao2Lancamentos.setEnabled(false);
                radioButtonClassificacao3Lancamentos.setEnabled(false);

                TextPontosLancamentos.setText("");

                spinnerListaRankingLancamentos.requestFocus();
            }
        });

        ButtonAlterarLancamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BancoController crud = new BancoController(getBaseContext());

                String resultado;

                resultado = crud.AlteraDadoLancamentos(IDCodigoLancamentos, IDCodigoRankingLancamentos, IDCodigoTorneiosLancamentos, IDCodigoTimesLancamentos, ClassificacaoTorneiosLancamentos, TextPontosLancamentos.getText().toString());

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                ButtonInserirLancamentos.setEnabled(false);
                ButtonAlterarLancamentos.setEnabled(false);
                ButtonExcluirLancamentos.setEnabled(false);

                CarregaSpinnerLancamentos();
                CarregaSpinnerRankingLancamentos();
                CarregaSpinnerTorneiosLancamentos();
                CarregaSpinnerTimesLancamentos();

                spinnerListaTorneiosLancamentos.setEnabled(false);
                spinnerListaTimesLancamentos.setEnabled(false);

                radioButtonClassificacao1Lancamentos.setChecked(false);
                radioButtonClassificacao2Lancamentos.setChecked(false);
                radioButtonClassificacao3Lancamentos.setChecked(false);

                radioButtonClassificacao1Lancamentos.setEnabled(false);
                radioButtonClassificacao2Lancamentos.setEnabled(false);
                radioButtonClassificacao3Lancamentos.setEnabled(false);

                TextPontosLancamentos.setText("");

                spinnerListaRankingLancamentos.requestFocus();
            }
        });

        ButtonExcluirLancamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BancoController crud = new BancoController(getBaseContext());

                String resultado;

                resultado = crud.ExcluiDadoLancamentos(Integer.parseInt(IDCodigoLancamentos));

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                ButtonInserirLancamentos.setEnabled(false);
                ButtonAlterarLancamentos.setEnabled(false);
                ButtonExcluirLancamentos.setEnabled(false);

                CarregaSpinnerLancamentos();
                CarregaSpinnerRankingLancamentos();
                CarregaSpinnerTorneiosLancamentos();
                CarregaSpinnerTimesLancamentos();

                radioButtonClassificacao1Lancamentos.setChecked(false);
                radioButtonClassificacao2Lancamentos.setChecked(false);
                radioButtonClassificacao3Lancamentos.setChecked(false);

                radioButtonClassificacao1Lancamentos.setEnabled(false);
                radioButtonClassificacao2Lancamentos.setEnabled(false);
                radioButtonClassificacao3Lancamentos.setEnabled(false);

                TextPontosLancamentos.setText("");

                spinnerListaRankingLancamentos.requestFocus();
            }
        });

        radioButtonClassificacao1Lancamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextPontosLancamentos.setText(String.valueOf(Pontos1LugarLancamentos));
                ClassificacaoTorneiosLancamentos = "1";
                ButtonInserirLancamentos.setEnabled(true);
            }
        });

        radioButtonClassificacao2Lancamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextPontosLancamentos.setText(String.valueOf(Pontos2LugarLancamentos));
                ClassificacaoTorneiosLancamentos = "2";
                ButtonInserirLancamentos.setEnabled(true);
            }
        });

        radioButtonClassificacao3Lancamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextPontosLancamentos.setText(String.valueOf(Pontos3LugarLancamentos));
                ClassificacaoTorneiosLancamentos = "3";
                ButtonInserirLancamentos.setEnabled(true);
            }
        });

        spinnerListaLancamentos = (Spinner) findViewById(R.id.spinnerLancamentos);

        spinnerListaLancamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (id != 0) {
                    IDCodigoLancamentos = String.valueOf(vIDCodigoLancamentos[(int) id]);

                    BancoController crud = new BancoController(getBaseContext());

                    Cursor cursor = crud.carregaDadosLancamentoPorId(Integer.parseInt(IDCodigoLancamentos));

                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            cursor.moveToFirst();

                            IDAtualCodigoRankingLancamentos = cursor.getString(cursor.getColumnIndex("Id_Ranking"));
                            IDAtualCodigoTorneiosLancamentos = cursor.getString(cursor.getColumnIndex("Id_Torneios"));
                            IDAtualCodigoTimesLancamentos = cursor.getString(cursor.getColumnIndex("Id_Times"));

                            IDCodigoRankingLancamentos = cursor.getString(cursor.getColumnIndex("Id_Ranking"));
                            IDCodigoTorneiosLancamentos = cursor.getString(cursor.getColumnIndex("Id_Torneios"));
                            IDCodigoTimesLancamentos = cursor.getString(cursor.getColumnIndex("Id_Times"));

                            ClassificacaoTorneiosLancamentos = cursor.getString(cursor.getColumnIndex("Classificacao"));

                            radioButtonClassificacao1Lancamentos.setEnabled(true);
                            radioButtonClassificacao2Lancamentos.setEnabled(true);
                            radioButtonClassificacao3Lancamentos.setEnabled(true);

                            if (ClassificacaoTorneiosLancamentos.contains("1")) {
                                radioButtonClassificacao1Lancamentos.setChecked(true);
                            }

                            if (ClassificacaoTorneiosLancamentos.contains("2")) {
                                radioButtonClassificacao2Lancamentos.setChecked(true);
                            }

                            if (ClassificacaoTorneiosLancamentos.contains("3")) {
                                radioButtonClassificacao3Lancamentos.setChecked(true);
                            }

                            TextPontosLancamentos.setText(String.valueOf(Double.parseDouble(cursor.getString(cursor.getColumnIndex("Pontos")))));

                            spinnerListaTorneiosLancamentos.setEnabled(true);
                            spinnerListaTimesLancamentos.setEnabled(true);

                            CarregaSpinnerRankingLancamentos();
                            CarregaSpinnerTorneiosLancamentos();
                            CarregaSpinnerTimesLancamentos();

                            spinnerListaRankingLancamentos.setSelection(PosicaospinnerRankingLancamentos);
                            spinnerListaTorneiosLancamentos.setSelection(PosicaospinnerTorneiosLancamentos);
                            spinnerListaTimesLancamentos.setSelection(PosicaospinnerTimesLancamentos);

                            ButtonInserirLancamentos.setEnabled(true);
                            ButtonAlterarLancamentos.setEnabled(true);
                            ButtonExcluirLancamentos.setEnabled(true);
                        }
                    }

                } else {
                    ButtonInserirLancamentos.setEnabled(false);
                    ButtonAlterarLancamentos.setEnabled(false);
                    ButtonExcluirLancamentos.setEnabled(false);

                    CarregaSpinnerRankingLancamentos();
                    CarregaSpinnerTorneiosLancamentos();
                    CarregaSpinnerTimesLancamentos();

                    radioButtonClassificacao1Lancamentos.setChecked(false);
                    radioButtonClassificacao2Lancamentos.setChecked(false);
                    radioButtonClassificacao3Lancamentos.setChecked(false);

                    radioButtonClassificacao1Lancamentos.setEnabled(false);
                    radioButtonClassificacao2Lancamentos.setEnabled(false);
                    radioButtonClassificacao3Lancamentos.setEnabled(false);

                    ClassificacaoTorneiosLancamentos = "";

                    TextPontosLancamentos.setText("");

                    spinnerListaRankingLancamentos.requestFocus();
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerListaRankingLancamentos = (Spinner) findViewById(R.id.spinnerLancamentosRanking);

        spinnerListaRankingLancamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (id != 0) {
                    IDCodigoRankingLancamentos = String.valueOf(vIDCodigoRankingLancamentos[(int) id]);

                    BancoController crud = new BancoController(getBaseContext());

                    Cursor cursor = crud.carregaDadosRankingPorId(Integer.parseInt(IDCodigoRankingLancamentos));

                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            cursor.moveToFirst();
                            spinnerListaTorneiosLancamentos.setEnabled(true);
                        }
                    }
                } else {
                    CarregaSpinnerTorneiosLancamentos();
                    CarregaSpinnerTimesLancamentos();

                    spinnerListaTorneiosLancamentos.setEnabled(false);
                    spinnerListaTimesLancamentos.setEnabled(false);

                    radioButtonClassificacao1Lancamentos.setChecked(false);
                    radioButtonClassificacao2Lancamentos.setChecked(false);
                    radioButtonClassificacao3Lancamentos.setChecked(false);

                    radioButtonClassificacao1Lancamentos.setEnabled(false);
                    radioButtonClassificacao2Lancamentos.setEnabled(false);
                    radioButtonClassificacao3Lancamentos.setEnabled(false);

                    TextPontosLancamentos.setText("");
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerListaTorneiosLancamentos = (Spinner) findViewById(R.id.spinnerLancamentosTorneios);

        spinnerListaTorneiosLancamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (id != 0) {
                    IDCodigoTorneiosLancamentos = String.valueOf(vIDCodigoTorneiosLancamentos[(int) id]);

                    BancoController crud = new BancoController(getBaseContext());

                    Cursor cursor = crud.carregaDadosTorneiosPorId(Integer.parseInt(IDCodigoTorneiosLancamentos));

                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            cursor.moveToFirst();
                            Pontos1LugarLancamentos = Double.parseDouble(cursor.getString(cursor.getColumnIndex("Pontos1Lugar")).toString());
                            Pontos2LugarLancamentos = Double.parseDouble(cursor.getString(cursor.getColumnIndex("Pontos2Lugar")).toString());
                            Pontos3LugarLancamentos = Double.parseDouble(cursor.getString(cursor.getColumnIndex("Pontos3Lugar")).toString().replace(",", "."));

                            spinnerListaTimesLancamentos.setEnabled(true);
                        }
                    }
                } else {
                    CarregaSpinnerTimesLancamentos();

                    spinnerListaTimesLancamentos.setEnabled(false);

                    radioButtonClassificacao1Lancamentos.setChecked(false);
                    radioButtonClassificacao2Lancamentos.setChecked(false);
                    radioButtonClassificacao3Lancamentos.setChecked(false);

                    radioButtonClassificacao1Lancamentos.setEnabled(false);
                    radioButtonClassificacao2Lancamentos.setEnabled(false);
                    radioButtonClassificacao3Lancamentos.setEnabled(false);

                    TextPontosLancamentos.setText("");
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerListaTimesLancamentos = (Spinner) findViewById(R.id.spinnerLancamentosTimes);

        spinnerListaTimesLancamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (id != 0) {
                    IDCodigoTimesLancamentos = String.valueOf(vIDCodigoTimesLancamentos[(int) id]);

                    BancoController crud = new BancoController(getBaseContext());

                    Cursor cursor = crud.carregaDadosTimesPorId(Integer.parseInt(IDCodigoTimesLancamentos));

                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            cursor.moveToFirst();
                            radioButtonClassificacao1Lancamentos.setEnabled(true);
                            radioButtonClassificacao2Lancamentos.setEnabled(true);
                            radioButtonClassificacao3Lancamentos.setEnabled(true);
                        }
                    }
                } else {
                    radioButtonClassificacao1Lancamentos.setChecked(false);
                    radioButtonClassificacao2Lancamentos.setChecked(false);
                    radioButtonClassificacao3Lancamentos.setChecked(false);

                    radioButtonClassificacao1Lancamentos.setEnabled(false);
                    radioButtonClassificacao2Lancamentos.setEnabled(false);
                    radioButtonClassificacao3Lancamentos.setEnabled(false);

                    TextPontosLancamentos.setText("");
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void CarregaSpinnerLancamentos() {

        int TotalRegistrosLancamentos = 0;

        spinnerListaLancamentos = (Spinner) findViewById(R.id.spinnerLancamentos);

        BancoController crud = new BancoController(getBaseContext());

        Cursor cursor = crud.carregaDadosLancamentos();

        List<String> vListaLancamentos = new ArrayList<>();

        vListaLancamentos.add("Selecione o Lançamento");

        if (cursor != null) {
            cursor.moveToFirst();

            vIDCodigoLancamentos = new int[cursor.getCount() + 1];
            vIDCodigoLancamentos[0] = 0;

            while (TotalRegistrosLancamentos <= (cursor.getCount() - 1)) {
                vListaLancamentos.add(cursor.getString(cursor.getColumnIndex("DescricaoTorneios")).trim() + "-" + cursor.getString(cursor.getColumnIndex("DescricaoTimes")).trim());
                TotalRegistrosLancamentos++;
                vIDCodigoLancamentos[TotalRegistrosLancamentos] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")).toString().trim());
                cursor.moveToNext();
            }

            ArrayAdapter<String> adaptadorSpinnerLancamentos = new ArrayAdapter<String>(Lancamentos.this, android.R.layout.simple_gallery_item, vListaLancamentos);

            spinnerListaLancamentos.setAdapter(adaptadorSpinnerLancamentos);
        }

        spinnerListaLancamentos.requestFocus();
    }

    private void CarregaSpinnerRankingLancamentos() {

        int TotalRegistrosRanking = 0;

        spinnerListaRankingLancamentos = (Spinner) findViewById(R.id.spinnerLancamentosRanking);

        BancoController crud = new BancoController(getBaseContext());

        Cursor cursor = crud.carregaDadosRankingLancamentos();

        List<String> vListaRankingLancamentos = new ArrayList<>();

        vListaRankingLancamentos.add("Selecione o Ranking");

        if (cursor != null) {
            cursor.moveToFirst();

            vIDCodigoRankingLancamentos = new int[cursor.getCount() + 1];
            vIDCodigoRankingLancamentos[0] = 0;

            while (TotalRegistrosRanking <= (cursor.getCount() - 1)) {
                vListaRankingLancamentos.add(cursor.getString(cursor.getColumnIndex("DescricaoRanking")).trim());
                TotalRegistrosRanking++;
                vIDCodigoRankingLancamentos[TotalRegistrosRanking] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")).toString().trim());

                if (IDAtualCodigoRankingLancamentos.equals(cursor.getString(cursor.getColumnIndex("_id")))) {
                    IDCodigoRankingLancamentos = cursor.getString(cursor.getColumnIndex("_id")).toString().trim();
                    PosicaospinnerRankingLancamentos = TotalRegistrosRanking;
                }

                cursor.moveToNext();
            }

            ArrayAdapter<String> adaptadorSpinnerRankingLancamentos = new ArrayAdapter<String>(Lancamentos.this, android.R.layout.simple_gallery_item, vListaRankingLancamentos);

            spinnerListaRankingLancamentos.setAdapter(adaptadorSpinnerRankingLancamentos);
        }
    }

    private void CarregaSpinnerTorneiosLancamentos() {

        int TotalRegistrosTorneios = 0;

        spinnerListaTorneiosLancamentos = (Spinner) findViewById(R.id.spinnerLancamentosTorneios);

        BancoController crud = new BancoController(getBaseContext());

        Cursor cursor = crud.carregaDadosTorneiosLancamentos();

        List<String> vListaTorneiosLancamentos = new ArrayList<>();

        vListaTorneiosLancamentos.add("Selecione o Torneio");

        if (cursor != null) {
            cursor.moveToFirst();

            vIDCodigoTorneiosLancamentos = new int[cursor.getCount() + 1];
            vIDCodigoTorneiosLancamentos[0] = 0;

            while (TotalRegistrosTorneios <= (cursor.getCount() - 1)) {
                vListaTorneiosLancamentos.add(cursor.getString(cursor.getColumnIndex("DescricaoTorneios")).trim());
                TotalRegistrosTorneios++;
                vIDCodigoTorneiosLancamentos[TotalRegistrosTorneios] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")).toString().trim());

                if (IDAtualCodigoTorneiosLancamentos.equals(cursor.getString(cursor.getColumnIndex("_id")))) {
                    IDCodigoTorneiosLancamentos = cursor.getString(cursor.getColumnIndex("_id")).toString().trim();
                    PosicaospinnerTorneiosLancamentos = TotalRegistrosTorneios;
                }

                cursor.moveToNext();
            }

            ArrayAdapter<String> adaptadorSpinnerTorneiosLancamentos = new ArrayAdapter<String>(Lancamentos.this, android.R.layout.simple_gallery_item, vListaTorneiosLancamentos);

            spinnerListaTorneiosLancamentos.setAdapter(adaptadorSpinnerTorneiosLancamentos);
        }
    }

    private void CarregaSpinnerTimesLancamentos() {

        int TotalRegistrosTimes = 0;

        spinnerListaTimesLancamentos = (Spinner) findViewById(R.id.spinnerLancamentosTimes);

        BancoController crud = new BancoController(getBaseContext());

        Cursor cursor = crud.carregaDadosTimesLancamentos();

        List<String> vListaTimesLancamentos = new ArrayList<>();

        vListaTimesLancamentos.add("Selecione o Time");

        if (cursor != null) {
            cursor.moveToFirst();

            vIDCodigoTimesLancamentos = new int[cursor.getCount() + 1];
            vIDCodigoTimesLancamentos[0] = 0;

            while (TotalRegistrosTimes <= (cursor.getCount() - 1)) {
                vListaTimesLancamentos.add(cursor.getString(cursor.getColumnIndex("DescricaoTimes")).trim());
                TotalRegistrosTimes++;
                vIDCodigoTimesLancamentos[TotalRegistrosTimes] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")).toString().trim());

                if (IDAtualCodigoTimesLancamentos.equals(cursor.getString(cursor.getColumnIndex("_id")))) {
                    IDCodigoTimesLancamentos = cursor.getString(cursor.getColumnIndex("_id")).toString().trim();
                    PosicaospinnerTimesLancamentos = TotalRegistrosTimes;
                }
                cursor.moveToNext();
            }

            ArrayAdapter<String> adaptadorSpinnerTimesLancamentos = new ArrayAdapter<String>(Lancamentos.this, android.R.layout.simple_gallery_item, vListaTimesLancamentos);

            spinnerListaTimesLancamentos.setAdapter(adaptadorSpinnerTimesLancamentos);
        }
    }
}
