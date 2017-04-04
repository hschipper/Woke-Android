package woke.woke;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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


import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    //these variables are used to display the cards
    private ListView listView;
    // store retrieved data
    String curMember;
    String curState;
    String curServe;
    String curParty;
    String curDistrict;
    String curBill;
    String curSponser;
    String curHeader;
    String curCommittee;
    String curAction;
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
        setContentView(R.layout.activity_main);

        Button agriculture_h = (Button) findViewById(R.id.agriculture_h);
        Button agriculture_s = (Button) findViewById(R.id.agriculture_s);
        Button appropriations_h = (Button) findViewById(R.id.appropriations_h);
        Button appropriations_s = (Button) findViewById(R.id.appropriations_s);
        Button armed_services_h = (Button) findViewById(R.id.armed_services_h);
        Button armed_services_s = (Button) findViewById(R.id.armed_services_s);
        Button banking_housing = (Button) findViewById(R.id.banking_housing);
        Button budget_h = (Button) findViewById(R.id.budget_h);
        Button budget_s = (Button) findViewById(R.id.budget_s);
        Button commerce = (Button) findViewById(R.id.commerce_science_transport);
        Button education = (Button) findViewById(R.id.education_workforce);
        Button energy_commerce = (Button) findViewById(R.id.energy_commerce);
        Button energy_natural = (Button) findViewById(R.id.energy_natural);
        Button environment = (Button) findViewById(R.id.environment);
        Button ethics = (Button) findViewById(R.id.ethics);
        Button financial_services = (Button) findViewById(R.id.financial_services);
        Button finance = (Button) findViewById(R.id.finance);
        Button foreign_affairs = (Button) findViewById(R.id.foreign_affairs);
        Button foreign_relations = (Button) findViewById(R.id.foreign_relations);
        Button health = (Button) findViewById(R.id.health);
        Button homeland_h = (Button) findViewById(R.id.homeland_h);
        Button homeland_s = (Button) findViewById(R.id.homeland_s);
        Button house = (Button) findViewById(R.id.house);
        Button judiciary_h = (Button) findViewById(R.id.judiciary_h);
        Button judiciary_s = (Button) findViewById(R.id.judiciary_s);
        Button natural = (Button) findViewById(R.id.natural_resources);
        Button oversight = (Button) findViewById(R.id.oversight);
        Button rules_h = (Button) findViewById(R.id.rules_h);
        Button rules_s = (Button) findViewById(R.id.rules_s);
        Button science = (Button) findViewById(R.id.science_space);
        Button small_business_h = (Button) findViewById(R.id.small_business_h);
        Button small_business_s = (Button) findViewById(R.id.small_business_s);
        Button transportation = (Button) findViewById(R.id.transportation);
        Button veterans_h = (Button) findViewById(R.id.veterans_h);
        Button veterans_s = (Button) findViewById(R.id.veterans_s);
        Button ways = (Button) findViewById(R.id.ways);
        Button congress = (Button) findViewById(R.id.congress_button);

        agriculture_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Agriculture";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        agriculture_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Agriculture, Nutrition, and Forestry";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        appropriations_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Appropriations";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        armed_services_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Armed Services";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        budget_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Budget";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Education and the Workforce";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        energy_commerce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Energy and Commerce";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        ethics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Ethics";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        financial_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Financial Services";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        foreign_affairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Foreign Affairs";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        homeland_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Homeland Security";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - House Administration";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        judiciary_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Judiciary";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        natural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Natural Resources";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        oversight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Oversight and Government Reform";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        rules_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Rules";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Science, Space, and Technology";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        small_business_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Small Business";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        transportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Transportation and Infrastructure";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        veterans_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Veterans' Affairs";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        ways.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Ways and Means";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        appropriations_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Appropriations";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        armed_services_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Armed Services";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        banking_housing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Banking, Housing, and Urban Affairs";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        budget_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Budget";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        commerce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Commerce, Science, and Transportation";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        energy_natural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Energy and Natural Resources";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        environment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Environment and Public Works";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Finance";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        foreign_relations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Foreign Relations";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Health, Education, Labor, and Pensions";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        homeland_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Homeland Security and Governmental Affairs";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        judiciary_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Judiciary";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        rules_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Rules and Administration";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        small_business_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Small Business and Entrepreneurship";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });

        veterans_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Veterans' Affairs";
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("committees",committees);
                startActivity(i);
            }
        });
        congress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CongressActivity.class));
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
        collectMembers(state);
        String committees = "House - Education and the Workforce";
        collectBills(committees);

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
            Log.d("displaying", m.getMember() + m.getState() + m.getParty() + m.getServed() + m.getDistrict());
            //add each member to be displayed as a card
            cardArrayAdapter.add(m);
        }
        listView.setAdapter(cardArrayAdapter);

    }
    public void onBillSuccess() {
        //this is accessing the listview found in side res/layout/listview.xml
        listView = (ListView) findViewById(R.id.card_listView);
        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));
        //list_item_card.xml is found in res/layout/list_item_card.xml
        BillCardArrayAdapter cardArrayAdapter = new BillCardArrayAdapter(getApplicationContext(), R.layout.list_item_card);
        //traverse through the ListArray declared at the top (above onCreate) and filled inside onResponse
        for (Bill b : bills) {
            //display for debugging
            Log.d("displaying", b.getTitle() + b.getSponser() + b.getCommittees() + b.getHeading() + b.getLatestAction());
            //add each member to be displayed as a card
            cardArrayAdapter.add(b);
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
            startActivity( new Intent(MainActivity.this,LoginActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /* This function connects to my website 104.198.148.208:8000/member_page/STATE
     * String state: this parameter sets which state to query members from
     */
    public void collectMembers(String state) {
        //connect to website and return data found at 104.198.148.208:8000/members
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

                //traverse through the array and stuff each member into a list of members
                for (int i = 0; i < jarray.size(); i++) {
                    //extract each object
                    JsonObject jobject = jarray.get(i).getAsJsonObject();
                    //extract information from object
                    curMember = jobject.get("member").toString();
                    curState = jobject.get("state").toString();
                    curParty = jobject.get("party").toString();
                    curDistrict = jobject.get("district").toString();
                    curServe = jobject.get("served").toString();
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

    public void collectBills(String committees) {

        //connect to website and return data found at 104.198.148.208:8000/members
        ApiService.getInstance().getBillDetails(committees).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                //api success
                //when this returns 200 we connected to webpage2
                Log.d("MainActivity", "Status Code = " + response.code());
                //string of json collected from website
                Log.d("MainActivity", "body test = " + response.body());
                //stuff that string into a JsonElement
                JsonElement jelement = new JsonParser().parse(response.body().toString());
                //get the JsonArray out of the element.
                jarray = jelement.getAsJsonArray();

                //traverse through the array and stuff each member into a list of members
                for (int i = 0; i < jarray.size(); i++) {
                    //extract each object
                    JsonObject jobject = jarray.get(i).getAsJsonObject();
                    //extract information from object
                    curBill = jobject.get("billTitle").toString();
                    curSponser = jobject.get("sponser").toString();
                    curHeader = jobject.get("billHeading").toString();
                    curCommittee = jobject.get("committees").toString();
                    curAction = jobject.get("latestAction").toString();
                    //display information for dubug purposes
                    Log.d("Inside for loop ", "billTitle = " + curBill);
                    Log.d("Insdie for loop", "sponser=" + curSponser);
                    //insert into list
                    bills.add(new Bill(curBill, curSponser, curHeader, curCommittee, curAction));
                }
                //call the function on success.
                onBillSuccess();
            }

            @Override  //couldn't connect to website.
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("Apicall", t.getMessage());
                t.printStackTrace();
            }
        });

    }


}
