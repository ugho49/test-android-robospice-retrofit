package fr.ughostephan.myapprobospice.network.service;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import retrofit.RestAdapter;

/**
 * Created by ughostephan on 12/01/2018.
 */
public class RetrofitSpiceService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(PostService.class);
    }

    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        return new RestAdapter.Builder()
//                .setRequestInterceptor(new RequestInterceptor() {
//                    @Override
//                    public void intercept(RequestFacade request) {
//                        request.
//                    }
//                })
                .setEndpoint(getServerUrl())
                .setConverter(getConverter());
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }

}