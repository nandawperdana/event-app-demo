package com.nandawperdana.eventappdemo.api.people;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nandawperdana.
 */

public interface PeopleService {
    @GET("people")
    Call<List<PeopleModel>>
    getPeople();
}
