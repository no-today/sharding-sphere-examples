package io.github.notoday.sharding.sphere.examples.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author no-today
 * @date 2023/03/21 15:10
 */
@Data
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
