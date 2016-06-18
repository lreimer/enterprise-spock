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
