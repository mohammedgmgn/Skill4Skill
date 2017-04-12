package sol.yackeen.skill4skill.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.adapters.AvailableTimeAdapter;
import sol.yackeen.skill4skill.adapters.Myadabter;
import sol.yackeen.skill4skill.backend.availabletimeapi.AvailableTime;
import sol.yackeen.skill4skill.extra_classes.SpacesItemDecoration;

public class learn extends AppCompatActivity {
    List<AvailableTime>listoFtime=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewlearn);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL));
        List<String> wanna_learn=new ArrayList<>();
        wanna_learn.add("c++");
        wanna_learn.add("java");
        wanna_learn.add("oop");
        wanna_learn.add("math");

        Myadabter adapter = new Myadabter(false, wanna_learn, this, new Myadabter.RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {

            }
        });
        recyclerView.setAdapter(adapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        recyclerView.setPadding(15,15,15,15);

        RecyclerView recyclerView1=(RecyclerView)findViewById(R.id.RecyclerViewavtimechoose);
        recyclerView1.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayout.HORIZONTAL));

        for(int i=0;i<5;i++)
        {
            AvailableTime obj=new AvailableTime();
            obj.setDaystring("Sun");
            obj.setFromstring("11:00");
            obj.setTostring("12:00");
            listoFtime.add(obj);
        }


        AvailableTimeAdapter AVadapter = new AvailableTimeAdapter(false, listoFtime, this, new AvailableTimeAdapter.RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {

            }
        });
        recyclerView1.setAdapter(AVadapter);


        recyclerView1.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        recyclerView1.setPadding(5,15,15,15);

    }
}
