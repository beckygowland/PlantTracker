package com.land.gow.plantplanner.activities.landing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.land.gow.plantplanner.R;
import com.land.gow.plantplanner.activities.addPlant.AddPlant;
import com.land.gow.plantplanner.database.AppDatabase;
import com.land.gow.plantplanner.model.Plant;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class HomeFragment extends Fragment {
    private static final String LOG_TAG = HomeFragment.class.getSimpleName();

    private List<Plant> plants;
    private PlantListAdapter iconListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        plants = AppDatabase.getAppDatabase(container.getContext()).plantDao().getAll();
        Log.d(LOG_TAG, "--------------get all " + plants);
        // Inflate the layout for this fragment

        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_show_plants);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 5);
        mRecyclerView.setLayoutManager(mLayoutManager);

        iconListAdapter = new PlantListAdapter(plants);
        mRecyclerView.setAdapter(iconListAdapter);


        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab_create_new_plant);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // User chose the "Settings" item, show the app settings UI...
                Intent intent = new Intent(getActivity(), AddPlant.class);
                startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        plants = AppDatabase.getAppDatabase(getContext()).plantDao().getAll();
        iconListAdapter.notifyDataSetChanged();
        Log.d(LOG_TAG, "--------------resume " + plants);
    }
}
