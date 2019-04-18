package vitoria.apsapp;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by Note on 11/07/2018.
 */

public class ListaDispositivos extends ListActivity
{

    private BluetoothAdapter meuBluetooth = null;
    static String enderecoMac = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluetooth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        meuBluetooth = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> dispositivosPareados = meuBluetooth.getBondedDevices();

        if(dispositivosPareados.size() > 0)
        {
            for(BluetoothDevice dispositivo : dispositivosPareados)
            {
                String nomeBt = dispositivo.getName();
                String macBt = dispositivo.getAddress();
                ArrayBluetooth.add(nomeBt + "\n" +macBt);
            }
        }

        setListAdapter(ArrayBluetooth);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        String informacaoGeral= ((TextView)v).getText().toString();

        String mac = informacaoGeral.substring(informacaoGeral.length()-17);

        Toast.makeText(getApplicationContext(),"Mac:" + mac, Toast.LENGTH_LONG).show();

        Intent retornaMac = new Intent();
        retornaMac.putExtra(enderecoMac,mac);
        setResult(RESULT_OK,retornaMac);

        finish();

    }
}
