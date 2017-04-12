package sol.yackeen.skill4skill.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.adapters.fllowingadapter;

public class followers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewfllowers);
        recyclerView.setLayoutManager(new GridLayoutManager(followers.this,2));
        List<String> names=new ArrayList<>();
        names.add("Mohammed ");
        names.add("Aymn Zaky");
        names.add("Mohsen");
        names.add("Tarek");
        names.add("siko");
        names.add("amr");
        names.add("Ibrahim");
        names.add("Lotfy");

        fllowingadapter adapter = new fllowingadapter(names);
        recyclerView.setAdapter(adapter);
/*
        int spacingInPixels1 = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels1));
        recyclerView.setPadding(0,0,0,0);
*/
    }
}
