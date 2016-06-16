package de.qaware.showcase.spock.spring;

import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * A simple User POJO for the Spok spec.
 */
public class User {
    private final String username;
    private final String password;
    private final boolean active;

    public User(String username, String password, boolean active) {
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public User deactivate() {
        return new User(username, password, false);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("username", username)
                .append("password", password)
                .append("active", active)
                .toString();
    }
}
