package de.qaware.showcase.spock.wiremock;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * A simple JerseyClient implementation for an imaginary book service. The client
 * will then be tested using Spock and WireMock to simulate the book service.
 */
public class BookServiceClient {

    private final Client client;
    private final WebTarget books;

    /**
     * Default constructor.
     */
    public BookServiceClient() {
        this("http://localhost:18080");
    }

    /**
     * Initialize client with service URI.
     *
     * @param uri the service URI
     */
    public BookServiceClient(final String uri) {
        this.client = ClientBuilder.newClient().register(JacksonFeature.class);
        this.books = client.target(uri).path("book");
    }

    /**
     * Find a specific book by its ID.
     *
     * @param isbn the ISBN
     * @return a book
     */
    public Book findByISBN(String isbn) {
        return books.path("/{isbn}").resolveTemplate("isbn", isbn).request().get(Book.class);
    }

    /**
     * Find all books.
     *
     * @return a list of books
     */
    public List<Book> findAll() {
        GenericType<List<Book>> bookType = new GenericType<List<Book>>() {
        };
        return books.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(bookType);
    }

}
