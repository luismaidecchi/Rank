package com.example.luismaidecchi.rank;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itemMenuTimes) {
            Intent times = new Intent(this, Times.class);
            startActivity(times);
            return true;
        }

        if (id == R.id.itemMenuTorneios) {
            Intent torneios = new Intent(this, Torneios.class);
            startActivity(torneios);
            return true;
        }

        if (id == R.id.itemMenuRanking) {
            Intent ranking = new Intent(this, Ranking.class);
            startActivity(ranking);
            return true;
        }

        if (id == R.id.itemMenuLancamentos) {
            Intent lancamentos = new Intent(this, Lancamentos.class);
            startActivity(lancamentos);
            return true;
        }

        if (id == R.id.itemMenuRank) {
            Intent rank = new Intent(this, Rank.class);
            startActivity(rank);
            return true;
        }

        if (id == R.id.itemMenuFechar) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
