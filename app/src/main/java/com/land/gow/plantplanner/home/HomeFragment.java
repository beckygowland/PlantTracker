package com.land.gow.plantplanner.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.land.gow.plantplanner.R;
import com.land.gow.plantplanner.dao.PlantDao;
import com.land.gow.plantplanner.database.AppDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class HomeFragment extends Fragment {
    private static final String LOG_TAG = HomeFragment.class.getSimpleName();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PlantDao userDao = AppDatabase
                .getAppDatabase(container.getContext())
                .plantDao();
        Log.d(LOG_TAG, "--------------get all " + userDao.getAll());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        PlantDao userDao = AppDatabase
                .getAppDatabase(getContext())
                .plantDao();
        Log.d(LOG_TAG, "--------------resume " + userDao.getAll());
    }
}
