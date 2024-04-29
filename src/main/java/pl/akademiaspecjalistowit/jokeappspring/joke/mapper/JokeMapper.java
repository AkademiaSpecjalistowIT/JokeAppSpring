package pl.akademiaspecjalistowit.jokeappspring.joke.mapper;

import org.springframework.stereotype.Component;
import pl.akademiaspecjalistowit.jokeappspring.joke.entity.JokeEntity;
import pl.akademiaspecjalistowit.jokeappspring.joke.model.Joke;

@Component
public class JokeMapper {

    public Joke fromEntity(JokeEntity jokeEntity) {
        return new Joke(jokeEntity.getTechnicalId(), jokeEntity.getContent(), jokeEntity.getCategory());
    }

    public JokeEntity toEntity(Joke joke) {
        return new JokeEntity(joke.getId(), joke.getContent(), joke.getCategory());
    }
}
