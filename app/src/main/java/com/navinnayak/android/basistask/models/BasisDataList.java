package com.navinnayak.android.basistask.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BasisDataList {

    @SerializedName("data")
    @Expose
    private ArrayList<BasisData> data = null;

    public ArrayList<BasisData> getData() {
        return data;
    }

    public void setData(ArrayList<BasisData> data) {
        this.data = data;
    }

}