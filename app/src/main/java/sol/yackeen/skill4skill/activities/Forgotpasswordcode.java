package sol.yackeen.skill4skill.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.backend.loginapi.RequestBodyPasswordCode;

public class Forgotpasswordcode extends AppCompatActivity {
    Button okbtn;
    EditText codetext;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpasswordcode);
        requestQueue= Volley.newRequestQueue(this);
        okbtn = (Button) findViewById(R.id.okcodeid);
        codetext = (EditText) findViewById(R.id.codemailid);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCodeToBackend(codetext.getText().toString());
            }
        });

    }

    private void sendCodeToBackend(String Code){

        RequestBodyPasswordCode requestBodyPasswordCode=new RequestBodyPasswordCode();
        requestBodyPasswordCode.UserId=1;
        requestBodyPasswordCode.Code=Code;

        Gson gson = new Gson();
        String json = gson.toJson(requestBodyPasswordCode);

        String URL="http://yakensolution.cloudapp.net/one2one/forget-password/code";
        try {

            JSONObject object = new JSONObject(json);

            JsonObjectRequest jr = new JsonObjectRequest(Request.Method.POST, URL, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(Forgotpasswordcode.this,response+"",Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //  Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    // Log.d("TAG",error.toString());


                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(Forgotpasswordcode.this,"error_network_timeout",Toast.LENGTH_LONG).show();


                    } else if (error instanceof AuthFailureError) {
                        Toast.makeText(Forgotpasswordcode.this,"AuthFailureError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ServerError) {
                        Toast.makeText(Forgotpasswordcode.this,"ServerError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(Forgotpasswordcode.this,"NetworkError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ParseError) {
                        Toast.makeText(Forgotpasswordcode.this,"ParseError",Toast.LENGTH_LONG).show();

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

