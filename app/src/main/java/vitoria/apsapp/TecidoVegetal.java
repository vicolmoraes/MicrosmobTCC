package vitoria.apsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class TecidoVegetal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecido_vegetal);
    }
    public void startIndex(View view){
        Intent indexActivity = new Intent(this,Index.class);
        startActivity(indexActivity);
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
}
