package com.ice.songsapp.web.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;
import jakarta.validation.Valid;

@Value
@Builder
public class AddSongParamsDto {

    @NotEmpty
    @Valid
    String song;

    @NotEmpty
    @Valid
    String artist;

    @NotEmpty
    @Valid
    String album;

    @NotEmpty
    @Valid
    String releaseYear;

    @NotEmpty
    @Valid
    String length;

    @NotEmpty
    @Valid
    String genre;
}
