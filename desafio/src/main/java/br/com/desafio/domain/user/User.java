package br.com.desafio.domain.user;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import br.com.desafio.domain.correntista.Correntista;

@Entity
@Getter
@Builder
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(length = 200, nullable = false)
    private String password;

    @Column(length = 30, nullable = false, unique = false)
    private String username;

    // @JoinColumn(name = "correntista_id")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Correntista correntista;

    @Builder.Default
    @Column(length = 500, nullable = false)
    private String bio = "";

    private String image;

    @CreatedDate
    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Transient
    private String token;

    @Transient
    @Builder.Default
    private boolean anonymous = false;

    public static User anonymous() {
        return User.builder().id(null).anonymous(true).build();
    }

    public boolean isAnonymous() {
        return this.id == null && this.anonymous;
    }

    public User setToken(String token) {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("token must not be null or blank");
        }

        this.token = token;
        return this;
    }

    public void updateEmail(String email) {
        if (email == null || email.isBlank() || this.email.equals(email)) {
            return;
        }

        // Note: You can add some more validations here if you want. (ex. regex)
        this.email = email;
    }

    public void updateUsername(String username) {
        if (username == null || username.isBlank() || this.username.equals(username)) {
            return;
        }

        // Note: You can add some more validations here if you want. (ex. regex)
        this.username = username;
    }

    public void updatePassword(PasswordEncoder passwordEncoder, String plaintext) {
        if (passwordEncoder == null) {
            return;
        }

        if (plaintext == null || plaintext.isBlank()) {
            return;
        }

        // Note: You can add some more validations here if you want. (ex. regex)
        this.password = passwordEncoder.encode(plaintext);
    }

    public void updateBio(String bio) {
        if (bio == null || bio.isBlank()) {
            return;
        }

        this.bio = bio;
    }

    public void updateImage(String imageUrl) {
        if (imageUrl != null && imageUrl.isBlank()) {
            return;
        }

        this.image = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof User other && Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
