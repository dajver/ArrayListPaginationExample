package com.project.dajver.listpaginationexample.api;

import com.project.dajver.listpaginationexample.api.model.SearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gleb on 8/8/17.
 */

public interface API {
    @GET("api.php?method=search")
    Call<SearchModel> searchAudio(@Query("q") String query, @Query("key") String key);
}
