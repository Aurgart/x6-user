package java_jabki_15.x6_user.service;

import java_jabki_15.x6_user.exception.UserException;
import java_jabki_15.x6_user.model.User;
import java_jabki_15.x6_user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository users;

    public User addUser(User user) {
        validateUser(user);
        users.insert(user);
        return user;
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new UserException("User is null");
        }
        validateUserData(user.getName(), user.getEmail(), user.getBirthday());
    }

    private void validateUserData(String name, String email, LocalDate birthday) {

        if (!StringUtils.hasText(email) || !StringUtils.hasText(name)) {
            throw new UserException("One of the parameters is empty: name - " + name + " email - " + email);
        }
        if (birthday.isAfter(LocalDate.now())) {
            throw new UserException("Birthday hasn't happend yet - " + birthday);
        }
    }

    public User getbyId(final Long id) {
        return users.getById(id);
    }

    public void deleteUser(final Long id) {
        users.delete(id);
    }

    public void updateUser(User user) {
        users.update(user);
    }

}
