package org.mwdziak.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.dto.AuthenticationRequest;
import org.mwdziak.dto.AuthenticationResponse;
import org.mwdziak.dto.TokensDTO;
import org.mwdziak.dto.RegistrationRequest;
import org.mwdziak.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/auth/register")
    public AuthenticationResponse registerUser(@RequestBody RegistrationRequest user) {
        return authenticationService.register(user);
    }

    @PostMapping("/auth/login")
    public AuthenticationResponse loginUser(@RequestBody AuthenticationRequest user) {
        return authenticationService.authenticate(user);
    }
    @PostMapping("/auth/refresh")
    public AuthenticationResponse refreshToken(@RequestBody TokensDTO request) {
        return authenticationService.refresh(request);
    }

    @PostMapping("/auth/logout")
    public void logout(@RequestBody TokensDTO request) {
        authenticationService.logout(request);
    }

}
