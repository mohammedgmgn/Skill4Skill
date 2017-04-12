package sol.yackeen.skill4skill.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
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
import sol.yackeen.skill4skill.adapters.Myadabter;
import sol.yackeen.skill4skill.backend.welcometwoapi.RequestBodyWelcomeTwo;
import sol.yackeen.skill4skill.extra_classes.SpacesItemDecoration;
import sol.yackeen.skill4skill.models.UserInfo;

public class Welcome2 extends AppCompatActivity {


    private EditText mEditText;
    private Button mButton;
    List<String> skills = new ArrayList<>();
    Myadabter adapter;
    private Toolbar toolbar;
    RequestQueue requestQueue;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);
  if(getIntent().getParcelableExtra("userinfoobj")!=null)
  {
       userInfo=getIntent().getParcelableExtra("userinfoobj");
      Toast.makeText(Welcome2.this,userInfo.getUserid()+"",Toast.LENGTH_LONG).show();

  }


        requestQueue = Volley.newRequestQueue(Welcome2.this);
        toolbar = (Toolbar) findViewById(R.id.mywelcomebar_two);

        setSupportActionBar(toolbar);
        setTitle("Welcome");
        mEditText = (EditText) findViewById(R.id.addskillset);
        mButton = (Button) findViewById(R.id.addbtntwo);
        mButton.setOnClickListener(onClick());
        //TextView textView = new TextView(this);

       // textView.setText("New text");

      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDisplayShowHomeEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL));
        adapter = new Myadabter(true, skills, this, new Myadabter.RecyclerViewClickListener() {
            AlertDialog.Builder builder = new AlertDialog.Builder(Welcome2.this);

            @Override
            public void recyclerViewListClicked(View v, final int position) {
                builder.setTitle("delete item");
                builder.setMessage("are you want to delete this time");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        skills.remove(position);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeChanged(position, skills.size());

                    }
                });
                builder.setNegativeButton("cancel", null);
                AlertDialog dialog= builder.create();
                dialog.show();

            }
        });
        recyclerView.setAdapter(adapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        recyclerView.setPadding(15,15,15,15);

        ImageView next = (ImageView) findViewById(R.id.nexttoavailable);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SendTopicsToBackEnd();

                Intent intent=new Intent(Welcome2.this,Welcome3.class);
                intent.putExtra("userinfoobj",userInfo);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);

            }
        });




    }
    private View.OnClickListener onClick() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
             if(mEditText.getText().toString().isEmpty())
             {
                 Toast.makeText(Welcome2.this,"please enter what you want to learn",Toast.LENGTH_SHORT).show();
             }
                else
             {
                 skills.add(mEditText.getText().toString());
                 adapter.notifyDataSetChanged();
                 mEditText.setText("");

             }

            }
        };

    }
    private void SendTopicsToBackEnd()
    {
        List<Integer>arr=new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);

        // int arr[]={4,6,7,8};

        RequestBodyWelcomeTwo requestBodyWelcomeTwo=new RequestBodyWelcomeTwo();
        requestBodyWelcomeTwo.IsKnown=false;
        requestBodyWelcomeTwo.UserId=1;
       // requestBodyWelcomeTwo.TopicId=arr;
        Gson gson = new Gson();
        String json = gson.toJson(requestBodyWelcomeTwo);

        String URL="http://yakensolution.cloudapp.net/one2one/edit/topics";
        try {

            JSONObject object = new JSONObject(json);

              // String s=object.toString();
            JsonObjectRequest jr = new JsonObjectRequest(Request.Method.POST, URL, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(Welcome2.this,response+"from welcome2 Topics",Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //  Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    // Log.d("TAG",error.toString());


                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(Welcome2.this,"error_network_timeout",Toast.LENGTH_LONG).show();


                    } else if (error instanceof AuthFailureError) {
                        Toast.makeText(Welcome2.this,"AuthFailureError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ServerError) {
                        Toast.makeText(Welcome2.this,"ServerError from welcome2 Topics",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(Welcome2.this,"NetworkError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ParseError) {
                        Toast.makeText(Welcome2.this,"ParseError",Toast.LENGTH_LONG).show();

                        //TODO
                    }

                }
            });
            requestQueue.add(jr);
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }


}
