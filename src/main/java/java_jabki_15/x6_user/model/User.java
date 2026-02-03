package java_jabki_15.x6_user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class User {
    private int id;
    private String name;
    private String email;
    private LocalDate birthday;
    private String info;
}
