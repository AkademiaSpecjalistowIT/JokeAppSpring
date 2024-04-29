package pl.akademiaspecjalistowit.jokeappspring.joke.service;

import java.util.List;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.jokeappspring.joke.model.Joke;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.provider.JokeProvider;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.supplier.JokeSupplier;

@Service
public class JokeServiceImpl implements JokeService {

    private final List<JokeProvider> jokeProviders;
    private final JokeSupplier jokeSupplier;
    private static long counter = 0;

    public JokeServiceImpl(List<JokeProvider> jokeProviders,
                           JokeSupplier jokeSupplier) {
        this.jokeSupplier = jokeSupplier;
        if (jokeProviders == null || jokeProviders.isEmpty()) {
            throw new RuntimeException("Required at least one JokeProvider for the application to run");
        }
        this.jokeProviders = jokeProviders;
    }

    @Override
    public Joke getJoke() {
        return getJokeProvider().getJoke();
    }

    @Override
    public Joke getJoke(String category) {
        return getJokeProvider().getJokeByCategory(category);
    }

    @Override
    public void addJoke(Joke joke) {
        jokeSupplier.addJoke(joke);
    }

    private JokeProvider getJokeProvider() {
        return jokeProviders.get((int) counter++ % jokeProviders.size());
    }
}
