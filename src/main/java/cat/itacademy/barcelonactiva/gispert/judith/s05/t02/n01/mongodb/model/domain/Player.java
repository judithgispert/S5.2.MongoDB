package cat.itacademy.barcelonactiva.gispert.judith.s05.t02.n01.mongodb.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "players")
public class Player {
    @Id
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("registration_date")
    private LocalDateTime registrationDate;

    @JsonProperty("games won")
    private int gamesWon;
    @JsonProperty("% won")
    private double percentageWon;
    @JsonProperty("games lost")
    private int gamesLost;
    @JsonProperty("% lost")
    private double percentageLost;
}
