package ch.groovlet.model.representations;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class User {

    private final long id;

    @Length(min = 1, max = 100)
    private final String nickname;

    @Email
    @Length(min = 1, max = 100)
    private final String email;

    public User(final long id, final String nickname, final String email) {
        super();
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }
}
