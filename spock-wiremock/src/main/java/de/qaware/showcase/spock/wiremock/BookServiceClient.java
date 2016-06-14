/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 QAware GmbH, Munich, Germany
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
