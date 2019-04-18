package vitoria.apsapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Questionario1 extends AppCompatActivity {
    TextView txtQuest1, txtQuest2, txtQuest3, textViewTimer;
    RadioButton rbQuest1A, rbQuest1B, rbQuest2A, rbQuest2B, rbQuest3A, rbQuest3B,rbQuest1C, rbQuest2C, rbQuest3C ;
    Button btnEnviar;
    BancoDados db = new BancoDados(this);
    int x=0;
    int numero=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario1);
        ListaDeQuestoes g = new ListaDeQuestoes();

        textViewTimer = (TextView) findViewById(R.id.textViewTimer);
        txtQuest1 = (TextView) findViewById(R.id.txtQuest1);
        txtQuest2 = (TextView) findViewById(R.id.txtQuest2);
        txtQuest3 = (TextView) findViewById(R.id.txtQuest3);
        rbQuest1A = (RadioButton) findViewById(R.id.rbQuest1A);
        rbQuest1B = (RadioButton) findViewById(R.id.rbQuest1B);
        rbQuest1C = (RadioButton) findViewById(R.id.rbQuest1C);
        rbQuest2A = (RadioButton) findViewById(R.id.rbQuest2A);
        rbQuest2B = (RadioButton) findViewById(R.id.rbQuest2B);
        rbQuest2C = (RadioButton) findViewById(R.id.rbQuest2C);
        rbQuest3A = (RadioButton) findViewById(R.id.rbQuest3A);
        rbQuest3B = (RadioButton) findViewById(R.id.rbQuest3B);
        rbQuest3C = (RadioButton) findViewById(R.id.rbQuest3C);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        int segundos = 100;
        CountDownTimer timer = new CountDownTimer(segundos * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText("Timer\n" + (int) (millisUntilFinished/1000) );
            }

            @Override
            public void onFinish() {
                btnEnviar.callOnClick();
            }
        }.start();


        Questao questao1 = db.selecionaQuestao(db.idAleatorio());

            try {

                while (questao1.getAssunto() != 1 || questao1 == null) {

                    questao1 = db.selecionaQuestao(db.idAleatorio());


                }
            } catch (Exception e) {
                Toast.makeText(Questionario1.this, "Erro " + e, Toast.LENGTH_LONG).show();
            }

       txtQuest1.setText(questao1.getEnunciado());
       rbQuest1A.setText(questao1.getAlternativaA());
       rbQuest1B.setText(questao1.getAlternativaB());
       rbQuest1C.setText(questao1.getAlternativaC());


                /*if(questao.getAlternativaA().equals(questao.getAlternativaCerta())){
                    if(rbQuest1A.isChecked()){
                        Toast.makeText(Questionario1.this, "Parabens 1 ponto", Toast.LENGTH_LONG).show();
                    }
                }
            if(questao.getAlternativaB().equals(questao.getAlternativaCerta())){
                if(rbQuest1B.isChecked()){
                    Toast.makeText(Questionario1.this, "Parabens 1 ponto", Toast.LENGTH_LONG).show();
                }
            }
         */


       Questao questao2 = db.selecionaQuestao(db.idAleatorio());

            try {
                while (questao2.getAssunto() != 1 || questao2 == null) {

                    questao2 = db.selecionaQuestao(db.idAleatorio());

                }
            } catch (Exception e) {
                Toast.makeText(Questionario1.this, "Erro " + e, Toast.LENGTH_LONG).show();
            }

       txtQuest2.setText(questao2.getEnunciado());
       rbQuest2A.setText(questao2.getAlternativaA());
       rbQuest2B.setText(questao2.getAlternativaB());
       rbQuest2C.setText(questao2.getAlternativaC());



       Questao questao3 = db.selecionaQuestao(db.idAleatorio());

            try {
                while (questao3.getAssunto() != 1 || questao3 == null) {

                    questao3 = db.selecionaQuestao(db.idAleatorio());

                }
            } catch (Exception e) {
                Toast.makeText(Questionario1.this, "Erro " + e, Toast.LENGTH_LONG).show();
            }

       txtQuest3.setText(questao3.getEnunciado());
       rbQuest3A.setText(questao3.getAlternativaA());
       rbQuest3B.setText(questao3.getAlternativaB());
       rbQuest3C.setText(questao3.getAlternativaC());



        final Questao q1 = questao1;
        final Questao q2 = questao2;
        final Questao q3 = questao3;


            btnEnviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    x = 0;
                    if (q1.getAlternativaA().equals(q1.getAlternativaCerta())) {
                        if (rbQuest1A.isChecked() == true) {
                            x++;
                            rbQuest1A.setBackgroundColor(Color.GREEN);
                        }else { rbQuest1A.setBackgroundColor(Color.RED);}
                    }
                    if (q1.getAlternativaB().equals(q1.getAlternativaCerta())) {
                        if (rbQuest1B.isChecked() == true) {
                            x++;
                            rbQuest1B.setBackgroundColor(Color.GREEN);
                        }else { rbQuest1B.setBackgroundColor(Color.RED);}
                    }
                    if (q1.getAlternativaC().equals(q1.getAlternativaCerta())) {
                        if (rbQuest1C.isChecked() == true) {
                            x++;
                            rbQuest1C.setBackgroundColor(Color.GREEN);
                        }else { rbQuest1C.setBackgroundColor(Color.RED);}
                    }
                    if (q2.getAlternativaA().equals(q2.getAlternativaCerta())) {
                        if (rbQuest2A.isChecked() == true) {
                            x++;
                            rbQuest2A.setBackgroundColor(Color.GREEN);
                        }else { rbQuest2A.setBackgroundColor(Color.RED);}
                    }
                    if (q2.getAlternativaB().equals(q2.getAlternativaCerta())) {
                        if (rbQuest2B.isChecked() == true) {
                            x++;
                            rbQuest2B.setBackgroundColor(Color.GREEN);
                        }else { rbQuest2B.setBackgroundColor(Color.RED);}
                    }
                    if (q2.getAlternativaC().equals(q2.getAlternativaCerta())) {
                        if (rbQuest2C.isChecked() == true) {
                            x++;
                            rbQuest2C.setBackgroundColor(Color.GREEN);
                        }else { rbQuest2C.setBackgroundColor(Color.RED);}
                    }

                    if (q3.getAlternativaA().equals(q3.getAlternativaCerta())) {
                        if (rbQuest3A.isChecked() == true) {
                            x++;
                            rbQuest3A.setBackgroundColor(Color.GREEN);
                        }else { rbQuest3A.setBackgroundColor(Color.RED);}
                    }
                    if (q3.getAlternativaB().equals(q3.getAlternativaCerta())) {
                        if (rbQuest3B.isChecked() == true) {
                            x++;
                            rbQuest3B.setBackgroundColor(Color.GREEN);
                        }else { rbQuest3B.setBackgroundColor(Color.RED);}
                    }
                    if (q3.getAlternativaC().equals(q3.getAlternativaCerta())) {
                        if (rbQuest3C.isChecked() == true) {
                            x++;
                            rbQuest3C.setBackgroundColor(Color.GREEN);
                        }else { rbQuest3C.setBackgroundColor(Color.RED);}
                    }
                    Toast.makeText(Questionario1.this, "Parabens voce fez " + x + " pontos", Toast.LENGTH_LONG).show();

                }
            });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_principal, menu);
        return super.onCreateOptionsMenu(menu);


    }
    public void startIndex (View view){
        Intent indexActivity = new Intent(this,Index.class);
        startActivity(indexActivity);
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

    public void startNovoQuest(View view){
        Intent x = new Intent(this,Questionario1.class);
        startActivity(x);
    }
}
