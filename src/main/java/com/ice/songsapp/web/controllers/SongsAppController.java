package com.ice.songsapp.web.controllers;

import com.ice.songsapp.db.dbos.SongCatalogueDbo;
import com.ice.songsapp.db.services.SongsAppDbServiceProvider;
import com.ice.songsapp.db.services.SongsAppDbService;
import com.ice.songsapp.web.dtos.AddSongParamsDto;
import com.ice.songsapp.web.exceptions.*;
import com.ice.songsapp.web.mappers.SongsCatalogueMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@Slf4j
public class SongsAppController {
    private final SongsAppDbServiceProvider songsAppDbServiceProvider;

    @GetMapping("/get-songs")
    @CrossOrigin(origins = "*")
    public Flux<SongCatalogueDbo> getSongs() {
        var songsAppDbService = getSongsAppDbService();
        return Flux.fromIterable(songsAppDbService.getAllSongs());
    }

    @PostMapping("/add-song")
    @CrossOrigin(origins = "*")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<SongCatalogueDbo> addSong(@Valid @RequestBody AddSongParamsDto addSongParamsDto) {
        var songsAppDbService = getSongsAppDbService();
        if (!songsAppDbService.findBySongName(addSongParamsDto.getSong()).isEmpty()) return songAlreadyAdded();
        var songCatalogueDbo = SongsCatalogueMapper.mapToSongsCatalogueDbo(addSongParamsDto);
        return Mono.just(songsAppDbService.addSong(songCatalogueDbo));
    }

    private <T> Mono<T> songAlreadyAdded() {
        return Mono.error(() -> new ApiException(HttpStatus.CONFLICT, "SONG_ALREADY_EXISTS"));
    }

    private SongsAppDbService getSongsAppDbService() {
        return songsAppDbServiceProvider.getSongsDbService();
    }

}
