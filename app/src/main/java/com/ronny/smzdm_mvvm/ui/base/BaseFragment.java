package com.ronny.smzdm_mvvm.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ronny.smzdm_mvvm.vm.base.BaseViewModel;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ronny on 2020/11/24
 */
public abstract class BaseFragment<VM extends BaseViewModel> extends Fragment {

    protected VM viewModel;

    private Unbinder butterBinder;

    protected abstract int getContentView();

    @NonNull
    protected abstract VM createViewModel();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        viewModel = createViewModel();
        butterBinder = ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onDestroy() {
        butterBinder.unbind();
        super.onDestroy();
    }
}
