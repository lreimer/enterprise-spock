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

    UserRepository repository

    String password

    def setup() {
        assert manager instanceof UserManager
        repository = Mock(UserRepository)
        manager.repository = repository;
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
