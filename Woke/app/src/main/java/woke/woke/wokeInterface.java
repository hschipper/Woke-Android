package woke.woke;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
        //org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hana on 3/16/2017.
 */

public interface wokeInterface {
    //this calls a GET response to my website at base_url/members
    @GET("/member_page")
    Call<JsonArray> getMemberDetails(@Query("state") String state);

    @GET("/bills/")
    Call<JsonArray> getBillDetails();
}
