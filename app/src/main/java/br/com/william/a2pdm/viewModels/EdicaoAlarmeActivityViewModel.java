package br.com.william.a2pdm.viewModels;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.databinding.Observable;
import androidx.lifecycle.ViewModel;



@SuppressLint("StaticFieldLeak")
@RequiresApi(api = Build.VERSION_CODES.M)
public class EdicaoAlarmeActivityViewModel extends ViewModel implements Observable {

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}
