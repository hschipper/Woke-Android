package woke.woke;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hana on 5/6/2017.
 */



public class UserSettings extends AppCompatActivity {
    private EditText input_state, input_person;
    public String state, person;

    public static class User {
        public String state;
        public String congress_person;

        public User(String state, String person) {
            this.state = state;
            congress_person = person;
        }
        public String getState() {
            return this.state;
        }
        public String getCongress_person() {
            return this.congress_person;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_settings);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final TextView stateView = (TextView) findViewById(R.id.State);
        final TextView personView = (TextView) findViewById(R.id.Congress_person);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Map<String, Object> but = (HashMap<String, Object>) dataSnapshot.getValue();
                    List<Object> u = new ArrayList<Object>(but.values());
                    JsonElement el = new JsonParser().parse(u.toString());
                    JsonArray jarray = el.getAsJsonArray();
                    Log.d("testing", u.toString());
                    for (int i = 0; i < jarray.size(); i++) {
                        //extract each object
                        JsonObject jobject = jarray.get(i).getAsJsonObject();
                        JsonElement  element = (JsonElement) jobject.get(user.getUid());
                        if(element != null) {
                            JsonObject obj = element.getAsJsonObject();
                            if (obj != null) {
                                Log.d("level", obj.get(user.getUid()).toString());
                                JsonElement ele = obj.get(user.getUid());
                                JsonObject jobj = ele.getAsJsonObject();
                                Log.d("jobj", jobj.get("state").toString());
                                JsonElement state = (JsonElement) jobj.get("state");
                                stateView.setText("The state I follow is : " + state.getAsString());
                                if (jobj.get("congress_person").toString() != null) {
                                    personView.setText("The congress person I follow is : " + jobj.get("congress_person").toString());
                                    Log.d("object", jobject.get(user.getUid()).toString());
                                }
                            }
                        }
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        Button btn_settings = (Button) findViewById(R.id.settings_btn);
        input_state = (EditText) findViewById(R.id.change_state);
        input_person = (EditText) findViewById(R.id.change_congress_person);

        input_state.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false) {
                    state = input_state.getText().toString();
                    Log.d("settings" ,state);
                }
            }
        });

        input_person.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false) {
                    person = input_person.getText().toString();
                    Log.d("settings" ,person);
                }
            }
        });

        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
 //               input_state = (EditText) v.findViewById(R.id.change_state);
 //               input_person = (EditText) v.findViewById(R.id.change_congress_person);

//                String state = input_state.getText().toString();
//                String person = input_person.getText().toString();
                Log.d("settings" ,state);
                Log.d("settings" ,person);
                DatabaseReference ref = database.getReference("user");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference usersRef = ref.child("users").child(user.getUid());


                Map<String, User> users = new HashMap<String, User>();

         //       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // The user's ID, unique to the Firebase project. Do NOT use this value to
                    // authenticate with your backend server, if you have one. Use
                    // FirebaseUser.getToken() instead.
                    Log.d("settings","insert into db");
                    users.put( user.getUid() , new User(state, person));
                    usersRef.setValue(users);
                } else {
                    // No user is signed in.
                }

            }
        });
    }


}
