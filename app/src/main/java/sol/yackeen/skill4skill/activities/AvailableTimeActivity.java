package sol.yackeen.skill4skill.activities;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
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
import java.util.Calendar;
import java.util.List;

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.adapters.AvailableTimeAdapter;
import sol.yackeen.skill4skill.models.User;
import sol.yackeen.skill4skill.backend.availabletimeapi.AvailableTime;
import sol.yackeen.skill4skill.backend.availabletimeapi.RequestBodyAvailableTime;
import sol.yackeen.skill4skill.extra_classes.SpacesItemDecoration;
import sol.yackeen.skill4skill.models.UserInfo;

public class AvailableTimeActivity extends AppCompatActivity {
    AvailableTimeAdapter adapter;
    Spinner day;
    Button from,to;
    String daystring,fromstring,FROM="",TO="";
    TextView fromtv,totv;
    RequestQueue requestQueue;


 //   List<String> AVAdays = new ArrayList<>();
   // List<String> AVAtime= new ArrayList<>();
    //List<Double> TIME= new ArrayList<>();
 List<AvailableTime>  avtimelist=new ArrayList<>();
    AvailableTime timeobj;

    User obj;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_time_activity);
        final UserInfo userInfo=getIntent().getParcelableExtra("userinfoobj");
        Toast.makeText(AvailableTimeActivity.this,userInfo.getUserid()+"",Toast.LENGTH_LONG).show();

        setTitle("Welcome");
        requestQueue = Volley.newRequestQueue(AvailableTimeActivity.this);
        toolbar = (Toolbar) findViewById(R.id.toolbaravailabletime);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        fromtv=(TextView)findViewById(R.id.fromtv);
        totv=(TextView)findViewById(R.id.totv);
        obj=new User();
        day = (Spinner) findViewById(R.id.spinner);
        from=(Button) findViewById(R.id.addskillsetavailabletime);
        to=(Button) findViewById(R.id.spinner2);
      //  tostring=to.getSelectedItem().toString();
     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button addbtn=(Button)findViewById(R.id.addbtntwoavailabletime);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewavailabletime);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL));
        ImageView next=(ImageView)findViewById(R.id.nexttwoavailabletime);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(avtimelist.size()>0)
               {
                   userInfo.setAvailableTime(avtimelist);
                   SendAvailableTimetoBackend(userInfo);
                   Intent intent=new Intent(AvailableTimeActivity.this,navigation.class);

                   intent.putExtra("userinfoobj",userInfo);
                   //intent.putExtra("activityid","From_Activity_availabletime");
                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   startActivity(intent);
                   finish();

               }
                else
                   Toast.makeText(AvailableTimeActivity.this,"add your available time please",Toast.LENGTH_SHORT).show();


                // overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);

            }
        });
        adapter = new AvailableTimeAdapter(true, avtimelist, this, new AvailableTimeAdapter.RecyclerViewClickListener() {
            AlertDialog.Builder builder = new AlertDialog.Builder(AvailableTimeActivity.this);

            @Override
            public void recyclerViewListClicked(View v, final int position) {
                Toast.makeText(AvailableTimeActivity.this,"hi from onclick"+position,Toast.LENGTH_LONG).show();

                Log.v("psition",position+"");
                builder.setTitle("delete item");
                builder.setMessage("are you want to delete this time");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        avtimelist.remove(position);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeChanged(position, avtimelist.size());
                        userInfo.setAvailableTime(avtimelist);
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
       // obj.setAVTIMELIST(avtimelist);
        from.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(AvailableTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                //   eReminderTime.setText( selectedHour + ":" + selectedMinute);
                  //  FROM=selectedHour + ":" + selectedMinute;

                String hour=selectedHour+"";
                String min=selectedMinute+"";
                if(selectedHour<10)
                {
                    hour="0"+selectedHour;

                }
                if(selectedMinute<10)
                {
                    min="0"+selectedMinute;
                }
                FROM=hour + ":" + min;

                Toast.makeText(AvailableTimeActivity.this,hour + ":" + min,Toast.LENGTH_SHORT).show();

                fromtv.setText(FROM);

            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();    }


});
        to.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         Calendar mcurrentTime = Calendar.getInstance();
         int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
         int minute = mcurrentTime.get(Calendar.MINUTE);
         TimePickerDialog mTimePicker;
         mTimePicker = new TimePickerDialog(AvailableTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
             @Override
             public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                 //   eReminderTime.setText( selectedHour + ":" + selectedMinute);
                 String hour=selectedHour+"";
                 String min=selectedMinute+"";
                 if(selectedHour<10)
                 {
                      hour="0"+selectedHour;

                 }
                 if(selectedMinute<10)
                 {
                      min="0"+selectedMinute;
                 }
                 TO=hour + ":" + min;

                 Toast.makeText(AvailableTimeActivity.this,hour + ":" + min,Toast.LENGTH_SHORT).show();


                 totv.setText(TO);

             }
         }, hour, minute, true);//Yes 24 hour time
         mTimePicker.setTitle("Select Time");
         mTimePicker.show();
     }
 });
        addbtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  if(FROM.equals("")||TO.equals(""))
                  {
                      Toast.makeText(AvailableTimeActivity.this,"please choose AV time ",Toast.LENGTH_SHORT).show();

                  }
                  else
                  {
                      try {
                          timeobj=new AvailableTime();
                          daystring=day.getSelectedItem().toString();

                              //     fromstring=from.getSelectedItem().toString();
                              timeobj.setDaystring(daystring);
                              timeobj.setFromstring(FROM);
                            timeobj.setTostring(TO);
                              avtimelist.add(timeobj);
                              //   timeobj.setFrom(converttime(fromstring));
                              timeobj.setDayId(getdayID(daystring));

                              // senDataTobackend(timeobj);

                              //    appointments.add(obj);
                              //TIME.add(converttime(fromstring));
                              // appointments.add(obj);
                              adapter.notifyDataSetChanged();
                              //        daystring=null;
                              //    fromstring=null;



                      }catch (Exception e)
                      {
                          Toast.makeText(AvailableTimeActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                      }
                  }

              }
           });

    }

private void timedialg(){

    Calendar mcurrentTime = Calendar.getInstance();
    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
    int minute = mcurrentTime.get(Calendar.MINUTE);
    TimePickerDialog mTimePicker;
    mTimePicker = new TimePickerDialog(AvailableTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
            //   eReminderTime.setText( selectedHour + ":" + selectedMinute);
            Toast.makeText(AvailableTimeActivity.this,selectedHour + ":" + selectedMinute,Toast.LENGTH_LONG).show();
        }
    }, hour, minute, true);//Yes 24 hour time
    mTimePicker.setTitle("Select Time");
    mTimePicker.show();

}
   private Integer converttime(String str)

    {
        Integer resault = null;
           String s1= String.valueOf(str.charAt(0))+String.valueOf(str.charAt(1));
        Integer num1=Integer.valueOf(s1);

        String s2= String.valueOf(str.charAt(3)+String.valueOf(str.charAt(4)));
        Integer num2=Integer.valueOf(s2);

        resault=num1*60+num2;
        Toast.makeText(AvailableTimeActivity.this,resault+"",Toast.LENGTH_LONG).show();

        return resault;
    }
    private int getdayID (String day)
    {
        if(day.equals("Sa"))
        {
            return 0;
        }
        else if(day.equals("Su"))
            return 1;
       else if (day.equals("Mo"))
            return 2;

        else if (day.equals("Tu"))
            return 3;
        else if (day.equals("We"))
            return 4;
        else if (day.equals("Th"))
            return 5;
        else
            return 6;

    }
    private void SendAvailableTimetoBackend(UserInfo user)
    {
        RequestBodyAvailableTime requestBodyAvailableTime=new RequestBodyAvailableTime();
        //AvailableTime obj1=new AvailableTime();
       /* obj1.setDayId(2);
        obj1.setTimeFrom(200);
        obj1.setTimeTo(200);
        mytimelist.add(obj1);*/
        requestBodyAvailableTime.AvailableTime=user.getAvailableTime();
        requestBodyAvailableTime.UserId=user.getUserid();
     Toast.makeText(this,""+user.getAvailableTime().size(),Toast.LENGTH_LONG).show();
        Gson gson = new Gson();
        String json = gson.toJson(requestBodyAvailableTime);

        String URL="http://yakensolution.cloudapp.net/one2one/avialabletime/edit";
        try {

            JSONObject object = new JSONObject(json);

            JsonObjectRequest jr = new JsonObjectRequest(Request.Method.POST, URL, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(AvailableTimeActivity.this,response+"from welcome available time",Toast.LENGTH_LONG).show();


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //  Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    // Log.d("TAG",error.toString());


                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(AvailableTimeActivity.this,"error_network_timeout",Toast.LENGTH_LONG).show();


                    } else if (error instanceof AuthFailureError) {
                        Toast.makeText(AvailableTimeActivity.this,"AuthFailureError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ServerError) {
                        Toast.makeText(AvailableTimeActivity.this,"ServerError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(AvailableTimeActivity.this,"NetworkError",Toast.LENGTH_LONG).show();

                        //TODO
                    } else if (error instanceof ParseError) {
                        Toast.makeText(AvailableTimeActivity.this,"ParseError",Toast.LENGTH_LONG).show();

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
