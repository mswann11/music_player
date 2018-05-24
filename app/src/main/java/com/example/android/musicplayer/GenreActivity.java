package com.example.android.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class GenreActivity extends AppCompatActivity {

    /**
     * Add menu to the app bar and set the Genres option to be not visible.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_menu, menu);
        menu.findItem(R.id.action_genres).setVisible(false);
        return true;
    }

    /**
     * Set intent for each option in the menu.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent mainIntent = new Intent(GenreActivity.this, MainActivity.class);
                startActivity(mainIntent);
                return true;

            case R.id.action_artists:
                Intent artistsIntent = new Intent(GenreActivity.this, ArtistActivity.class);
                startActivity(artistsIntent);
                return true;

            case R.id.action_songs:
                Intent songsIntent = new Intent(GenreActivity.this, SongActivity.class);
                startActivity(songsIntent);
                return true;

            case R.id.action_shuffle:
                Intent shuffleIntent = new Intent(GenreActivity.this, ShuffleActivity.class);
                startActivity(shuffleIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Create new ArrayList and add all genres
        final ArrayList<String> genres = new ArrayList<>();
        genres.add("Indie/Alternative Rock");
        genres.add("Electronic/Pop");

        //Create new adapter with genres ArrayList
        //Get ListView from XML and set adapter to list all genres
        GenreAdapter adapter = new GenreAdapter(this, genres);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        //Set click listener on ListView for each item in the list
        //Set intent to open SongActivity and pass in category and genre to filter
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String category = "genre";
                String genre = genres.get(position);
                Intent intent = new Intent(GenreActivity.this, SongActivity.class);
                intent.putExtra("CATEGORY", category);
                intent.putExtra("SPECIFIC", genre);
                startActivity(intent);
            }
        });
    }
}

