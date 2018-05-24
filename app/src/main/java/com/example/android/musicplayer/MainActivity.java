package com.example.android.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    /**
     * Add menu to the app bar and set the Home option to be not visible.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_menu, menu);
        menu.findItem(R.id.action_home).setVisible(false);
        return true;
    }

    /**
     * Set intent for each option in the menu.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_artists:
                Intent artistsIntent = new Intent(MainActivity.this, ArtistActivity.class);
                startActivity(artistsIntent);
                return true;

            case R.id.action_genres:
                Intent genresIntent = new Intent(MainActivity.this, GenreActivity.class);
                startActivity(genresIntent);
                return true;

            case R.id.action_songs:
                Intent songsIntent = new Intent(MainActivity.this, SongActivity.class);
                startActivity(songsIntent);
                return true;

            case R.id.action_shuffle:
                Intent shuffleIntent = new Intent(MainActivity.this, ShuffleActivity.class);
                startActivity(shuffleIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set my_toolbar as Action Bar with title as Home
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Home");

        //Set intent for Genres button
        RelativeLayout genresLayout = findViewById(R.id.genres);
        genresLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GenreActivity.class);
                startActivity(intent);
            }
        });

        //Set intent for Artists button
        RelativeLayout artistsLayout = findViewById(R.id.artists);
        artistsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ArtistActivity.class);
                startActivity(intent);
            }
        });

        //Set intent for Songs button
        RelativeLayout songsLayout = findViewById(R.id.songs);
        songsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SongActivity.class);
                startActivity(intent);
            }
        });

        //Set intent for Shuffle button
        RelativeLayout shuffleLayout = findViewById(R.id.shuffle);
        shuffleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShuffleActivity.class);
                startActivity(intent);
            }
        });

    }
}
