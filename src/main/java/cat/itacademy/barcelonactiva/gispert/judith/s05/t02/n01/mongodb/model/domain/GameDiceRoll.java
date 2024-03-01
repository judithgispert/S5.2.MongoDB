package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.domain;

import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.methods.DiceRandomNum;
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

    public GameDiceRoll(){
        this.dice1 = DiceRandomNum.randomNum();
        this.dice2 = DiceRandomNum.randomNum();
    }


}
