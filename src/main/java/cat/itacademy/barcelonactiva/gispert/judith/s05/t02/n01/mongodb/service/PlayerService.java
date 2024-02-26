package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.service;

import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.domain.GameDiceRoll;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.domain.Player;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.dto.GameDiceRollDTO;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {
    PlayerDTO createPlayer();
    void addPlayer();
    List<Player> getPlayers();
    PlayerDTO updatePlayer(PlayerDTO newPlayerDTO, String id);

    GameDiceRollDTO play (String id);
    void updateResultGame(GameDiceRollDTO gameDiceRollDTO, PlayerDTO playerDTO);
    List<GameDiceRoll> getGames(String id);
    void deleteGames(String id);
    PlayerDTO restartPercentage(PlayerDTO playerDTO);
    List<PlayerDTO> getRanking();
    double getPercentageRanking();
    PlayerDTO getLoser();
    PlayerDTO getWinner();

}
