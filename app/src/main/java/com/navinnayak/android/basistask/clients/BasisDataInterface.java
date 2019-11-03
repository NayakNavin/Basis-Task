package com.navinnayak.android.basistask.clients;

import com.navinnayak.android.basistask.models.BasisDataList;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Retrofit get annotation with the URL
 * And this method  will return  the List of data
 */
public interface BasisDataInterface {


    @GET("/anishbajpai014/d482191cb4fff429333c5ec64b38c197/raw/b11f56c3177a9ddc6649288c80a004e7df41e3b9/HiringTask.json")
    Call<BasisDataList> getAllData();
}