package br.com.william.a2pdm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalTime;
import java.util.Calendar;

import br.com.william.a2pdm.model.Alarme;
import br.com.william.a2pdm.model.dao.AlarmeDao;
import br.com.william.a2pdm.recievers.AlarmeReciever;

import static br.com.william.a2pdm.MainActivity.CHAVE_ALARME;
import static br.com.william.a2pdm.recievers.AlarmeReciever.CHAVE_ID_SERVICO;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EdicaoAlarmeActivity extends AppCompatActivity {

    public AlarmManager alarmManager;
    private TimePicker timePicker;
    private EditText campoNome;
    private Alarme alarme = new Alarme();
    private AlarmeDao dao = new AlarmeDao();
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        setContentView(R.layout.activity_edicao_alarme);
        configuraBotao();
        inicializaCampos();
        carregaAlarme();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void carregaAlarme() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALARME)) {
            alarme = (Alarme) dados.getSerializableExtra(CHAVE_ALARME);
            preencheCampos();
        }
    }

    private void preencheCampos() {
        campoNome.setText(alarme.getNome());
        timePicker.setHour(alarme.getHorario().getHour());
        timePicker.setMinute(alarme.getHorario().getMinute());
    }

    private void configuraBotao() {
        FloatingActionButton confirma = findViewById(R.id.fab_confirma_alarme);
        confirma.setOnClickListener(view -> finalizaEdicao());
    }

    private void finalizaEdicao() {
        preencheAlarme();
        if(alarme.temIdValido()) {
            this.dao.edita(this.alarme);
            editaAlarme();
        } else {
           alarme = this.dao.salvar(this.alarme);
           configuraAlarmeSistema();
        }
        finish();
    }

    private void configuraAlarmeSistema() {
        atualizaCalendar();


        Intent intent = new Intent(this, AlarmeReciever.class);
        intent.putExtra(CHAVE_ID_SERVICO, alarme.getId());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, alarme.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        dao.getIntentList().put(alarme.getId(), pendingIntent);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(this, "Alarme Adicionado", Toast.LENGTH_SHORT).show();
    }

    private void editaAlarme() {
        atualizaCalendar();
        PendingIntent pdInt = dao.getIntentList().get(alarme.getId());
        alarmManager.cancel(pdInt);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pdInt);
    }

    private void atualizaCalendar() {
        calendar.set(Calendar.HOUR_OF_DAY, alarme.getHorario().getHour());
        calendar.set(Calendar.MINUTE, alarme.getHorario().getMinute());
        calendar.set(Calendar.SECOND, 0);
    }


    private void preencheAlarme() {
        this.alarme.setHorario(LocalTime.of(this.timePicker.getHour(), this.timePicker.getMinute()));
        this.alarme.setNome(this.campoNome.getText().toString());
    }

    private void inicializaCampos() {
        this.campoNome = findViewById(R.id.nome_alarme);
        configuraTimePicker();
    }

    private void configuraTimePicker() {
        this.timePicker = findViewById(R.id.timepicker_alarme);
        this.timePicker.setIs24HourView(true);
        this.timePicker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
    }



}
