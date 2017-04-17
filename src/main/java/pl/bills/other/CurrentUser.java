package pl.bills.other;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import pl.bills.entities.UserEntity;
import pl.bills.enums.Role;

/**
 * Created by trot on 13.04.17.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private UserEntity user;

    public CurrentUser(UserEntity user) {
        super(user.getEmail(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public Role getRole() {
        return user.getRole();
    }

}
