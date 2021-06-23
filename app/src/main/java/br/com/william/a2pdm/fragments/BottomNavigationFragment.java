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

import br.com.william.a2pdm.R;
import br.com.william.a2pdm.databinding.FragmentBottomNavigationBinding;
import br.com.william.a2pdm.model.MenuAplicacaoEnum;
import br.com.william.a2pdm.viewModels.fragments.BottomNavigationFragmentViewModel;

@RequiresApi(api = Build.VERSION_CODES.O)
public class BottomNavigationFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BottomNavigationFragmentViewModel viewModel = new ViewModelProvider(requireActivity()).get(BottomNavigationFragmentViewModel.class);
        FragmentBottomNavigationBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_navigation, container, false);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        binding.getViewModel().setMenu(MenuAplicacaoEnum.RELOGIO);

        View view = binding.getRoot();

        return view;
    }
}
