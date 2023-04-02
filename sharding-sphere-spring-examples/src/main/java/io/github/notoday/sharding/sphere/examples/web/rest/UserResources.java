package io.github.notoday.sharding.sphere.examples.web.rest;

import com.github.pagehelper.PageHelper;
import io.github.notoday.sharding.sphere.examples.domain.User;
import io.github.notoday.sharding.sphere.examples.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author no-today
 * @date 2023/03/21 16:11
 */
@RestController
@RequestMapping("/api")
public class UserResources {

    private final UserRepository userRepository;

    public UserResources(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody User user) {
        userRepository.insert(user);
        return ResponseEntity.ok(userRepository.findById(user.getId()));
    }

    @PutMapping("/users")
    public ResponseEntity<User> update(@RequestBody User user) {
        userRepository.update(user);
        return ResponseEntity.ok(userRepository.findById(user.getId()));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userRepository.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll(@RequestParam(defaultValue = "1") Integer pageNum,
                                              @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/users/listByEmail/{email}")
    public ResponseEntity<List<User>> listByFirstName(@PathVariable("email") String email) {
        return ResponseEntity.ok(userRepository.listByFirstEmail(email));
    }

    @GetMapping("/users/listByLikeEmail/{email}")
    public ResponseEntity<List<User>> listByLikeFirstName(@PathVariable("email") String email) {
        return ResponseEntity.ok(userRepository.listByLikeEmail(email));
    }
}
