package com.example.android.musicplayer;

import android.app.Activity;
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

import java.util.ArrayList;

public class ShuffleActivity extends AppCompatActivity {

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
                Intent mainIntent = new Intent(ShuffleActivity.this, MainActivity.class);
                startActivity(mainIntent);
                return true;

            case R.id.action_artists:
                Intent artistsIntent = new Intent(ShuffleActivity.this, ArtistActivity.class);
                startActivity(artistsIntent);
                return true;

            case R.id.action_genres:
                Intent genresIntent = new Intent(ShuffleActivity.this, GenreActivity.class);
                startActivity(genresIntent);
                return true;

            case R.id.action_songs:
                Intent songsIntent = new Intent(ShuffleActivity.this, SongActivity.class);
                startActivity(songsIntent);
                return true;

            case R.id.action_shuffle:
                Intent shuffleIntent = new Intent(ShuffleActivity.this, ShuffleActivity.class);
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

        //Get info passed from previous activity to play correct artist
        Intent intent = getIntent();
        String artist = intent.getStringExtra("ARTIST");

        //Create new ArrayList and add all Song objects
        final ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Left Hand Free", "alt-J", "Indie/Alternative Rock", R.drawable.altj_this_is_all_yours));
        songs.add(new Song("bodyache", "Purity Ring", "Electronic/Pop", R.drawable.purity_ring_another_eternity));
        songs.add(new Song("Cleopatra", "Lumineers", "Indie/Alternative Rock", R.drawable.lumineers_cleopatra));
        songs.add(new Song("Miracle", "CHVRCHES", "Electronic/Pop", R.drawable.chvrches_miracle));
        songs.add(new Song("In Cold Blood", "alt-J", "Indie/Alternative Rock", R.drawable.altj_relaxer));
        songs.add(new Song("Sleep on the Floor", "Lumineers", "Indie/Alternative Rock", R.drawable.lumineers_cleopatra));
        songs.add(new Song("Fitzpleasure", "alt-J", "Indie/Alternative Rock", R.drawable.altj_an_awesome_wave));
        songs.add(new Song("Never Ending Circles", "CHVRCHES", "Electronic/Pop", R.drawable.chvrches_every_open_eye));
        songs.add(new Song("begin again", "Purity Ring", "Electronic/Pop", R.drawable.purity_ring_another_eternity));
        songs.add(new Song("Asido", "Purity Ring", "Electronic/Pop", R.drawable.purityring_asido));
        songs.add(new Song("Stubborn Love", "Lumineers", "Indie/Alternative Rock", R.drawable.lumineers_album));
        songs.add(new Song("Tether", "CHVRCHES", "Electronic/Pop", R.drawable.chvrches_bones));

        Song randomSong;
        //Check to see if artist passed in from previous activity
        //Get random song to display
        if (artist != null) {
            final ArrayList<Song> artistSongs = new ArrayList<>();
            for (int i = 0; i < songs.size(); i++) {
                if (songs.get(i).getArtist().equals(artist)) {
                    artistSongs.add(songs.get(i));
                }
            }
            int random = (int) Math.floor(Math.random() * artistSongs.size());
            randomSong = artistSongs.get(random);
        } else {
            int random = (int) Math.floor(Math.random() * songs.size());
            randomSong = songs.get(random);
        }

        //Set TextView in XML to display current artist
        TextView artistTextView = findViewById(R.id.playing_artist);
        final String playingArtist = randomSong.getArtist();
        artistTextView.setText(playingArtist);

        //Set TextView in XML to display current title
        TextView titleTextView = findViewById(R.id.playing_title);
        final String playingTitle = randomSong.getTitle();
        titleTextView.setText(playingTitle);

        //Set ImageView in XML to display current album art
        ImageView albumImageView = findViewById(R.id.album_art);
        final int playingID = randomSong.getAlbumArt();
        albumImageView.setImageResource(playingID);

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

        //Set next button to start new activity and pass in current artist
        if (artist != null) {
            final ImageView nextImageView = findViewById(R.id.next_button);
            nextImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent playIntent = new Intent(ShuffleActivity.this, PlayActivity.class);
                    playIntent.putExtra("ARTIST", playingArtist);
                    playIntent.putExtra("TITLE", playingTitle);
                    playIntent.putExtra("ID", Integer.toString(playingID));
                    startActivity(playIntent);
                }
            });
        } else {
            final ImageView nextImageView = findViewById(R.id.next_button);
            nextImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent shuffleIntent = new Intent(ShuffleActivity.this, ShuffleActivity.class);
                    startActivity(shuffleIntent);
                }
            });
        }
    }
}
