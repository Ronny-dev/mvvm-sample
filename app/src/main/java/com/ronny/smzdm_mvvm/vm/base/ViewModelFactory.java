package com.ronny.smzdm_mvvm.vm.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ronny.smzdm_mvvm.vm.DailyFragmentViewModel;
import com.ronny.smzdm_mvvm.vm.MainViewModel;

/**
 * Created by Ronny on 2020/11/24
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel();
        } else if (modelClass.isAssignableFrom(DailyFragmentViewModel.class)) {
            return (T) new DailyFragmentViewModel();
        }
        return null;
    }
}
