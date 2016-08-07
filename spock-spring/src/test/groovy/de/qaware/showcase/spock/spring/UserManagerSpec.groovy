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
package de.qaware.showcase.spock.spring

import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.inject.Inject

import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.CoreMatchers.not
import static spock.util.matcher.HamcrestSupport.expect

/**
 * The Spock testcase to showcase injection and mocking using Spring managed beans.
 */
@ContextConfiguration(locations = "classpath:/UserManagerSpec-context.xml")
class UserManagerSpec extends Specification {

    @Inject
    UserManager manager

    @Inject
    UserRepository repository

    String password

    def setup() {
        assert manager instanceof UserManager
    }

    def "Check proper interactions with repository for resetPassword"() {
        setup: 'a user'
        def aUser = new User('lreimer', 'secret', true)

        when: 'we reset the password for the user'
        password = manager.resetPassword('lreimer')

        then: 'we have correct interactions and a changed password'
        1 * repository.findByUsername('lreimer') >> aUser
        1 * repository.store(_ as User)
        expect password, not(equalTo('secret'))
    }

    def "Check proper interactions with repository for deactiveAll"() {
        setup: 'a list of users to deactivate'
        def users = [new User('lreimer', 'secret', true), new User('test', '1234', true)].asList()
        repository.all() >> users

        when: 'we deactivate all user accouts'
        manager.deactiveAll()

        then: 'each user is stored as not active'
        2 * repository.store({it.active == false})
    }
}
