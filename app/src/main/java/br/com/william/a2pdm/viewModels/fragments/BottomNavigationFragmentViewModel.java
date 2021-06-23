package br.com.william.a2pdm.viewModels.fragments;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.databinding.Observable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.william.a2pdm.MainActivity;
import br.com.william.a2pdm.model.MenuAplicacaoEnum;

@RequiresApi(api = Build.VERSION_CODES.O)
public class BottomNavigationFragmentViewModel extends ViewModel implements Observable {

    private final MutableLiveData<MenuAplicacaoEnum> menuSelecionado = new MutableLiveData<MenuAplicacaoEnum>();
    private final MutableLiveData<Boolean> isRelogioSelected = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isCronometroSelected = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isAlarmeSelected = new MutableLiveData<>(false);

    public LiveData<Boolean> getIsRelogioSelected() {
        return isRelogioSelected;
    }

    public LiveData<Boolean> getIsCronometroSelected() {
        return isCronometroSelected;
    }

    public LiveData<Boolean> getIsAlarmeSelected() {
        return isAlarmeSelected;
    }

    public void setMenu(MenuAplicacaoEnum menu) {
        toogleSelected(menu);
        menuSelecionado.setValue(menu);
        MainActivity.menuAtual = menuSelecionado;
        Log.i("LOG", "setMenu: " + menu);
    }

    private void toogleSelected(MenuAplicacaoEnum menu) {
        this.isAlarmeSelected.setValue(false);
        this.isCronometroSelected.setValue(false);
        this.isRelogioSelected.setValue(false);

        switch(menu) {
            case RELOGIO:
                this.isRelogioSelected.setValue(true);
                break;
            case ALARME:
                this.isAlarmeSelected.setValue(true);
                break;
            case CRONOMETRO:
                this.isCronometroSelected.setValue(true);
                break;
        }
    }

    public LiveData<MenuAplicacaoEnum> getMenu() {
        return this.menuSelecionado;
    }


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}
