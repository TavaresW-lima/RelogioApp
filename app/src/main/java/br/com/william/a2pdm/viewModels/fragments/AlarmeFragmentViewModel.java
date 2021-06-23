package br.com.william.a2pdm.viewModels.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.Observable;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalTime;
import java.util.Collections;

import br.com.william.a2pdm.EdicaoAlarmeActivity;
import br.com.william.a2pdm.MainActivity;
import br.com.william.a2pdm.customAdapters.AlarmItemsAdapter;
import br.com.william.a2pdm.model.Alarme;
import br.com.william.a2pdm.model.dao.AlarmeDao;
import br.com.william.a2pdm.recievers.AlarmeReciever;

import static br.com.william.a2pdm.recievers.AlarmeReciever.CHAVE_ID_SERVICO;

@SuppressLint("StaticFieldLeak")
@RequiresApi(api = Build.VERSION_CODES.O)
public class AlarmeFragmentViewModel extends ViewModel implements Observable {
    private MutableLiveData<Boolean> isAlarmListEmpty = new MutableLiveData<>(true);
    public LiveData<Boolean> getIsAlarmListEmpty() {
        return isAlarmListEmpty;
    }

    private Activity activity;
    private FragmentManager fm;
    private AlarmeDao dao = new AlarmeDao();
    private AlarmItemsAdapter adapter = new AlarmItemsAdapter(dao.all());
    private RecyclerView alarmListView;

    public void setFragmentManager(FragmentManager fm) {
        this.fm = fm;
    }

    public void configuraListaComRecycleView(RecyclerView rv) {
        this.alarmListView = rv;
        this.adapter.setActivity(activity);
        this.alarmListView.setAdapter(adapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(
                new ItemTouchHandler(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT)
        );
        touchHelper.attachToRecyclerView(this.alarmListView);
    }



    public void setActivity(Activity mainActivity) {
        this.activity = mainActivity;
    }

    public void novoAlarme() {
        this.activity.startActivity(new Intent(this.activity, EdicaoAlarmeActivity.class));
    }

    public void refreshList() {
        adapter.refreshAdapter();
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    private class ItemTouchHandler extends ItemTouchHelper.SimpleCallback {

        public ItemTouchHandler(int dragDirs, int swipeDirs) {
            super(dragDirs, swipeDirs);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int from = viewHolder.getAdapterPosition();
            int to = target.getAdapterPosition();

            adapter.notifyItemMoved(from, to);
            dao.syncReorder(from, to);

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            PendingIntent pendingIntent = dao.getIntentList().get(viewHolder.getLayoutPosition() + 1);

            AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);

            adapter.remover(viewHolder.getLayoutPosition());
            dao.remove(viewHolder.getLayoutPosition());


        }
    }
}
