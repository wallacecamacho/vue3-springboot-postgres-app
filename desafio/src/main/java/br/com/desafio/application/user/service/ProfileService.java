package br.com.desafio.application.user.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import br.com.desafio.domain.user.ProfileVO;
import br.com.desafio.domain.user.User;
import br.com.desafio.domain.user.UserRepository;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ProfileVO getProfile(User me, String targetUsername) {
        User targetUser = findUserByUsername(targetUsername);
        return new ProfileVO(me, targetUser);
    }

    private User findUserByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User(`%s`) not found".formatted(username)));
    }
}
