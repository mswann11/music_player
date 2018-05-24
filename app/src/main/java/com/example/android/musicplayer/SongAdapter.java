package com.example.android.musicplayer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {

    /**
     * @param context       Current context. Used to inflate the layout file.
     * @param songArrayList List of Song objects to display in a list.
     */
    public SongAdapter(Activity context, ArrayList<Song> songArrayList) {
        super(context, 0, songArrayList);
    }

    /**
     * @param position    Position in the list of data that should be displayed.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     * <p>
     * Set TextView to current song (title, artist, album) in list.
     * Set background color to color specified for artist category.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_list_item, parent, false);
        }

        Song currentSong = getItem(position);

        TextView songNameTextView = listItemView.findViewById(R.id.song_name_text_view);
        songNameTextView.setText(currentSong.getTitle());
        TextView artistTextView = listItemView.findViewById(R.id.artist_text_view);
        artistTextView.setText(currentSong.getArtist());
        ImageView albumIconImageView = listItemView.findViewById(R.id.album_icon);
        albumIconImageView.setImageResource(currentSong.getAlbumArt());
        RelativeLayout songLayout = listItemView.findViewById(R.id.list_item_layout);
        songLayout.setBackgroundColor(songLayout.getResources().getColor(R.color.songColor));
        return listItemView;
    }
}
