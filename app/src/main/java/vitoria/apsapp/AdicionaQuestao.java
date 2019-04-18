package vitoria.apsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AdicionaQuestao extends AppCompatActivity {
    EditText txtQuestaoEnunciado, txtQuestaoAlternativaA,txtQuestaoAlternativaB, txtQuestaoAlternativaC,txtQuestaoAlternativaCerta,txtQuestaoAssunto;
    Button btnAdicionarQuestao, btnBanco;
    BancoDados db = new BancoDados(this);


    public void startBanco(View view){
        Intent bancoActivity = new Intent(this,ListaDeQuestoes.class);
        startActivity(bancoActivity);
    }

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
        setContentView(R.layout.activity_adiciona_questao);

        txtQuestaoEnunciado = (EditText)findViewById(R.id.txtQuestaoEnunciado);
        txtQuestaoAlternativaA = (EditText)findViewById(R.id.txtQuestaoAlternativaA);
        txtQuestaoAlternativaB = (EditText)findViewById(R.id.txtQuestaoAlternativaB);
        txtQuestaoAlternativaC = (EditText)findViewById(R.id.txtQuestaoAlternativaC);
        txtQuestaoAlternativaCerta = (EditText)findViewById(R.id.txtQuestaoAlternativaCerta);
        txtQuestaoAssunto = (EditText)findViewById(R.id.txtQuestaoAssunto);

        btnAdicionarQuestao = (Button) findViewById(R.id.btnAdicionarQuestao);
        btnBanco = (Button) findViewById(R.id.btnBanco);





        btnAdicionarQuestao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                db.addQuestao(new Questao(txtQuestaoEnunciado.getText().toString() , txtQuestaoAlternativaA.getText().toString(), txtQuestaoAlternativaB.getText().toString(),
                        txtQuestaoAlternativaC.getText().toString(), txtQuestaoAlternativaCerta.getText().toString(),Integer.parseInt( txtQuestaoAssunto.getText().toString() )));
                Toast.makeText(AdicionaQuestao.this, "Questão adicionada com sucesso", Toast.LENGTH_LONG).show();
            }
        });

        /* TESTE DO CRUD */

    //ADICIONA QUESTOES
       // db.addQuestao(new Questao(txtQuestaoEnunciado.toString(), txtQuestaoAlternativaA.toString(), txtQuestaoAlternativaB.toString(), txtQuestaoAlternativaC.toString(), txtQuestaoAlternativaCerta.toString(),Integer.parseInt( txtQuestaoAssunto.toString() )));
       /* db.addQuestao(new Questao("Quanto é 1+1?", "4", "3", "2", "2", 1));
        db.addQuestao(new Questao("Quanto é 1+1?", "4", "3", "2", "2", 1));*/



      //APAGA QUESTOES

     /*   Questao questao = new Questao();
        questao.setCodigo(1);

        try {
            db.apagaQuestao(questao);
            Toast.makeText(AdicionaQuestao.this, "apagado com sucesso", Toast.LENGTH_LONG).show();
        }
        catch (Exception x){
            x.getMessage();
        }
        */

      // SELECIONA 1 QUESTAO
       /*
        Questao questao = db.selecionaQuestao(5);
        Log.d("Questao selecionada", " Codigo : " + questao.getCodigo() + " Enunciado : "+ questao.getEnunciado()+
                " Alternativa A : " + questao.getAlternativaA() +  " Alternativa B : " + questao.getAlternativaB() +
        " Alternativa C : " + questao.getAlternativaC() + " Alternativa Certa : " + questao.getAlternativaCerta() + " Assunto : " + questao.getAssunto());
        */

        // UPDATE
       /* Questao questao = new Questao("Quanto é 2+1?", "4", "3", "2", "3", 1);
        questao.setCodigo(1);
        db.atualizarQuestao(questao);
       ------------------------- db.addQuestao(new Questao(1,"Quanto é 1+1?", "4", "3", "2", "2", 1));
       */



    }



}
