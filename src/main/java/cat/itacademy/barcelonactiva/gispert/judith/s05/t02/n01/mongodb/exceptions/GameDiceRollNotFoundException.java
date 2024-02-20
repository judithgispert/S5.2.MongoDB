package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.exceptions;

public class GameDiceRollNotFoundException extends RuntimeException{
    public GameDiceRollNotFoundException (String errorMessage){
        super (errorMessage);
    }
}
