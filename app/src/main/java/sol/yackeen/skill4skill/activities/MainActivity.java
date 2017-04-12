package sol.yackeen.skill4skill.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.FacebookSdk;

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.fragments.mainfragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        getSupportFragmentManager().beginTransaction().add(R.id.container,new mainfragment()).commit();
    }
}
