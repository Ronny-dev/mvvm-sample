package com.ronny.smzdm_mvvm.data;

import com.ronny.smzdm_mvvm.data.network.SmzdmDataService;

/**
 * Created by Ronny on 2020/11/24
 */
public class DataManager {

    private static DataManager mInstance;

    public static DataManager getInstance() {
        synchronized (Object.class) {
            if (mInstance == null) {
                mInstance = new DataManager();
            }
            return mInstance;
        }
    }

    public SmzdmDataService getSmzdmDataService() {
        return new SmzdmDataService();
    }
}
