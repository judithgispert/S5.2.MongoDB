package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.exceptions;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException (String errorMessage){
        super (errorMessage);
    }
}
