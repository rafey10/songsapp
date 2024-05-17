package com.ice.songsapp.web.controllers;

import com.ice.songsapp.db.dbos.SongCatalogueDbo;
import com.ice.songsapp.db.repositories.SongsCatalogueRepository;
import com.ice.songsapp.web.dtos.AddSongParamsDto;
import com.ice.songsapp.web.exceptions.*;
import com.ice.songsapp.web.mappers.SongsCatalogueMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@Slf4j
public class SongsAppController {

    SongsCatalogueRepository songsCatalogueRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(SongsAppController.class);

    @GetMapping("/get-songs")
    @CrossOrigin(origins = "*")
    public Flux<SongCatalogueDbo> getSongs() {
        return Flux.fromIterable(songsCatalogueRepository.findAll());
    }

    @PostMapping("/add-song")
    @CrossOrigin(origins = "*")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<SongCatalogueDbo> addSong(@Valid @RequestBody AddSongParamsDto addSongParamsDto) {
        if (!songsCatalogueRepository.findBySong(addSongParamsDto.getSong()).isEmpty()) return songAlreadyAdded();
        var songCatalogueDbo = SongsCatalogueMapper.mapToSongsCatalogueDbo(addSongParamsDto);
        return Mono.just(songsCatalogueRepository.save(songCatalogueDbo));
    }

    private <T> Mono<T> songAlreadyAdded() {
        return Mono.error(() -> new ApiException(HttpStatus.CONFLICT, "SONG_ALREADY_EXISTS"));
    }

}
