package pl.akademiaspecjalistowit.jokeappspring.joke.repository;

import pl.akademiaspecjalistowit.jokeappspring.joke.model.Joke;

public interface JokeSupplierRepository {

    void addJoke(Joke joke);
}
