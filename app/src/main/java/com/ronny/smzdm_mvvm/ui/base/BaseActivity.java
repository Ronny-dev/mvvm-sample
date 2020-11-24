package com.ronny.smzdm_mvvm.ui.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ronny.smzdm_mvvm.vm.base.BaseViewModel;

import butterknife.ButterKnife;

/**
 * Created by Ronny on 2020/11/24
 */
public abstract class BaseActivity<VM extends BaseViewModel> extends AppCompatActivity {

    protected VM viewModel;

    protected abstract int getContentView();

    @NonNull
    protected abstract VM createViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = createViewModel();
        setContentView(getContentView());
        ButterKnife.bind(this);
    }
}
