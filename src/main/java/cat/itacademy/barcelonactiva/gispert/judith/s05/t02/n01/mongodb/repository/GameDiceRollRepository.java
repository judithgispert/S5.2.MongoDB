package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.repository;

import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.domain.GameDiceRoll;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameDiceRollRepository extends MongoRepository<GameDiceRoll, String> {
    List<GameDiceRoll> findByPlayer(Player player);
}
