package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.service.impl;

import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.exceptions.EmptyListException;
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
    public GameDiceRollDTO createGame() {
        GameDiceRoll gameDiceRoll = new GameDiceRoll();
        return gameDiceRollToDTO(gameDiceRoll);
    }

    @Override
    public GameDiceRollDTO addGame(Player player) {
        GameDiceRollDTO gameDiceRollDTO = createGame();
        GameDiceRoll gameDiceRoll = gameDTOToDiceRoll(gameDiceRollDTO);
        gameDiceRollRepository.save(gameDiceRoll);
        player.getGames().add(gameDiceRoll);
        return gameDiceRollToDTO(gameDiceRoll);
    }

    @Override
    public List<GameDiceRollDTO> getPlayerGames(Player player) {
        List<GameDiceRollDTO> gamesDTOPlayer = new ArrayList<>();
        player.getGames().stream().toList().forEach(l -> gamesDTOPlayer.add(gameDiceRollToDTO(l)));
        return gamesDTOPlayer;
    }

    @Override
    public void deleteGames(Player player) {
        if(player.getGames().isEmpty()){
            throw new EmptyListException("This player doesn't play.");
        } else {
            List<GameDiceRoll> games = new ArrayList<>(player.getGames().stream().toList());
            games.forEach(l -> gameDiceRollRepository.delete(l));
        }
    }

    private static GameDiceRoll gameDTOToDiceRoll(GameDiceRollDTO gameDiceRollDTO){
        GameDiceRoll gameDiceRoll = new GameDiceRoll();
        gameDiceRoll.setId(gameDiceRollDTO.getIdDTO());
        gameDiceRoll.setDice1(gameDiceRollDTO.getDice1DTO());
        gameDiceRoll.setDice2(gameDiceRollDTO.getDice2DTO());
        gameDiceRoll.setWin(gameDiceRollDTO.isWinDTO());
        return gameDiceRoll;
    }
    private static GameDiceRollDTO gameDiceRollToDTO(GameDiceRoll gameDiceRoll){
        GameDiceRollDTO gameDiceRollDTO = new GameDiceRollDTO();
        gameDiceRollDTO.setIdDTO(gameDiceRoll.getId());
        gameDiceRollDTO.setDice1DTO(gameDiceRoll.getDice1());
        gameDiceRollDTO.setDice2DTO(gameDiceRoll.getDice2());
        gameDiceRollDTO.setWinDTO(gameDiceRollDTO.winGame());
        return gameDiceRollDTO;
    }
}
