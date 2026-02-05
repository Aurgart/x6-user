package java_jabki_15.x6_user;

import java_jabki_15.x6_user.model.User;
import java_jabki_15.x6_user.repositories.UserRepository;
import java_jabki_15.x6_user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class x6UserTests {

    @Mock
    private UserRepository testLogic;

    @InjectMocks
    private UserService testService;

    @Test
    void createUserTest(){
        final User user = testUser();
        Assertions.assertDoesNotThrow(() -> {
            testService.addUser(user);
        });
        RuntimeException excp = assertThrows(RuntimeException.class, () -> testService.addUser(
                User.builder()
                        .id(1)
                        .name("Testyk")
                        .email("test@mk.ru")
                        .info("Старый пердун")
                        .birthday(LocalDate.of(2027, Month.JANUARY, 15))
                        .build()));
        Assertions.assertNotNull(excp.getMessage());
    }


    private User testUser() {
        return User.builder()
                .id(1)
                .name("Testyk")
                .email("test@mk.ru")
                .info("Старый пердун")
                .birthday(LocalDate.of(1985, Month.JANUARY, 15))
                .build();
    }
}
