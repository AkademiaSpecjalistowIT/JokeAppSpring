package pl.akademiaspecjalistowit.jokeappspring.joke;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiaspecjalistowit.jokeappspring.joke.entity.JokeEntity;
import pl.akademiaspecjalistowit.jokeappspring.joke.model.Joke;
import pl.akademiaspecjalistowit.jokeappspring.joke.repository.JokeJpaRepository;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.JokeService;

@RestController
@AllArgsConstructor
@RequestMapping("/jokes")
public class JokeController {

    private final JokeService jokeService;

    private final JokeJpaRepository jokeJpaRepository;

    @GetMapping
    public Joke getRandomJoke(@RequestParam(value = "category", required = false)
                                  Optional<String> category) {
        return category.map(jokeService::getJoke)
            .orElse(jokeService.getJoke());
    }

    @PostMapping
    public void addJoke(@RequestBody Joke joke){
        JokeEntity jokeEntity = new JokeEntity(joke.getId(), joke.getContent(), joke.getCategory());
        jokeJpaRepository.save(jokeEntity);
    }

    @GetMapping("/{id}")
    public Joke getJokeById(@PathVariable Long id){
        return jokeJpaRepository.findById(id)
            .map(e->new Joke(e.getTechnicalId(),e.getContent(),e.getCategory()))
            .orElseThrow(() -> new RuntimeException("Joke not found"));
    }

    @GetMapping("/category/{category}")
    public List<Joke> getJokeByCategory(@PathVariable String category){
        return jokeJpaRepository.getJokeEntitiesByCategory(category).stream()
            .map(e->new Joke(e.getTechnicalId(),e.getContent(),e.getCategory()))
            .toList();
    }

}
