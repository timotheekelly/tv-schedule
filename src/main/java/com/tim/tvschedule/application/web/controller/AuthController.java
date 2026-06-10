package com.tim.tvschedule.application.web.controller;

import com.tim.tvschedule.application.web.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/user")
    public ResponseEntity<UserResponse> getCurrentUser(
            @AuthenticationPrincipal OidcUser oidcUser
    ) {

        if (oidcUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(
                new UserResponse(
                        oidcUser.getEmail(),
                        oidcUser.getFullName(),
                        oidcUser.getPicture()
                )
        );
    }
}
