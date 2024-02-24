package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.repository;

import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    Optional<Player> findByName(String name);
}
