package io.github.notoday.sharding.sphere.examples.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author no-today
 * @date 2023/03/24 16:05
 */
@Data
public class UserExtendWrap {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Date createdDate;
    private Date lastModifiedDate;

    private String extField1;
    private String extField2;
    private String extField3;
}
