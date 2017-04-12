package sol.yackeen.skill4skill.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sol.yackeen.skill4skill.R;
import sol.yackeen.skill4skill.adapters.CurrentlyLearningAdapter;
import sol.yackeen.skill4skill.extra_classes.SpacesItemDecoration;


public class learned extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View root= inflater.inflate(R.layout.fragment_learned, container, false);


        RecyclerView recyclerView = (RecyclerView)root.findViewById(R.id.recyclerViewlearned);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        List<String> History=new ArrayList<>();
        History.add("c++");
        History.add("java");
        History.add("oop");
        History.add("math");
        CurrentlyLearningAdapter adapter = new CurrentlyLearningAdapter(History);
        recyclerView.setAdapter(adapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        recyclerView.setPadding(5,15,15,15);


        return root;


    }


}
