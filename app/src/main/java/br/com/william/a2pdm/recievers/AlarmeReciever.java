package br.com.william.a2pdm.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmeReciever extends BroadcastReceiver {

    public static final String CHAVE_ID_SERVICO = "idAlarme";

    @Override
    public void onReceive(Context context, Intent intent) {
        int idAlarme = intent.getIntExtra(CHAVE_ID_SERVICO, 0);
        Log.d("LOG RECIEVER", "Alarme Recebido: " + idAlarme);
        Toast.makeText(context, "Este é o alarme de número " + idAlarme, Toast.LENGTH_LONG).show();
    }
}
