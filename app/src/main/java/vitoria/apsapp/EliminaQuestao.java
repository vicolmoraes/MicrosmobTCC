package vitoria.apsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EliminaQuestao extends AppCompatActivity {
    EditText txtCodigo;
    Button btnEliminar;
    BancoDados db = new BancoDados(this);

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
        setContentView(R.layout.activity_elimina_questao);
        txtCodigo = (EditText)findViewById(R.id.txtCodigo);
        btnEliminar=(Button)findViewById(R.id.btnEliminar);

        btnEliminar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){



        try {
           Questao questao =   db.selecionaQuestao(Integer.parseInt(txtCodigo.getText().toString().trim()));
            db.addQuestaoEliminadaCodigo(questao);

            NetClientEliminacao client = new NetClientEliminacao("10.0.0.4", 9090, EliminaQuestao.this);
            client.execute();


            Toast.makeText(EliminaQuestao.this, "Eliminada com sucesso", Toast.LENGTH_LONG).show();
        }
        catch (Exception x){
            x.getMessage();
        }}});
    }
}
