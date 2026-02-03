package java_jabki_15.x6_user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java_jabki_15.x6_user.model.ApiStatus;
import java_jabki_15.x6_user.model.User;
import java_jabki_15.x6_user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "Пользователи")
public class UserController {
    private final UserService userLogic;

    @PostMapping
    @Operation(summary = "Создать пользюка")
    public User create(@RequestBody User user) {
        return userLogic.addUser(user);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Иди сюда")
    public User getById(@PathVariable("id") String id) {
        return userLogic.getbyId(Long.parseLong(id));
    }

    @PatchMapping
    @Operation(summary = "Обновление пользователей.")
    public User update(@RequestBody User user) {
        return userLogic.updateUser(user);
    }

    @GetMapping("/check/{id}")
    @Operation(summary = "Проверить пользователя")
    public ResponseEntity<ApiStatus> checkById(@PathVariable("id") String id) {
        User usr = userLogic.getbyId(Long.parseLong(id));
        if (usr != null) {
            return ResponseEntity.ok().body(new ApiStatus(true, "User " + usr.getName() + " exists!"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
