package sol.yackeen.skill4skill.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.backend.loginapi.RequestBodyForgotPass;

public class Forgotpassword extends AppCompatActivity {
    private Toolbar toolbar;
    private TextInputLayout inputLayoutmail;
    EditText mail;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        toolbar= (Toolbar) findViewById(R.id.mybar);
        setSupportActionBar(toolbar);
        setTitle("Forget Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        requestQueue= Volley.newRequestQueue(this);

        inputLayoutmail=(TextInputLayout)findViewById(R.id.TextInputLayoutmailforget);
         mail=(EditText)findViewById(R.id.editTextmailforget);
        ImageButton ok=(ImageButton)findViewById(R.id.doneforgot);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmailToBackend(mail.getText().toString());

                startActivity(new Intent(Forgotpassword.this,Forgotpasswordcode.class));
                //here send the email to the backend
               /* if(validateemail())
                {
                    Toast.makeText(Forgotpassword.this,"done",Toast.LENGTH_LONG).show();

                }*/

            }
        });
        setSupportActionBar(toolbar);
    }

    private boolean validateemail(){
 /*
 * one condition from database if email already exist
 *
 * */



        if(mail.getText().toString().isEmpty())
        {
            inputLayoutmail.setError("name cannot be blank");

            return false;

        }
        else if(!isEmailValid(mail.getText().toString()))    {
            inputLayoutmail.setError("plese enter valid email");
            return false;
        }
        else
            inputLayoutmail.setErrorEnabled(false);
        return true;
        //!isEmailValid(email.getText().toString())
    }
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    private void sendEmailToBackend(String EmailAddress){

        RequestBodyForgotPass requestBodyForgotPass=new RequestBodyForgotPass();
        requestBodyForgotPass.Email=EmailAddress;
        Gson gson = new Gson();
        String json = gson.toJson(requestBodyForgotPass);

        String URL="http://yakensolution.cloudapp.net/one2one/forget-password";
        try {

            JSONObject object = new JSONObject(json);

            JsonObjectRequest jr = new JsonObjectRequest(Request.Method.POST, URL, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(Forgotpassword.this,response+"",Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //  Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    // Log.d("TAG",error.toString());


                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(Forgotpassword.this,"error_network_timeout",Toast.LENGTH_LONG).show();


                    } else if (error instanceof AuthFailureError) {
                        Toast.makeText(Forgotpassword.this,"AuthFailureError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ServerError) {
                        Toast.makeText(Forgotpassword.this,"ServerError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(Forgotpassword.this,"NetworkError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ParseError) {
                        Toast.makeText(Forgotpassword.this,"ParseError",Toast.LENGTH_LONG).show();

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
