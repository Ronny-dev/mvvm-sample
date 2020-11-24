package com.ronny.smzdm_mvvm.vm;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.ronny.smzdm_mvvm.data.DataManager;
import com.ronny.smzdm_mvvm.data.entity.DailyEntity;
import com.ronny.smzdm_mvvm.data.network.SmzdmDataService;
import com.ronny.smzdm_mvvm.vm.base.BaseViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ronny on 2020/11/24
 */
public class DailyFragmentViewModel extends BaseViewModel {
    MutableLiveData<Boolean> isDataLoading;

    MutableLiveData<List<DailyEntity>> mDataEntity;

    public MutableLiveData<Boolean> getDataLoading() {
        return isDataLoading;
    }

    public MutableLiveData<List<DailyEntity>> getDataSource() {
        return mDataEntity;
    }

    public void loadSmzdmDataSource() {
        isDataLoading.postValue(true);
        SmzdmDataService smzdmDataService = DataManager.getInstance().getSmzdmDataService();
        smzdmDataService.getDataApi().getData().enqueue(new DataSourceCallback());
    }

    private void setDataSource(List<DailyEntity> list) {
        isDataLoading.postValue(false);
        mDataEntity.postValue(list);
    }

    private void setLoadDataSourceErr(@Nullable String errMsg) {
        isDataLoading.postValue(false);
    }

    private class DataSourceCallback implements Callback<List<DailyEntity>> {

        @Override
        public void onResponse(Call<List<DailyEntity>> call, Response<List<DailyEntity>> response) {
            if (!response.isSuccessful()) {
                setLoadDataSourceErr(null);
                return;
            }
            if(response.body() == null) {
                setLoadDataSourceErr(null);
                return;
            }
            setDataSource(response.body());
        }

        @Override
        public void onFailure(Call<List<DailyEntity>> call, Throwable t) {
            setLoadDataSourceErr(t.toString());
        }
    }
}
