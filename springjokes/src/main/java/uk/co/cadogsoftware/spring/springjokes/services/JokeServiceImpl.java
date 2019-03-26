package uk.co.cadogsoftware.spring.springjokes.services;

import org.springframework.stereotype.Service;
import uk.co.cadogsoftware.spring.springjokes.utilities.JokeGenerator;

@Service
public class JokeServiceImpl implements JokeService {

    private final JokeGenerator jokeGenerator;

    public JokeServiceImpl(JokeGenerator jokeGenerator) {
        this.jokeGenerator = jokeGenerator;
    }

    public String getJoke() {
        return jokeGenerator.generateJoke();
    }

}
