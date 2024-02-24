package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.dto;

import cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.domain.GameDiceRoll;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private String idDTO;
    private String nameDTO;
    private LocalDateTime registrationDateDTO = LocalDateTime.now();
    private List<GameDiceRoll> gamesDTO = new ArrayList<>();

    private int gamesWonDTO;
    private double percentageWonDTO;
    private int gamesLostDTO;
    private double percentageLostDTO;
}
