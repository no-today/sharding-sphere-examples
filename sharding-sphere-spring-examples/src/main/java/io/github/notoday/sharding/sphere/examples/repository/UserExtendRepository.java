package io.github.notoday.sharding.sphere.examples.repository;

import io.github.notoday.sharding.sphere.examples.domain.UserExtend;
import io.github.notoday.sharding.sphere.examples.domain.UserExtendWrap;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author no-today
 * @date 2023/03/24 11:55
 */
@Repository
public interface UserExtendRepository {

    void insert(UserExtend userExtend);

    void update(UserExtend userExtend);

    void delete(Long userId);

    UserExtend findById(Long userId);

    List<UserExtend> findAll();

    UserExtendWrap findWrapById(Long userId);
}
