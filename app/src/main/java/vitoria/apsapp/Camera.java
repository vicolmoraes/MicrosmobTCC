package vitoria.apsapp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/**
 * Created by Note on 11/07/2018.
 */

public class Camera extends AppCompatActivity {

    BluetoothAdapter meuBluetooth = null;
    BluetoothDevice meuDevice = null;
    BluetoothSocket meuSocket = null;

    private  InputStream mmInStream;
    private  OutputStream mmOutStream;

    UUID meuUUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    private static final int solicitaAtiva = 1;
    private static final int solicitaConexao = 2;
    private static String mac = null;
    boolean aux=false;

    long TEMPO = (1000);
    Timer timer = null;

    String url = "http://10.0.1.2:8100/snapshot.cgi?user=admin&pwd=&next_url=";

    ConnectedThread connectedThread;

    WebView webView;

    Button btnLuz, btnZoom, btnConectar;
    boolean conexao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btnLuz = (Button) findViewById(R.id.btnLuz);
        btnZoom = (Button) findViewById(R.id.btnZoom);
        btnConectar = (Button) findViewById(R.id.btnConectar);
        meuBluetooth = BluetoothAdapter.getDefaultAdapter();
        webView = (WebView) findViewById(R.id.webView);

        WebViewClient cliente = new WebViewClient();

        webView.setWebViewClient(cliente);
        webView.setWebChromeClient(new WebChromeClient());

        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        ws.setSupportZoom(false);
        ws.setDomStorageEnabled(true);
        ws.setSupportMultipleWindows(true);

        webView.loadUrl(url);   //load o endereço url

        if (timer == null)
        {
            timer = new Timer();
            TimerTask tarefa = new TimerTask()
            {
                public void run()
                {
                    try
                    {
                        webView.reload();   //reload o endereço url a cada 1 segundo
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            };
            timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
        }


        if (meuBluetooth == null)
        {
            Toast.makeText(getApplicationContext(), "Seu dispositivo não possui bluetooth", Toast.LENGTH_LONG).show();
        }
        else if (!meuBluetooth.isEnabled())
        {
            Intent enableBlIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBlIntent, solicitaAtiva);
        }

        btnConectar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (conexao) {
                    //desconectar
                    try {
                        meuSocket.close();

                        conexao = false;

                        Toast.makeText(getApplicationContext(), "Bluetooth desconectado", Toast.LENGTH_LONG).show();

                        btnConectar.setText("Conectar no Arduino");
                    } catch (IOException erro) {
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro: " + erro, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Intent abreLista = new Intent(Camera.this, ListaDispositivos.class);
                    startActivityForResult(abreLista, solicitaConexao);
                }

            }
        });

        btnLuz.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                connectedThread.enviar("0");//para o zoom
            }
        });

        btnZoom.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(conexao)
                {
                    if(aux==false)
                    {
                      connectedThread.enviar("2"); //aproxima o zoom
                      aux=true;
                    }
                    else
                    {
                      connectedThread.enviar("1");//afasta o zoom
                        aux=false;
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Bluetooth não conectado" , Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode) {

            case solicitaAtiva:

                if (resultCode == Activity.RESULT_OK)
                {
                    Toast.makeText(getApplicationContext(), "O bluetooth foi ativado", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "O bluetooth NÃO foi ativado", Toast.LENGTH_LONG).show();
                }
                break;

            case solicitaConexao:

                if (resultCode == Activity.RESULT_OK)
                {
                    mac = data.getExtras().getString(ListaDispositivos.enderecoMac);
                    meuDevice = meuBluetooth.getRemoteDevice(mac);

                    try
                    {
                        meuSocket = meuDevice.createInsecureRfcommSocketToServiceRecord(meuUUID);
                        meuSocket.connect();

                        conexao = true;
                        connectedThread = new ConnectedThread(meuSocket);
                        connectedThread.start();

                        btnConectar.setText("Desconectar");

                        Toast.makeText(getApplicationContext(), "Você foi conectado com " + mac, Toast.LENGTH_LONG).show();

                    }
                    catch (IOException erro)
                    {
                        conexao = false;
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro: " + erro, Toast.LENGTH_LONG).show();
                    }


                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Falha ao obter o mac", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    private class ConnectedThread extends Thread
    {

        public ConnectedThread(BluetoothSocket socket)
        {
            meuSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try
            {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            }
            catch (IOException e)
            {
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run()
        {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
           /* while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);
                    // Send the obtained bytes to the UI activity
                    mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                            .sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }*/
        }

        /* Call this from the main activity to send data to the remote device */
        public void enviar(String inteiro)           //Envio da informação pro arduino
        {
            byte[] mbyte = inteiro.getBytes();

            try
            {
                mmOutStream.write(mbyte);
            }
            catch (IOException e)
            {
            }
        }

    }
}

