package woke.woke;

import android.content.Intent;
import android.graphics.Color;
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
    String curBill;
    String curSponser;
    String curHeader;
    String curCommittee;
    String curAction;
    JsonArray jarray;
    //move retrieved data above into this array list.
    ArrayList<Bill> featureBills = new ArrayList<Bill>();
    ArrayList<Bill> bills = new ArrayList<Bill>();



    //declare auth
    private FirebaseAuth mAuth;
    //declare auth listener
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button agriculture_h = (Button) findViewById(R.id.agriculture_h);
        final Button appropriations_h = (Button) findViewById(R.id.appropriations_h);
        final Button armed_services_h = (Button) findViewById(R.id.armed_services_h);
        final Button banking_housing = (Button) findViewById(R.id.banking_housing);
        final Button budget_h = (Button) findViewById(R.id.budget_h);
        final Button commerce = (Button) findViewById(R.id.commerce_science_transport);
        final Button education = (Button) findViewById(R.id.education_workforce);
        final Button energy_commerce = (Button) findViewById(R.id.energy_commerce);
        final Button energy_natural = (Button) findViewById(R.id.energy_natural);
        final Button environment = (Button) findViewById(R.id.environment);
        final Button ethics = (Button) findViewById(R.id.ethics);
        final Button financial_services = (Button) findViewById(R.id.financial_services);
        final Button foreign_affairs = (Button) findViewById(R.id.foreign_affairs);
        final Button health = (Button) findViewById(R.id.health);
        final Button homeland_h = (Button) findViewById(R.id.homeland_h);
        final Button house = (Button) findViewById(R.id.house);
        final Button judiciary_h = (Button) findViewById(R.id.judiciary_h);
        final Button natural = (Button) findViewById(R.id.natural_resources);
        final Button oversight = (Button) findViewById(R.id.oversight);
        final Button rules_h = (Button) findViewById(R.id.rules_h);
        final Button science = (Button) findViewById(R.id.science_space);
        final Button small_business_h = (Button) findViewById(R.id.small_business_h);
        final Button transportation = (Button) findViewById(R.id.transportation);
        final Button veterans_h = (Button) findViewById(R.id.veterans_h);
        final Button ways = (Button) findViewById(R.id.ways);
        Button congress = (Button) findViewById(R.id.congress_button);
        Button reset = (Button) findViewById(R.id.reset);

        agriculture_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Agriculture";
                collectBills(committees);
                committees = "Senate - Agriculture, Nutrition, and Forestry";
                collectBills(committees);
                agriculture_h.setBackgroundColor(Color.DKGRAY);
                agriculture_h.setTextColor(Color.WHITE);

            }
        });

        appropriations_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Appropriations";
                collectBills(committees);
                committees = "Senate - Appropriations";
                collectBills(committees);
                appropriations_h.setBackgroundColor(Color.DKGRAY);
                appropriations_h.setTextColor(Color.WHITE);
            }
        });

        armed_services_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Armed Services";
                collectBills(committees);
                committees = "Senate - Armed Services";
                collectBills(committees);
                armed_services_h.setBackgroundColor(Color.DKGRAY);
                armed_services_h.setTextColor(Color.WHITE);
            }
        });

        budget_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Budget";
                collectBills(committees);
                committees = "Senate - Budget";
                collectBills(committees);
                budget_h.setBackgroundColor(Color.DKGRAY);
                budget_h.setTextColor(Color.WHITE);
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Education and the Workforce";
                collectBills(committees);
                education.setBackgroundColor(Color.DKGRAY);
                education.setTextColor(Color.WHITE);
            }
        });

        energy_commerce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Energy and Commerce";
                collectBills(committees);
                energy_commerce.setBackgroundColor(Color.DKGRAY);
                energy_commerce.setTextColor(Color.WHITE);
            }
        });

        ethics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Ethics";
                collectBills(committees);
                ethics.setBackgroundColor(Color.DKGRAY);
                ethics.setTextColor(Color.WHITE);
            }
        });

        financial_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Financial Services";
                collectBills(committees);
                committees = "Senate - Finance";
                collectBills(committees);
                financial_services.setBackgroundColor(Color.DKGRAY);
                financial_services.setTextColor(Color.WHITE);
            }
        });

        foreign_affairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Foreign Affairs";
                collectBills(committees);
                committees = "Senate - Foreign Relations";
                collectBills(committees);
                foreign_affairs.setBackgroundColor(Color.DKGRAY);
                foreign_affairs.setTextColor(Color.WHITE);
            }
        });

        homeland_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Homeland Security";
                collectBills(committees);
                committees = "Senate - Homeland Security and Government Affairs";
                collectBills(committees);
                homeland_h.setBackgroundColor(Color.DKGRAY);
                homeland_h.setTextColor(Color.WHITE);
            }
        });

        house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - House Administration";
                collectBills(committees);
                house.setBackgroundColor(Color.DKGRAY);
                house.setTextColor(Color.WHITE);
            }
        });

        judiciary_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Judiciary";
                collectBills(committees);
                committees = "Senate - Judiciary";
                collectBills(committees);
                judiciary_h.setBackgroundColor(Color.DKGRAY);
                judiciary_h.setTextColor(Color.WHITE);
            }
        });

        natural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Natural Resources";
                collectBills(committees);
                natural.setBackgroundColor(Color.DKGRAY);
                natural.setTextColor(Color.WHITE);
            }
        });

        oversight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Oversight and Government Reform";
                collectBills(committees);
                oversight.setBackgroundColor(Color.DKGRAY);
                oversight.setTextColor(Color.WHITE);
            }
        });

        rules_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Rules";
                collectBills(committees);
                committees = "Senate - Rules and Administration";
                collectBills(committees);
                rules_h.setBackgroundColor(Color.DKGRAY);
                rules_h.setTextColor(Color.WHITE);
            }
        });

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Science, Space, and Technology";
                collectBills(committees);
                science.setBackgroundColor(Color.DKGRAY);
                science.setTextColor(Color.WHITE);
            }
        });

        small_business_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Small Business";
                collectBills(committees);
                committees = "Senate - Small Business and Entrepreneurship";
                collectBills(committees);
                small_business_h.setBackgroundColor(Color.DKGRAY);
                small_business_h.setTextColor(Color.WHITE);
            }
        });

        transportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Transportation and Infrastructure";
                collectBills(committees);
                transportation.setBackgroundColor(Color.DKGRAY);
                transportation.setTextColor(Color.WHITE);
            }
        });

        veterans_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Veterans' Affairs";
                collectBills(committees);
                committees = "Senate - Veterans' Affairs";
                collectBills(committees);
                veterans_h.setBackgroundColor(Color.DKGRAY);
                veterans_h.setTextColor(Color.WHITE);
            }
        });

        ways.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "House - Ways and Means";
                collectBills(committees);
                ways.setBackgroundColor(Color.DKGRAY);
                ways.setTextColor(Color.WHITE);
            }
        });

        banking_housing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Banking, Housing, and Urban Affairs";
                collectBills(committees);
                banking_housing.setBackgroundColor(Color.DKGRAY);
                banking_housing.setTextColor(Color.WHITE);
            }
        });

        commerce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Commerce, Science, and Transportation";
                collectBills(committees);
                commerce.setBackgroundColor(Color.DKGRAY);
                commerce.setTextColor(Color.WHITE);
            }
        });

        energy_natural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Energy and Natural Resources";
                collectBills(committees);
                energy_natural.setBackgroundColor(Color.DKGRAY);
                energy_natural.setTextColor(Color.WHITE);
            }
        });

        environment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Environment and Public Works";
                collectBills(committees);
                environment.setBackgroundColor(Color.DKGRAY);
                environment.setTextColor(Color.WHITE);
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String committees = "Senate - Health, Education, Labor, and Pensions";
                collectBills(committees);
                health.setBackgroundColor(Color.DKGRAY);
                health.setTextColor(Color.WHITE);
            }
        });

        congress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CongressActivity.class));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
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

            }

        };

        String committees = "House - Education and the Workforce";
        String feature = "House - Natural Resources";
        collectFeatureBills(feature);
        collectBills(committees);

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
    public void onFeatureSuccess() {
        //this is accessing the listview found in side res/layout/listview.xml
        listView = (ListView) findViewById(R.id.bill_list_view);
        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));
        //list_item_card.xml is found in res/layout/list_item_card.xml
        BillCardArrayAdapter cardArrayAdapter = new BillCardArrayAdapter(getApplicationContext(), R.layout.list_item_card);
        //traverse through the ListArray declared at the top (above onCreate) and filled inside onResponse
        for (Bill b : featureBills) {
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

    public void collectBills(String committees) {

        //connect to website and return data found at 104.198.148.208:8000/members
        ApiService.getInstance().getBillDetails(committees).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                //api success
                //when this returns 200 we connected to webpage2
                Log.d("MainActivity", "Status Code = " + response.code());
                //string of json collected from website
                //Log.d("MainActivity", "body test = " + response.body());
                //stuff that string into a JsonElement
                JsonElement jelement = new JsonParser().parse(response.body().toString());
                //get the JsonArray out of the element.
                jarray = jelement.getAsJsonArray();
                JsonElement element;
                //traverse through the array and stuff each member into a list of members
                for (int i = 0; i < jarray.size(); i++) {
                    //extract each object
                    JsonObject jobject = jarray.get(i).getAsJsonObject();
                    //extract information from object
                    element = jobject.get("billTitle");
                    curBill = element.getAsString();
                    //curBill = jobject.get("billTitle").toString();
                    element = jobject.get("sponser");
                    curSponser = element.getAsString();
                    element =  jobject.get("billHeading");
                    curHeader = element.getAsString();
                    element = jobject.get("committees");
                    curCommittee = element.getAsString();
                    element = jobject.get("latestAction");
                    curAction = element.getAsString();
                    //display information for dubug purposes
                    //Log.d("Inside for loop ", "billTitle = " + curBill);
                    //Log.d("Insdie for loop", "sponser=" + curSponser);
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

    public void collectFeatureBills(String committees) {

        //connect to website and return data found at 104.198.148.208:8000/members
        ApiService.getInstance().getBillDetails(committees).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                //api success
                //when this returns 200 we connected to webpage2
                Log.d("MainActivity", "Status Code = " + response.code());
                //string of json collected from website
                //Log.d("MainActivity", "body test = " + response.body());
                //stuff that string into a JsonElement
                JsonElement jelement = new JsonParser().parse(response.body().toString());
                //get the JsonArray out of the element.
                jarray = jelement.getAsJsonArray();
                JsonElement element;
                //traverse through the array and stuff each member into a list of members
                for (int i = 0; i < jarray.size(); i++) {
                    //extract each object
                    JsonObject jobject = jarray.get(i).getAsJsonObject();
                    //extract information from object
                    element = jobject.get("billTitle");
                    curBill = element.getAsString();
                    //curBill = jobject.get("billTitle").toString();
                    element = jobject.get("sponser");
                    curSponser = element.getAsString();
                    element =  jobject.get("billHeading");
                    curHeader = element.getAsString();
                    element = jobject.get("committees");
                    curCommittee = element.getAsString();
                    element = jobject.get("latestAction");
                    curAction = element.getAsString();
                    //display information for dubug purposes
                    //Log.d("Inside for loop ", "billTitle = " + curBill);
                    //Log.d("Insdie for loop", "sponser=" + curSponser);
                    //insert into list
                    featureBills.add(new Bill(curBill, curSponser, curHeader, curCommittee, curAction));
                }
                //call the function on success.
                onFeatureSuccess();

            }

            @Override  //couldn't connect to website.
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("Apicall", t.getMessage());
                t.printStackTrace();
            }
        });

    }

}
