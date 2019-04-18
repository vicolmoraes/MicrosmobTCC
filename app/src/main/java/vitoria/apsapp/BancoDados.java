package vitoria.apsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;



public class BancoDados extends SQLiteOpenHelper {

    private static final int BANCO_VERSAO = 1;
    private static final String BANCO_QUESTAO = "bd_questoes";
    private static final String TABELA_QUESTAO = "tb_questoes";
    private static final String COLUNA_CODIGO = "codigo";
    private static final String COLUNA_ENUNCIADO = "enunciado";
    private static final String COLUNA_ALTERNATIVA_A = "alternativa_a";
    private static final String COLUNA_ALTERNATIVA_B = "alternativa_b";
    private static final String COLUNA_ALTERNATIVA_C = "alternativa_c";
    private static final String COLUNA_ALTERNATIVA_CERTA = "alternativa_certa";
    private static final String COLUNA_ASSUNTO = "assunto";
    private static final String TABELA_ELIMINADAS = "tb_eliminadas";


    public BancoDados(Context context) {
        super(context, BANCO_QUESTAO, null, BANCO_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY_COLUNA = " CREATE TABLE " + TABELA_QUESTAO + "("+ COLUNA_CODIGO + " INTEGER PRIMARY KEY," + COLUNA_ENUNCIADO + " TEXT," +
                COLUNA_ALTERNATIVA_A + " TEXT," +COLUNA_ALTERNATIVA_B + " TEXT," + COLUNA_ALTERNATIVA_C + " TEXT,"+ COLUNA_ALTERNATIVA_CERTA +
                " TEXT," + COLUNA_ASSUNTO + " INTEGER )";
        db.execSQL(QUERY_COLUNA);

        String QUERY_COLUNA2 = " CREATE TABLE " + TABELA_ELIMINADAS + "("+ COLUNA_CODIGO + " INTEGER ," + COLUNA_ENUNCIADO + " TEXT," +
                COLUNA_ALTERNATIVA_A + " TEXT," +COLUNA_ALTERNATIVA_B + " TEXT," + COLUNA_ALTERNATIVA_C + " TEXT,"+ COLUNA_ALTERNATIVA_CERTA +
                " TEXT," + COLUNA_ASSUNTO + " INTEGER )";
        db.execSQL(QUERY_COLUNA2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /* CRUD ABAIXO */

    void addQuestao (Questao questao){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
       // values.put(COLUNA_CODIGO, questao.getCodigo());
        values.put(COLUNA_ENUNCIADO, questao.getEnunciado());
        values.put(COLUNA_ALTERNATIVA_A, questao.getAlternativaA());
        values.put(COLUNA_ALTERNATIVA_B, questao.getAlternativaB());
        values.put(COLUNA_ALTERNATIVA_C, questao.getAlternativaC());
        values.put(COLUNA_ALTERNATIVA_CERTA, questao.getAlternativaCerta());
        values.put(COLUNA_ASSUNTO, questao.getAssunto());
        db.insert(TABELA_QUESTAO, null, values);
        db.close();
    }

    void addQuestaoCodigo (Questao questao){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_CODIGO, questao.getCodigo());
        values.put(COLUNA_ENUNCIADO, questao.getEnunciado());
        values.put(COLUNA_ALTERNATIVA_A, questao.getAlternativaA());
        values.put(COLUNA_ALTERNATIVA_B, questao.getAlternativaB());
        values.put(COLUNA_ALTERNATIVA_C, questao.getAlternativaC());
        values.put(COLUNA_ALTERNATIVA_CERTA, questao.getAlternativaCerta());
        values.put(COLUNA_ASSUNTO, questao.getAssunto());
        db.insert(TABELA_QUESTAO, null, values);
        db.close();
    }


    void apagaQuestao(Questao questao){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_QUESTAO, COLUNA_CODIGO + " = ?", new String[]{String.valueOf(questao.getCodigo())});

        db.close();
    }

    Questao selecionaQuestao( int codigo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_QUESTAO, new String[] { COLUNA_CODIGO, COLUNA_ENUNCIADO, COLUNA_ALTERNATIVA_A,
                COLUNA_ALTERNATIVA_B, COLUNA_ALTERNATIVA_C, COLUNA_ALTERNATIVA_CERTA, COLUNA_ASSUNTO}, COLUNA_CODIGO + "= ?",
                new String[]{String.valueOf(codigo)}, null, null, null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }

        Questao questao1 = new Questao(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                Integer.parseInt(cursor.getString(6))
                );
        return questao1;
    }

    void atualizarQuestao (Questao questao){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_ENUNCIADO, questao.getEnunciado());
        values.put(COLUNA_ALTERNATIVA_A, questao.getAlternativaA());
        values.put(COLUNA_ALTERNATIVA_B, questao.getAlternativaB());
        values.put(COLUNA_ALTERNATIVA_C, questao.getAlternativaC());
        values.put(COLUNA_ALTERNATIVA_CERTA, questao.getAlternativaCerta());
        values.put(COLUNA_ASSUNTO, questao.getAssunto());

        db.update(TABELA_QUESTAO, values, COLUNA_CODIGO + " = ?", new String[]{String.valueOf(questao.getCodigo())});
    }

    public List<Questao> listaTodasQuestoes (){
        List <Questao> listaQuestoes = new ArrayList<Questao>();

        String query = "SELECT * FROM "+ TABELA_QUESTAO;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()){
            do{
               Questao questao = new Questao();
                questao.setCodigo(Integer.parseInt(c.getString(0)));
                questao.setEnunciado(c.getString(1));
                questao.setAlternativaA(c.getString(2));
                questao.setAlternativaB(c.getString(3));
                questao.setAlternativaC(c.getString(4));
                questao.setAlternativaCerta(c.getString(5));
                questao.setAssunto(Integer.parseInt(c.getString(6)));

                listaQuestoes.add(questao);
            }while (c.moveToNext());

        }
        return listaQuestoes;
    }

    void addQuestaoEliminadaCodigo (Questao questao){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_CODIGO, questao.getCodigo());
        values.put(COLUNA_ENUNCIADO, questao.getEnunciado());
        values.put(COLUNA_ALTERNATIVA_A, questao.getAlternativaA());
        values.put(COLUNA_ALTERNATIVA_B, questao.getAlternativaB());
        values.put(COLUNA_ALTERNATIVA_C, questao.getAlternativaC());
        values.put(COLUNA_ALTERNATIVA_CERTA, questao.getAlternativaCerta());
        values.put(COLUNA_ASSUNTO, questao.getAssunto());
        db.insert(TABELA_ELIMINADAS, null, values);
        db.close();
    }

    public List<Questao> listaTodasQuestoesEliminadas (){
        List <Questao> listaQuestoes = new ArrayList<Questao>();

        String query = "SELECT * FROM "+ TABELA_ELIMINADAS;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()){
            do{
                Questao questao = new Questao();
                questao.setCodigo(Integer.parseInt(c.getString(0)));
                questao.setEnunciado(c.getString(1));
                questao.setAlternativaA(c.getString(2));
                questao.setAlternativaB(c.getString(3));
                questao.setAlternativaC(c.getString(4));
                questao.setAlternativaCerta(c.getString(5));
                questao.setAssunto(Integer.parseInt(c.getString(6)));

                listaQuestoes.add(questao);
            }while (c.moveToNext());

        }
        return listaQuestoes;
    }

    void apagaQuestaoEliminada(Questao questao){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_ELIMINADAS, COLUNA_CODIGO + " = ?", new String[]{String.valueOf(questao.getCodigo())});

        db.close();
    }



    public int idAleatorio (){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUNA_CODIGO + " FROM " + TABELA_QUESTAO + " ORDER BY RANDOM () LIMIT 1 ";

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        int numero = Integer.parseInt(c.getString(0));

        return numero;

    }
}

