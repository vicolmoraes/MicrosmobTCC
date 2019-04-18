package vitoria.apsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaDeQuestoes extends AppCompatActivity {
    ListView listViewQuestoes ;
    BancoDados db = new BancoDados(this);

    ArrayAdapter <String> adapter;
    ArrayList <String> arrayList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==(R.id.action_sair)){
            Intent it= new Intent(getApplicationContext(), Index.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            it.putExtra("SAIR", true);
            startActivity(it);
        }
        if (id==(R.id.action_adiciona)){
            Intent it= new Intent(this, Login.class);
            startActivity(it);

        }
        if (id==(R.id.action_inicio)){
            Intent it= new Intent(this, Index.class);
            startActivity(it);

        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_questoes);

        listViewQuestoes = (ListView)findViewById(R.id.listViewQuestoes);
        this.listarQuestoes();
    }

    public  void listarQuestoes(){

        List<Questao> questoes = db.listaTodasQuestoes();
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(ListaDeQuestoes.this, android.R.layout.simple_list_item_1, arrayList);
        listViewQuestoes.setAdapter(adapter);
        for (Questao c : questoes){
            //Log.d("Lista", " Codigo : " + c.getCodigo() + " Enunciado : "+ c.getEnunciado()+
              //      " Alternativa A : " + c.getAlternativaA() +  " Alternativa B : " + c.getAlternativaB() +
                //    " Alternativa C : " + c.getAlternativaC() + " Alternativa Certa : " + c.getAlternativaCerta() + " Assunto : " + c.getAssunto());
        arrayList.add(c.getCodigo() + " | "+ c.getEnunciado() + " | "+ c.getAlternativaA() + " | " + c.getAlternativaB() + " | "+
        c.getAlternativaC() + " | "+ c.getAlternativaCerta()+ " | "+ c.getAssunto());
            adapter.notifyDataSetChanged();
        }

    }

}
