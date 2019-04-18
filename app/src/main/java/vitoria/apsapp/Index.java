package vitoria.apsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }
    public void startAnimal(View view){
        Intent animalActivity = new Intent(this,TecidoAnimal.class);
        startActivity(animalActivity);
    }

    public void startVegetal(View view){
        Intent vegetalActivity = new Intent(this,TecidoVegetal.class);
        startActivity(vegetalActivity);
    }
    public void startCamera(View view){
        Intent cameraActivity = new Intent(this,Camera.class);
        startActivity(cameraActivity);
    }

    @Override
    protected void onResume() {
        if(getIntent().getBooleanExtra("SAIR",false)){
            finish();
        }
        super.onResume();
    }
}
