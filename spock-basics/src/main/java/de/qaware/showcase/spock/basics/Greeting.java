package de.qaware.showcase.spock.basics;

/**
 * General interface to generate different greeting messages.
 */
public interface Greeting {
    /**
     * Produce message with given parameters.
     *
     * @param parameter optional message parameters
     * @return the message
     */
    String message(Object... parameter);
}
