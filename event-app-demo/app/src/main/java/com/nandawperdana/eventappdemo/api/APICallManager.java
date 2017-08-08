package com.nandawperdana.eventappdemo.api;

import com.nandawperdana.eventappdemo.BuildConfig;
import com.nandawperdana.eventappdemo.api.people.PeopleModel;
import com.nandawperdana.eventappdemo.api.people.PeopleService;
import com.nandawperdana.eventappdemo.util.Constants;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nandawperdana.
 */
public class APICallManager {
    public String mEndPoint = Constants.Path.DEFAULT_URL_API_STAGING;
    public static APICallManager mInstance;
    private static Retrofit sRetrofit;

    private final String mContentType = "application/json";

    public PeopleManager peopleManager;

    /**
     * singleton class instance
     *
     * @return APICallManager
     */
    public static APICallManager getInstance() {
        if (mInstance == null) {
            synchronized (APICallManager.class) {
                if (mInstance == null) {
                    mInstance = new APICallManager();
                }
            }
        }
        return mInstance;
    }

    public APICallManager() {
        if (BuildConfig.BUILD_RELEASE)
            mEndPoint = Constants.Path.DEFAULT_URL_API_PRODUCTION;

        // enable logging
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        sRetrofit = new Retrofit.Builder()
                .baseUrl(mEndPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // registering API endpoint manager
        this.peopleManager = new PeopleManager();
    }

    public static <T> T getService(Class<T> serviceClass) {
        return sRetrofit.create(serviceClass);
    }

    public class PeopleManager {
        public Call<List<PeopleModel>> getPeople() {
            PeopleService service = getService(PeopleService.class);
            return service.getPeople();
        }
    }
}
