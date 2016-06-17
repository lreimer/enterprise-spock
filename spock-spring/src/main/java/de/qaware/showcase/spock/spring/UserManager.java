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
