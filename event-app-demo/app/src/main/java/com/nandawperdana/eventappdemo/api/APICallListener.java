package com.nandawperdana.eventappdemo.api;

import com.nandawperdana.eventappdemo.api.people.PeopleModel;
import com.nandawperdana.eventappdemo.util.Enums;

import java.util.List;

/**
 * Created by nandawperdana.
 */
public interface APICallListener {
    void onAPICallSucceed(Enums.APIRoute route, List<PeopleModel> responseModels);

    void onAPICallFailed(Enums.APIRoute route, Throwable throwable);
}
