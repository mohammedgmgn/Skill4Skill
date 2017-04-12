package sol.yackeen.skill4skill.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.adapters.PageAdapter;
import sol.yackeen.skill4skill.backend.historyapi.RequestBodyHistory;

public class history extends AppCompatActivity {
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        requestQueue= Volley.newRequestQueue(history.this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("learned"));

        tabLayout.addTab(tabLayout.newTab().setText("tought"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        GetHistoryHormBackEnd();
    }
private void GetHistoryHormBackEnd()
{

    String url = "http://yakensolution.cloudapp.net/one2one/history";

    RequestBodyHistory requestBodyHistory=new RequestBodyHistory();
    requestBodyHistory.UserId=1;
    Gson gson = new Gson();

    String json = gson.toJson(requestBodyHistory);
    try {
        JSONObject object = new JSONObject(json);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d("TAG", jsonObject.toString());
                Toast.makeText(history.this, jsonObject+"from History ", Toast.LENGTH_LONG).show();



            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Log.d("error_message","hi");
                Toast.makeText(history.this, error.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("TAG",error.toString());


                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(history.this, "error_network_timeout", Toast.LENGTH_LONG).show();


                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(history.this, "AuthFailureError", Toast.LENGTH_LONG).show();

                    //TODO
                } else if (error instanceof ServerError) {
                    Toast.makeText(history.this, "ServerError", Toast.LENGTH_LONG).show();

                    //TODO
                } else if (error instanceof NetworkError) {
                    Toast.makeText(history.this, "NetworkError", Toast.LENGTH_LONG).show();

                    //TODO
                } else if (error instanceof ParseError) {
                    Toast.makeText(history.this, "ParseError", Toast.LENGTH_LONG).show();

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
