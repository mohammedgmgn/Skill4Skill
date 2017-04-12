package sol.yackeen.skill4skill.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import sol.yackeen.skill4skill.R;

public class feedback extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        toolbar = (Toolbar) findViewById(R.id.mybarfeeback);
        toolbar.setTitleTextColor(Color.WHITE);
      //  toolbar.setTitleMarginBottom(35);
      //  toolbar.setTitleMargin(60,0,0,0);
       // getSupportActionBar().setDisplayShowTitleEnabled(false);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageView closeicon=(ImageView)findViewById(R.id.close);
        closeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RatingBar ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        ratingBar.setRating(0.0f);

    }
}
