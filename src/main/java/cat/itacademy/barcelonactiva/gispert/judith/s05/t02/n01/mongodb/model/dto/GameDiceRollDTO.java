package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.dto;

import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.methods.DiceRandomNum;
import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.domain.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDiceRollDTO {
    private String id;
    private int dice1;
    private int dice2;
    private boolean isWin;
    private Player player;

    public GameDiceRollDTO(Player player){
        this.dice1 = DiceRandomNum.randomNum();
        this.dice2 = DiceRandomNum.randomNum();
        this.isWin = winGame();
        this.player = player;
    }

    public boolean winGame(){
        boolean win;
        if(dice1 + dice2==7){
            win = true;
        } else {
            win = false;
        }
        return win;
    }
}
