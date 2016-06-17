package de.qaware.showcase.spock.basics

import spock.lang.Specification

import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.CoreMatchers.notNullValue
import static spock.util.matcher.HamcrestSupport.expect
import static spock.util.matcher.HamcrestSupport.that


/**
 * A Spock spec to show the usage of Hamcrest matchers.
 */
class HamcrestSpec extends Specification {

    def "Usage of Hamcrest matchers using expect"() {
        expect:
        that "A String", notNullValue()
    }

    def "Usage of Hamcrest matchers using given-then-when"() {
        given: 'a dummy string'
        def dummy = 'Dummy'

        when: 'we copy the string'
        def copy = new String(dummy)

        then:
        expect copy, equalTo(dummy)
    }
}