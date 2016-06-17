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

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Timeout

import java.util.concurrent.TimeUnit


/**
 * A Spock spec to test the @Timed behaviour. Careful with the value,
 * if you make it too small the test will still fail.
 */
class TimedSpec extends Specification {

    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    def "This test should not run longer than 500ms"() {
        expect: 'no timeout if we sleep 450ms'
        TimeUnit.MILLISECONDS.sleep(450)
    }

    @Ignore("The timeout values seem to be too close")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    def "This test should not fail but still does"() {
        expect: 'no timeout if we sleep 499ms'
        TimeUnit.MILLISECONDS.sleep(499)
    }
}