package java_jabki_15.x6_user.repositories;

import java_jabki_15.x6_user.repositories.mapper.UserMapper;
import java_jabki_15.x6_user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepository {
    private static final String INSERT = """
            INSERT INTO x6_user.user(name,email,birthday,info)
            VALUES (:name, :email, :birthday, :info)
            RETURNING *;
            """;
    private static final String UPDATE = """
            UPDATE x6_user.user
            SET name = :name, email = :email, birthday = :birthday, info = :info
            WHERE id = :id
            RETURNING *;
            """;
    private static final String DELETE = """
            DELETE x6_user.user
            WHERE id = :id
            """;
    private static final String GET_BY_ID = """
            SELECT *
            FROM x6_user.user
            WHERE id = :id
            """;

    private final UserMapper userMapp;
    private final NamedParameterJdbcTemplate jbcTemplate;

    public User insert(User user) {
        return jbcTemplate.queryForObject(INSERT, userParamForSql(user), userMapp);
    }

    public User update(User user) {
        return jbcTemplate.queryForObject(UPDATE, userParamForSql(user), userMapp);
    }

    public void delete(Long id) {
        jbcTemplate.update(DELETE, new MapSqlParameterSource("id", id));
    }

    public User getById(Long id) {
        return jbcTemplate.queryForObject(GET_BY_ID, new MapSqlParameterSource("id", id), userMapp);

    }

    public MapSqlParameterSource userParamForSql(User user) {
        final MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", user.getId());
        params.addValue("name", user.getName());
        params.addValue("email", user.getEmail());
        params.addValue("info", user.getInfo());
        params.addValue("birthday", user.getBirthday());

        return params;
    }

}

