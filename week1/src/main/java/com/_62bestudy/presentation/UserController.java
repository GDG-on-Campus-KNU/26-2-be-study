package com._62bestudy.presentation;

import com._62bestudy.application.PostService;
import com._62bestudy.application.SessionService;
import com._62bestudy.application.UserService;
import com._62bestudy.presentation.dto.PostResponse;
import com._62bestudy.presentation.dto.UserCreateRequest;
import com._62bestudy.presentation.dto.UserLoginRequest;
import com._62bestudy.presentation.dto.UserResponse;
import com._62bestudy.presentation.dto.UserUpdateRequest;
import com._62bestudy.presentation.resolver.CurrentUser;
import com._62bestudy.presentation.resolver.LoginUser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;
    private final PostService postService;

    @PostMapping
    public ResponseEntity<UserResponse> create(
            @Valid @RequestBody UserCreateRequest request
    ) {
        var user = userService.create(
                request.name(),
                request.password()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserResponse.from(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody UserLoginRequest request,
            HttpServletResponse response
    ) {
        var sessionId = sessionService.login(
                request.name(),
                request.password()
        );
        var cookie = new Cookie(SessionAttributes.SESSION_COOKIE_NAME, sessionId);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);
        response.setHeader(SessionAttributes.SESSION_HEADER_NAME, sessionId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> findMe(
            @LoginUser CurrentUser currentUser
    ) {
        var user = userService.findById(currentUser.id());

        return ResponseEntity.ok(
                UserResponse.from(user)
        );
    }

    @GetMapping("/me/posts")
    public ResponseEntity<List<PostResponse>> findMyPosts(
            @LoginUser CurrentUser currentUser
    ) {
        var posts = postService.findAllByAuthorId(currentUser.id())
                .stream()
                .map(PostResponse::from)
                .toList();

        return ResponseEntity.ok(posts);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        var users = userService.findAll()
                .stream()
                .map(UserResponse::from)
                .toList();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(
            @PathVariable String id
    ) {
        var user = userService.findById(id);

        return ResponseEntity.ok(
                UserResponse.from(user)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable String id,
            @Valid @RequestBody UserUpdateRequest request
    ) {
        var user = userService.update(
                id,
                request.name(),
                request.password()
        );

        return ResponseEntity.ok(
                UserResponse.from(user)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id
    ) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
