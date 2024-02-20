package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private String id;
    private String name;
    private LocalDateTime registrationDate = LocalDateTime.now();

    private int gamesWon;
    private double percentageWon;
    private int gamesLost;
    private double percentageLost;
}
