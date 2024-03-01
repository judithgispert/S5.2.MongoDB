package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.service;

import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.domain.Player;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.dto.GameDiceRollDTO;

import java.util.List;

public interface GameDiceRollService {
    GameDiceRollDTO createGame();
    GameDiceRollDTO addGame(Player player);
    List<GameDiceRollDTO> getPlayerGames(Player player);
    void deleteGames(Player player);

}
