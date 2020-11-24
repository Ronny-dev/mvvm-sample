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
public class MainViewModel extends BaseViewModel {

    private MutableLiveData<String> mDataSource;

    private MutableLiveData<Boolean> isDataLoading;

    public MainViewModel() {
        mDataSource = new MutableLiveData<>();
        isDataLoading = new MutableLiveData<>();
    }

    public MutableLiveData<String> getDataSource() {
        return mDataSource;
    }

    public MutableLiveData<Boolean> getDataLoading() {
        return isDataLoading;
    }

    public void loadSmzdmDataSource() {
        isDataLoading.postValue(true);
        SmzdmDataService smzdmDataService = DataManager.getInstance().getSmzdmDataService();
        smzdmDataService.getDataApi().getData().enqueue(new DataSourceCallback());
    }

    private void setDataSource(String s) {
        isDataLoading.postValue(false);
        mDataSource.postValue(s);
    }

    private void setLoadDataSourceErr(@Nullable String errMsg) {
        isDataLoading.postValue(false);
        mDataSource.postValue(errMsg);
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
            int size = response.body().size();
            setDataSource("size: " + size);
        }

        @Override
        public void onFailure(Call<List<DailyEntity>> call, Throwable t) {
            setLoadDataSourceErr(t.toString());
        }
    }
}
