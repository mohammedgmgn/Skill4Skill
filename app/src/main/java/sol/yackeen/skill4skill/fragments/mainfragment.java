package sol.yackeen.skill4skill.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.activities.Forgotpassword;
import sol.yackeen.skill4skill.activities.Signup;
import sol.yackeen.skill4skill.activities.navigation;
import sol.yackeen.skill4skill.backend.loginapi.RequestBodyLogin;
import sol.yackeen.skill4skill.backend.loginapi.SocialMedialogin;
import sol.yackeen.skill4skill.extra_classes.SaveSharedPreference;
import sol.yackeen.skill4skill.models.User;


public class mainfragment extends Fragment {
    private CallbackManager callbackManager;
    private User user;
    private String type="";
   private SocialMedialogin socialMedialogin;
    boolean checksocialmedia=false;
    private TextInputLayout inputLayoutmail,inputLayoutpassword;
    EditText email,password;
    RequestQueue requestQueue;
    LoginButton loginButton;
    private FacebookCallback<LoginResult> mcallback=new FacebookCallback<LoginResult> (){
        @Override
        public void onSuccess(final LoginResult loginResult) {
            final Profile profile;
            final String accessToken=loginResult.getAccessToken().getToken();

            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    Log.i("LoginActivity", response.toString());
                    // Get facebook data from login
                    try {
                        checksocialmedia=true;
                        type="Facebook";
                        Bundle bFacebookData = getFacebookData(object);
                        String mail=(String) bFacebookData.get("email");
                        String name=(String)bFacebookData.get("first_name");
                        String fbid=object.getString("id");
                     //   String id=profile.getId();
                      //  String ID=(String)bFacebookData.get("id");
                        String picurl=(String)bFacebookData.get("profile_pic");
                       // user=new User();
                        //user.setName(name);
                        //user.setEmail(mail);
                     //   user.setFbid(fbid);
                       // user.setImageurl(picurl);
                        //user.setAccesstoken(accessToken);
                        sendData(user,type,socialMedialogin);
                        Toast.makeText(getContext(),fbid,Toast.LENGTH_LONG).show();
                    //    showsucess();
                        SaveSharedPreference.setUserName(getContext(),mail);
                       startActivity(new Intent(getActivity(),navigation.class));
                       getActivity().finish();

                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finally {


                    }




                }
            }
        );
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
        request.setParameters(parameters);
        request.executeAsync();
        }


        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            Toast.makeText(getContext(),"error",Toast.LENGTH_LONG).show();
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getContext());
        callbackManager=CallbackManager.Factory.create();
        requestQueue = Volley.newRequestQueue(getContext());
        if(SaveSharedPreference.getUserName(getContext()).length()> 0)
        {
            startActivity(new Intent(getContext(), navigation.class));
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View root= inflater.inflate(R.layout.fragment_mainfragment, container, false);
         loginButton=(LoginButton)root.findViewById(R.id.fbbtn);
        loginButton.setFragment(this);
        loginButton.setReadPermissions(Arrays.asList("email"));
        loginButton.registerCallback(callbackManager,mcallback);
        TextView newaccount=(TextView)root.findViewById(R.id.createaccounttv);
        newaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),Signup.class));
                getActivity().overridePendingTransition(R.anim.activity_push_up_in,R.anim.activity_push_up_out);

                //  getActivity().finish();
            }
        });
        TextView forgotpassword=(TextView)root.findViewById(R.id.forgotpasswordtv);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),Forgotpassword.class));

            }
        });
        Button signin=(Button)root.findViewById(R.id.signinbtn);
        email=(EditText)root.findViewById(R.id.emailet);
        password=(EditText)root.findViewById(R.id.passwordet);
        user=new User();
        socialMedialogin=new SocialMedialogin();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                //validateAndLogin(email.getText().toString());
                user.setEmail(email.getText().toString());
                sendData(user,type,socialMedialogin);
                startActivity(new Intent(getActivity(),navigation.class));

            }
        });
        inputLayoutmail=(TextInputLayout)root.findViewById(R.id.TextInputLayoutmail);
inputLayoutpassword=(TextInputLayout)root.findViewById(R.id.TextInputLayoutpass);

        return root;


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
    private Bundle getFacebookData(JSONObject object) throws JSONException {
        Bundle bundle = new Bundle();

        try {
            String id = object.getString("id");
            socialMedialogin.setSocialMediaId(id);
            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

        }catch (Exception e)
        {

        }
        return bundle;

    }
    public void validateAndLogin(String maill){

        if(validateemail()&&validatepassword())
        {
            if(exist(maill))
            {
                SaveSharedPreference.setUserName(getContext(),maill);

                // showsucess();
                startActivity(new Intent(getActivity(),navigation.class));
                getActivity().finish();
                //  Toast.makeText(getContext(),"Successufully Logged In !",Toast.LENGTH_LONG).show();

            }
            else {
                Toast.makeText(getContext(),"you are not registred",Toast.LENGTH_LONG).show();

            }
        }
    }

    private boolean exist(String email) {

        return true;
    }

    private boolean validateemail(){
 /*
 * one condition from database if email already exist
 *
 * */
if(email.getText().toString().isEmpty())
{
inputLayoutmail.setError("email cannot be blank");
    return false;

}
 else if(!isEmailValid(email.getText().toString()))    {
    inputLayoutmail.setError("plese enter valid email");
    return false;
}
        else
    inputLayoutmail.setErrorEnabled(false);
        return true;
        //!isEmailValid(email.getText().toString())
    }
    private boolean validatepassword(){
    String pwd=password.getText().toString().trim();
       if(pwd.length()<8)
       {
           inputLayoutpassword.setError("Minim 8 characters required");
           return false;
       }
        else if(password.getText().toString().isEmpty())
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
    private void sendData(User client,String type,SocialMedialogin socialobj){
        Gson gson = new Gson();
        RequestBodyLogin requestBodyLogin=new RequestBodyLogin();
        requestBodyLogin.User=client;
        requestBodyLogin.SocialMediaData=socialobj;
        requestBodyLogin.Type=type;
        String json = gson.toJson(requestBodyLogin);

        String URL="http://yakensolution.cloudapp.net/one2one/signin";
        try {

            JSONObject object = new JSONObject(json);
            JsonObjectRequest jr = new JsonObjectRequest(Request.Method.POST, URL, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getContext(),response+"",Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                  //  Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    // Log.d("TAG",error.toString());


                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(getContext(),"error_network_timeout",Toast.LENGTH_LONG).show();


                    } else if (error instanceof AuthFailureError) {
                        Toast.makeText(getContext(),"AuthFailureError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ServerError) {
                        Toast.makeText(getContext(),"ServerError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(getContext(),"NetworkError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ParseError) {
                        Toast.makeText(getContext(),"ParseError",Toast.LENGTH_LONG).show();

                        //TODO
                    }

                }
            });
             requestQueue.add(jr);
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
    public void showsucess(){

        //Custom Toast
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View appearance = inflater.inflate(R.layout.mytoast, (ViewGroup)getActivity().findViewById(R.id.mycustom_view));
        TextView tv = (TextView) appearance.findViewById(R.id.text);
        tv.setText("Success");
        Toast toast = new Toast(getContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(appearance);
        toast.show();


    }
}
