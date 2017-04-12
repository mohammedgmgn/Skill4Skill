package sol.yackeen.skill4skill.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import sol.yackeen.skill4skill.R;

/**
 * Created by gmgn on 8/31/2016.
 */
public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.myholder> {

    List<String> notificationsBody = new ArrayList<>();
    public NotificationsAdapter(List<String> notifications)

    {
        this.notificationsBody=notifications;

    }

    @Override
    public myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.notifications_row,parent,false);
        myholder holder=new myholder(row);
        // animate(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(myholder holder, int position) {
        String nameprofile=notificationsBody.get(position);
        holder.name.setText(nameprofile);

    }

    @Override
    public int getItemCount() {
        return notificationsBody.size();
    }

    class myholder extends RecyclerView.ViewHolder{
        TextView name;
        TextView time;
        TextView notificationbody;
        CircleImageView circleImageView;
        ImageView check;
        public myholder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.profilenamenotifications);
            time=(TextView)itemView.findViewById(R.id.timenotifications);
            notificationbody=(TextView)itemView.findViewById(R.id.notificationbody);
            circleImageView=(CircleImageView)itemView.findViewById(R.id.profile_imagenotifications);
            check=(ImageView)itemView.findViewById(R.id.checknotifications);

        }
    }
}
