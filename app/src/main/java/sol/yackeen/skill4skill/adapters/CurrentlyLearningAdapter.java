package sol.yackeen.skill4skill.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sol.yackeen.skill4skill.R;

/**
 * Created by gmgn on 8/21/2016.
 */
public class CurrentlyLearningAdapter extends RecyclerView.Adapter<CurrentlyLearningAdapter.myholdercurrent> {
    List<String> node=new ArrayList<>();


    public CurrentlyLearningAdapter(List<String> mylist)
    {
     this.node=mylist;
    }

    @Override
    public myholdercurrent onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.current_learn_item,parent,false);
        myholdercurrent holder=new myholdercurrent(row);

        return holder;

    }

    @Override
    public void onBindViewHolder(myholdercurrent holder, int position) {
        String title=node.get(position);
        holder.item.setText(title);


    }




    @Override
    public int getItemCount()
    {
        return node.size();
    }

    class myholdercurrent extends RecyclerView.ViewHolder{

        TextView item;
        public myholdercurrent(View itemView) {
            super(itemView);
             item=(TextView)itemView.findViewById(R.id.nodename);
        }
    }
}
