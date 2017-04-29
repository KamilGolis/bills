package pl.bills.other;

import org.springframework.security.core.authority.AuthorityUtils;
import pl.bills.entities.UserEntity;
import pl.bills.enums.Role;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private final UserEntity user;

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
