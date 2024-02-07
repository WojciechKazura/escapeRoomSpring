package com.escapeRoom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EscapeRoomApplication {
	public static void main(String[] args) {
		SpringApplication.run(EscapeRoomApplication.class, args);
	}

}

/*
* Setup - PostConstruct w GameService - na rozruch
* rozpoczęcie gry = stworzenie playera: POST: players (dostajemy w odpowiedzi - nasze id plyera i id room. Tworzy się dla nas room i wszystkie przedmioty - caly setup
* nasze opcje: GET: rooms/id/items (id room)- jakie mamy opcje //todo po id playera
* robimy akcje: POST: actions (id przedmiotu, id playera) -
*
*
* //PlayerDTO musi mieć: id playera, id roomu
* */
