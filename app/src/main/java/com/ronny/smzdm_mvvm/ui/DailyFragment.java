package com.ronny.smzdm_mvvm.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.ronny.smzdm_mvvm.R;
import com.ronny.smzdm_mvvm.data.entity.DailyEntity;
import com.ronny.smzdm_mvvm.ui.base.BaseFragment;
import com.ronny.smzdm_mvvm.vm.DailyFragmentViewModel;
import com.ronny.smzdm_mvvm.vm.base.ViewModelFactory;

import java.util.List;

/**
 * Created by Ronny on 2020/11/24
 */
public class DailyFragment extends BaseFragment<DailyFragmentViewModel> {

    @Override
    protected int getContentView() {
        return R.layout.fragment_daily;
    }

    @NonNull
    @Override
    protected DailyFragmentViewModel createViewModel() {
        ViewModelFactory factory = new ViewModelFactory();
        return ViewModelProviders.of(this, factory).get(DailyFragmentViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.getDataSource().observe(this, new DataSourceObserver());
        viewModel.getDataLoading().observe(this, new LoadingObserver());
    }

    private class LoadingObserver implements Observer<Boolean> {

        @Override
        public void onChanged(Boolean b) {
            Log.i("Ronny", "on loading changed: " + b);
        }
    }

    private class DataSourceObserver implements Observer<List<DailyEntity>> {

        @Override
        public void onChanged(List<DailyEntity> s) {
            Log.i("Ronny", "on DataSource changed: " + s);
        }
    }
}
