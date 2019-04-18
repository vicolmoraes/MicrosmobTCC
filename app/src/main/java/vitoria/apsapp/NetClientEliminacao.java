package vitoria.apsapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static android.R.attr.id;
import static android.R.id.input;
import static java.lang.System.in;

public class NetClientEliminacao extends AsyncTask<Object, Object, String> {



    String dstAddress;
    int dstPort;
    String response = "";
    String resposta = "";
    Context gContext;


    ArrayList <String> arrayList;

    NetClientEliminacao(String addr, int port, final Context context) {
        dstAddress = addr;
        dstPort = port;
        gContext = context;
    }

    @Override
    protected String doInBackground(Object... arg0) {

        Socket socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);

            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),true);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];

            int bytesRead;
            InputStream inputStream = socket.getInputStream();

			/*
             * notice: inputStream.read() will block if no data return
			 */

            bytesRead = inputStream.read(buffer);
            byteArrayOutputStream.write(buffer, 0, bytesRead);
            String welcome = byteArrayOutputStream.toString("UTF-8");
            System.out.println(welcome);
            byteArrayOutputStream.reset();

            listarQuestoes();
            for( int x=0; x < arrayList.size(); x++){
                out.println( arrayList.get(x) );
            }

            out.println(".");


            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                response += byteArrayOutputStream.toString("UTF-8");
                resposta = byteArrayOutputStream.toString("UTF-8");

                String[] textoSeparado = resposta.split("\\|");
                if(textoSeparado.length == 8 ) {
                    Questao qu = new Questao();
                    qu.setCodigo(Integer.parseInt(textoSeparado[0].trim()));
                    qu.setEnunciado(textoSeparado[1]);
                    qu.setAlternativaA(textoSeparado[2]);
                    qu.setAlternativaB(textoSeparado[3]);
                    qu.setAlternativaC(textoSeparado[4]);
                    qu.setAlternativaCerta(textoSeparado[5]);
                    qu.setAssunto(Integer.parseInt(textoSeparado[6].trim()));
                    BancoDados db = new BancoDados(gContext);
                    try{
                        db.addQuestaoCodigo(qu);
                        db.atualizarQuestao(qu);        }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                    System.out.println(resposta);
                }

                byteArrayOutputStream.reset();

            }

            System.out.println(response);
            if (response.length()>0){
                response = response.substring(0,(response.length()-1));}
            response = welcome + response;

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return response;


    }



    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Toast.makeText(gContext,result,Toast.LENGTH_LONG).show();


    }

    public  void listarQuestoes(){
        BancoDados db = new BancoDados(gContext);

        List<Questao> questoes = db.listaTodasQuestoesEliminadas();
        arrayList = new ArrayList<String>();
        String hora = "h:mm - a";

        java.util.Date agora = new java.util.Date();
        SimpleDateFormat formata = new SimpleDateFormat(hora);
        String hora1 = formata.format(agora);



        for (Questao c : questoes){
            //Log.d("Lista", " Codigo : " + c.getCodigo() + " Enunciado : "+ c.getEnunciado()+
            //      " Alternativa A : " + c.getAlternativaA() +  " Alternativa B : " + c.getAlternativaB() +
            //    " Alternativa C : " + c.getAlternativaC() + " Alternativa Certa : " + c.getAlternativaCerta() + " Assunto : " + c.getAssunto());
            arrayList.add(c.getCodigo() + "|"+ c.getEnunciado() + "|"+ c.getAlternativaA() + "|" + c.getAlternativaB() + "|"+
                    c.getAlternativaC() + "|"+ c.getAlternativaCerta()+ "|"+ c.getAssunto() + "|" + hora1 );

        }

    }
}