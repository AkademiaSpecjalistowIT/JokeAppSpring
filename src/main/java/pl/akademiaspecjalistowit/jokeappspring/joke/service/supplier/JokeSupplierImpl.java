package pl.akademiaspecjalistowit.jokeappspring.joke.service.supplier;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.jokeappspring.joke.model.Joke;
import pl.akademiaspecjalistowit.jokeappspring.joke.repository.JokeSupplierRepository;
import pl.akademiaspecjalistowit.jokeappspring.joke.repository.JpaJokeRepositoryAdapter;

@Service
@AllArgsConstructor
public class JokeSupplierImpl implements JokeSupplier {

    private final JokeSupplierRepository jokeSupplierRepository;

    @Override
    public void addJoke(Joke joke) {
        jokeSupplierRepository.addJoke(joke);
    }
}
