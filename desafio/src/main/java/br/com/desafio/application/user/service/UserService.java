package br.com.desafio.application.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import br.com.desafio.application.config.BearerTokenProvider;
import br.com.desafio.application.user.controller.LoginRequest;
import br.com.desafio.application.user.controller.SignUpRequest;
import br.com.desafio.application.user.controller.UpdateUserRequest;
import br.com.desafio.domain.correntista.Correntista;
import br.com.desafio.domain.correntista.CorrentistaRepository;
import br.com.desafio.domain.user.User;
import br.com.desafio.domain.user.UserRepository;
import br.com.desafio.domain.user.UserVO;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BearerTokenProvider bearerTokenProvider;
    private final CorrentistaRepository correntistaRepository;

    @Transactional
    public User signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email `%s` is already exists.".formatted(request.email()));
        }

        User userSaved = userRepository.save(this.createNewUser(request));
        Correntista teste = createCorrentista(request, userSaved);
        correntistaRepository.save(teste);
        return userSaved;
    }

    @Transactional(readOnly = true)
    public UserVO login(LoginRequest request) {
        return userRepository
                .findByEmail(request.email())
                .filter(user -> passwordEncoder.matches(request.password(), user.getPassword()))
                .map(user -> {
                    String token = bearerTokenProvider.createBearerToken(user);
                    return new UserVO(user.setToken(token));
                })
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
    }

    @Transactional
    public UserVO update(User user, UpdateUserRequest request) {
        String email = request.email();
        if (!user.getEmail().equals(email) && userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email `%s` is already exists.".formatted(email));
        }

        String username = request.username();
        if (!user.getUsername().equals(username) && userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username `%s` is already exists.".formatted(request.username()));
        }

        user.updateEmail(email);
        user.updateUsername(username);
        user.updatePassword(passwordEncoder, request.password());
        user.updateBio(request.bio());
        user.updateImage(request.image());

        return new UserVO(user);
    }

    private User createNewUser(SignUpRequest request) {
        return User.builder()
                .email(request.email())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                // .correntista(new Correntista(request.cpf()))
                .build();
    }

    private Correntista createCorrentista(SignUpRequest request, User user) {
        return Correntista.builder()
                // .id(user.getId())
                .cpf(request.cpf())
                .user(user)
                .build();
    }
}
