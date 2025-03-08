package com.escapeRoom.controller;

import com.escapeRoom.dto.WeightForScaleDto;
import com.escapeRoom.entity.*;
import com.escapeRoom.repository.GameRepository;
import com.escapeRoom.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/weight")
@CrossOrigin()
public class WeightController {

    private GameRepository gameRepository;
    private GameService gameService;

    @PostMapping
    public ResponseEntity<String> placeWeight(@RequestBody WeightForScaleDto weightForScaleDto) {
        // Znalezienie gry na podstawie ID
        Game game = gameRepository.findById(weightForScaleDto.getGameId()).orElseThrow();
        Scene scene = game.getActiveScene();

        // Znalezienie mechanizmu wagi w aktualnym pokoju
        WeightMechanism weightMechanism = scene.getItemList().stream()
                .filter(item -> item instanceof WeightMechanism)
                .map(item -> (WeightMechanism) item)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Brak mechanizmu wagi w tej scenie."));

        // Znalezienie ciężarka w ekwipunku gracza
        WeightForScale weight = game.getPlayer().getItemList().stream()
                .filter(item -> item instanceof WeightForScale && item.getId() == weightForScaleDto.getItemId())
                .map(item -> (WeightForScale) item)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nie znaleziono ciężarka."));

        // Wykonanie akcji: położenie ciężarka na wadze
        String result = weight.use(new Context(game), weightForScaleDto.getSide());

        // Zapisanie stanu gry po akcji
        gameRepository.save(game);

        // Zwrócenie odpowiedzi
        return ResponseEntity.ok(result);
    }
}
