package vitoria.apsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TecidoAnimal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecido_animal);
    }
    public void startIndex(View view){
        Intent indexActivity = new Intent(this,Index.class);
        startActivity(indexActivity);
    }
    public void startQuestionario(View view){
        Intent questionarioActivity = new Intent(this,Questionario1.class);
        startActivity(questionarioActivity);
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
        if (id==(R.id.action_sincronizar)){
                NetClient client = new NetClient("192.168.0.16", 9090,this);
            client.execute();
        }
        return super.onOptionsItemSelected(item);
    }
}
