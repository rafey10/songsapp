package com.ice.songsapp.db.dbos;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.Year;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "songs_catalogue")
public class SongCatalogueDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "song")
    String song;

    @Column(name = "artist")
    String artist;

    @Column(name = "album")
    String album;

    @Column(name = "release-year")
    Year releaseYear;

    @Column(name = "length")
    Time length;

    @Column(name = "genre")
    String genre;

}

