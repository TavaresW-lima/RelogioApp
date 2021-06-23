package br.com.william.a2pdm.viewModels.fragments;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.databinding.Observable;
import androidx.lifecycle.ViewModel;

@RequiresApi(api = Build.VERSION_CODES.O)
public class RelogioFragmentViewModel extends ViewModel implements Observable {

    @Override
    public void addOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {

    }

}
