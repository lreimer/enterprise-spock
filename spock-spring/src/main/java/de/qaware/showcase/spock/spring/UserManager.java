package de.qaware.showcase.spock.spring;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;

/**
 * A simple user manager component to handler users.
 */
@Component
public class UserManager {

    private UserRepository repository;

    /**
     * Reset the password for the user identified by the given username.
     *
     * @param username the username
     * @return the new password after reset
     */
    public String resetPassword(String username) {
        User user = repository.findByUsername(username);

        String password = UUID.randomUUID().toString();
        repository.store(new User(user.getUsername(), password, user.isActive()));

        return password;
    }

    /**
     * Deactivate alle users in our repository.
     */
    public void deactiveAll() {
        Collection<User> all = repository.all();
        all.stream().map(User::deactivate).forEach(repository::store);
    }

    public UserRepository getRepository() {
        return repository;
    }

    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

}
