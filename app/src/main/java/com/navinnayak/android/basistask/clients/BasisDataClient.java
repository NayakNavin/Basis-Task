package com.navinnayak.android.basistask.clients;

import com.navinnayak.android.basistask.CustomGsonConverter.CleanGsonConverter;

import retrofit2.Retrofit;

public class BasisDataClient {

    private static final String BASE_URL = "https://gist.githubusercontent.com/";
    private static Retrofit retrofit = null;

    /**
     * Retrofit Instance
     */
    public static Retrofit getClient() {

        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(CleanGsonConverter.create())
                .build();
        return retrofit;
    }
}