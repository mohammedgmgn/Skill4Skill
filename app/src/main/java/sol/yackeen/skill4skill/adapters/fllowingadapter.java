package sol.yackeen.skill4skill.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sol.yackeen.skill4skill.R;

/**
 * Created by gmgn on 8/30/2016.
 */
public class fllowingadapter extends RecyclerView.Adapter<fllowingadapter.myholder>  {

    List<String> usernames = new ArrayList<>();

    public fllowingadapter(List<String> mylist) {
        this.usernames = mylist;
    }

    @Override
    public myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.followers_item,parent,false);
        myholder holder=new myholder(row);

        return holder;
    }

    @Override
    public void onBindViewHolder(myholder holder, int position) {
        String name=usernames.get(position);
        holder.username.setText(name);

    }

    @Override
    public int getItemCount() {
        return usernames.size();
    }

    class myholder extends RecyclerView.ViewHolder{

        TextView username;
        ImageView cover;
        ImageView followicon;
        LinearLayout linearLayout;
        public myholder(View itemView) {
            super(itemView);
            username=(TextView)itemView.findViewById(R.id.usernamefollow);
            cover=(ImageView) itemView.findViewById(R.id.coverfolloweing);
            followicon=(ImageView) itemView.findViewById(R.id.folowic);
           // linearLayout=(LinearLayout)itemView.findViewById(R.id.myfollowerslineritem);

        }
    }

}
