package br.com.william.a2pdm.model.dao;

import android.app.PendingIntent;
import android.os.Build;
import android.util.ArrayMap;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.com.william.a2pdm.model.Alarme;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AlarmeDao {

    private final static Map<Integer, PendingIntent> pendingIntentList = new ArrayMap<>();

    public static final List<Alarme> alarmeList = new ArrayList<>();
    public static int idCounter = 1;

    public Alarme salvar(Alarme alarme) {
        alarme.setId(idCounter);
        alarme.setActive(true);
        alarmeList.add(alarme);
        atualizaIds();
        return alarmeList.get(idCounter - 2);
    }

    public void edita(Alarme alarme) {
        Alarme alarmeEncontrado = buscaAlarmePeloId(alarme);
        if (alarmeEncontrado != null) {
            alarmeList.set(alarmeList.indexOf(alarmeEncontrado), alarme);
        }
    }

    public void remove(int position) {
        alarmeList.remove(position);
    }

    public List<Alarme> all() {
        return new ArrayList<>(alarmeList);
    }

    public void syncReorder(int from, int to) {
        Collections.swap(alarmeList, from, to);
    }

    @Nullable
    private Alarme buscaAlarmePeloId(Alarme alarme) {
        Alarme alarmeEncontrado = null;
        for (Alarme a : alarmeList) {
            if (a.getId() == alarme.getId()) {
                return a;
            }
        }
        return null;
    }

    private void atualizaIds() {
        idCounter++;
    }

    public Map<Integer, PendingIntent> getIntentList() {
        return pendingIntentList;
    }


}


