package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.service.impl;

import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.exceptions.GameDiceRollNotFoundException;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.exceptions.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.exceptions.RepeatedValueException;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.domain.Player;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.dto.GameDiceRollDTO;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.service.GameDiceRollService;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameDiceRollService gameDiceRollService;

    @Override
    public PlayerDTO createPlayer() {
        return new PlayerDTO();
    }

    @Override
    public void addPlayer() {
        PlayerDTO playerDTO = createPlayer();
        if(playerDTO.getNameDTO() == null ||
                playerDTO.getNameDTO().isBlank()||
                playerDTO.getNameDTO().isEmpty()){
            playerDTO.setNameDTO("ANONYMOUS");
            Player player = playerDTOToPlayer(playerDTO);
            playerRepository.save(player);
        } else {
            Player player = playerDTOToPlayer(playerDTO);
            Optional<Player> playerName = playerRepository.findByName(player.getName());
            if(playerName.isPresent()){
                throw new RepeatedValueException("This name already exist.");
            } else {
                playerRepository.save(player);
            }
        }
    }

    @Override
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public PlayerDTO updatePlayer(PlayerDTO newPlayerDTO, String id) {
        Optional<Player> player = playerRepository.findById(id);
        if(player.isPresent()){
            Player updatedPlayer = player.get();
            if(newPlayerDTO.getNameDTO() == null||
                    newPlayerDTO.getNameDTO().isEmpty()||
                    newPlayerDTO.getNameDTO().isBlank()){
                updatedPlayer.setName("ANONYMOUS");
            } else {
                Optional<Player> playerName = playerRepository.findByName(newPlayerDTO.getNameDTO());
                if(playerName.isPresent()){
                    throw new RepeatedValueException("This name already exist.");
                } else {
                    updatedPlayer.setName(newPlayerDTO.getNameDTO());
                }
            }
            playerRepository.save(updatedPlayer);
            return playerToPlayerDTO(updatedPlayer);
        } else {
            throw new PlayerNotFoundException("The id: " + id + ", doesn't correspond to any player.");
        }
    }

    @Override
    public GameDiceRollDTO play(String id) {
        Optional<Player> playerSearch = playerRepository.findById(id);
        if(playerSearch.isPresent()){
            Player player = playerSearch.get();
            GameDiceRollDTO diceRollDTO = gameDiceRollService.addGame(player);
            updateResultGame(diceRollDTO, playerToPlayerDTO(player));
            return diceRollDTO;
        } else {
            throw new PlayerNotFoundException("The id: " + id + ", doesn't correspond to any player.");
        }
    }

    @Override
    public void updateResultGame(GameDiceRollDTO gameDiceRollDTO, PlayerDTO playerDTO) {
        if(gameDiceRollDTO.winGame()){
            playerDTO.setGamesWonDTO(playerDTO.getGamesWonDTO()+1);
            double resultPercentageWon = ((double) playerDTO.getGamesWonDTO() / playerDTO.getGamesDTO().size())*100;
            playerDTO.setPercentageWonDTO(resultPercentageWon);
            playerRepository.save(playerDTOToPlayer(playerDTO));
        } else {
            playerDTO.setGamesLostDTO(playerDTO.getGamesLostDTO()+1);
            double resultPercentageLost = (100 - playerDTO.getPercentageWonDTO());
            playerDTO.setPercentageLostDTO(resultPercentageLost);
            playerRepository.save(playerDTOToPlayer(playerDTO));
        }
    }


    @Override
    public List<GameDiceRollDTO> getGames(String id) {
        Optional<Player> playerSearch = playerRepository.findById(id);
        if(playerSearch.isPresent()){
            Player player = playerSearch.get();
            return gameDiceRollService.getPlayerGames(player);
        } else {
            throw new PlayerNotFoundException("The id: " + id + ", doesn't correspond to any player.");
        }
    }

    @Override
    public void deleteGames(String id) {
        Optional<Player> playerSearch = playerRepository.findById(id);
        if(playerSearch.isPresent()){
            Player player = playerSearch.get();
            gameDiceRollService.deleteGames(player);
            PlayerDTO playerDTO = restartPercentage(playerToPlayerDTO(player));
            playerRepository.save(playerDTOToPlayer(playerDTO));
        } else {
            throw new PlayerNotFoundException("The id: " + id + ", doesn't correspond to any player.");
        }
    }

    @Override
    public PlayerDTO restartPercentage(PlayerDTO playerDTO) {
        playerDTO.setPercentageLostDTO(0);
        playerDTO.setPercentageWonDTO(0);
        playerDTO.setGamesLostDTO(0);
        playerDTO.setGamesWonDTO(0);
        return playerDTO;
    }

    @Override
    public List<PlayerDTO> getRanking() {
        List<Player> players = playerRepository.findAll();
        List<PlayerDTO> playersRanking = new ArrayList<>();
        players.stream().toList().forEach(l -> playersRanking.add(playerToPlayerDTO(l)));
        playersRanking.sort(Comparator.comparing(PlayerDTO::getPercentageWonDTO));
        if(playersRanking.isEmpty()){
            throw new GameDiceRollNotFoundException("No games played.");
        }
        return playersRanking;
    }

    @Override
    public double getPercentageRanking() {
        List <Player> players = playerRepository.findAll();
        List<PlayerDTO> playersDTO = new ArrayList<>();
        players.stream().toList().forEach(l -> playersDTO.add(playerToPlayerDTO(l)));
        return (playersDTO.stream().mapToDouble(PlayerDTO::getPercentageWonDTO).sum())/players.size();
    }

    @Override
    public PlayerDTO getLoser() {
        List<PlayerDTO> rankingList = getRanking();
        return rankingList.get(rankingList.size()-1);
    }

    @Override
    public PlayerDTO getWinner() {
        List<PlayerDTO> rankingList = getRanking();
        return rankingList.get(0);
    }

    private Player playerDTOToPlayer(PlayerDTO playerDTO){
        Player player = new Player();
        player.setId(playerDTO.getIdDTO());
        player.setName(playerDTO.getNameDTO());
        player.setRegistrationDate(playerDTO.getRegistrationDateDTO());
        player.setGames(playerDTO.getGamesDTO());
        player.setGamesWon(playerDTO.getGamesWonDTO());
        player.setGamesLost(playerDTO.getGamesLostDTO());
        player.setPercentageWon(playerDTO.getPercentageWonDTO());
        player.setPercentageLost(playerDTO.getPercentageLostDTO());
        return player;
    }
    private PlayerDTO playerToPlayerDTO(Player player){
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setIdDTO(player.getId());
        playerDTO.setNameDTO(player.getName());
        playerDTO.setRegistrationDateDTO(player.getRegistrationDate());
        playerDTO.setGamesDTO(player.getGames());
        playerDTO.setGamesWonDTO(player.getGamesWon());
        playerDTO.setGamesLostDTO(player.getGamesLost());
        playerDTO.setPercentageWonDTO(player.getPercentageWon());
        playerDTO.setPercentageLostDTO(player.getPercentageLost());
        return playerDTO;
    }
}
