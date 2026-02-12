package java_jabki_15.x6_user.service;

import java_jabki_15.x6_user.exception.UserException;
import java_jabki_15.x6_user.model.User;
import java_jabki_15.x6_user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository users;

    @Transactional
    public User addUser(User user) {
        validateUser(user);
        users.insert(user);
        return user;
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new UserException("User is null");
        }
        validateUserData(user);
    }

    private void validateUserData(User user) {
        if (!StringUtils.hasText(user.getEmail()) || !StringUtils.hasText(user.getName())) {
            throw new UserException("One of the parameters is empty: name - " + user.getName() + " email - " + user.getEmail());
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new UserException("Birthday hasn't happend yet - " + user.getBirthday());
        }
    }

    public User getbyId(final Long id) {
        return users.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(final Long id) {
        users.delete(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public User updateUser(User user) {
        return users.update(user);
    }

}
