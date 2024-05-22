package com.ice.songsapp.db.services;

import com.ice.songsapp.db.repositories.SongsCatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SongsAppDbServiceProvider {
    private final SongsCatalogueRepository songsCatalogueRepository;

    @Autowired
    public SongsAppDbServiceProvider(SongsCatalogueRepository songsCatalogueRepository) {
        this.songsCatalogueRepository = songsCatalogueRepository;
    }

    @Bean
    public SongsAppDbService getSongsDbService() {
        return new CachedSongAppDbService(songsCatalogueRepository);
    }
}
