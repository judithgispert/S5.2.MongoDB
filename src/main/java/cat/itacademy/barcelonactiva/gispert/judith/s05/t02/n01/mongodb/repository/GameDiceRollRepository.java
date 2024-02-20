package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.repository;

import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.domain.GameDiceRoll;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameDiceRollRepository extends MongoRepository<GameDiceRoll, String> {
}
