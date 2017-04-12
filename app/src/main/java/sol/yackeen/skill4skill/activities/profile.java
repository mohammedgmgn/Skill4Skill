package sol.yackeen.skill4skill.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.adapters.AvailableTimeAdapter;
import sol.yackeen.skill4skill.adapters.CurrentlyLearningAdapter;
import sol.yackeen.skill4skill.backend.availabletimeapi.AvailableTime;
import sol.yackeen.skill4skill.extra_classes.SpacesItemDecoration;
import sol.yackeen.skill4skill.models.UserInfo;
import sol.yackeen.skill4skill.profileapi.ProfileBodyRequest;

public class profile extends AppCompatActivity {
    List<AvailableTime> listoFtime=new ArrayList<>();
    ImageButton mFeedbackbtn;
    RequestQueue requestQueue;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarprofile);
        // toolbar.setTitle("Module 5");
        //toolbar.inflateMenu(R.menu.menu_main);
        requestQueue= Volley.newRequestQueue(this);
        if(getIntent().getParcelableExtra("userinfoobj")!=null)
        {
            userInfo=getIntent().getParcelableExtra("userinfoobj");
            sendDatatoBackend(userInfo);

        }

        toolbar.setNavigationIcon(ContextCompat.getDrawable(profile.this, R.drawable.back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                profile.this.finish();
            }
        });
        setSupportActionBar(toolbar);
        ImageButton chat=(ImageButton)findViewById(R.id.messageprofile);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile.this,send_message.class));
            }
        });

        mFeedbackbtn=(ImageButton)findViewById(R.id.feedbackbtnprofile);
        mFeedbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile.this,feedback.class));
            }
        });
        FloatingActionButton learnbtn=(FloatingActionButton)findViewById(R.id.learnbprofile);

        learnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile.this,learn.class));
            }
        });

        CardView knowsaboutcard=(CardView)findViewById(R.id.knowsaboutcardprofile);
        CardView wantstonowcard=(CardView)findViewById(R.id.wantstoknowcardprofile);
        CardView historycard=(CardView)findViewById(R.id.historycardprofile);
        knowsaboutcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile.this,knowesabout.class));
            }
        });
        historycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile.this,history.class));
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewcurrentlearningprofile);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayout.HORIZONTAL));
        List<String>current=new ArrayList<>();
        current.add("c++");
        current.add("java");
        current.add("oop");
        current.add("math");
        CurrentlyLearningAdapter adapter = new CurrentlyLearningAdapter(current);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView1=(RecyclerView)findViewById(R.id.recyclerViewavailabletimeprofile);
        recyclerView1.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayout.HORIZONTAL));

        for(int i=0;i<5;i++)
        {
            AvailableTime obj=new AvailableTime();
            obj.setDaystring("Sun");
            obj.setFromstring("11:00");
            obj.setTostring("12:00");
            listoFtime.add(obj);
        }


        AvailableTimeAdapter AVadapter = new AvailableTimeAdapter(false, listoFtime, this, new AvailableTimeAdapter.RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {

            }
        });
        recyclerView1.setAdapter(AVadapter);


        int spacingInPixels1 = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView1.addItemDecoration(new SpacesItemDecoration(spacingInPixels1));
        recyclerView1.setPadding(5,15,15,15);

        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels1));
        recyclerView.setPadding(5,15,15,15);





        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarprofile);

        collapsingToolbar.setTitle("Lona");

    }
    private void sendDatatoBackend(UserInfo client) {
        //send data to backend
        String url="http://yakensolution.cloudapp.net/one2one/profile";
        Gson gson = new Gson();

        ProfileBodyRequest profileBodyRequest=new ProfileBodyRequest();
        profileBodyRequest.FromUserId=client.getUserid();
        final String json = gson.toJson(profileBodyRequest);
        try {
            JSONObject obj = new JSONObject(json);
//            obj.put("User",client);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("TAG", response.toString());
                    Toast.makeText(profile.this,response.toString(),Toast.LENGTH_LONG).show();
                    try {
                        JSONObject jsonObj = new JSONObject(response.toString());
                        JSONObject user=jsonObj.getJSONObject("User");
                        userInfo.setUserid(user.getInt("UserId"));
                        userInfo.setName(user.getString("Name"));
                        userInfo.setEmail(user.getString("Email"));
                        userInfo.setFollowing(user.getBoolean("Following"));
                        userInfo.setHistory(user.getInt("History"));
                        userInfo.setWantToKnow(user.getInt("WantToKnow"));
                        userInfo.setKnowesAbout(user.getInt("KnowesAbout"));



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }




                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    // Log.d("error_message","hi");
                    Toast.makeText(profile.this,error.toString(),Toast.LENGTH_SHORT).show();
                    // Log.d("TAG",error.toString());


                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(profile.this,"error_network_timeout",Toast.LENGTH_LONG).show();


                    } else if (error instanceof AuthFailureError) {
                        Toast.makeText(profile.this,"AuthFailureError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ServerError) {
                        Toast.makeText(profile.this,"ServerError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(profile.this,"NetworkError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ParseError) {
                        Toast.makeText(profile.this,"ParseError",Toast.LENGTH_LONG).show();

                        //TODO
                    }


                }
            }




            );
            int socketTimeout = 30000;//30 seconds - change to what you want
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            request.setRetryPolicy(policy);


            requestQueue.add(request);




        }
        catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
