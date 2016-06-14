package de.qaware.showcase.spock.wiremock

import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.github.tomjankes.wiremock.WireMockGroovy
import org.junit.Rule
import spock.lang.Shared
import spock.lang.Specification

/**
 * The Spock specification for our BookServiceClient. It uses WireMock and the WireMock Groovy
 * bindings to stub the required REST service endpoint for our client class.
 */
class BookServiceClientSpec extends Specification {

    @Rule
    WireMockRule wireMockRule = new WireMockRule(18080)

    @Shared
    def client = new BookServiceClient("http://localhost:18080")
    def wireMock = new WireMockGroovy(18080)

    def "Find a book by ISBN from a WireMock stub server using object JSON body"() {
        given: "a stubbed GET request for a single book"
        wireMock.stub {
            request {
                method "GET"
                url "/book/9781617292538"
            }
            response {
                status 200
                jsonBody new Book(title: "Java Testing with Spock", isbn: "9781617292538")
                headers { "Content-Type" "application/json" }
            }
        }

        when: "we invoke the REST client to find the book by its ISBN"
        def book = client.findByISBN("9781617292538")

        then: "we expect the correct book"
        book
        book.title == "Java Testing with Spock"
        book.isbn == "9781617292538"

        and: "the mock to be invoked exactly once"
        1 == wireMock.count {
            method "GET"
            url "/book/9781617292538"
        }
    }

    def "Find all books from a WireMock stub server using inline JSON body"() {
        given: "a stubbed GET request for all books"
        wireMock.stub {
            request {
                method "GET"
                url "/book"
            }
            response {
                status 200
                body """[
                            {"title": "Java Testing with Spock", "isbn": "9781617292538"},
                            {"title": "The Hitchhiker's Guide to the Galaxy", "isbn": "0345391802"}
                        ]
                     """
                headers { "Content-Type" "application/json" }
            }
        }

        when: "we invoke the REST client to find all books"
        def books = client.findAll()

        then: "we expect to books and the mock to be invoked once"
        books.size() == 2

        and: "the mock to be invoked exactly once"
        1 == wireMock.count {
            method "GET"
            url "/book"
        }
    }

    def "Find all books from a WireMock stub server using a JSON body file"() {
        given: "a stubbed GET request for all books"
        wireMock.stub {
            request {
                method "GET"
                url "/book"
            }
            response {
                status 200
                bodyFileName "books.json"
                headers { "Content-Type" "application/json" }
            }
        }

        when: "we invoke the REST client to find all books"
        def books = client.findAll()

        then: "we expect to books and the mock to be invoked once"
        books.size() == 2

        and: "the mock to be invoked exactly once"
        1 == wireMock.count {
            method "GET"
            url "/book"
        }
    }
}
