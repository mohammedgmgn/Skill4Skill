package sol.yackeen.skill4skill.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.models.Recommendation;

/**
 * Created by gmgn on 9/6/2016.
 */
public class recommandationadapter extends RecyclerView.Adapter<recommandationadapter.myholderR> {

    List<Recommendation> mylist = new ArrayList<>();
    Context context;
    public recommandationadapter(Context ctx,List<Recommendation> mylist) {

        this.mylist = mylist;
        this.context=ctx;

    }


    @Override
    public myholderR onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.recommand_item,parent,false);
        myholderR holder=new myholderR(row);

        return holder;
    }

    @Override
    public void onBindViewHolder(myholderR holder, int position) {
        String name=mylist.get(position).getUseername();
        String Node=mylist.get(position).getNode();
     //   holder.name.setText(name);
        //holder.node.setText(Node);


    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class myholderR extends RecyclerView.ViewHolder{
        TextView name;
       // TextView node;
        public myholderR(View itemView) {
            super(itemView);

          //  name=(TextView)itemView.findViewById(R.id.namerecommandid);
          //  node=(TextView)itemView.findViewById(R.id.noderecommandid);


        }
    }



}
