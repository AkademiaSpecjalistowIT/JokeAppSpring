package pl.akademiaspecjalistowit.jokeappspring.joke.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "JOKE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JokeEntity {

    public JokeEntity(UUID technicalId, String content, String category) {
        this.technicalId = technicalId;
        this.content = content;
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "tech_id")
    private UUID technicalId;

    private String content;
    private String category;

}
