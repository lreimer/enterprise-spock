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
package de.qaware.showcase.spock.geb

import geb.spock.GebReportingSpec
import spock.lang.Narrative
import spock.lang.Stepwise
import spock.lang.Title

import static org.hamcrest.CoreMatchers.containsString
import static org.junit.Assert.assertThat

/**
 * A simple Spock specification to test the QAware homepage.
 */
@Title("Basic navigation features for QAware homepage.")
@Narrative('''We need to make sure the navigation to the career and community pages is working correctly.''')
@Stepwise
class HomePageSpec extends GebReportingSpec {

    def "Launch browser and navigate to index page"() {
        when: 'we navigate to the QAware homepage'
        go("http://www.qaware.de")

        then: 'the index page is displayed'
        waitFor { at IndexPage }

        and: 'the headline is correct'
        assertThat headline.text(), containsString("Qualität und Agilität")
    }

    def "Navigate to the career page"() {
        when: 'we navigate to the career page'
        to CareerPage

        then: 'the career page is displayed correctly'
        waitFor { at CareerPage }
        assertThat headline.text(), containsString("Erfindergeist und Handwerksstolz.")
    }

    def "Navigate to the community page"() {
        when: 'we navigate to the community page'
        to CommunityPage

        then: 'the community page is displayed correctly'
        waitFor { at CommunityPage }
        assertThat headline.text(), containsString("IT-Community in der Welt")
    }
}