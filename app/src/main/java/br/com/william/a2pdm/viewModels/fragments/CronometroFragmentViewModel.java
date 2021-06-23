package br.com.william.a2pdm.viewModels.fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

import androidx.annotation.RequiresApi;
import androidx.databinding.Observable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalTime;

import br.com.william.a2pdm.customAdapters.FlaggedItemsAdapter;
import br.com.william.a2pdm.fragments.CronometroFragment;
import br.com.william.a2pdm.model.dao.FlaggedTimeDao;

@SuppressLint("StaticFieldLeak")
@RequiresApi(api = Build.VERSION_CODES.O)
public class CronometroFragmentViewModel extends ViewModel implements Observable {

    private MutableLiveData<Boolean> isFlaggedListEmpty = new MutableLiveData<>(true);
    public LiveData<Boolean> getIsFlaggedListEmpty() {
        return isFlaggedListEmpty;
    }

    private Chronometer cronometro;
    private RecyclerView flaggedList;
    private FlaggedTimeDao flagDao = new FlaggedTimeDao();
    private FlaggedItemsAdapter adapter = new FlaggedItemsAdapter(flagDao.all());
    private long pauseOffset = 0;
    private boolean running = false;

    public void setCronometro(Chronometer cronometro) {
        this.cronometro = cronometro;
        cronometro.setOnChronometerTickListener( (cronometroAtual) -> {
            cronometroAtual.setText(cronometerToText(cronometroAtual));
        });
    }

    public void setFlaggedList(RecyclerView list) {
        this.flaggedList = list;
        this.flaggedList.setAdapter(adapter);
    }

    public void startChronometer() {
        if(!running) {
            cronometro.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            cronometro.start();
            this.running = true;
        }
    }

    public void pauseChronometer() {
        if(running) {
            cronometro.stop();
            this.pauseOffset = SystemClock.elapsedRealtime() - cronometro.getBase();
            this.running = false;
        }
    }

    public void stopChronometer() {
        if(running) {
          cronometro.stop();
          cronometro.setBase(SystemClock.elapsedRealtime());
          this.pauseOffset = 0;
          adapter.clean();
          adapter.notifyDataSetChanged();
          cronometro.setText("00:00:00");
          isFlaggedListEmpty.setValue(true);
          this.running = false;
        }

    }

    public void flagChronometer() {
        if(running) {
            isFlaggedListEmpty.setValue(false);
            adapter.flagTime(cronometerToText(cronometro));
            adapter.notifyDataSetChanged();
            Log.i("LOG", "flagChronometer: " + cronometerToText(cronometro));
        }
    }

    private String cronometerToText(Chronometer cronometro) {
        long time = SystemClock.elapsedRealtime() - cronometro.getBase();
        int h   = (int)(time /3600000);
        int m = (int)(time - h*3600000)/60000;
        int s= (int)(time - h*3600000- m*60000)/1000 ;
        String hh = h < 10 ? "0"+h: h+"";
        String mm = m < 10 ? "0"+m: m+"";
        String ss = s < 10 ? "0"+s: s+"";
        return hh+":"+mm+":"+ss;
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}
