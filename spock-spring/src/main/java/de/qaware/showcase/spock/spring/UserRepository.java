package de.qaware.showcase.spock.spring;

import java.util.Collection;

/**
 * The user repository. We will mock this interface later.
 */
public interface UserRepository {
    /**
     * Find a user by its username.
     *
     * @param username the username
     * @return the User or NULL
     */
    User findByUsername(String username);

    /**
     * FInd and return all users.
     *
     * @return a lis of users
     */
    Collection<User> all();

    /**
     * Store the given user.
     *
     * @param user the user
     */
    void store(User user);
}
