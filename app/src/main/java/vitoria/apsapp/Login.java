package vitoria.apsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText txtLogin, txtSenha;
    Button btnEntrarLogin;

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
        setContentView(R.layout.activity_login);

        txtLogin = (EditText)findViewById(R.id.txtLogin);
        txtSenha = (EditText)findViewById(R.id.txtSenha);
        btnEntrarLogin = (Button)findViewById(R.id.btnEntrarLogin);

        btnEntrarLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                        if ( (((txtLogin.getText()).toString()).equals("vitoria") ) && (((txtSenha.getText()).toString()).equals("123")) )
                {
                    Toast.makeText(Login.this, "Login liberado", Toast.LENGTH_LONG).show();
                    startIndexAdm(v);

                } else {Toast.makeText(Login.this, "Acesso Negado", Toast.LENGTH_LONG).show();
                        }
            }});
    }

    public void startIndexAdm(View view){
        Intent ad = new Intent(this,IndexAdministrador.class);
        startActivity(ad);


    }
}
