package woke.woke;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
        //org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hana on 3/16/2017.
 */

public interface wokeInterface {
    //this calls a GET response to my website at base_url/members
    @GET("/members")
    Call<JsonArray> getMemberDetails();
}
