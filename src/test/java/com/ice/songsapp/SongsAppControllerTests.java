package com.ice.songsapp;

import com.ice.songsapp.db.dbos.SongCatalogueDbo;
import com.ice.songsapp.db.services.SongsAppDbService;
import com.ice.songsapp.db.services.SongsAppDbServiceProvider;
import com.ice.songsapp.web.controllers.SongsAppController;
import com.ice.songsapp.web.dtos.AddSongParamsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.sql.Time;
import java.time.Year;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@WebFluxTest(SongsAppController.class)
class SongsAppControllerTests {
	@Autowired
	WebTestClient webTestClient;
	@MockBean
	SongsAppDbServiceProvider songsAppDbServiceProvider;

	private SongsAppDbService songsAppDbService;

	@BeforeEach
	void setUp() {
		songsAppDbService = new InMemorySongsAppDbService();
		given(songsAppDbServiceProvider.getSongsDbService()).willReturn(songsAppDbService);
	}

	@Test
	void givenCallToGetSongsEndpoint_whenGetAllSongs_thenReturnAllSongs(){
		var song1 = songCatalogueDboBuilder().id(1).song("Song1").build();
		var song2 = songCatalogueDboBuilder().id(2).song("Song2").build();
		var song3 = songCatalogueDboBuilder().id(3).song("Song3").build();

		var expected = List.of(song1, song2, song3);

		var firstCallResult = webTestClient.get().uri(uriBuilder -> uriBuilder
						.path("/get-songs")
						.build())
						.accept(MediaType.APPLICATION_JSON)
						.exchange();
		firstCallResult.expectStatus().isOk().expectBodyList(SongCatalogueDbo.class).hasSize(0);

		songsAppDbService.addSong(song1);
		songsAppDbService.addSong(song2);
		songsAppDbService.addSong(song3);

		var secondCallResult = webTestClient.get().uri(uriBuilder -> uriBuilder
				.path("/get-songs")
				.build())
				.accept(MediaType.APPLICATION_JSON)
				.exchange();
		//assert
		secondCallResult
				.expectStatus()
				.isOk()
				.expectBodyList(SongCatalogueDbo.class)
				.returnResult()
				.getResponseBody()
				.containsAll(expected);
	}

	@Test
	void givenValidSubmitSongParams_whenCallingAddSong_thenReturnAddedSong(){
		var addSongParamsDto = addSongParamsDtoBuilder().song("MySong").build();
		var expectedSong = songCatalogueDboBuilder().song("MySong").build();
		var songsAppDbService = Mockito.mock(SongsAppDbService.class);
		when(songsAppDbService.findBySongName("MySong")).thenReturn(List.of());
		when(songsAppDbService.addSong(expectedSong)).thenReturn(expectedSong);
		webTestClient.post()
				.uri("/add-song")
				.bodyValue(addSongParamsDto)
				.exchange()
				.expectStatus()
				.isAccepted()
				.expectBody(SongCatalogueDbo.class)
				.returnResult().getResponseBody().equals(expectedSong);
	}

	@Test
	void givenInValidSubmitSongParams_whenCallingAddSong_thenReturnAddedSong(){
		var addSongParamsDto = addSongParamsDtoBuilder().song("").build();
		webTestClient.post()
				.uri("/add-song")
				.bodyValue(addSongParamsDto)
				.exchange()
				.expectStatus()
				.isBadRequest();
	}

	private SongCatalogueDbo.SongCatalogueDboBuilder songCatalogueDboBuilder (){
		return SongCatalogueDbo.builder()
				.id(1)
				.song("song")
				.artist("artist")
				.album("album")
				.genre("genre")
				.length(Time.valueOf("00:02:40"))
				.releaseYear(Year.parse("1967"));
	}

	private AddSongParamsDto.AddSongParamsDtoBuilder addSongParamsDtoBuilder() {
		return AddSongParamsDto.builder()
				.song("SongToAdd")
				.artist("artist")
				.album("album")
				.genre("genre")
				.length("00:02:40")
				.releaseYear("1967");
	}
}
