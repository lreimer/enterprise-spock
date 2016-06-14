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
import spock.lang.Subject
import spock.lang.Title

/**
 * A showcase Spock specification to test the Calculator implementation. The testling is
 * implemented in Java whereas the test is written in Groovy.
 */
@Title("Calculator Showcase Specification")
class CalculatorSpec extends Specification {

    @Subject
    def calculator = new Calculator()

    def "1 plus 1 is 2"() {
        given: "we initialize x=1 and y=1"
        calculator.x = 1
        calculator.y = 1

        when: "we add x and y"
        def result = calculator.add()

        then: "we expect the result to be 2"
        result == new BigDecimal(2)
    }

    def "4 minus 2 is 2"() {
        given: "we initialize x=3 and y=2"
        calculator.x = 4
        calculator.y = 2

        when: "we subtract y from x"
        def result = calculator.subtract()

        then: "we expect the result to be 2"
        result == new BigDecimal(2)
    }

    def "0 plus 0 is 0"() {
        expect: "correct result from default instance"
        calculator.add() == BigDecimal.ZERO
    }

    def "0 minus 0 is 0"() {
        expect: "correct result from default instance"
        calculator.subtract() == BigDecimal.ZERO
    }
}
