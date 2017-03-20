package woke.woke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
//org.json.JSONObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    //these variables are used to display the cards
    private CardArrayAdapter cardArrayAdapter;
    private ListView listView;
    //store retrieved data
    String curMember;
    String curState;
    JsonArray jarray;
    //move retrieved data above into this array list.
    ArrayList<Member> members = new ArrayList<Member>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //connect to website and return data found at 104.198.148.208:8000/members
        ApiService.getInstance().getMemberDetails().enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                //api success
                //when this returns 200 we connected to webpage
                Log.d("MainActivity", "Status Code = " + response.code());
                //string of json collected from website
                Log.d("MainActivity", "body test = " + response.body());
                //stuff that string into a JsonElement
                JsonElement jelement = new JsonParser().parse(response.body().toString());

                //IMPORTANT NOTE: there is a difference between JsonArray and JSONArray.
                // as well as JsonObject and JSONObject. careful not to mix the two they are useful for
                //different reasons and do not work the same!

                //get the JsonArray out of the element.
                jarray = jelement.getAsJsonArray();

                //traverse through the array and stuff each member into a list of members
                for (int i = 0; i < jarray.size(); i++) {
                    //extract each object
                    JsonObject jobject = jarray.get(i).getAsJsonObject();
                    //extract information from object
                    curMember = jobject.get("member").toString();
                    curState = jobject.get("state").toString();
                    //display information for dubug purposes
                    Log.d("Inside for loop ", "member = " + curMember);
                    Log.d("Insdie for loop", "state=" + curState);
                    //insert into list
                    members.add(new Member(curMember,curState));
                }
                //call the function on success.
                onSuccess();
            }
            @Override  //couldn't connect to website.
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("Apicall", t.getMessage());
                t.printStackTrace();
            }
        });
    }
    /* This function was created because if I put this code inside the onCreate function it ran
     * before the response returned with the data, thus it was trying to access a null variable and crashing.
     * this was my way to enforce that this code ran AFTER collecting data from my website.
     */
    public void onSuccess() {
        //this is accessing the listview found in side res/layout/listview.xml
        listView = (ListView) findViewById(R.id.card_listView);
        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));
        //list_item_card.xml is found in res/layout/list_item_card.xml
        cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.list_item_card);
        //traverse through the ListArray declared at the top (above onCreate) and filled inside onResponse
        for (Member m : members) {
            //display for debugging
            Log.d("displaying", m.getMember() + m.getState());
            //add each member to be displayed as a card
            cardArrayAdapter.add(m);
        }
        listView.setAdapter(cardArrayAdapter);

    }
}
