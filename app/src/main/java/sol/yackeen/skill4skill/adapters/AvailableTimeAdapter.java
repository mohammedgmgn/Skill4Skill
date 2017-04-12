package sol.yackeen.skill4skill.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.backend.availabletimeapi.AvailableTime;

/**
 * Created by gmgn on 8/15/2016.
 */
public class AvailableTimeAdapter extends RecyclerView.Adapter<AvailableTimeAdapter.myholder> {
    String day1;
    String from;
    String to;
    private static RecyclerViewClickListener itemListener;


    boolean AVactivity;
  //  List<User> client = new ArrayList<>();
      List<AvailableTime>listoFtime=new ArrayList<>();
    Context context;
    public AvailableTimeAdapter(boolean ACTIVITY,List<AvailableTime> times,Context ctx,RecyclerViewClickListener listener) {

        this.listoFtime=times;
        this.AVactivity=ACTIVITY;
        this.context=ctx;
        this.itemListener=listener;

    }

    @Override
    public myholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.toinflateavaliabletime,parent,false);
        myholder holder=new myholder(row);

        return holder;
    }

    @Override
    public void onBindViewHolder(final myholder holder, final int position) {
         day1=listoFtime.get(position).getDaystring();
         from=listoFtime.get(position).getFromstring();
        to=listoFtime.get(position).getTostring();
        holder.oneday.setText(day1);
        holder.fromtime.setText(from);
        holder.totime.setText(to);

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }


    @Override
    public int getItemCount() {
        return listoFtime.size();
    }

    class myholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView oneday;
        TextView fromtime;
        TextView totime;
        ImageView closeicon;
        public myholder(View itemView) {
            super(itemView);
            oneday=(TextView)itemView.findViewById(R.id.mytextsav);
            fromtime=(TextView)itemView.findViewById(R.id.timeav);
            closeicon=(ImageView)itemView.findViewById(R.id.removeav);
            totime=(TextView)itemView.findViewById(R.id.timeavto);

            if(!AVactivity)
            {
                ((ViewGroup) closeicon.getParent()).removeView(closeicon);

            }
            itemView.setOnClickListener(this);


        }
        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view,this.getLayoutPosition());

        }

    }



    public interface RecyclerViewClickListener
    {

        void recyclerViewListClicked(View v, int position);
    }


}
