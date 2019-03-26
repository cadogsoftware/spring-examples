package uk.co.cadogsoftware.spring.springjokes.utilities;

import org.springframework.stereotype.Service;

@Service
public class JokeGeneratorImpl implements JokeGenerator {

    @Override
    public String generateJoke() {
        return "some generated joke";
    }
}
