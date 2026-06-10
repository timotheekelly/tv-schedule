package com.tim.tvschedule.application.auth.service;

import com.tim.tvschedule.domain.model.AuthProvider;
import com.tim.tvschedule.infrastructure.repository.mongodb.users.UserMongoRepository;
import com.tim.tvschedule.infrastructure.repository.mongodb.users.document.UserDocument;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

    private static final AuthProvider PROVIDER = AuthProvider.GOOGLE;

    private final UserMongoRepository userRepository;
    private final OidcUserService delegate = new OidcUserService();

    public CustomOAuth2UserService(UserMongoRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = delegate.loadUser(userRequest);

        String userId = buildUserId(PROVIDER, oidcUser.getSubject());

        userRepository.findById(userId)
                .map(existing -> {
                    existing.setEmail(oidcUser.getEmail());
                    existing.setDisplayName(oidcUser.getFullName());
                    existing.setPictureUrl(oidcUser.getPicture());
                    return userRepository.save(existing);
                })
                .orElseGet(() -> userRepository.save(new UserDocument(
                        userId,
                        PROVIDER,
                        oidcUser.getEmail(),
                        generateUsername(oidcUser),
                        oidcUser.getFullName(),
                        oidcUser.getPicture(),
                        Instant.now()
                )));

        return oidcUser;
    }

    private String buildUserId(AuthProvider provider, String providerUserId) {
        return provider.name().toLowerCase() + ":" + providerUserId;
    }

    private String generateUsername(OidcUser oidcUser) {
        String email = oidcUser.getEmail();

        if (email != null && email.contains("@")) {
            return email.substring(0, email.indexOf("@"));
        }

        return "user-" + oidcUser.getSubject();
    }
}