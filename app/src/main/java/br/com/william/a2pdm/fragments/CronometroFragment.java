package br.com.william.a2pdm.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import br.com.william.a2pdm.R;
import br.com.william.a2pdm.customAdapters.FlaggedItemsAdapter;
import br.com.william.a2pdm.databinding.FragmentCronometroBinding;
import br.com.william.a2pdm.model.dao.FlaggedTimeDao;
import br.com.william.a2pdm.viewModels.fragments.CronometroFragmentViewModel;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CronometroFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CronometroFragmentViewModel viewModel = new ViewModelProvider(this).get(CronometroFragmentViewModel.class);
        FragmentCronometroBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cronometro, container, false);

        View view = binding.getRoot();
        viewModel.setCronometro(view.findViewById(R.id.cronometro_text));

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        configuraLista(viewModel, view);

        return view;
    }

    private void configuraLista(CronometroFragmentViewModel viewModel, View view) {
        RecyclerView flaggedTimeList = view.findViewById(R.id.flagged_time_list);
        viewModel.setFlaggedList(flaggedTimeList);
    }
}
