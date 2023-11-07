package br.com.desafio.application.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import br.com.desafio.application.user.service.ProfileService;
import br.com.desafio.domain.user.ProfileVO;
import br.com.desafio.domain.user.User;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/api/profiles/{username}")
    public ProfileRecord getProfile(User me, @PathVariable("username") String target) {
        ProfileVO profile = profileService.getProfile(me, target);
        return new ProfileRecord(profile);
    }
}
