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

/**
 * Created by gmgn on 8/9/2016.
 */
public class Myadabter extends RecyclerView.Adapter<Myadabter.myholder> {

    List<String> mylist = new ArrayList<>();
boolean add=true;
    Context ctx;
    RecyclerViewClickListener itemListener;

    public Myadabter(boolean Add,List<String> mylist,Context ctx,RecyclerViewClickListener listener) {
        this.mylist = mylist;
        this.add=Add;
        this.ctx=ctx;
        this.itemListener=listener;
    }

    @Override
    public myholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_toinfliate,parent,false);
        myholder holder=new myholder(row);

        return holder;
    }

    @Override
    public void onBindViewHolder(myholder holder, final int position) {
        String title=mylist.get(position);
        holder.item.setText(title);


    }


    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class myholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView item;
        ImageView redbtnclose;
        public myholder(View itemView) {
            super(itemView);
     item=(TextView)itemView.findViewById(R.id.mytexts);
            redbtnclose=(ImageView) itemView.findViewById(R.id.closeic);
            if(!add)
            {
                ((ViewGroup) redbtnclose.getParent()).removeView(redbtnclose);


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
