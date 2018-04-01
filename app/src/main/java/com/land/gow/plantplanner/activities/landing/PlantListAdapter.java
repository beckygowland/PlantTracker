package com.land.gow.plantplanner.activities.landing;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.land.gow.plantplanner.R;
import com.land.gow.plantplanner.activities.addPlant.CardIconView;
import com.land.gow.plantplanner.model.Plant;

import java.util.List;

public class PlantListAdapter extends RecyclerView.Adapter<CardIconView> {

    private static final String LOG_TAG = PlantListAdapter.class.getSimpleName();

    private List<Plant> mDataset;
    private Integer clickedPosition;


    public PlantListAdapter(List<Plant> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public CardIconView onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.icon_button_card, null);
        return new CardIconView(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(CardIconView holder, int position) {
        Button iconButton = holder.getIconButton();
        iconButton.setBackgroundResource(mDataset.get(position).getIconDrawble());
        iconButton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      clickedPosition = position;
                  }
              }
        );

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public Integer getClickedPosition() {
        return clickedPosition;
    }

}