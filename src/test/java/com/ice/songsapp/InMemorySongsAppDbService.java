package com.ice.songsapp;

import com.ice.songsapp.db.dbos.SongCatalogueDbo;
import com.ice.songsapp.db.services.SongsAppDbService;

import java.util.*;

public class InMemorySongsAppDbService implements SongsAppDbService {

    private final Map<String, SongCatalogueDbo> songDbos = new HashMap<>();

    @Override
    public List<SongCatalogueDbo> getAllSongs() {
        return new ArrayList<>(songDbos.values());
    }
    @Override
    public List<SongCatalogueDbo> findBySongName(String songName) {
        if (Objects.isNull(songDbos.get(songName))) return List.of();
        return List.of(songDbos.get(songName));
    }
    @Override
    public SongCatalogueDbo addSong(SongCatalogueDbo songCatalogueDbo) {
        songDbos.put(songCatalogueDbo.getSong(), songCatalogueDbo);
        return songCatalogueDbo;
    }
}
