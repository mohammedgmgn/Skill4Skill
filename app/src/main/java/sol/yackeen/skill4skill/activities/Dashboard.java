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
import android.widget.TextView;
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
import sol.yackeen.skill4skill.backend.dashboardapi.DashBoardRequestBody;
import sol.yackeen.skill4skill.extra_classes.SpacesItemDecoration;
import sol.yackeen.skill4skill.models.UserInfo;

public class Dashboard extends AppCompatActivity {
    List<AvailableTime>listoFtime=new ArrayList<>();
ImageButton mFeedbackbtn;
    RequestQueue requestQueue;
    UserInfo userInfo;
String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbartest);
       // toolbar.setTitle("Module 5");
        //toolbar.inflateMenu(R.menu.menu_main);
        requestQueue= Volley.newRequestQueue(this);
 if(getIntent().getParcelableExtra("userinfoobj")!=null)
 {
      userInfo=getIntent().getParcelableExtra("userinfoobj");
      sendDatatoBackend(userInfo);
     name=userInfo.getName();


 }
        toolbar.setNavigationIcon(ContextCompat.getDrawable(Dashboard.this, R.drawable.back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dashboard.this.finish();
            }
        });
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        mFeedbackbtn=(ImageButton)findViewById(R.id.feedbackbtn);
        mFeedbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,feedback.class));
            }
        });
        TextView follow=(TextView)findViewById(R.id.followingidtv);
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,followers.class));

            }
        });
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbartest);

        collapsingToolbar.setTitle(name);

        FloatingActionButton learnbtn=(FloatingActionButton)findViewById(R.id.learnb);

        learnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,learn.class));
            }
        });
      //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabtest);
        CardView knowsaboutcard=(CardView)findViewById(R.id.card_viewav1);
        CardView wantstonowcard=(CardView)findViewById(R.id.card_viewav2);
        CardView historycard=(CardView)findViewById(R.id.card_viewav3);
        knowsaboutcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,knowesabout.class));
            }
        });
        historycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,history.class));
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewcurrentlearning);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayout.HORIZONTAL));
        List<String>current=new ArrayList<>();
        current.add("c++");
        current.add("java");
        current.add("oop");
        current.add("math");
        CurrentlyLearningAdapter adapter = new CurrentlyLearningAdapter(current);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView1=(RecyclerView)findViewById(R.id.recyclerViewavailabletime);
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




    }

    private void sendDatatoBackend(UserInfo client) {
        //send data to backend
        String url="http://yakensolution.cloudapp.net/one2one/profile";
        Gson gson = new Gson();

        DashBoardRequestBody dashBoardRequestBody=new DashBoardRequestBody();
        dashBoardRequestBody.FromUserId=client.getUserid();
        final String json = gson.toJson(dashBoardRequestBody);
        try {
            JSONObject obj = new JSONObject(json);
//            obj.put("User",client);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("TAG", response.toString());
                    Toast.makeText(Dashboard.this,response.toString(),Toast.LENGTH_LONG).show();
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
                    Toast.makeText(Dashboard.this,error.toString(),Toast.LENGTH_SHORT).show();
                    // Log.d("TAG",error.toString());


                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(Dashboard.this,"error_network_timeout",Toast.LENGTH_LONG).show();


                    } else if (error instanceof AuthFailureError) {
                        Toast.makeText(Dashboard.this,"AuthFailureError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ServerError) {
                        Toast.makeText(Dashboard.this,"ServerError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(Dashboard.this,"NetworkError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ParseError) {
                        Toast.makeText(Dashboard.this,"ParseError",Toast.LENGTH_LONG).show();

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



/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.getItem(0);
        item.setTitle(220+" Followers");
        MenuItem item2 = menu.getItem(1);
        item2.setTitle(150+" Following");
        MenuItem item3 = menu.getItem(2);
        item3.setTitle("Wall");


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemid=item.getItemId();

   if(itemid==R.id.followers)
   {
     startActivity(new Intent(Dashboard.this,followers.class));
   }
        else if(itemid==R.id.following)
   {
   }
        else if(itemid==R.id.wall)

   {
   }
        return true;
    }
    */
}
