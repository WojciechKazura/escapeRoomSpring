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
    private ItemRepository itemRepository;


    @Test
    void shouldSetNextScenesCorrectly() {
        Game game = new Game();
        game = gameRepository.save(game);

        Scene scene1 = new Scene("Scene 1", "image1.png", new ArrayList<>());
        Scene scene2 = new Scene("Scene 2", "image2.png", new ArrayList<>());
        scene1.setGame(game);
        scene2.setGame(game);

        sceneRepository.save(scene1);
        sceneRepository.save(scene2);

        List<Scene> nextScenes = new ArrayList<>();
        nextScenes.add(scene2);
        scene1.setNextScenes(nextScenes);

        sceneRepository.save(scene1);

        assertEquals(1, scene1.getNextScenes().size());
        assertEquals(scene2.getId(), scene1.getNextScenes().get(0).getId());
    }
}