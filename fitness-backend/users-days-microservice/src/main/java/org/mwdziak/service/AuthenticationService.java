package org.mwdziak.service;


import lombok.RequiredArgsConstructor;
import org.mwdziak.domain.BlacklistedToken;
import org.mwdziak.domain.NutritionalGoals;
import org.mwdziak.domain.User;
import org.mwdziak.dto.AuthenticationResponse;
import org.mwdziak.dto.TokensDTO;
import org.mwdziak.repository.BlacklistedTokenRepository;
import org.mwdziak.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.mwdziak.dto.AuthenticationRequest;
import org.mwdziak.dto.RegistrationRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final BlacklistedTokenRepository blacklistedTokenRepository;
    public AuthenticationResponse register(RegistrationRequest request) {
        var user = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .password(encoder.encode(request.getPassword()))
                .nutritionalGoals(new NutritionalGoals())
            .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return AuthenticationResponse.builder()
            .token(jwtToken)
            .refreshToken(refreshToken)
            .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );

        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .refreshToken(refreshToken)
            .build();
    }

    public AuthenticationResponse refresh(TokensDTO request) {
        var email = jwtService.extractUsername(request.getRefreshToken());
        var isTokenBlacklisted = blacklistedTokenRepository.existsByToken(request.getRefreshToken());
        if (isTokenBlacklisted) {
            throw new RuntimeException("Token is blacklisted");
        }
        blacklistedTokenRepository.save(new BlacklistedToken(request.getToken()));
        blacklistedTokenRepository.save(new BlacklistedToken(request.getRefreshToken()));
        var user = repository.findByEmail(email).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .refreshToken(refreshToken)
            .build();
    }

    public void logout(TokensDTO request) {
        blacklistedTokenRepository.save(new BlacklistedToken(request.getToken()));
        blacklistedTokenRepository.save(new BlacklistedToken(request.getRefreshToken()));
    }

}
