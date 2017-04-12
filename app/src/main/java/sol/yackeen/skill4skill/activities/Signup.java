package sol.yackeen.skill4skill.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.backend.signinapi.RegisterRequestBody;
import sol.yackeen.skill4skill.models.User;
import sol.yackeen.skill4skill.models.UserInfo;

public class Signup extends AppCompatActivity {
    private Toolbar toolbar;
    ImageView imageProfile;
    User user;
    RequestQueue requestQueue;
    LinearLayout linearLayout;
     UserInfo userInfo;
    EditText name,mail,pass;
    private TextInputLayout inputLayoutname,inputLayoutmail,inputLayoutpassword;

    private static final int SELECTED_PICTURE = 1;
   ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        requestQueue= Volley.newRequestQueue(this);
        user=new User();
         userInfo=new UserInfo();
        toolbar = (Toolbar) findViewById(R.id.mybar);
        setSupportActionBar(toolbar);
        setTitle("Registration");
     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        ImageView ok = (ImageView) findViewById(R.id.donereg);
      //   linearLayout=(LinearLayout)findViewById(R.id.mylinear);
        imageProfile = (ImageView) findViewById(R.id.profilepic);
       name=(EditText)findViewById(R.id.nameed);
        mail=(EditText)findViewById(R.id.emailed);
        pass=(EditText)findViewById(R.id.passed);
        inputLayoutmail=(TextInputLayout)findViewById(R.id.TextInputLayoutmailsignup);
        inputLayoutname=(TextInputLayout)findViewById(R.id.TextInputLayoutsinglenamesignup);
        inputLayoutpassword=(TextInputLayout)findViewById(R.id.TextInputLayoutpasssignup);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);



        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, SELECTED_PICTURE);//one can be replaced with any action code

            }
        });



        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               validateAndLogin();
               //startActivity(new Intent(Signup.this,Welcome2.class));
                    try{

                        if(!name.getText().toString().isEmpty()||!mail.getText().toString().isEmpty()||!pass.getText().toString().isEmpty())

                        {
                            user.setName(name.getText().toString());
                            user.setEmail(mail.getText().toString());
                            user.setPassword(pass.getText().toString());
                            userInfo.setName(name.getText().toString());
                            userInfo.setEmail(mail.getText().toString());
                            userInfo.setPassword(pass.getText().toString());
                            progressBar.setVisibility(View.VISIBLE);

                            sendDatatoBackend(user);

                        }



                    }catch (Exception e){


                    }


                //here send the email to the backend
              //  Toast.makeText(Signup.this, "done Registration", Toast.LENGTH_LONG).show();


            }
        });
        setSupportActionBar(toolbar);

    }

    private void sendDatatoBackend(final User client) {
        //send data to backend
        String url="http://yakensolution.cloudapp.net/one2one/signup";
        Gson gson = new Gson();
        RegisterRequestBody registerRequestBody=new RegisterRequestBody();
        registerRequestBody.User=client;
        final String json = gson.toJson(registerRequestBody);
        try {
            JSONObject obj = new JSONObject(json);
//            obj.put("User",client);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("TAG", response.toString());
                    try {
                        JSONObject jsonObj = new JSONObject(response.toString());
                        if(jsonObj.getInt("UserId")==0)
                        {
                            Toast.makeText(Signup.this,"Already Exist",Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            userInfo.setUserid(jsonObj.getInt("UserId"));
                            Intent intent=new Intent(Signup.this,Welcome2.class);
                            //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("userinfoobj",userInfo);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
                            finish();

                        }
                        progressBar.setVisibility(View.GONE);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }




                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                   // Log.d("error_message","hi");
                    Toast.makeText(Signup.this,error.toString(),Toast.LENGTH_SHORT).show();
                    // Log.d("TAG",error.toString());


                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(Signup.this,"error_network_timeout",Toast.LENGTH_LONG).show();


                    } else if (error instanceof AuthFailureError) {
                        Toast.makeText(Signup.this,"AuthFailureError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ServerError) {
                        Toast.makeText(Signup.this,"ServerError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(Signup.this,"NetworkError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ParseError) {
                        Toast.makeText(Signup.this,"ParseError",Toast.LENGTH_LONG).show();

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

    public Boolean validateAndLogin(){

        if(validateemail()&&validatepassword())
        {
          //  Toast.makeText(this,"Successufully Regesterd !",Toast.LENGTH_LONG).show();

            //startActivity(new Intent(Signup.this,navigation.class));
            //finish();
            return true;

        }
        else
            return false;
    }

    private boolean validateemail(){
 /*
 * one condition from database if email already exist
 *
 * */
        if(name.getText().toString().isEmpty())
        {
            inputLayoutmail.setError("email cannot be blank");


        }

        if(mail.getText().toString().isEmpty())
        {
            inputLayoutname.setError("name cannot be blank");

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
    private boolean validatepassword(){
        String pwd=pass.getText().toString().trim();
        if(pwd.length()<8)
        {
            inputLayoutpassword.setError("Minim 8 characters required");
            Toast.makeText(this,"pwd error",Toast.LENGTH_LONG).show();

            return false;
        }
        else if(pass.getText().toString().isEmpty())
        {

            inputLayoutpassword.setError("password cannot be blank");
            return false;

        }
        else
            inputLayoutpassword.setErrorEnabled(false);
        return true;


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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECTED_PICTURE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
/*
                    Picasso.with(getBaseContext()).load(ProfilePictureUrl)
                            .placeholder(R.drawable.ic_profile)
                            .into(imageView);*/

                    String[] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, projection, null, null, null);
                    cursor.moveToFirst();
                    int columnindex=cursor.getColumnIndex(projection[0]);
                    String filepath=cursor.getString(columnindex);
                    cursor.close();
                    Bitmap yourselectdImage= BitmapFactory.decodeFile(filepath);
                    Drawable d=new BitmapDrawable(getResources(), yourselectdImage);
                    imageProfile.setBackground(d);
                    Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
                    //linearLayout.setBackground(d);

                }

        }

    }

}
