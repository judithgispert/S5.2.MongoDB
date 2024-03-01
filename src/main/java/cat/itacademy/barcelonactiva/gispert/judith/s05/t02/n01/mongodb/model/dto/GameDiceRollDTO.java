package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.dto;

import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.methods.DiceRandomNum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDiceRollDTO {
    private String idDTO;
    private int dice1DTO;
    private int dice2DTO;
    private boolean isWinDTO;

    public GameDiceRollDTO(){
        this.dice1DTO = DiceRandomNum.randomNum();
        this.dice2DTO = DiceRandomNum.randomNum();
        this.isWinDTO = winGame();
    }

    public boolean winGame(){
        boolean win;
        if(dice1DTO + dice2DTO == 7){
            win = true;
        } else {
            win = false;
        }
        return win;
    }
}
