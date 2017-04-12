package sol.yackeen.skill4skill.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.adapters.Myadabter;
import sol.yackeen.skill4skill.extra_classes.SpacesItemDecoration;
import sol.yackeen.skill4skill.models.UserInfo;

public class Welcome3 extends AppCompatActivity {
    private EditText mEditText;
    private Button add;
    List<String> skillsknown = new ArrayList<>();
    Myadabter adapter;
    private Toolbar toolbar;
    UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome3);
        if(getIntent().getParcelableExtra("userinfoobj")!=null)
        {
             userInfo=getIntent().getParcelableExtra("userinfoobj");
            Toast.makeText(Welcome3.this,userInfo.getUserid()+"",Toast.LENGTH_LONG).show();

        }

        toolbar = (Toolbar) findViewById(R.id.mywelcomebar_three);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        setTitle("Welcome");
     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDisplayShowHomeEnabled(true);
        mEditText = (EditText) findViewById(R.id.addskillsetwelcome);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewtwo);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL));

        adapter = new Myadabter(true, skillsknown, this, new Myadabter.RecyclerViewClickListener() {
            AlertDialog.Builder builder = new AlertDialog.Builder(Welcome3.this);
            @Override
            public void recyclerViewListClicked(View v, final int position) {
                builder.setTitle("delete item");
                builder.setMessage("are you want to delete this time");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        skillsknown.remove(position);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeChanged(position, skillsknown.size());

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
        ImageView next = (ImageView) findViewById(R.id.nextgo);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Welcome3.this,AvailableTimeActivity.class);
                intent.putExtra("userinfoobj",userInfo);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);

            }
        });



 add=(Button)findViewById(R.id.addbtntwowelcome);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEditText.getText().toString().isEmpty())
                {
                    Toast.makeText(Welcome3.this,"please enter what you know",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    skillsknown.add(mEditText.getText().toString());
                    adapter.notifyDataSetChanged();
                    mEditText.setText("");

                }

            }
        });

    }
}
