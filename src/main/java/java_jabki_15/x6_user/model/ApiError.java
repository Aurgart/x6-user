package java_jabki_15.x6_user.model;

import lombok.Data;

@Data
public class ApiError {
    final boolean result;
    final String description;
}
