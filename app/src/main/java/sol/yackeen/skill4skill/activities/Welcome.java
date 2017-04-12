package sol.yackeen.skill4skill.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.models.UserInfo;

public class Welcome extends AppCompatActivity {
    private Toolbar toolbar;
EditText what_learn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
       final UserInfo userInfo=getIntent().getParcelableExtra("userinfoobj");


        toolbar = (Toolbar) findViewById(R.id.mywelcomebar);
        what_learn=(EditText)findViewById(R.id.lern);
        Button add=(Button)findViewById(R.id.addlearn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(what_learn.getText().toString().isEmpty())
                {
                    Toast.makeText(Welcome.this,"please enter valid input",Toast.LENGTH_LONG).show();


                }

            }
        });
        setSupportActionBar(toolbar);
        setTitle("Welcome");
        ImageView next = (ImageView) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Intent intent=new Intent(Welcome.this,Welcome2.class);
               // intent.putExtra("userinfoobj",userInfo);
                //startActivity(intent);
                //finish();


                overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);

            }
        });


        setSupportActionBar(toolbar);

      // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);


    }
}
