package io.github.notoday.sharding.sphere.examples.web.rest;

import io.github.notoday.sharding.sphere.examples.domain.UserExtend;
import io.github.notoday.sharding.sphere.examples.domain.UserExtendWrap;
import io.github.notoday.sharding.sphere.examples.repository.UserExtendRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author no-today
 * @date 2023/03/21 16:11
 */
@RestController
@RequestMapping("/api")
public class UserExtendResources {

    private final UserExtendRepository userExtendRepository;

    public UserExtendResources(UserExtendRepository userRepository) {
        this.userExtendRepository = userRepository;
    }

    @PostMapping("/user-extends")
    public ResponseEntity<UserExtend> create(@RequestBody UserExtend user) {
        userExtendRepository.insert(user);
        return ResponseEntity.ok(userExtendRepository.findById(user.getUserId()));
    }

    @PutMapping("/user-extends")
    public ResponseEntity<UserExtend> update(@RequestBody UserExtend user) {
        userExtendRepository.update(user);
        return ResponseEntity.ok(userExtendRepository.findById(user.getUserId()));
    }

    @DeleteMapping("/user-extends/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userExtendRepository.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user-extends/{id}")
    public ResponseEntity<UserExtend> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userExtendRepository.findById(id));
    }

    @GetMapping("/user-extends")
    public ResponseEntity<List<UserExtend>> findAll() {
        return ResponseEntity.ok(userExtendRepository.findAll());
    }

    @GetMapping("/user-extends/wrap/{id}")
    public ResponseEntity<UserExtendWrap> findWrapById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userExtendRepository.findWrapById(id));
    }
}
