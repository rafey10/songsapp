package com.ice.songsapp.db.services;

import com.ice.songsapp.db.dbos.SongCatalogueDbo;

import java.util.List;

public interface SongsAppDbService {
    /**
     * Returns all Songs in the DB.
     */
    List<SongCatalogueDbo> getAllSongs();
    /**
     * Returns the Song DBO found by song name.
     */
    List<SongCatalogueDbo> findBySongName(String songName);
    /**
     * Adds a song to the DB and returns the Song DBO
     */
    SongCatalogueDbo addSong(SongCatalogueDbo songCatalogueDbo);
}
