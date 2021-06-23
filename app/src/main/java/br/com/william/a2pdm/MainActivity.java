package br.com.william.a2pdm;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import java.time.LocalTime;

import br.com.william.a2pdm.databinding.ActivityMainBinding;
import br.com.william.a2pdm.fragments.AlarmeListFragment;
import br.com.william.a2pdm.fragments.CronometroFragment;
import br.com.william.a2pdm.fragments.RelogioFragment;
import br.com.william.a2pdm.model.MenuAplicacaoEnum;
import br.com.william.a2pdm.viewModels.MainActivityViewModel;
import br.com.william.a2pdm.viewModels.fragments.BottomNavigationFragmentViewModel;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    public static final String CHAVE_ALARME = "alarme";
    private LocalTime time = LocalTime.now();
    private BottomNavigationFragmentViewModel menuViewModel;
    public static MutableLiveData<MenuAplicacaoEnum> menuAtual = new MutableLiveData<>(MenuAplicacaoEnum.RELOGIO);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configuraDataBinding();
        if (savedInstanceState == null) {
            configuraNavegacao();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void configuraNavegacaoInicial() {
        navega(menuAtual.getValue());
    }

    private void configuraDataBinding() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainActivityViewModel model = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding.setViewModel(model);
        binding.setLifecycleOwner(this);
    }

    private void configuraNavegacao() {
        this.menuViewModel = new ViewModelProvider(this).get(BottomNavigationFragmentViewModel.class);
        this.menuViewModel.getMenu().observe(this, menu -> {
            navega(menu);
        });
    }

    private void navega(MenuAplicacaoEnum menu) {
        switch (menu) {
            case RELOGIO:
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.main_center_fragment, RelogioFragment.class, null)
                        .commit();
                break;
            case ALARME:
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.main_center_fragment, AlarmeListFragment.class, null)
                        .commit();
                break;
            case CRONOMETRO:
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.main_center_fragment, CronometroFragment.class, null)
                        .commit();
                break;
        }
    }

    private void restorePendingIntents() {

    }
}
