package com.example.android.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {
    /**
     * Add menu to the app bar.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    /**
     * Set intent for each option in the menu.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent mainIntent = new Intent(PlayActivity.this, MainActivity.class);
                startActivity(mainIntent);
                return true;

            case R.id.action_artists:
                Intent artistsIntent = new Intent(PlayActivity.this, ArtistActivity.class);
                startActivity(artistsIntent);
                return true;

            case R.id.action_genres:
                Intent genresIntent = new Intent(PlayActivity.this, GenreActivity.class);
                startActivity(genresIntent);
                return true;

            case R.id.action_songs:
                Intent songsIntent = new Intent(PlayActivity.this, SongActivity.class);
                startActivity(songsIntent);
                return true;

            case R.id.action_shuffle:
                Intent shuffleIntent = new Intent(PlayActivity.this, ShuffleActivity.class);
                startActivity(shuffleIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Set my_toolbar as Action Bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Get info passed from previous activity to display correct artist, song, and album art
        Intent intent = getIntent();
        final String artist = intent.getStringExtra("ARTIST");
        String title = intent.getStringExtra("TITLE");
        int resID = Integer.parseInt(intent.getStringExtra("ID"));

        //Set TextView in XML to display current artist
        TextView artistTextView = findViewById(R.id.playing_artist);
        artistTextView.setText(artist);

        //Set TextView in XML to display current title
        TextView titleTextView = findViewById(R.id.playing_title);
        titleTextView.setText(title);

        //Set ImageView in XML to display current album art
        ImageView albumImageView = findViewById(R.id.album_art);
        albumImageView.setImageResource(resID);

        //Set play button to switch between play and pause
        final ImageView playImageView = findViewById(R.id.play_button);
        playImageView.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;

            @Override
            public void onClick(View view) {
                if (isPlaying) {
                    playImageView.setImageResource(R.drawable.pause_button);
                    isPlaying = false;
                } else {
                    playImageView.setImageResource(R.drawable.play_button);
                    isPlaying = true;
                }
            }
        });

        //Set next button to start new Shuffle activity and pass in current artist
        final ImageView nextImageView = findViewById(R.id.next_button);
        nextImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shuffleIntent = new Intent(PlayActivity.this, ShuffleActivity.class);
                shuffleIntent.putExtra("ARTIST", artist);
                startActivity(shuffleIntent);
            }
        });
    }
}
