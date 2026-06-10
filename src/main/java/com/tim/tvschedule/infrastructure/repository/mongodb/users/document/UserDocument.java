package com.tim.tvschedule.infrastructure.repository.mongodb.users.document;

import com.tim.tvschedule.domain.model.AuthProvider;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "users")
public class UserDocument {

    @Id
    private String id;

    private AuthProvider provider;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String username;

    private String displayName;

    private String pictureUrl;

    private Instant createdAt;

    public UserDocument() {

    }

    public UserDocument(String id, AuthProvider provider, String email, String username, String displayName, String pictureUrl, Instant createdAt) {
        this.id = id;
        this.provider = provider;
        this.email = email;
        this.username = username;
        this.displayName = displayName;
        this.pictureUrl = pictureUrl;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
