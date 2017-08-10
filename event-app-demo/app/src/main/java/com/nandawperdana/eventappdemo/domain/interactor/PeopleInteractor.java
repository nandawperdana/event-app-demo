package com.nandawperdana.eventappdemo.domain.interactor;


import com.nandawperdana.eventappdemo.api.APICallListener;
import com.nandawperdana.eventappdemo.api.APICallManager;
import com.nandawperdana.eventappdemo.api.people.PeopleModel;
import com.nandawperdana.eventappdemo.util.Enums;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nandawperdana.
 */

public class PeopleInteractor implements Interactor {
    APICallListener listener;
    private RealmList<PeopleModel> people = new RealmList<>();
    private Realm realm = Realm.getDefaultInstance();

    public PeopleInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetPeople() {
        final Enums.APIRoute route = Enums.APIRoute.GET_PEOPLE;
        Call<List<PeopleModel>> call = APICallManager.getInstance().peopleManager.getPeople();
        call.enqueue(new Callback<List<PeopleModel>>() {
            @Override
            public void onResponse(Call<List<PeopleModel>> call, Response<List<PeopleModel>> response) {
                people = new RealmList<>(response.body().toArray(new PeopleModel[response.body().size()]));
                insertData();
                listener.onAPICallSucceed(route, getPeople());
            }

            @Override
            public void onFailure(Call<List<PeopleModel>> call, Throwable t) {
                if (getPeople().size() == 0)
                    listener.onAPICallFailed(route, t);
                else {
                    getData();
                    listener.onAPICallSucceed(route, getPeople());
                }
            }
        });
    }

    public RealmList<PeopleModel> getPeople() {
        return people;
    }

    private void insertData() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                if (realm.where(PeopleModel.class).count() != people.size()) {
                    clearData();
                    realm.beginTransaction();
                    for (PeopleModel item : people) {
                        if (realm.where(PeopleModel.class).equalTo("name", item.getName()).count() == 0) {
                            realm.copyToRealmOrUpdate(people);
                        }
                    }
                }
            }
        });
    }

    public void getData() {
        RealmResults<PeopleModel> results = realm.where(PeopleModel.class).findAll();
        people.addAll(results.subList(0, results.size()));
    }

    private void clearData() {
        realm.delete(PeopleModel.class);
        realm.commitTransaction();
    }
}
