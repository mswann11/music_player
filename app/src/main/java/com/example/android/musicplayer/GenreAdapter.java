package com.example.android.musicplayer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GenreAdapter extends ArrayAdapter<String> {

    /**
     * @param context        Current context. Used to inflate the layout file.
     * @param genreArrayList List of genres to display in a list.
     */
    public GenreAdapter(Activity context, ArrayList<String> genreArrayList) {
        super(context, 0, genreArrayList);
    }

    /**
     * @param position    Position in the list of data that should be displayed.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     * <p>
     * Set TextView to current genre in list.
     * Set background color to color specified for genre category.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        String currentGenre = getItem(position);

        TextView genreTextView = listItemView.findViewById(R.id.item_text_view);
        genreTextView.setText(currentGenre);
        RelativeLayout genreLayout = listItemView.findViewById(R.id.list_item_layout);
        genreLayout.setBackgroundColor(genreLayout.getResources().getColor(R.color.genreColor));
        return listItemView;
    }
}
