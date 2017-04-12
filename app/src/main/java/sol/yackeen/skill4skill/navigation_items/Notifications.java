package sol.yackeen.skill4skill.navigation_items;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import sol.yackeen.skill4skill.extra_classes.CustomDecorationPosts;
import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.adapters.NotificationsAdapter;


public class Notifications extends Fragment {
    List<String> notificationsnames = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View root= inflater.inflate(R.layout.fragment_notifications, container, false);

        notificationsnames.add("Mohammed Mahmoud");
        for(int i=0;i<10;i++)
        {
            notificationsnames.add("Psycho");


        }

        RecyclerView recyclerView = (RecyclerView)root.findViewById(R.id.recyclerViewnotifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false));
        NotificationsAdapter adapter = new NotificationsAdapter(notificationsnames);

        recyclerView.setAdapter(adapter);
        //recyclerView.scrollToPosition(0);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacingnotification);
        recyclerView.addItemDecoration(new CustomDecorationPosts(spacingInPixels));
       // recyclerView.setPadding(0,0,0,0);


        return root;
    }

}
