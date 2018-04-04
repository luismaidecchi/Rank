package com.example.luismaidecchi.rank;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Rank extends Activity {

    private String IDCodigoRank;
    private int[] vCodigoRank;

    Button ButtonRankLista;

    private Spinner spinnerListaRank;

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_rank);

        ButtonRankLista = (Button) findViewById(R.id.buttonRankLista);

        CarregaSpinnerRank();

        ButtonRankLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.layout_rank_lista);

                BancoController crud = new BancoController(getBaseContext());

                Cursor cursor = crud.carregaDadosRank(IDCodigoRank);

                String[] nomeCampos = new String[]{ "DescricaoTimes", "Pontos", "_id"};
                int[] idViews = new int[]{R.id.DescricaoTimes_Rank, R.id.PontosRank_Rank};

                SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.layout_rank_lista_lista, cursor, nomeCampos, idViews, 0);

                lista = (ListView) findViewById(R.id.ListaRank);
                lista.setAdapter(adaptador);
            }
        });

        spinnerListaRank = (Spinner) findViewById(R.id.spinnerRank);

        spinnerListaRank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (id != 0) {
                    IDCodigoRank = String.valueOf(vCodigoRank[(int) id]);

                } else {

                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void CarregaSpinnerRank() {

        int TotalRegistrosRank = 0;

        spinnerListaRank = (Spinner) findViewById(R.id.spinnerRank);

        BancoController crud = new BancoController(getBaseContext());

        Cursor cursor = crud.carregaDadosRanking();

        List<String> vListaRank = new ArrayList<>();

        vListaRank.add("Selecione o Ranking");

        if (cursor != null) {
            cursor.moveToFirst();

            vCodigoRank = new int[cursor.getCount() + 1];
            vCodigoRank[0] = 0;

            while (TotalRegistrosRank <= (cursor.getCount() - 1)) {
                vListaRank.add(cursor.getString(cursor.getColumnIndex("DescricaoRanking")).trim() );
                TotalRegistrosRank++;
                vCodigoRank[TotalRegistrosRank] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")).toString().trim());
                cursor.moveToNext();
            }

            ArrayAdapter<String> adaptadorSpinnerRank = new ArrayAdapter<String>(Rank.this, android.R.layout.simple_gallery_item, vListaRank);

            spinnerListaRank.setAdapter(adaptadorSpinnerRank);
        }
    }
}
