package woke.woke;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hana on 3/30/2017.
 */

public class CongressActivity extends AppCompatActivity {
    //these variables are used to display the cards
    private ListView listView;
    // store retrieved data
    String curMember;
    String curState;
    String curDistrict;
    String curParty;
    String curServe;

    JsonArray jarray;
    //move retrieved data above into this array list.
    ArrayList<Member> members = new ArrayList<Member>();
    ArrayList<Bill> bills = new ArrayList<Bill>();

    //declare auth
    private FirebaseAuth mAuth;
    //declare auth listener
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congress);


        final EditText inputzip = (EditText) findViewById(R.id.zipcode);
        Button btn_zip = (Button) findViewById(R.id.zipsearch);
        Button home = (Button) findViewById(R.id.home_button);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CongressActivity.this, MainActivity.class));
            }
        });

        btn_zip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String state = "";
                int zip = Integer.parseInt(inputzip.getText().toString());
                if ((zip >= Integer.parseInt("01001")) && (zip <= Integer.parseInt("02799"))) {
                    state = "Massachusetts";
                } else if ((zip >= Integer.parseInt("02800")) &&  (zip <= Integer.parseInt("02999"))){
                    state = "Rhode Island";
                } else if ((zip >= Integer.parseInt("03000")) && (zip <= Integer.parseInt("03899"))) {
                    state ="New Hampshire";
                } else if ((zip >= Integer.parseInt("03900")) && (zip <= Integer.parseInt("04999"))) {
                    state ="Maine";
                } else if ((zip >= Integer.parseInt("05000")) && (zip <= Integer.parseInt("05999"))) {
                    state ="Vermont";
                } else if ((zip >= Integer.parseInt("06000")) && (zip <= Integer.parseInt("06999"))) {
                    state ="Connecticut";
                } else if ((zip >= Integer.parseInt("07000")) && (zip <= Integer.parseInt("08999"))) {
                    state ="New Jersey";
                } else if ((zip >= 10000) && (zip <= 14999)) {
                    state ="New York";
                } else if ((zip >= 15000) && (zip <= 19699)) {
                    state ="Pennsylvania";
                } else if ((zip >= 19700) && (zip <= 19999)) {
                    state ="Delaware";
                } else if ((zip >= 20600) && (zip <= 21999)) {
                    state ="Maryland";
                } else if ((zip >= 22000) && (zip <= 24699)) {
                    state ="Virginia";
                } else if ((zip >= 24700) && (zip <= 26899)) {
                    state ="West Virginia";
                } else if ((zip >= 27000) && (zip <= 28999)) {
                    state ="North Carolina";
                } else if ((zip >= 29000) && (zip <= 29999)) {
                    state ="South Carolina";
                } else if (((zip >= 30000) && (zip <= 31999)) || ((zip >= 39800) && (zip <= 39999))) {
                    state ="Georgia";
                } else if ((zip >= 32000) && (zip <= 34999)) {
                    state ="Florida";
                } else if ((zip >= 35000) && (zip <= 36999)) {
                    state ="Alabama";
                } else if ((zip >= 37000) && (zip <= 38599)) {
                    state ="Tennessee";
                } else if ((zip >= 38600) && (zip <= 37999)) {
                    state ="Mississippi";
                } else if ((zip >= 40000) && (zip <= 42799)) {
                    state ="Kentucky";
                } else if ((zip >= 43000) && (zip <= 45999)) {
                    state ="Ohio";
                } else if ((zip >= 46000) && (zip <= 47999)) {
                    state ="Indiana";
                } else if ((zip >= 48000) && (zip <= 49999)) {
                    state ="Michigan";
                } else if ((zip >= 50000) && (zip <= 52899)) {
                    state ="Iowa";
                } else if ((zip >= 53000) && (zip <= 54999)) {
                    state ="Wisconsin";
                } else if ((zip >= 55000) && (zip <= 56799)) {
                    state ="Minnesota";
                } else if ((zip >= 57000) && (zip <= 57799)) {
                    state ="South Dakota";
                } else if ((zip >= 58000) && (zip <= 58899)) {
                    state ="North Dakota";
                } else if ((zip >= 59000) && (zip <= 59999)) {
                    state ="Montana";
                } else if ((zip >= 60000) && (zip <= 62999)) {
                    state ="Illinois";
                } else if ((zip >= 63000) && (zip <= 65899)) {
                    state ="Missouri";
                } else if ((zip >= 66000) && (zip <= 67999)) {
                    state ="Kansas";
                } else if ((zip >= 68000) && (zip <= 69399)) {
                    state ="Nebraska";
                } else if ((zip >= 70000) && (zip <= 71499)) {
                    state ="Louisiana";
                } else if ((zip >= 71600) && (zip <= 72999)) {
                    state ="Arkansas";
                } else if ((zip >= 73000) && (zip <= 74999)) {
                    state ="Oklahoma";
                } else if ((zip >= 75000) && (zip <= 79999)) {
                    state ="Texas";
                } else if ((zip >= 80000) && (zip <= 81699)) {
                    state ="Colorado";
                } else if ((zip >= 82000) && (zip <= 83199)) {
                    state ="Wyoming";
                } else if ((zip >= 83200) && (zip <= 83899)) {
                    state ="Idaho";
                } else if ((zip >= 84000) && (zip <= 84799)) {
                    state ="Utah";
                } else if ((zip >= 85000) && (zip <= 86599)) {
                    state ="Arizona";
                } else if ((zip >= 87000) && (zip <= 88499)) {
                    state ="New Mexico";
                } else if ((zip >= 88900) && (zip <= 89899)) {
                    state ="Nevada";
                } else if ((zip >= 90000) && (zip <= 96199)) {
                    state ="California";
                } else if ((zip >= 96700) && (zip <= 96899)) {
                    state ="Hawaii";
                } else if ((zip >= 97000) && (zip <= 97999)) {
                    state ="Oregon";
                } else if ((zip >= 98000) && (zip <= 99499)) {
                    state ="Washington";
                } else if ((zip >= 99500) && (zip <= 99999)) {
                    state="Alaska";
                } else {
                    state="California";
                }
                Intent i = new Intent(CongressActivity.this, CongressActivity.class);
                i.putExtra("state",state);
                startActivity(i);
                //collectMembers(state);
            }
        });

        //initialize auth
        mAuth = FirebaseAuth.getInstance();


        //auth state listener
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //User is signed in
                    Log.d("Auth","onAuthStateChanged:signed_in:"+ user.getUid());
                } else {
                    //User is signed out
                    Log.d("Auth","onAuthStateChanged:signed_out");
                }
                //exclude
                //updateUI(user);
            }

        };



        String state = "Texas";
        Bundle extras = getIntent().getExtras();
        if  (extras != null) {
            state = extras.getString("state");
        }
        collectMembers(state);


    }
    /* This function was created because if I put this code inside the onCreate function it ran
     * before the response returned with the data, thus it was trying to access a null variable and crashing.
     * this was my way to enforce that this code ran AFTER collecting data from my website.
     */
    public void onSuccess() {
        //this is accessing the listview found in side res/layout/listview.xml
        listView = (ListView) findViewById(R.id.filtered_listView);
        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));
        //list_item_card.xml is found in res/layout/list_item_card.xml
        CardArrayAdapter cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.list_item_card);
        //traverse through the ListArray declared at the top (above onCreate) and filled inside onResponse
        for (Member m : members) {
            //display for debugging
            Log.d("displaying", m.getMember() + m.getState());
            //add each member to be displayed as a card

            cardArrayAdapter.add(m);
        }
        listView.setAdapter(cardArrayAdapter);

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.login_icon){
            startActivity( new Intent(CongressActivity.this,LoginActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /* This function connects to my website 104.198.148.208:8000/member_page/STATE
     * String state: this parameter sets which state to query members from
     */
    public void collectMembers(String state) {
        //connect to website and return data found at 104.198.148.208:8000
        ApiService.getInstance().getMemberDetails(state).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                //api success
                //when this returns 200 we connected to webpage2
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
                JsonElement element;
                //traverse through the array and stuff each member into a list of members
                for (int i = 0; i < jarray.size(); i++) {
                    //extract each object
                    JsonObject jobject = jarray.get(i).getAsJsonObject();
                    //extract information from object
                    element =  jobject.get("member");
                    curMember = element.getAsString();
                    element = jobject.get("state");
                    curState = element.getAsString();
                    element = jobject.get("district");
                    curDistrict = element.getAsString();
                    element = jobject.get("party");
                    curParty = element.getAsString();
                    element = jobject.get("served");
                    curServe = element.getAsString();
                    //display information for dubug purposes
                    Log.d("Inside for loop ", "member = " + curMember);
                    Log.d("Insdie for loop", "state=" + curState);
                    //insert into list
                    members.add(new Member(curMember, curState, curDistrict, curParty, curServe));
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

}
