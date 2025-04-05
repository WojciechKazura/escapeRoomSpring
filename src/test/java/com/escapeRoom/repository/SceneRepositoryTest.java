package com.escapeRoom.repository;

import com.escapeRoom.entity.Game;
import com.escapeRoom.entity.Scene;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SceneRepositoryTest {

    @Autowired
    private SceneRepository sceneRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ItemRepository itemRepository;  // Jeżeli testujesz powiązanie z przedmiotami


    @Test
    void shouldSetNextScenesCorrectly() {
        // Given
        Game game = new Game();
        game = gameRepository.save(game);  // Zapisujemy grę w bazie

        Scene scene1 = new Scene("Scene 1", "image1.png", new ArrayList<>());
        Scene scene2 = new Scene("Scene 2", "image2.png", new ArrayList<>());
        scene1.setGame(game);
        scene2.setGame(game);

        sceneRepository.save(scene1);
        sceneRepository.save(scene2);

        // When
        List<Scene> nextScenes = new ArrayList<>();
        nextScenes.add(scene2);  // Dodajemy scenę jako następna
        scene1.setNextScenes(nextScenes);  // Ustawiamy powiązanie między scenami

        sceneRepository.save(scene1);  // Zapisujemy zaktualizowaną scenę

        // Then
        assertEquals(1, scene1.getNextScenes().size());  // Powinna być jedna scena w nextScenes
        assertEquals(scene2.getId(), scene1.getNextScenes().get(0).getId());  // Powinna to być scena 2
    }
}