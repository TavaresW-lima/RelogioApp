package br.com.william.a2pdm.customAdapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.william.a2pdm.EdicaoAlarmeActivity;
import br.com.william.a2pdm.R;
import br.com.william.a2pdm.model.Alarme;
import br.com.william.a2pdm.model.dao.AlarmeDao;

import static br.com.william.a2pdm.MainActivity.CHAVE_ALARME;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AlarmItemsAdapter extends RecyclerView.Adapter<AlarmItemsAdapter.AlarmItemViewHolder> {

    private final List<Alarme> alarmeList = new ArrayList<>();
    private AlarmeDao dao = new AlarmeDao();
    private Activity activity;

    public AlarmItemsAdapter(List<Alarme> alarmeList) {
        this.alarmeList.addAll(alarmeList);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public AlarmItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_item, parent ,false);

        return new AlarmItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmItemsAdapter.AlarmItemViewHolder holder, int position) {
        holder.itemView.setOnClickListener(view -> {
            Intent navegaEdicao = new Intent(activity, EdicaoAlarmeActivity.class);
            navegaEdicao.putExtra(CHAVE_ALARME, alarmeList.get(position));
            activity.startActivity(navegaEdicao);

        });

        Alarme alarme = alarmeList.get(position);
        holder.bind(alarme);
    }

    @Override
    public int getItemCount() {
        return alarmeList.size();
    }

    public static class AlarmItemViewHolder extends RecyclerView.ViewHolder {

        private Alarme alarme;
        private final TextView nomeAlarme;
        private final TextView horarioAlarme;
        private final SwitchMaterial switchAlarme;

        public AlarmItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeAlarme = itemView.findViewById(R.id.nome_alarme);
            horarioAlarme = itemView.findViewById(R.id.horario_alarme);
            switchAlarme = itemView.findViewById(R.id.switch_alarme);
        }

        public void bind(Alarme alarme) {
            this.alarme = alarme;
            nomeAlarme.setText(this.alarme.getNome());
            horarioAlarme.setText(this.alarme.getHorario().format(DateTimeFormatter.ofPattern("HH:mm")));
            switchAlarme.setChecked(this.alarme.isActive());
            configuraCliqueSwitch();
        }

        private void configuraCliqueSwitch() {
            switchAlarme.setOnCheckedChangeListener((buttonView, isChecked) -> {
                alarme.setActive(isChecked);
            });
        }
    }

    public List<Alarme> getAlarmeList() {
        return alarmeList;
    }

    public void salvar(Alarme alarme) {
        this.alarmeList.add(alarme);
        notifyDataSetChanged();
    }

    public void remover(int position) {
        this.alarmeList.remove(position);
        notifyItemRemoved(position);
    }

    public void refreshAdapter() {
        this.alarmeList.clear();
        this.alarmeList.addAll(dao.all());
        notifyDataSetChanged();
    }
}
