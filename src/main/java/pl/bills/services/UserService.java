package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.bills.entities.UserEntity;
import pl.bills.forms.UserCreateForm;
import pl.bills.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> getUserById(Integer id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    public Collection<UserEntity> getAllUsers() {
        return userRepository.findAll(new Sort("email"));
    }

    public UserEntity create(UserCreateForm form) {
        UserEntity user = new UserEntity();
        user.setEmail(form.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        return userRepository.save(user);
    }
}
