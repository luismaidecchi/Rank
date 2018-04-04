package com.example.luismaidecchi.rank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "rank.db3";

    public static final String TABELATIMES = "Times";
    public static final String IDTIMES = "_id";
    public static final String DESCRICAOTIMES = "DescricaoTimes";
    public static final String FUNDACAOTIMES = "FundacaoTimes";

    public static final String TABELATORNEIOS = "Torneios";
    public static final String IDTORNEIOS = "_id";
    public static final String DESCRICAOTORNEIOS = "DescricaoTorneios";
    public static final String DATATORNEIOS = "DataTorneios";
    public static final String PONTOS1LUGARTORNEIOS = "Pontos1Lugar";
    public static final String PONTOS2LUGARTORNEIOS = "Pontos2Lugar";
    public static final String PONTOS3LUGARTORNEIOS = "Pontos3Lugar";

    public static final String TABELARANKING = "Ranking";
    public static final String IDRANKING = "_id";
    public static final String DESCRICAORANKING = "DescricaoRanking";
    public static final String DATARANKING = "DataRanking";

    public static final String TABELALANCAMENTOS = "Lancamentos";
    public static final String IDLANCAMENTOS = "_id";
    public static final String IDRANKINGLANCAMENTOS = "Id_Ranking";
    public static final String IDTORNEIOSLANCAMENTOS = "Id_Torneios";
    public static final String IDTIMESLANCAMENTOS = "Id_Times";
    public static final String CLASSIFICACAOLANCAMENTOS = "Classificacao";
    public static final String PONTOSLANCAMENTOS = "Pontos";

    public static final int VERSAO = 37;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;

        sql = "CREATE TABLE "
                + TABELATIMES + " ("
                + IDTIMES + " integer primary key autoincrement,"
                + DESCRICAOTIMES + " text,"
                + FUNDACAOTIMES + " date"
                + ");";
        db.execSQL(sql);

        sql = "CREATE TABLE "
                + TABELATORNEIOS + " ("
                + IDTORNEIOS + " integer primary key autoincrement,"
                + DESCRICAOTORNEIOS + " text,"
                + DATATORNEIOS + " date,"
                + PONTOS1LUGARTORNEIOS + " real,"
                + PONTOS2LUGARTORNEIOS + " real,"
                + PONTOS3LUGARTORNEIOS + " real"
                + ");";
        db.execSQL(sql);

        sql = "CREATE TABLE "
                + TABELARANKING + " ("
                + IDRANKING + " integer primary key autoincrement,"
                + DESCRICAORANKING + " text,"
                + DATARANKING + " date"
                + ");";
        db.execSQL(sql);

        sql = "CREATE TABLE "
                + TABELALANCAMENTOS + " ("
                + IDLANCAMENTOS + " integer primary key autoincrement,"
                + IDRANKINGLANCAMENTOS + " integer,"
                + IDTIMESLANCAMENTOS + " integer,"
                + IDTORNEIOSLANCAMENTOS + " integer,"
                + CLASSIFICACAOLANCAMENTOS + " real,"
                + PONTOSLANCAMENTOS + " real,"
                + "FOREIGN KEY(Id_Ranking)  REFERENCES Ranking(_id),"
                + "FOREIGN KEY(Id_Times)    REFERENCES Times(_id),"
                + "FOREIGN KEY(Id_Torneios) REFERENCES Torneios(_id));";
        db.execSQL(sql);

        sql = "CREATE UNIQUE INDEX IX_Times_Descricao on Times (DescricaoTimes) ;";
        db.execSQL(sql);

        sql = "CREATE UNIQUE INDEX IX_Torneios_Descricao on Torneios (DescricaoTorneios) ;";
        db.execSQL(sql);

        sql = "CREATE UNIQUE INDEX IX_Ranking_Descricao on Ranking (DescricaoRanking) ;";
        db.execSQL(sql);

        sql = "CREATE UNIQUE INDEX IX_Lancamentos_ID_Ranking_ID_Torneios_ID_Times on Lancamentos (ID_Ranking,ID_Torneios,ID_Times) ;";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABELALANCAMENTOS);
        db.execSQL(" DROP TABLE IF EXISTS " + TABELATORNEIOS);
        db.execSQL(" DROP TABLE IF EXISTS " + TABELATIMES);
        db.execSQL(" DROP TABLE IF EXISTS " + TABELARANKING);

        onCreate(db);
    }
}