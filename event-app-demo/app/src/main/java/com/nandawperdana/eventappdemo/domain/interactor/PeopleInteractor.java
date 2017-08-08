package com.nandawperdana.eventappdemo.domain.interactor;


import com.nandawperdana.eventappdemo.api.APICallListener;
import com.nandawperdana.eventappdemo.api.APICallManager;
import com.nandawperdana.eventappdemo.api.RootResponseModel;
import com.nandawperdana.eventappdemo.api.people.PeopleModel;
import com.nandawperdana.eventappdemo.util.Enums;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nandawperdana.
 */

public class PeopleInteractor implements Interactor {
    APICallListener listener;

    public PeopleInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetPeople() {
        final Enums.APIRoute route = Enums.APIRoute.GET_PEOPLE;
        Call<List<PeopleModel>> call = APICallManager.getInstance().peopleManager.getPeople();
        call.enqueue(new Callback<List<PeopleModel>>() {
            @Override
            public void onResponse(Call<List<PeopleModel>> call, Response<List<PeopleModel>> response) {
                listener.onAPICallSucceed(route, response.body());
            }

            @Override
            public void onFailure(Call<List<PeopleModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }
}
