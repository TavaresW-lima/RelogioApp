package br.com.william.a2pdm.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import br.com.william.a2pdm.R;
import br.com.william.a2pdm.databinding.FragmentRelogioBinding;
import br.com.william.a2pdm.viewModels.fragments.RelogioFragmentViewModel;

@RequiresApi(api = Build.VERSION_CODES.O)
public class RelogioFragment extends Fragment {

    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    FragmentRelogioBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RelogioFragmentViewModel viewModel = new ViewModelProvider(this).get(RelogioFragmentViewModel.class);
        FragmentRelogioBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_relogio, container, false);
        View view = binding.getRoot();
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        this.binding = binding;
        return view;
    }


}
