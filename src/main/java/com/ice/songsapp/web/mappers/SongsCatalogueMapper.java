package com.ice.songsapp.web.mappers;

import com.ice.songsapp.db.dbos.SongCatalogueDbo;
import com.ice.songsapp.web.dtos.AddSongParamsDto;

import java.sql.Time;
import java.time.Year;

public class SongsCatalogueMapper {
    private SongsCatalogueMapper() {
            throw new IllegalStateException("Utility class");
        }

    public static SongCatalogueDbo mapToSongsCatalogueDbo(AddSongParamsDto addSongParamsDto){
        return SongCatalogueDbo.builder()
                .song(addSongParamsDto.getSong())
                .album(addSongParamsDto.getAlbum())
                .artist(addSongParamsDto.getArtist())
                .length(Time.valueOf(addSongParamsDto.getLength()))
                .releaseYear(Year.parse(addSongParamsDto.getReleaseYear()))
                .genre(addSongParamsDto.getGenre())
                .build();
    }
}
