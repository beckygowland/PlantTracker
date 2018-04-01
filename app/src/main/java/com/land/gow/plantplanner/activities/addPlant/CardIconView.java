package com.land.gow.plantplanner.activities.addPlant;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.land.gow.plantplanner.R;

/**
 * Created by becky on 2018-03-23.
 */
public class CardIconView extends RecyclerView.ViewHolder {

    private static final String LOG_TAG = CardIconView.class.getSimpleName();

    private Button iconButton;
    private View cardView;

    public CardIconView(View view) {
        super(view);
        cardView = view;
        iconButton = (Button) view.findViewById(R.id.button_icon_selector);
    }

    public Button getIconButton() {
        return iconButton;
    }

    public View getCardView() {
        return cardView;
    }

    public Object getViewById(int id){
        switch (id) {
            case R.id.button_change_icon:
                return iconButton;
            default:
                return null;
        }
    }
}
