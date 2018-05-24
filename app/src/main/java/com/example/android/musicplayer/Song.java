package com.example.android.musicplayer;

public class Song {
    private String mTitle;
    private String mArtist;
    private String mGenre;
    private int mAlbumArtResId;

    /**
     * Song constructor.
     * @param title Title of song.
     * @param artist Artist of song.
     * @param genre Genre of song.
     * @param albumArtResId Art for album of song.
     */
    public Song(String title, String artist, String genre, int albumArtResId){
        mTitle = title;
        mArtist = artist;
        mGenre = genre;
        mAlbumArtResId = albumArtResId;
    }

    /**
     * @return Song title.
     */
    public String getTitle(){
        return mTitle;
    }

    /**
     * @return Song artist.
     */
    public String getArtist(){
        return mArtist;
    }

    /**
     * @return Song genre.
     */
    public String getGenre(){
        return mGenre;
    }

    /**
     * @return Song album art.
     */
    public int getAlbumArt(){
        return mAlbumArtResId;
    }
}
