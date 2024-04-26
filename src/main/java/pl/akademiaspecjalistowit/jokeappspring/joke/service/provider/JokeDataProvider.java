package pl.akademiaspecjalistowit.jokeappspring.joke.service.provider;

import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.jokeappspring.joke.entity.JokeEntity;
import pl.akademiaspecjalistowit.jokeappspring.joke.mapper.JokeMapper;
import pl.akademiaspecjalistowit.jokeappspring.joke.model.Joke;
import pl.akademiaspecjalistowit.jokeappspring.joke.repository.JokeJpaRepository;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.provider.exception.JokeDataProviderException;

@Service
public class JokeDataProvider implements JokeProvider {

    private final List<JokeJpaRepository> jokeRepositories;
    private final JokeMapper jokeMapper;
    private static long counter = 0;


    public JokeDataProvider(List<JokeJpaRepository> jokeRepositories,
                            JokeMapper jokeMapper) {
        this.jokeRepositories = jokeRepositories;
        this.jokeMapper = jokeMapper;
    }

    @Override
    public Joke getJoke() {
        Random rand = new Random();
        List<JokeEntity> anyJokes = getJokeRepository().findAll();
        return jokeMapper.fromEntity(anyJokes.get(rand.nextInt(anyJokes.size())));
    }

    @Override
    public Joke getJokeByCategory(String category) {
        Random rand = new Random();
        List<JokeEntity> jokesByCategory =
            getJokeRepository().findAllByCategory(category);
        if (jokesByCategory.isEmpty()) {
            throw new JokeDataProviderException("No joke for this category is available");
        }
        JokeEntity jokeEntity = jokesByCategory.get(rand.nextInt(jokesByCategory.size()));
        return jokeMapper.fromEntity(jokeEntity);
    }

    private JokeJpaRepository getJokeRepository() {
        return jokeRepositories.get((int) counter++ % jokeRepositories.size());
    }
}