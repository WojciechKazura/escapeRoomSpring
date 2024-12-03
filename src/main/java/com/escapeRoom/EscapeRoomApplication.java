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


/*
*  stara apka:
* Scene -> Lokalizacja w której jest gracz. Może to być pokój lub miejsce w pokoju.
* Jak nazwać scenę będącą fragmentem pokoju?
*
 * Player -> Scene -> Items
 * Action -> Player + Item
 *
 * Nowa wersja:
 * Game -> Player + Rooms + Current Room
 * Room -> Items + Rooms
 * Player -> Items
 * ActionDto -> GameId + ItemId
 * */

/*
* Odpalić starą wersję aplikacji
* Ogarnąć gita Frontu
* Dodać klasę Gry i dostosować działanie żeby Player nie miał rooma tylko gra wskazywała na aktualnego rooma i playera
* Scena może prowadzić do innych scen
* Algorym ścieżek
* Funkcja podglądu mapy w JS
* Rozbudowa mapy tak żeby nie było widać miejsc gdzie nie bylismy
* */