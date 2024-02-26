package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.service.impl;

import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.domain.GameDiceRoll;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.domain.Player;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.dto.GameDiceRollDTO;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.repository.GameDiceRollRepository;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.service.GameDiceRollService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameDiceRollServiceImpl implements GameDiceRollService{
    @Autowired
    private GameDiceRollRepository gameDiceRollRepository;

    @Override
    public GameDiceRollDTO createGame(Player player) {
        GameDiceRoll gameDiceRoll = new GameDiceRoll(player);
        return gameDiceRollToDTO(gameDiceRoll, player);
    }

    @Override
    public GameDiceRollDTO addGame(Player player) {
        GameDiceRollDTO gameDiceRollDTO = createGame(player);
        GameDiceRoll gameDiceRoll = gameDTOToDiceRoll(gameDiceRollDTO, player);
        gameDiceRollRepository.save(gameDiceRoll);
        player.getGames().add(gameDiceRoll);
        return gameDiceRollToDTO(gameDiceRoll, player);
    }

    @Override
    public List<GameDiceRollDTO> getPlayerGames(Player player) {
        List<GameDiceRoll> gamesPlayer = gameDiceRollRepository.findByPlayer(player);
        List<GameDiceRollDTO> gamesDTOPlayer = new ArrayList<>();
        gamesPlayer.stream().toList().forEach(l -> gamesDTOPlayer.add(gameDiceRollToDTO(l, player)));
        return gamesDTOPlayer;
    }

    @Override
    public void deleteGames(Player player) {
        List<GameDiceRoll> games = gameDiceRollRepository.findByPlayer(player);
        if(games!=null){
            games.forEach(l -> gameDiceRollRepository.delete(l));
        }
    }

    private static GameDiceRoll gameDTOToDiceRoll(GameDiceRollDTO gameDiceRollDTO, Player player){
        GameDiceRoll gameDiceRoll = new GameDiceRoll(player);
        gameDiceRoll.setId(gameDiceRollDTO.getIdDTO());
        gameDiceRoll.setDice1(gameDiceRollDTO.getDice1DTO());
        gameDiceRoll.setDice2(gameDiceRollDTO.getDice2DTO());
        return gameDiceRoll;
    }
    private static GameDiceRollDTO gameDiceRollToDTO(GameDiceRoll gameDiceRoll, Player player){
        GameDiceRollDTO gameDiceRollDTO = new GameDiceRollDTO(player);
        gameDiceRollDTO.setIdDTO(gameDiceRoll.getId());
        gameDiceRollDTO.setDice1DTO(gameDiceRoll.getDice1());
        gameDiceRollDTO.setDice2DTO(gameDiceRoll.getDice2());
        gameDiceRollDTO.setWinDTO(gameDiceRollDTO.isWinDTO());
        return gameDiceRollDTO;
    }
}
