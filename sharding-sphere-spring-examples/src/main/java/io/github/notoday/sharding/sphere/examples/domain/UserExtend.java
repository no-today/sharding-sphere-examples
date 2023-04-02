package io.github.notoday.sharding.sphere.examples.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author no-today
 * @date 2023/03/24 11:53
 */
@Data
public class UserExtend {

    private Long userId;
    private String extField1;
    private String extField2;
    private String extField3;
    private LocalDateTime lastModifiedDate;
}
