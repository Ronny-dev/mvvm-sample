package com.ronny.smzdm_mvvm.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import com.ronny.smzdm_mvvm.R;
import com.ronny.smzdm_mvvm.ui.base.BaseActivity;
import com.ronny.smzdm_mvvm.vm.MainViewModel;
import com.ronny.smzdm_mvvm.vm.base.ViewModelFactory;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainViewModel> {

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @NonNull
    @Override
    protected MainViewModel createViewModel() {
        ViewModelFactory factory = new ViewModelFactory();
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.getDataSource().observe(this, new DataSourceObserver());
        viewModel.getDataLoading().observe(this, new LoadingObserver());

        viewModel.loadSmzdmDataSource();
    }

    private class LoadingObserver implements Observer<Boolean> {

        @Override
        public void onChanged(Boolean b) {
            Log.i("Ronny", "on loading changed: " + b);
        }
    }

    private class DataSourceObserver implements Observer<String> {

        @Override
        public void onChanged(String s) {
            Log.i("Ronny", "on DataSource changed: " + s);
        }
    }
}