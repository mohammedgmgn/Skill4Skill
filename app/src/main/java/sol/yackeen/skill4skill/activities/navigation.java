package sol.yackeen.skill4skill.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.backend.postapi.RequestBodyPost;
import sol.yackeen.skill4skill.backend.searchapi.SearchBodyRequest;
import sol.yackeen.skill4skill.extra_classes.SaveSharedPreference;
import sol.yackeen.skill4skill.models.Post;
import sol.yackeen.skill4skill.models.UserInfo;
import sol.yackeen.skill4skill.navigation_items.Home;
import sol.yackeen.skill4skill.navigation_items.Messages;
import sol.yackeen.skill4skill.navigation_items.Notifications;

public class navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    MaterialSearchView searchView;
    Fragment fragment = null;
    private String title="";
    Toolbar toolbar;
    FloatingActionButton fab;
    RequestQueue requestQueue;
    boolean checkfirsttime=false;
    UserInfo userInfo;
    Bundle arguments;
    List<UserInfo>userInfos=new ArrayList<UserInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        requestQueue= Volley.newRequestQueue(navigation.this);

        if(getIntent().getParcelableExtra("userinfoobj")!=null)
        {
            userInfo=getIntent().getParcelableExtra("userinfoobj");
            arguments = new Bundle();
            arguments.putParcelable("userinfoobj", getIntent().getParcelableExtra("userinfoobj"));

            Toast.makeText(navigation.this,userInfo.getUserid()+"",Toast.LENGTH_LONG).show();

        }
        searchlist("aymn");


        /*
        if(intent!=null)
        {
            String strdata = intent.getExtras().getString("activityid");
           if(strdata.equals("From_Activity_availabletime"))
           {
               /*
                 userInfo=getIntent().getParcelableExtra("userinfoobj");
                arguments = new Bundle();
               arguments.putParcelable("userinfoobj", getIntent().getParcelableExtra("userinfoobj"));

               Toast.makeText(navigation.this,userInfo.getUserid()+"",Toast.LENGTH_LONG).show();

           }
        }*/



        toolbar = (Toolbar) findViewById(R.id.toolbar);
       // toolbar.setNavigationIcon(R.drawable.ic_menu_camera);
        Drawable d= ResourcesCompat.getDrawable(getResources(),R.drawable.options,null);
        toolbar.setOverflowIcon(d);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);


         fab = (FloatingActionButton) findViewById(R.id.fab);
        checkfirsttime=true;


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(navigation.this, WritePost.class));
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Post post=new Post();
                post.setUserId(1);
                post.setPostDescription("first post on my app");


                Sendposttobackend(post);*/

            }
        });
        getSupportActionBar().setTitle("Home");
        fragment = new Home();
        if(arguments!=null)
        {
            fragment.setArguments(arguments);

        }

        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        searchView = (MaterialSearchView)findViewById(R.id.search_view);


        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                searchView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onSearchViewClosed() {

                //If closed Search View , lstView will return default
                //lstView = (ListView)findViewById(R.id.lstView);
                //ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,lstSource);
                //lstView.setAdapter(adapter);

            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(newText != null && !newText.isEmpty()){

                //    List<String> lstFound = new ArrayList<String>();
                    //List<UserInfo>lstFound=new ArrayList<UserInfo>();

//                    Toast.makeText(navigation.this,newText,Toast.LENGTH_LONG).show();

                  /*
                    for(String item:lstSource){
                        if(item.contains(newText))
                            lstFound.add(item);
                    }*/

                 //   ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,lstFound);
                   // lstView.setAdapter(adapter);
                }
                else{
                    //if search text is null
                    //return default
                 //   ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,lstSource);
                   // lstView.setAdapter(adapter);
                }
                return true;
            }

        });





        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.setDrawerIndicatorEnabled(false);

        // mDrawerToggle.setHomeAsUpIndicator(R.drawable.menu_icon);
        toolbar.setNavigationIcon(R.drawable.menu);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });





        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
 private void Sendposttobackend(Post post){

     String url = "http://yakensolution.cloudapp.net/one2one/post/add";
     Gson gson = new Gson();
     RequestBodyPost postBody=new RequestBodyPost();
     postBody.Post=post;
     String json = gson.toJson(postBody);

try {

    JSONObject obj = new JSONObject(json);

    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,obj, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject jsonObject) {

          //  Toast.makeText(navigation.this, jsonObject.toString(), Toast.LENGTH_LONG).show();
        }

    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            // Log.d("error_message","hi");
            Toast.makeText(navigation.this, error.toString(), Toast.LENGTH_SHORT).show();
            // Log.d("TAG",error.toString());


            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                Toast.makeText(navigation.this, "error_network_timeout", Toast.LENGTH_LONG).show();


            } else if (error instanceof AuthFailureError) {
                Toast.makeText(navigation.this, "AuthFailureError", Toast.LENGTH_LONG).show();

                //TODO
            } else if (error instanceof ServerError) {
                Toast.makeText(navigation.this, "ServerError", Toast.LENGTH_LONG).show();

                //TODO
            } else if (error instanceof NetworkError) {
                Toast.makeText(navigation.this, "NetworkError", Toast.LENGTH_LONG).show();

                //TODO
            } else if (error instanceof ParseError) {
                Toast.makeText(navigation.this, "ParseError", Toast.LENGTH_LONG).show();

                //TODO
            }


        }
    }


    ) {

        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<String, String>();
            // params.put("UserId",String.valueOf(1));
            // Toast.makeText(getContext(), userID+"", Toast.LENGTH_LONG).show();

            return params;

        }

    };
    int socketTimeout = 30000;//30 seconds - change to what you want
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    request.setRetryPolicy(policy);

    requestQueue.add(request);
}catch (Exception e)
{

}


 }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_item,menu);
    MenuItem item = menu.findItem(R.id.action_search);
    searchView.setMenuItem(item);
    return true;
}

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home_id) {
            // Handle the camera action
            title="Home";
           fragment = new Home();
            if(arguments!=null)
            {
                fragment.setArguments(arguments);

            }


        } else if (id == R.id.Dashboard_id) {
          //  title="Dashboard";
            Intent intent=new Intent(navigation.this,Dashboard.class);
           // fragment = new Dashboard();
            if(userInfo!=null)
            {
                intent.putExtra("userinfoobj",userInfo);

            }
            startActivity(intent);
           // startActivity(new Intent(navigation.this,Dashboard.class));


        } else if (id == R.id.Notifications_id) {
            title="Notifications";
            fragment = new Notifications();
            if(checkfirsttime)
            {
                ((ViewGroup) fab.getParent()).removeView(fab);
                checkfirsttime=false;
            }



        } else if (id == R.id.Messages_id) {
            title="Messages";

            fragment = new Messages();



        }
        else if (id == R.id.Logout_id) {
            clearUserName(this);
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
          finish();
        }
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        getSupportActionBar().setTitle(title);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public  void clearUserName(Context ctx)
    {
        SharedPreferences.Editor editor = SaveSharedPreference.getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
        Toast.makeText(this,"member deleted", Toast.LENGTH_LONG).show();
    }


    private void searchlist(String mystring)
    {
        final List<UserInfo>users=new ArrayList<>();

        String url = "http://yakensolution.cloudapp.net/one2one/search";

        SearchBodyRequest searchBodyRequest=new SearchBodyRequest();
        searchBodyRequest.SubString=mystring;
        Gson gson = new Gson();

        String json = gson.toJson(searchBodyRequest);
        try {
            JSONObject object = new JSONObject(json);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Log.d("TAG", jsonObject.toString());
                    //  Toast.makeText(getContext(), jsonObject+"from home ", Toast.LENGTH_LONG).show();
                    try {
                        JSONArray array = jsonObject.getJSONArray("Users");
                        for(int i=0;i<array.length();i++)
                        {
                            JSONObject object = array.getJSONObject(i);
                            int userid = object.getInt("UserId");
                            String name=object.getString("Name");
                            String email=object.getString("Email");
                            String about=object.getString("About");
                            String Avatar=object.getString("Avatar");
                            int Rate=object.getInt("Rate");
                           UserInfo user=new UserInfo();
                            user.setName(name);
                            user.setUserid(userid);
                            user.setEmail(email);
                            user.setAbout(about);
                            user.setAvatar(Avatar);
                            user.setRate(Rate);
                            users.add(user);
                        }
                          userInfos=users;
                        Toast.makeText(navigation.this,users.get(0).getEmail(),Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    //  Toast.makeText(getContext(), jsonObject+"from home ", Toast.LENGTH_LONG).show();
                    // JSONArray array = null;
                /*
                array = jsonObject.getJSONArray("Posts");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                     int postid=object.getInt("PostId");
                     int usreid=object.getInt("UserId");
                    String postdescription=object.getString("PostDescription");
                    int bids=object.getInt("Bids");
                    int timestamb=object.getInt("TimeStamp");
                    Post post=new Post();
                    post.setPostId(postid);
                    post.setUserId(usreid);
                    post.setPostDescription(postdescription);
                    post.setBids(bids);
                    post.setTimeStamp(timestamb);
                    postslist.add(post);
                }

                homeclassapi obj=new homeclassapi();
                   obj.setPosts(postslist);

*/


                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    // Log.d("error_message","hi");
                    Toast.makeText(navigation.this, error.toString(), Toast.LENGTH_SHORT).show();
                    // Log.d("TAG",error.toString());


                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(navigation.this, "error_network_timeout", Toast.LENGTH_LONG).show();


                    } else if (error instanceof AuthFailureError) {
                        Toast.makeText(navigation.this, "AuthFailureError", Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ServerError) {
                        Toast.makeText(navigation.this, "ServerError", Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(navigation.this, "NetworkError", Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ParseError) {
                        Toast.makeText(navigation.this, "ParseError", Toast.LENGTH_LONG).show();

                        //TODO
                    }


                }
            }


            );

            int socketTimeout = 30000;//30 seconds - change to what you want
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            request.setRetryPolicy(policy);
            requestQueue.add(request);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
