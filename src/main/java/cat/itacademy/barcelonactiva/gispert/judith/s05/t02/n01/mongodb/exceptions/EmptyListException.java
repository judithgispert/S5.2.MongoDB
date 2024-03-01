package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.exceptions;

public class EmptyListException extends RuntimeException{
    public EmptyListException (String errorMessage){
        super (errorMessage);
    }
}
