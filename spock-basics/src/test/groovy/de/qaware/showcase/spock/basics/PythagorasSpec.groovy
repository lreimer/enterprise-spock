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
import spock.lang.Unroll


/**
 * Some example specs for Pythagoras.
 */
class PythagorasSpec extends Specification {

    @Unroll
    def "#a Quadrat + #b Quadrat = #c Quadrat"() {
        expect:
        Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2).round(0)

        where:
        a << [1, 2, 3, 4, 5]
        b << [5, 6, 7, 8, 9]
        c = Math.sqrt(a * a + b * b)
    }


}