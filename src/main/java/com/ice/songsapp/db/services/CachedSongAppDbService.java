package com.ice.songsapp.db.services;

import com.ice.songsapp.db.dbos.SongCatalogueDbo;
import com.ice.songsapp.db.repositories.SongsCatalogueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CachedSongAppDbService implements SongsAppDbService {
    private final SongsCatalogueRepository songsCatalogueRepository;
    private List<SongCatalogueDbo> songs;

    public CachedSongAppDbService(SongsCatalogueRepository songsCatalogueRepository){
        this.songsCatalogueRepository = songsCatalogueRepository;
    }

    @Override
    public List<SongCatalogueDbo> getAllSongs() {
        if (Objects.isNull(songs)) songs = songsCatalogueRepository.findAll();
        return songs;
    }

    @Override
    public List<SongCatalogueDbo> findBySongName(String songName) {
        return songsCatalogueRepository.findBySong(songName);
    }

    @Override
    public SongCatalogueDbo addSong(SongCatalogueDbo songCatalogueDbo) {
        return songsCatalogueRepository.save(songCatalogueDbo);
    }
}
