package com.ice.songsapp.db.repositories;

import com.ice.songsapp.db.dbos.SongCatalogueDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongsCatalogueRepository extends JpaRepository<SongCatalogueDbo, String> {
    List<SongCatalogueDbo> findAll();
    List<SongCatalogueDbo> findBySong(String song);
}
