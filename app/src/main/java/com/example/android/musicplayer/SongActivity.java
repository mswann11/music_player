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

public class SongActivity extends AppCompatActivity {

    /**
     * Add menu to the app bar.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_menu, menu);
        menu.findItem(R.id.action_songs).setVisible(false);
        return true;
    }

    /**
     * Set intent for each option in the menu.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent mainIntent = new Intent(SongActivity.this, MainActivity.class);
                startActivity(mainIntent);
                return true;

            case R.id.action_artists:
                Intent artistsIntent = new Intent(SongActivity.this, ArtistActivity.class);
                startActivity(artistsIntent);
                return true;

            case R.id.action_genres:
                Intent genresIntent = new Intent(SongActivity.this, GenreActivity.class);
                startActivity(genresIntent);
                return true;

            case R.id.action_shuffle:
                Intent shuffleIntent = new Intent(SongActivity.this, ShuffleActivity.class);
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

        //Set my_toolbar as Action Bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Get info passed from previous activity to play correct category to filter
        Intent intent = getIntent();
        final String category = intent.getStringExtra("CATEGORY");
        final String specific = intent.getStringExtra("SPECIFIC");

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

        //Create new adapter with songs ArrayList
        SongAdapter adapter = new SongAdapter(this, songs);

        //Check for category and specific item to filter
        //Create adapter for genres or artists as necessary
        final ArrayList<Song> genreSongs = new ArrayList<>();
        final ArrayList<Song> artistSongs = new ArrayList<>();
        if (category != null && specific != null) {
            int size = songs.size();
            if (category.equals("genre")) {
                for (int i = 0; i < size; i++) {
                    if (songs.get(i).getGenre().equals(specific)) {
                        genreSongs.add(songs.get(i));
                    }
                }
                adapter = new SongAdapter(this, genreSongs);
            } else if (category.equals("artist")) {
                for (int i = 0; i < size; i++) {
                    if (songs.get(i).getArtist().equals(specific)) {
                        artistSongs.add(songs.get(i));
                    }
                }
                adapter = new SongAdapter(this, artistSongs);
            }
        }

        //Get ListView and set adapter to display songs
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        //Set click listener according to category if necessary
        //Set intent and pass ing artist, title, and album art selected
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (category == null) {
                    Song nowPlaying = songs.get(position);
                    Intent intent = new Intent(SongActivity.this, PlayActivity.class);
                    intent.putExtra("ARTIST", nowPlaying.getArtist());
                    intent.putExtra("TITLE", nowPlaying.getTitle());
                    int resID = nowPlaying.getAlbumArt();
                    intent.putExtra("ID", Integer.toString(resID));
                    startActivity(intent);
                } else if (category.equals("artist")) {
                    Song nowPlaying = artistSongs.get(position);
                    Intent intent = new Intent(SongActivity.this, PlayActivity.class);
                    intent.putExtra("ARTIST", nowPlaying.getArtist());
                    intent.putExtra("TITLE", nowPlaying.getTitle());
                    int resID = nowPlaying.getAlbumArt();
                    intent.putExtra("ID", Integer.toString(resID));
                    startActivity(intent);
                } else if (category.equals("genre")) {
                    Song nowPlaying = genreSongs.get(position);
                    Intent intent = new Intent(SongActivity.this, PlayActivity.class);
                    intent.putExtra("ARTIST", nowPlaying.getArtist());
                    intent.putExtra("TITLE", nowPlaying.getTitle());
                    int resID = nowPlaying.getAlbumArt();
                    intent.putExtra("ID", Integer.toString(resID));
                    startActivity(intent);
                }
            }
        });
    }
}

