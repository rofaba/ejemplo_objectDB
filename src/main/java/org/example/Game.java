package org.example;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String platform;
    private Integer year;
    private String image;

    @ManyToOne
    @JoinColumn(name = "duenio_id")

    @ToString.Exclude // ESTO EVITA EL BUCLE QUE LANZA EL ERROR DE STACKOVERFLOW

    private User duenio;

}
