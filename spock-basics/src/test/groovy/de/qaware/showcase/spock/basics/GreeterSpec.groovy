package de.qaware.showcase.spock.basics

import spock.lang.Specification

/**
 * A Spock spec using mocks and stubs to test the Greeter.
 */
class GreeterSpec extends Specification {

    def "Call Greeter using a stub and sayHello"() {
        given: 'a stubbed greeting and greeter'
        def greeting = Stub(Greeting)
        greeting.message(_) >>> ['Hello Spock.', 'Hello Kirk.']
        def greeter = new Greeter(greeting)

        expect: 'the correct responses'
        greeter.sayHello('Spock') == 'Hello Spock.'
        greeter.sayHello('Kirk') == 'Hello Kirk.'
    }

    def "Call Greeter using a Mock and check interactions"() {
        given:
        def greeting = Mock(Greeting)
        def greeter = new Greeter(greeting)

        when:
        def reply = greeter.sayHello('test', 3)

        then:
        3 * greeting.message(_ as String) >>> ['test1', 'test2', 'test3']

        and:
        reply == 'test1 test2 test3'
    }
}
