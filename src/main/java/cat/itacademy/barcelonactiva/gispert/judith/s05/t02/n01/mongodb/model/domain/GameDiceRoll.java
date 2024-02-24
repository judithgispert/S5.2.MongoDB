package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "games")
public class GameDiceRoll {
    @Id
    @JsonProperty("id")
    private String id;
    @JsonProperty("dice1")
    private int dice1;
    @JsonProperty("dice2")
    private int dice2;
    @JsonProperty("isWin")
    private boolean isWin;
    @JsonIgnoreProperties({"games"})
    private Player player;

    public GameDiceRoll(Player player){
        this.player = player;
    }
}
