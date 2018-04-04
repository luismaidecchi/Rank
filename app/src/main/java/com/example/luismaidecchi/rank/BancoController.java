package com.example.luismaidecchi.rank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }

    public String insereDadoTimes(String descricao, String fundacao) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();

        valores = new ContentValues();

        valores.put(CriaBanco.DESCRICAOTIMES, descricao);
        valores.put(CriaBanco.FUNDACAOTIMES, fundacao);

        resultado = db.insert(CriaBanco.TABELATIMES, null, valores);

        db.close();

        if (resultado == -1)
            return "Erro ao Inserir registro TIME";
        else
            return "Registro TIME Inserido com sucesso";
    }

    public String AlteraDadoTimes(String descricao, String fundacao, int Id) {
        ContentValues valores;
        long resultado;

        String where = banco.IDTIMES + " = " + Id;

        db = banco.getWritableDatabase();

        valores = new ContentValues();

        valores.put(CriaBanco.DESCRICAOTIMES, descricao);
        valores.put(CriaBanco.FUNDACAOTIMES, fundacao);

        resultado = db.update(CriaBanco.TABELATIMES, valores, where, null);

        db.close();

        if (resultado == -1)
            return "Erro ao Alterar registro TIME";
        else
            return "Registro TIME Alterado com sucesso";
    }

    public String ExcluiDadoTimes(int Id) {
        long resultado;

        String where = banco.IDTIMES + " = " + Id;

        db = banco.getWritableDatabase();

        resultado = db.delete(CriaBanco.TABELATIMES, where, null);

        db.close();

        if (resultado == -1)
            return "Erro ao Excluir registro TIME";
        else
            return "Registro TIME Excluído com sucesso";
    }

    public Cursor carregaDadosTimes() {
        Cursor cursor;
        String[] campos = {banco.IDTIMES, banco.DESCRICAOTIMES, banco.FUNDACAOTIMES};

        db = banco.getReadableDatabase();

        cursor = db.query(banco.TABELATIMES, campos, null, null, null, null, banco.DESCRICAOTIMES, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public Cursor carregaDadosTimesPorId(int id) {

        String[] campos = {banco.DESCRICAOTIMES, banco.FUNDACAOTIMES};
        String where = banco.IDTIMES + " = " + id;

        db = banco.getReadableDatabase();

        Cursor cursor = db.query(banco.TABELATIMES, campos, where, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public String insereDadoTorneios(String descricao, String data, String pontos1lugar, String pontos2lugar, String pontos3lugar) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();

        valores = new ContentValues();

        valores.put(CriaBanco.DESCRICAOTORNEIOS, descricao);
        valores.put(CriaBanco.DATATORNEIOS, data);
        valores.put(CriaBanco.PONTOS1LUGARTORNEIOS, pontos1lugar);
        valores.put(CriaBanco.PONTOS2LUGARTORNEIOS, pontos2lugar);
        valores.put(CriaBanco.PONTOS3LUGARTORNEIOS, pontos3lugar);

        resultado = db.insert(CriaBanco.TABELATORNEIOS, null, valores);

        db.close();

        if (resultado == -1)
            return "Erro ao Inserir registro TORNEIO";
        else
            return "Registro TORNEIO Inserido com sucesso";
    }

    public String AlteraDadoTorneios(String descricao, String data, String pontos1Lugar, String pontos2Lugar, String pontos3Lugar, int Id) {
        ContentValues valores;
        long resultado;

        String where = banco.IDTORNEIOS + " = " + Id;

        db = banco.getWritableDatabase();

        valores = new ContentValues();

        valores.put(CriaBanco.DESCRICAOTORNEIOS, descricao);
        valores.put(CriaBanco.DATATORNEIOS, data);
        valores.put(CriaBanco.PONTOS1LUGARTORNEIOS, pontos1Lugar);
        valores.put(CriaBanco.PONTOS2LUGARTORNEIOS, pontos2Lugar);
        valores.put(CriaBanco.PONTOS3LUGARTORNEIOS, pontos3Lugar);

        resultado = db.update(CriaBanco.TABELATORNEIOS, valores, where, null);

        db.close();

        if (resultado == -1)
            return "Erro ao Alterar registro TORNEIO";
        else
            return "Registro TORNEIO Alterado com sucesso";
    }

    public String ExcluiDadoTorneios(int Id) {
        long resultado;

        String where = banco.IDTORNEIOS + " = " + Id;

        db = banco.getWritableDatabase();

        resultado = db.delete(CriaBanco.TABELATORNEIOS, where, null);

        db.close();

        if (resultado == -1)
            return "Erro ao Excluir registro TORNEIO";
        else
            return "Registro TORNEIO Excluído com sucesso";
    }

    public Cursor carregaDadosTorneios() {
        Cursor cursor;
        String[] campos = {banco.IDTORNEIOS, banco.DESCRICAOTORNEIOS, banco.DATATORNEIOS, banco.PONTOS1LUGARTORNEIOS, banco.PONTOS2LUGARTORNEIOS, banco.PONTOS3LUGARTORNEIOS};

        db = banco.getReadableDatabase();

        cursor = db.query(banco.TABELATORNEIOS, campos, null, null, null, null, banco.DESCRICAOTORNEIOS, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public Cursor carregaDadosTorneiosPorId(int id) {

        String[] campos = {banco.DESCRICAOTORNEIOS, banco.DATATORNEIOS, banco.PONTOS1LUGARTORNEIOS, banco.PONTOS2LUGARTORNEIOS, banco.PONTOS3LUGARTORNEIOS};
        String where = banco.IDTORNEIOS + " = " + id;

        db = banco.getReadableDatabase();

        Cursor cursor = db.query(banco.TABELATORNEIOS, campos, where, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public String insereDadoRanking(String descricao, String data) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();

        valores = new ContentValues();

        valores.put(CriaBanco.DESCRICAORANKING, descricao);
        valores.put(CriaBanco.DATARANKING, data);

        resultado = db.insert(CriaBanco.TABELARANKING, null, valores);

        db.close();

        if (resultado == -1)
            return "Erro ao Inserir registro RANKING";
        else
            return "Registro RANKING Inserido com sucesso";
    }

    public String AlteraDadoRanking(String descricao, String data, int Id) {
        ContentValues valores;
        long resultado;

        String where = banco.IDRANKING + " = " + Id;

        db = banco.getWritableDatabase();

        valores = new ContentValues();

        valores.put(CriaBanco.DESCRICAORANKING, descricao);
        valores.put(CriaBanco.DATARANKING, data);

        resultado = db.update(CriaBanco.TABELARANKING, valores, where, null);

        db.close();

        if (resultado == -1)
            return "Erro ao Alterar registro RANKING";
        else
            return "Registro RANKING Alterado com sucesso";
    }

    public String ExcluiDadoRanking(int Id) {
        long resultado;

        String where = banco.IDRANKING + " = " + Id;

        db = banco.getWritableDatabase();

        resultado = db.delete(CriaBanco.TABELARANKING, where, null);

        db.close();

        if (resultado == -1)
            return "Erro ao Excluir registro RANKING";
        else
            return "Registro RANKING Excluído com sucesso";
    }

    public Cursor carregaDadosRanking() {
        Cursor cursor;
        String[] campos = {banco.IDRANKING, banco.DESCRICAORANKING, banco.DATARANKING};

        db = banco.getReadableDatabase();

        cursor = db.query(banco.TABELARANKING, campos, null, null, null, null, banco.DESCRICAORANKING, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public Cursor carregaDadosRankingPorId(int id) {

        String[] campos = {banco.DESCRICAORANKING, banco.DATARANKING};
        String where = banco.IDRANKING + " = " + id;

        db = banco.getReadableDatabase();

        Cursor cursor = db.query(banco.TABELARANKING, campos, where, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public String insereDadoLancamentos(String iDRANKINGLANCAMENTOS, String iDTORNEIOSLANCAMENTOS,
                                        String iDTIMESLANCAMENTOS, String cLASSIFICACAOLANCAMENTOS,
                                        String pONTOSLANCAMENTOS) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();

        valores = new ContentValues();

        valores.put(CriaBanco.IDRANKINGLANCAMENTOS, iDRANKINGLANCAMENTOS);
        valores.put(CriaBanco.IDTIMESLANCAMENTOS, iDTIMESLANCAMENTOS);
        valores.put(CriaBanco.IDTORNEIOSLANCAMENTOS, iDTORNEIOSLANCAMENTOS);
        valores.put(CriaBanco.CLASSIFICACAOLANCAMENTOS, cLASSIFICACAOLANCAMENTOS);
        valores.put(CriaBanco.PONTOSLANCAMENTOS, pONTOSLANCAMENTOS);

        resultado = db.insert(CriaBanco.TABELALANCAMENTOS, null, valores);

        db.close();

        if (resultado == -1)
            return "Erro ao Inserir registro LANÇAMENTO";
        else
            return "Registro LANÇAMENTO Inserido com sucesso";
    }

    public String AlteraDadoLancamentos(String Id, String iDRANKINGLANCAMENTOS, String iDTORNEIOSLANCAMENTOS,
                                        String iDTIMESLANCAMENTOS, String cLASSIFICACAOLANCAMENTOS,
                                        String pONTOSLANCAMENTOS) {
        ContentValues valores;
        long resultado;

        String where = banco.IDLANCAMENTOS + " = " + Id;

        db = banco.getWritableDatabase();

        valores = new ContentValues();

        valores.put(CriaBanco.IDRANKINGLANCAMENTOS, iDRANKINGLANCAMENTOS);
        valores.put(CriaBanco.IDTORNEIOSLANCAMENTOS, iDTORNEIOSLANCAMENTOS);
        valores.put(CriaBanco.IDTIMESLANCAMENTOS, iDTIMESLANCAMENTOS);
        valores.put(CriaBanco.CLASSIFICACAOLANCAMENTOS, cLASSIFICACAOLANCAMENTOS);
        valores.put(CriaBanco.PONTOSLANCAMENTOS, pONTOSLANCAMENTOS);

        resultado = db.update(CriaBanco.TABELALANCAMENTOS, valores, where, null);

        db.close();

        if (resultado == -1)
            return "Erro ao Alterar registro LANÇAMENTO";
        else
            return "Registro LANÇAMENTO Alterado com sucesso";
    }

    public String ExcluiDadoLancamentos(int Id) {
        long resultado;

        String where = banco.IDLANCAMENTOS + " = " + Id;

        db = banco.getWritableDatabase();

        resultado = db.delete(CriaBanco.TABELALANCAMENTOS, where, null);

        db.close();

        if (resultado == -1)
            return "Erro ao Excluir registro LANÇAMENTO";
        else
            return "Registro LANÇAMENTO Excluído com sucesso";
    }

    public Cursor carregaDadosLancamentos() {
        Cursor cursor;
        String select = "Select TS.DescricaoTorneios, T.DescricaoTimes, L._Id From Lancamentos L Join Torneios TS On TS._Id = L.Id_Torneios Inner Join Ranking R On R._Id = L.Id_Ranking Inner Join Times T On T._Id = L.Id_Times Order By R.DescricaoRanking,TS.DescricaoTorneios,T.DescricaoTimes";
        db = banco.getReadableDatabase();

        cursor = db.rawQuery(select, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public Cursor carregaDadosLancamentoPorId(int id) {
        String[] campos = {banco.IDRANKINGLANCAMENTOS, banco.IDTORNEIOSLANCAMENTOS, banco.IDTIMESLANCAMENTOS, banco.CLASSIFICACAOLANCAMENTOS, banco.PONTOSLANCAMENTOS};
        String where = banco.IDLANCAMENTOS + " = " + id;

        db = banco.getReadableDatabase();

        Cursor cursor = db.query(banco.TABELALANCAMENTOS, campos, where, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public Cursor carregaDadosRankingLancamentos() {
        Cursor cursor;
        String[] campos = {banco.IDRANKING, banco.DESCRICAORANKING};

        db = banco.getReadableDatabase();

        cursor = db.query(banco.TABELARANKING, campos, null, null, null, null, banco.DESCRICAORANKING, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public Cursor carregaDadosTorneiosLancamentos() {
        Cursor cursor;
        String[] campos = {banco.IDTORNEIOS, banco.DESCRICAOTORNEIOS, banco.PONTOS1LUGARTORNEIOS, banco.PONTOS2LUGARTORNEIOS, banco.PONTOS3LUGARTORNEIOS};

        db = banco.getReadableDatabase();

        cursor = db.query(banco.TABELATORNEIOS, campos, null, null, null, null, banco.DESCRICAOTORNEIOS, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public Cursor carregaDadosTimesLancamentos() {
        Cursor cursor;
        String[] campos = {banco.IDTIMES, banco.DESCRICAOTIMES};

        db = banco.getReadableDatabase();

        cursor = db.query(banco.TABELATIMES, campos, null, null, null, null, banco.DESCRICAOTIMES, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public Cursor carregaDadosRank(String Ranking) {
        Cursor cursor;
        String query;

        query = " Select T.DescricaoTimes   As DescricaoTimes," +
                "        Sum(L.Pontos)      As Pontos," +
                "        '1' As _id"+
                " From   Lancamentos L " +
                " Inner " +
                " Join   Times T" +
                " On     T._id = L.Id_Times" +
                " Inner " +
                " Join   Ranking R " +
                " On     R._id = L.Id_Ranking" +
                " Where  L.Id_Ranking = " + Ranking +
                " Group " +
                " By     L.Id_Ranking," +
                "        L.Id_Times " +
                " Order " +
                " By     2 Desc";

        db = banco.getReadableDatabase();

        cursor = db.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }
}