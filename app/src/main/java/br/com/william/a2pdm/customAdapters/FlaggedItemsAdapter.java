package br.com.william.a2pdm.customAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.william.a2pdm.R;

public class FlaggedItemsAdapter extends RecyclerView.Adapter<FlaggedItemsAdapter.ViewHolder> {

    private final List<String> tempos = new ArrayList<>();

    public List<String> getTempos() {
        return this.tempos;
    }

    public FlaggedItemsAdapter(List<String> tempos) {
        this.tempos.addAll(tempos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flagged_time_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTempo().setText(this.tempos.get(position));
    }

    @Override
    public int getItemCount() {
        return this.tempos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tempo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tempo = itemView.findViewById(R.id.flagged_time);
        }

        public TextView getTempo() {
            return tempo;
        }
    }

    public void clean() {
        this.tempos.removeAll(tempos);
    }

    public void flagTime(String time) {
        this.tempos.add(0, time);
    }

}
