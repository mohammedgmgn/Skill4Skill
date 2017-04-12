package sol.yackeen.skill4skill.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import sol.yackeen.skill4skill.extra_classes.CustomDecorationPosts;
import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.activities.profile;
import sol.yackeen.skill4skill.models.BasePost;
import sol.yackeen.skill4skill.models.Post;
import sol.yackeen.skill4skill.models.PostType;
import sol.yackeen.skill4skill.models.Recommendation;
import sol.yackeen.skill4skill.models.RecommendationWrapper;

/**
 * Created by gmgn on 8/25/2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.BaseHolder> {
    List<BasePost> posts = new ArrayList<>();
    Context context;
    public HomeAdapter(List<BasePost> posts, Context cx) {
        this.posts = posts;
        this.context=cx;
    }

    @Override

    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==PostType.vertical) {
            View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.postrow, parent, false);
            myholder holder = new myholder(row);
            return holder;
        }
        else
        {
            View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_view, parent, false);
            recommandholder holder = new recommandholder(row);
            return holder;
        }
       // animate(holder);


    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
       BasePost basePost= posts.get(position);

        if(basePost.getPostType()== PostType.horizontal)
        {
            RecommendationWrapper recommendationWrapper =(RecommendationWrapper)basePost;
            List<Recommendation>  recommendations=recommendationWrapper.recommendations;
            recommandholder horizontalHolder=(recommandholder)holder;

            horizontalHolder.adapter=new recommandationadapter(context,recommendations);

            horizontalHolder.horizontallist.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayout.HORIZONTAL));
            horizontalHolder.horizontallist.setAdapter(horizontalHolder.adapter);

        }
        else
        {
            Post post=(Post)basePost;
            String nameprofile=post.getName();
            String time=post.getTime();
            String postcontent=post.getPost_content();
            myholder verticalHolder=(myholder)holder;
            verticalHolder.name.setText(nameprofile);
            verticalHolder.time.setText(time);
            verticalHolder.post.setText(postcontent);
        }

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class myholder extends  BaseHolder{

        TextView name;
        TextView time;
        TextView post;
        CircleImageView circleImageView;
        public myholder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.profilename);
            time=(TextView)itemView.findViewById(R.id.timepost);
            post=(TextView)itemView.findViewById(R.id.postcontentid);
            circleImageView=(CircleImageView)itemView.findViewById(R.id.profile_image);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context,profile.class));
                }
            });

        }
    }

    class recommandholder extends  BaseHolder

    {
      recommandationadapter adapter;
        RecyclerView horizontallist;

        public recommandholder(View itemView) {
            super(itemView);
            horizontallist=(RecyclerView)itemView.findViewById(R.id.my_recycler_viewh);
            int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.spacingpost);
            horizontallist.addItemDecoration(new CustomDecorationPosts(spacingInPixels));
            horizontallist.setPadding(0,0,0,0);

        }
//        public recommandholder(View itemView) {
//            super(itemView);
//
//
//        }
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public void insert(int position, Post data) {
        posts.add(position, data);
        notifyItemInserted(position);
    }
    public void remove(Post data) {
        int position = posts.indexOf(data);
        posts.remove(position);
        notifyItemRemoved(position);
    }
    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous

        BasePost basePost= posts.get(position);

return  basePost.getPostType();

    }
public class BaseHolder extends RecyclerView.ViewHolder
{
    public BaseHolder(View itemView) {
        super(itemView);
    }
}
}
