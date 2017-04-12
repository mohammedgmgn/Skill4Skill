package sol.yackeen.skill4skill.navigation_items;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.adapters.HomeAdapter;
import sol.yackeen.skill4skill.backend.homeapi.RequestBodyHome;
import sol.yackeen.skill4skill.extra_classes.CustomDecorationPosts;
import sol.yackeen.skill4skill.models.BasePost;
import sol.yackeen.skill4skill.models.Post;
import sol.yackeen.skill4skill.models.Recommendation;
import sol.yackeen.skill4skill.models.RecommendationWrapper;
import sol.yackeen.skill4skill.models.UserInfo;

public class Home extends Fragment {

    HomeAdapter adapter;
    List<BasePost> postsList = new ArrayList<>();
    Post post1=new Post();
    RequestQueue requestQueue;
    List<BasePost> postslistfrombackend = new ArrayList<>();
    RecyclerView recyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        requestQueue= Volley.newRequestQueue(getContext());
        View root= inflater.inflate(R.layout.fragment_home, container, false);
        try {
            if(getArguments().getParcelable("userinfoobj")!=null)
            {
                UserInfo userInfo=getArguments().getParcelable("userinfoobj");
                sendDatatoBackend(userInfo.getUserid());

            }

        }catch (Exception e)
        {}

   //     sendDatatoBackend(1);
       // post1.setName("Mohammed Mahmoud");
        //post1.setTime("10 min ago");
        //post1.setPost_content("Life is the most difficult exam many people fail becouse they trying too copy");
        for(int i=0;i<postslistfrombackend.size();i++)
        {
            if(i==4)
            {
                RecommendationWrapper recommendationWrapper=new RecommendationWrapper();
                recommendationWrapper.recommendations.add(new Recommendation());
                recommendationWrapper.recommendations.add(new Recommendation());
                recommendationWrapper.recommendations.add(new Recommendation());
                recommendationWrapper.recommendations.add(new Recommendation());
                recommendationWrapper.recommendations.add(new Recommendation());
                recommendationWrapper.recommendations.add(new Recommendation());
                recommendationWrapper.recommendations.add(new Recommendation());
                recommendationWrapper.recommendations.add(new Recommendation());

                postslistfrombackend.add(recommendationWrapper);

            }
            else
                postslistfrombackend.add(post1);

        }
         recyclerView = (RecyclerView)root.findViewById(R.id.recyclerViewposts);
         recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false));
         adapter = new HomeAdapter(postslistfrombackend,getContext());
         recyclerView.setAdapter(adapter);


        //recyclerView.scrollToPosition(0);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacingpost);
        recyclerView.addItemDecoration(new CustomDecorationPosts(spacingInPixels));
        recyclerView.setPadding(0,15,0,15);


/*
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
        */


        return root;

    }
    private void sendDatatoBackend(int userID) {

        String url = "http://yakensolution.cloudapp.net/one2one/home";

        RequestBodyHome requestBodyHome=new RequestBodyHome();
        requestBodyHome.UserId=userID;
        Gson gson = new Gson();

        String json = gson.toJson(requestBodyHome);
        try {
            JSONObject object = new JSONObject(json);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Log.d("TAG", jsonObject.toString());
                    try {
                        JSONArray array = jsonObject.getJSONArray("Posts");

                        for(int i=0;i<array.length();i++)
                        {
                            JSONObject object = array.getJSONObject(i);
                            int postid = object.getInt("PostId");
                            int userid = object.getInt("UserId");
                            String postdesc=object.getString("PostDescription");
                            int Bids=object.getInt("Bids");
                            Post post=new Post();
                            post.setPostId(postid);
                            post.setBids(Bids);
                            post.setPostDescription(postdesc);
                            post.setUserId(userid);
                            if(i!=4)
                            {
                                postslistfrombackend.add(post);

                            }

                        }



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
                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    // Log.d("TAG",error.toString());


                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(getContext(), "error_network_timeout", Toast.LENGTH_LONG).show();


                    } else if (error instanceof AuthFailureError) {
                        Toast.makeText(getContext(), "AuthFailureError", Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ServerError) {
                        Toast.makeText(getContext(), "ServerError", Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(getContext(), "NetworkError", Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ParseError) {
                        Toast.makeText(getContext(), "ParseError", Toast.LENGTH_LONG).show();

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
    private void getDatafromBackend(){





    }
}
