package com.project.dajver.listpaginationexample.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleb on 8/8/17.
 */

public class SearchModel {
    @SerializedName("list")
    @Expose
    private List<List<String>> list = new ArrayList<>();

    public List<List<String>> getList() {
        return list;
    }

    public void setList(List<List<String>> list) {
        this.list = list;
    }
}
