package br.com.william.a2pdm.fragments;

import android.app.AlarmManager;
import android.content.Context;
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
import br.com.william.a2pdm.databinding.FragmentAlarmListBinding;
import br.com.william.a2pdm.viewModels.fragments.AlarmeFragmentViewModel;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AlarmeListFragment extends Fragment {

//    public final AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

    private AlarmeFragmentViewModel viewModel;
    private FragmentAlarmListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        configuraViewModel();
        configuraDataBinding(inflater, container);
        View view = binding.getRoot();
        configuraLista(viewModel, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.refreshList();
    }

    private void configuraDataBinding(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alarm_list, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
    }

    private void configuraViewModel() {
        viewModel = new ViewModelProvider(this).get(AlarmeFragmentViewModel.class);
        viewModel.setFragmentManager(getChildFragmentManager());
        viewModel.setActivity(getActivity());
    }


    private void configuraLista(AlarmeFragmentViewModel viewModel, View view) {
        RecyclerView rv = view.findViewById(R.id.rv_alarm_list);
        viewModel.configuraListaComRecycleView(rv);
    }


}
