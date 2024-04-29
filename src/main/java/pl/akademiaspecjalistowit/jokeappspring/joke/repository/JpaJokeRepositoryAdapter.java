package pl.akademiaspecjalistowit.jokeappspring.joke.repository;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.jokeappspring.joke.entity.JokeEntity;
import pl.akademiaspecjalistowit.jokeappspring.joke.mapper.JokeMapper;
import pl.akademiaspecjalistowit.jokeappspring.joke.model.Joke;

@Repository
@AllArgsConstructor
public class JpaJokeRepositoryAdapter implements JokeRepository, JokeSupplierRepository {

    private final JokeJpaRepository jokeJpaRepository;
    private final JokeMapper jokeMapper;

    @Override
    public List<Joke> getAllJokes() {
        return jokeJpaRepository.findAll()
            .stream()
            .map(jokeMapper::fromEntity)
            .toList();
    }

    @Override
    public List<Joke> getAllByCategory(String category) {
        return jokeJpaRepository.getJokeEntitiesByCategory(category)
            .stream()
            .map(jokeMapper::fromEntity)
            .toList();
    }

    @Override
    public void addJoke(Joke joke) {
        JokeEntity jokeEntity = jokeMapper.toEntity(joke);
        jokeJpaRepository.save(jokeEntity);
    }
}
