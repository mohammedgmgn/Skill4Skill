package sol.yackeen.skill4skill.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import sol.yackeen.skill4skill.R;

public class WritePost extends AppCompatActivity {
    private Toolbar toolbar;
   EditText postcontent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);
        postcontent=(EditText)findViewById(R.id.postcontentid);
        toolbar= (Toolbar) findViewById(R.id.mybarpost);
        setSupportActionBar(toolbar);
        setTitle("Write Post");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setSupportActionBar(toolbar);


    }
}
