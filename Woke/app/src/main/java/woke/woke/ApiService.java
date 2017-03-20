package woke.woke;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hana on 3/16/2017.
 */

//this class is used to query data from my site
public class ApiService {
    //set a timeout delay because sometimes android is a little slow to connect
    //to prevent it from timing too early i set it to 1 minute
    final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build();

    //creating an instance here. check out wokeInterface in java/woke.woke/wokeInterface.java
    private static wokeInterface service;
    //this is my website. We were actually be collecting data from pages within this base page.
    private static String BASE_URL = "http://104.198.148.208:8000";

    //Connect to my website using retrofit2
    public static wokeInterface getInstance() {
        if (service == null) {
            Gson gson = new GsonBuilder().create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            service = retrofit.create(wokeInterface.class);
        }
        return service;
    }
}
