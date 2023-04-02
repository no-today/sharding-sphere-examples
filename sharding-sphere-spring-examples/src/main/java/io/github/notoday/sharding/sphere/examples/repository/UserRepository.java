package io.github.notoday.sharding.sphere.examples.repository;

import io.github.notoday.sharding.sphere.examples.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author no-today
 * @date 2023/03/21 16:01
 */
@Repository
public interface UserRepository {

    void insert(User user);

    void update(User user);

    void delete(Long id);

    User findById(Long id);

    List<User> findAll();

    List<User> listByFirstEmail(String email);

    List<User> listByLikeEmail(String email);
}
