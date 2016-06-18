package de.qaware.showcase.spock.basics;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A Greeter implementation that uses a Greeting implementation for the actual reply.
 */
public class Greeter {

    private final Greeting greeting;

    public Greeter(Greeting greeting) {
        this.greeting = greeting;
    }

    public String sayHello(String name) {
        return greeting.message(name);
    }

    public String sayHello(String name, int times) {
        return IntStream.range(0, times).mapToObj((int i) -> greeting.message(name))
                .collect(Collectors.joining(" "));
    }
}
