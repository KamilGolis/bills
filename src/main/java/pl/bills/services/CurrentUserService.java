package pl.bills.services;

import org.springframework.stereotype.Service;
import pl.bills.enums.Role;
import pl.bills.other.CurrentUser;

@Service
public class CurrentUserService {

    public boolean canAccessUser(CurrentUser currentUser, Integer userId) {
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
    }
}
